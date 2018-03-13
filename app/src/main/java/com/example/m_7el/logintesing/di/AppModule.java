package com.example.m_7el.logintesing.di;


import android.content.Context;
import android.content.SharedPreferences;

import com.example.m_7el.logintesing.modules.MySharedPreferences;
import com.example.m_7el.logintesing.modules.MySharedPreferencesImp;
import com.example.m_7el.logintesing.net.login.LoginApi;
import com.example.m_7el.logintesing.net.login.LoginApiImp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Context mContext;
    public AppModule(Context context) {
        mContext=context;
    }

    @Provides
    @Singleton

    LoginApi getLoginInfo() {
        return new LoginApiImp();
    }

    @Provides
    @Singleton
    MySharedPreferences provideSharedPreferences() {
        return  new MySharedPreferencesImp(mContext);
    }
}
