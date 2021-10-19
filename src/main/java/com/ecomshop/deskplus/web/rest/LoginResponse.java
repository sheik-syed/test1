package com.ecomshop.deskplus.web.rest;

/**
 * Author: Sheik Syed Ali
 * Date: 25 Sep 2021
 */
public class LoginResponse {

    private String jwt;

    public LoginResponse(String jwt){
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
