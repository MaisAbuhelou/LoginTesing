package com.example.m_7el.logintesing.modules;


import com.google.gson.annotations.SerializedName;

public class ResponseData {

    @SerializedName("data")
    private UserInformation userInformation;
    @SerializedName("status")
    private String status;
    @SerializedName("status_code")
    private Integer statusCode;

    public ResponseData(UserInformation userInformation, String status, Integer statusCode) {
        this.userInformation = userInformation;
        this.status = status;
        this.statusCode = statusCode;
    }

    public UserInformation getUserInformation() {
        return userInformation;
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
