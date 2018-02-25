package com.example.m_7el.logintesing.modules;

import com.google.gson.annotations.SerializedName;

public class ErrorResponse {

    @SerializedName("error")
    private String error;

    @SerializedName("field")
    private String field;

    public String getError() {
        return error;
    }

    public String getField() {
        return field;
    }
}

