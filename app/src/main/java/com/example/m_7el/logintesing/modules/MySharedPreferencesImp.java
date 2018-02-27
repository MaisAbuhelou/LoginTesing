package com.example.m_7el.logintesing.modules;

import android.content.Context;
import android.content.SharedPreferences;


public class MySharedPreferencesImp implements MySharedPreferences {

    private SharedPreferences mSharedPreferences;

    public MySharedPreferencesImp(Context mContext) {
       mSharedPreferences= mContext.getSharedPreferences("MyPref",Context.MODE_PRIVATE);
    }


    public void putData(String key, String data) {
        mSharedPreferences.edit().putString(key,data).apply();
    }

    public String  getData(String key) {
        return mSharedPreferences.getString(key,null);
    }

    @Override
    public void ClearData() {
        mSharedPreferences.edit().clear().apply();
    }
}