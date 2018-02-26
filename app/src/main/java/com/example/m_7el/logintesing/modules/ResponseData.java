package com.example.m_7el.logintesing.modules;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseData {

    @SerializedName("data")
    private Object data ;
    @SerializedName("status")
    private String status;
    @SerializedName("status_code")
    private Integer statusCode;


    public Object getData() {
        return data;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

}
