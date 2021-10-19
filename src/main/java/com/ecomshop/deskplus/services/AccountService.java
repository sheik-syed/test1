package com.ecomshop.deskplus.services;

import com.ecomshop.deskplus.web.rest.Response;
import com.ecomshop.deskplus.web.rest.SignupRequest;

/**
 * Author: Sheik Syed Ali
 * Date: 10 Oct 2021
 */
public interface AccountService {

    Response signup(SignupRequest signupRequest);

    Response activate(String activationKey);

    Response resendActivation(Long userId);
}
