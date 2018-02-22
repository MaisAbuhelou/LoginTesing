package com.example.m_7el.logintesing.net.login;


import com.example.m_7el.logintesing.modules.ResponseData;
import com.example.m_7el.logintesing.modules.LoginInfo;
import com.example.m_7el.logintesing.net.ApiCallback;


public interface LoginApi {

    void getUserInfo(LoginInfo loginInfo, ApiCallback<ResponseData, String> callbacks);
}
