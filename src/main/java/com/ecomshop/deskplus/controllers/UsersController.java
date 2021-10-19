package com.ecomshop.deskplus.controllers;

import com.ecomshop.deskplus.services.UserRoles;
import com.ecomshop.deskplus.services.UserServices;
import com.ecomshop.deskplus.web.rest.CreateUserRequest;
import com.ecomshop.deskplus.web.rest.LoginRequest;
import com.ecomshop.deskplus.web.rest.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

/**
 * Author: Sheik Syed Ali
 * Date: 24 Sep 2021
 */
@RestController
@RequestMapping("/api/v1/users")
public class UsersController {
    private final Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private UserServices userServices;

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public Response authenticate(@RequestBody LoginRequest loginRequest) throws Exception {
        return userServices.authenticate(loginRequest);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @RolesAllowed({UserRoles.ROLE_ADMIN})
    public Response createUser(@RequestBody CreateUserRequest createUserRequest){
        return userServices.createUser(createUserRequest);
    }








    @GetMapping
    public String testMe(){
        logger.info("Hellow Sheik test logging...");
        return "Hello Sheik";
    }


    @GetMapping
    @RequestMapping("/role")
    public String role() throws Exception{


//        mailService.sendAccountActivationMail("Sheik Syed", "sheiksyeda55@gmail.com");


        return "Role Test";
    }

    @GetMapping
    @RequestMapping("/admin")
    public String admin(){
        return "Only Admin";
    }

}
