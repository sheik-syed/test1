package com.ecomshop.deskplus.web.rest;



/**
 * Author: Sheik Syed Ali
 * Date: 26 Sep 2021
 */
public class SuccessResponse extends Response {

    private String message;

    public SuccessResponse(String message){
        status = ResponseConstants.SUCCESS;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
