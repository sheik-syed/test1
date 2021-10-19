package com.ecomshop.deskplus.web.rest;

import java.util.Map;

/**
 * Author: Sheik Syed Ali
 * Date: 10 Oct 2021
 */
public class ResponseManager {
    private static ResponseManager responseManager = null;

    public Response createSuccessResponse(String message){
        Response successResponse = new SuccessResponse(message);
        return successResponse;
    }

    public Response createFailureResponse(int errCode, String message){
        Response failureResponse = new FailureResponse(errCode, message);
        return failureResponse;
    }

    public Response createSuccessDetailedResponse(Map<String, Object> data){
        Response successDetailedResponse = new SuccessDetailResponse(data);
        return successDetailedResponse;
    }

    public static ResponseManager getInstance(){
        return (responseManager == null) ?  responseManager = new ResponseManager() :  responseManager;
    }
}
