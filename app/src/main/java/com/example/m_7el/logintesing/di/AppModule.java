package com.example.m_7el.logintesing.di;


import com.example.m_7el.logintesing.net.login.LoginApi;
import com.example.m_7el.logintesing.net.login.LoginApiImp;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    LoginApi getLoginInfo() {
        return new LoginApiImp();
    }

}
