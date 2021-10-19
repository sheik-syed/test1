package com.ecomshop.deskplus.web.rest;

import java.util.Map;

/**
 * Author: Sheik Syed Ali
 * Date: 26 Sep 2021
 */
public class SuccessDetailResponse extends Response {

    private Map<String, Object> data;

    public SuccessDetailResponse(Map<String, Object> data){
        status = ResponseConstants.SUCCESS;
        this.data = data;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
