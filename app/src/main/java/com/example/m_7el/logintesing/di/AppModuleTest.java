package com.example.m_7el.logintesing.di;


import android.content.Context;

import com.example.m_7el.logintesing.modules.MySharedPreferences;
import com.example.m_7el.logintesing.modules.MySharedPreferencesImp;
import com.example.m_7el.logintesing.net.RetrofitInterface;
import com.example.m_7el.logintesing.net.login.LoginApi;
import com.example.m_7el.logintesing.net.login.LoginApiImp;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.when;


public class AppModuleTest extends AppModule{


    public AppModuleTest(Context context) {
        super(context);
    }

    @Override
    LoginApi getLoginInfo() {
        LoginApiImp mock = Mockito.mock(LoginApiImp.class);
        return mock;
    }
    @Override
    MySharedPreferences provideSharedPreferences() {
        return  Mockito.mock(MySharedPreferencesImp.class);
    }
}
