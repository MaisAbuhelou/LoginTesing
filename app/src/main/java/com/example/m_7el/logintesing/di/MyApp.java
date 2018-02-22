package com.example.m_7el.logintesing.di;

import android.app.Application;



public class MyApp extends Application {
    private MyComponent myComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        myComponent = DaggerMyComponent.builder()
                .appModule(new AppModule())
                .build();
    }

    public MyComponent getMyComponent() {
        return myComponent;
    }

}
