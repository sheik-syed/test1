package com.ecomshop.deskplus.services;

import com.stripe.model.Customer;
import com.stripe.model.Plan;
import com.stripe.model.Subscription;

import java.util.Map;

/**
 * Author: Sheik Syed Ali
 * Date: 10 Oct 2021
 */
public interface StripeService {

    Customer addCustomer(String email, Map<String, String> metaData);

    Plan getPlan(String stripePlanId);

    Subscription addSubscription(String stripeCustomerId, Plan plan);

//    Subscription addSubscription(String stripeCustomerId, Plan plan, String coupon, int trialDays);

}
