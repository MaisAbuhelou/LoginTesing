package com.example.m_7el.logintesing.modules;



public interface MySharedPreferences {

    void putData(String key, String data) ;
    String  getData(String key);
    void  ClearData();
}