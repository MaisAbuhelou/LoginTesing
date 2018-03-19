package com.example.m_7el.logintesing.modules;

import com.google.gson.annotations.SerializedName;


public class ErrorResponse {

    @SerializedName("field")
    private String mField;

    @SerializedName("error")
    private String mErrorMessage;

    public String getField() {
        return mField;
    }

    public String getErrorMessage() {
        return mErrorMessage;
    }
}
