package com.example.m_7el.logintesing.net.login;


import com.example.m_7el.logintesing.modules.LoginInfo;
import com.example.m_7el.logintesing.modules.UserInformation;
import com.example.m_7el.logintesing.net.ApiCallback;


public interface LoginApi {

    void login(LoginInfo loginInfo, ApiCallback<UserInformation, String> callbacks);
}
