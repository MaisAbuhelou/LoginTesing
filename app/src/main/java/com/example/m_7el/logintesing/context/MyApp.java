package com.example.m_7el.logintesing.context;

import android.app.Application;

import com.example.m_7el.logintesing.di.AppModule;
import com.example.m_7el.logintesing.di.DaggerMyComponent;
import com.example.m_7el.logintesing.di.MyComponent;



public class MyApp extends Application {
    private MyComponent myComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        myComponent =initializeAppComponent();

    }
    protected MyComponent initializeAppComponent() {
        return DaggerMyComponent.builder()
                .appModule(new AppModule(getApplicationContext()))
                .build();
    }


    public MyComponent getMyComponent() {
        return myComponent;
    }

}
