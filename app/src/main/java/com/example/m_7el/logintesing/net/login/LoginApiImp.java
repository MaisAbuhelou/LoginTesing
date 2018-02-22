package com.example.m_7el.logintesing.net.login;


import com.example.m_7el.logintesing.modules.ResponseData;
import com.example.m_7el.logintesing.modules.LoginInfo;
import com.example.m_7el.logintesing.net.AbstractRetrofitClients;
import com.example.m_7el.logintesing.net.ApiCallback;
import com.example.m_7el.logintesing.net.RetrofitInterface;

public class LoginApiImp extends AbstractRetrofitClients<RetrofitInterface> implements LoginApi {
    private static final String BASE_URL = "http://api.harridev.com/";

    public LoginApiImp() {
        super(BASE_URL, RetrofitInterface.class);
    }

    @Override
    public void getUserInfo(LoginInfo loginInfo,final ApiCallback<ResponseData, String> callbacks) {
        enqueueCall(mApis.login(loginInfo), callbacks);
    }


}