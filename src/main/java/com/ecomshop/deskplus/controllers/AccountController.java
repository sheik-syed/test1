package com.ecomshop.deskplus.controllers;


import com.ecomshop.deskplus.services.AccountService;
import com.ecomshop.deskplus.web.rest.Response;
import com.ecomshop.deskplus.web.rest.SignupRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Author: Sheik Syed Ali
 * Date: 10 Oct 2021
 */
@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    private final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public Response signup(@RequestBody SignupRequest signupRequest){
        return accountService.signup(signupRequest);
    }

    @GetMapping("/activate/{activationKey}")
    public Response activate(@PathVariable String activationKey){
        return accountService.activate(activationKey);
    }

    @GetMapping("/activation/resend/{userId}")
    public Response resendActivation(@PathVariable Long userId){
        return accountService.resendActivation(userId);
    }

}
