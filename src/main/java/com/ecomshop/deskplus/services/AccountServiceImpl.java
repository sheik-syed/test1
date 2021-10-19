package com.ecomshop.deskplus.services;

import com.ecomshop.deskplus.models.*;
import com.ecomshop.deskplus.repositories.*;
import com.ecomshop.deskplus.services.utils.RandomUtil;
import com.ecomshop.deskplus.web.rest.*;
import com.stripe.model.Customer;
import com.stripe.model.Plan;
import com.stripe.model.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

/**
 * Author: Sheik Syed Ali
 * Date: 10 Oct 2021
 */

@Service
public class AccountServiceImpl implements AccountService {

    private final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private StripeService stripeService;

    @Autowired
    private PlansRepository plansRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RegistrationsRepository registrationsRepository;

    @Autowired
    private SubscriptionsRepository subscriptionsRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private UserServices userServices;

    @Autowired
    private MailService mailService;

    @Value("${stripe.deskplus.default-plan}")
    private String deskPlusDefaultPlanStripePlanId;

    @Value("${deskplus.product-key}")
    private String productDeskPlusKey;

    @Override
    public Response signup(SignupRequest signupRequest) {
        logger.info("Account registration initiated for : "+signupRequest.toString());
        String firstName = signupRequest.getFirstName();
        String lastName = signupRequest.getLastName();
        String email = signupRequest.getEmail();

        UsersEntity usersEntity = usersRepository.findByEmail(email);
        if(usersEntity != null){
            return new FailureResponse(ResponseConstants.ERR_USER_EXIST, ResponseConstants.ERR_USER_EXIST_MSG);
        }

        Map<String, String> customerMetaData = new HashMap<>();
        customerMetaData.put("firstName", firstName);
        customerMetaData.put("lastName", lastName);
        customerMetaData.put("signup-mode","user_sign_up");
        Customer customer = stripeService.addCustomer(email, customerMetaData);
        if(customer == null){
            return ResponseManager.getInstance().createFailureResponse(ResponseConstants.ERR_STRIPE_CUSTOMER_CREATION_FAILED, ResponseConstants.ERR_STRIPE_CUSTOMER_CREATION_FAILED_MSG);
        }
        String stripeCustomerId = customer.getId();
        String stripePlanId = signupRequest.getStripePlanId();
        stripePlanId = (stripePlanId == null) ? deskPlusDefaultPlanStripePlanId : stripePlanId;
        PlansEntity plansEntity = plansRepository.findPlanByStripeId(stripePlanId);
        if(plansEntity == null){
            return ResponseManager.getInstance().createFailureResponse(ResponseConstants.ERR_SIGNUP_PLAN_NOT_FOUND, ResponseConstants.ERR_SIGNUP_PLAN_NOT_FOUND_MSG);
        }

        Plan stripePlan = stripeService.getPlan(stripePlanId);
        Subscription subscription = stripeService.addSubscription(stripeCustomerId, stripePlan);
        if(subscription == null){
            return ResponseManager.getInstance().createFailureResponse(ResponseConstants.ERR_SIGNUP_STRIPE_SUBSCRIPTION_CREATION_FAILED, ResponseConstants.ERR_SIGNUP_STRIPE_SUBSCRIPTION_CREATION_FAILED_MSG);
        }
        String regUniqueId = "reg_"+stripeCustomerId;
        String activationKey = RandomUtil.generateActivationKey();
        RegistrationsEntity registrationsEntity = new RegistrationsEntity();
        registrationsEntity.setReg_unique_id(regUniqueId);
        registrationsEntity.setCust_stripe_id(stripeCustomerId);
        registrationsEntity.setRegistration_time(new Date());
        registrationsEntity.setActivation_key(activationKey);
        registrationsEntity.setActivated(false);
        registrationsEntity = registrationsRepository.saveAndFlush(registrationsEntity);

        Long stripeSubCurPeriodStartDate = subscription.getCurrentPeriodStart();
        Long stripeSubCurPeriodEndDate = subscription.getCurrentPeriodEnd();
        Long stripeTrailStart = subscription.getTrialStart();
        Long stripeTrailEnd = subscription.getTrialEnd();
        String subscriptionStatus =  subscription.getStatus();
        boolean isTrial = (subscriptionStatus.equals(SubscriptionConstants.SUBSCRIPTION_TRIAL)) ? true : false;

        SubscriptionsEntity subscriptionsEntity = new SubscriptionsEntity();
        subscriptionsEntity.setReg_unique_id(regUniqueId);
        subscriptionsEntity.setStripe_subscription_id(subscription.getId());
        subscriptionsEntity.setSub_start_date(new Date(Instant.ofEpochSecond(stripeSubCurPeriodStartDate).toEpochMilli()));
        subscriptionsEntity.setSub_end_date((new Date(Instant.ofEpochSecond(stripeSubCurPeriodEndDate).toEpochMilli())));
        subscriptionsEntity.setIs_in_trail(isTrial);
        if(isTrial){
            subscriptionsEntity.setTrail_start_date(new Date(Instant.ofEpochSecond(stripeTrailStart).toEpochMilli()));
            subscriptionsEntity.setTrail_end_date(new Date(Instant.ofEpochSecond(stripeTrailEnd).toEpochMilli()));
        }
        subscriptionsEntity.setSub_status(subscriptionStatus);
        subscriptionsEntity.setProduct_key(productDeskPlusKey);
        subscriptionsEntity.setRegistration(registrationsEntity);
        subscriptionsEntity.setPlan(plansEntity);
        subscriptionsEntity = subscriptionsRepository.saveAndFlush(subscriptionsEntity);

        RolesEntity adminRoleEntity = rolesRepository.findByRole(UserRoles.ROLE_ADMIN);
        List<RolesEntity> roles = new ArrayList<>();
        roles.add(adminRoleEntity);

        UsersEntity userEntity = userServices.createUser(firstName, lastName, email, signupRequest.getPassword(), roles, true, registrationsEntity, null);

        String jwt = null;
        try{
            jwt = userServices.authenticate(email, signupRequest.getPassword());
        } catch (Exception ex){
            logger.error("Exception caught on generate JWT token during signup", ex);
        }

        if(jwt == null){
            return new FailureResponse(ResponseConstants.ERR_SIGNUP_AUTH_FAILED, ResponseConstants.ERR_SIGNUP_AUTH_FAILED_MSG);
        }

        mailService.sendAccountActivationMail(firstName, email, activationKey);

        Map<String, Object> data = userServices.getRegistrationDetails(subscriptionsEntity);
        data.put("token", jwt);

        return ResponseManager.getInstance().createSuccessDetailedResponse(data);
    }

    @Override
    public Response activate(String activationKey){
        RegistrationsEntity registrationsEntity = registrationsRepository.findRegistrationByActivationKey(activationKey);
        if(registrationsEntity == null){
            return ResponseManager.getInstance().createFailureResponse(ResponseConstants.ERR_INVALID_ACTIVATION, ResponseConstants.ERR_INVALID_ACTIVATION_MSG);
        }
        registrationsEntity.setActivated(true);
        registrationsEntity.setActivation_time(new Date());
        registrationsEntity = registrationsRepository.saveAndFlush(registrationsEntity);
        return ResponseManager.getInstance().createSuccessResponse(ResponseConstants.ACCOUNT_ACTIVATION_SUCCEEDED);
    }

    @Override
    public Response resendActivation(Long userId) {
        // Optional<UsersEntity> usersEntityOpt = usersRepository.findById(userId);
        // if(usersEntityOpt.isEmpty()){
        //     return ResponseManager.getInstance().createFailureResponse(ResponseConstants.ERR_ACCOUNT_ACTIVATION_USER_NOT_FOUND, ResponseConstants.ERR_ACCOUNT_ACTIVATION_USER_NOT_FOUND_MSG);
        // }
        // UsersEntity usersEntity = usersEntityOpt.get();
        // RegistrationsEntity registrationsEntity = usersEntity.getRegistration();
        // String firstName = usersEntity.getFirst_name();
        // String email = usersEntity.getEmail();
        // String activationKey = registrationsEntity.getActivation_key();

        // mailService.sendAccountActivationMail(firstName, email, activationKey);

        return ResponseManager.getInstance().createSuccessResponse(ResponseConstants.RESEND_ACTIVATION_MAIL_SUCCEEDED);
    }
}
