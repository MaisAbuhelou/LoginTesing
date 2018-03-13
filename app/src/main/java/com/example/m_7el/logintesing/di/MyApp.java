package com.example.m_7el.logintesing.di;

import android.app.Activity;
import android.app.Application;



public class MyApp extends Application {
    private MyComponent myComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        myComponent = DaggerMyComponent.builder()
                .appModule(new AppModule(getApplicationContext()))
                .build();
    }

    public MyComponent getMyComponent() {
        return myComponent;
    }

    public void setMyAppComponent(MyComponent mockedComponent) {
        myComponent=mockedComponent;
    }
}
