package com.example.m_7el.logintesing.modules;

import com.google.gson.annotations.SerializedName;

public class EntryMode {

    @SerializedName("brand_id")
    private Integer brandId;
    @SerializedName("brand_type")
    private String brandType;

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandType() {
        return brandType;
    }

    public void setBrandType(String brandType) {
        this.brandType = brandType;
    }

}
