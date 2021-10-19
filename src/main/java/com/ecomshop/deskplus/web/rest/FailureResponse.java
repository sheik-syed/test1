package com.ecomshop.deskplus.web.rest;

/**
 * Author: Sheik Syed Ali
 * Date: 26 Sep 2021
 */
public class FailureResponse extends Response{
    private int errorCode;
    private String message;

    public FailureResponse(int errorCode, String message){
        status = ResponseConstants.FAIL;
        this.errorCode = errorCode;
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
