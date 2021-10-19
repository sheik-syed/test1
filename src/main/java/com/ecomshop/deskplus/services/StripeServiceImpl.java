package com.ecomshop.deskplus.services;

import com.stripe.Stripe;
import com.stripe.model.Customer;
import com.stripe.model.Plan;
import com.stripe.model.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Sheik Syed Ali
 * Date: 10 Oct 2021
 */

@Service
@Transactional
public class StripeServiceImpl implements StripeService{

    private final Logger logger = LoggerFactory.getLogger(StripeServiceImpl.class);
    @Value("${stripe.api-key}")
    private String apiKey;

    @Override
    public Customer addCustomer(String email, Map<String, String> metaData){
        Customer customer = null;
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        params.put("metadata", metaData);
        Stripe.apiKey = apiKey;
        try{
            customer = Customer.create(params);
            if(customer != null && customer.getId() != null) {
                logger.info("Customer created successfully on stripe side. Customer Id: "+customer.getId());
            }
        }catch (Exception ex){
            logger.error("Exception caught on adding customer, email: "+email+"; metadata: "+metaData+";", ex);
        }
        return customer;
    }

    @Override
    public Plan getPlan(String stripePlanId) {
        Plan plan = null;
        try{
            Stripe.apiKey = apiKey;
            plan = Plan.retrieve(stripePlanId);
        }catch (Exception ex){
            logger.error("Exception caught on get plan details from stripe stripe id: "+stripePlanId+";", ex);
        }
        return plan;
    }

    @Override
    public Subscription addSubscription(String stripeCustomerId, Plan plan) {
        Subscription subscription = null;
        String stripePlanId = plan.getId();
        try{
            Map<String, Object> item = new HashMap<>();
            item.put("plan", stripePlanId);
            Map<String, Object> items = new HashMap<>();
            items.put("0", item);
            Map<String, Object> updateParams = new HashMap<String, Object>();
            updateParams.put("created", new java.util.Date());
            Map<String, Object> params = new HashMap<>();
            params.put("customer", stripeCustomerId);
//            if(trialDays > 0){
//                params.put("trial_period_days", trialDays);
//            }
//
//            if(coupon != null && !coupon.isEmpty()) {
//                params.put("coupon", coupon);
//            }
            params.put("items", items);
            subscription = Subscription.create(params);
        }catch (Exception ex){
            logger.error("Exception caught on adding subscription for customer. customerStripeId: "+stripeCustomerId+"; stripePlanId: "+stripePlanId+";", ex);
        }
        return subscription;
    }


}
