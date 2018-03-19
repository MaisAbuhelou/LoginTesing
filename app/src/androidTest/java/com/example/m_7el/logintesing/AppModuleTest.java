package com.example.m_7el.logintesing;


import android.content.Context;

import com.example.m_7el.logintesing.di.AppModule;
import com.example.m_7el.logintesing.modules.LoginInfo;
import com.example.m_7el.logintesing.net.MockLoginApi;
import com.example.m_7el.logintesing.net.login.LoginApi;
import com.example.m_7el.logintesing.net.login.LoginApiImp;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModuleTest{


    private final Context mContext;

    public AppModuleTest(Context context) {

        mContext = context;
    }

   @Provides
    LoginApi getLoginInfo()  {
        return new LoginApiImp();
    }

}
