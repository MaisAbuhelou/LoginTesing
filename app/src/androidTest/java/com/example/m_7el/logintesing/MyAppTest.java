package com.example.m_7el.logintesing;


import com.example.m_7el.logintesing.context.MyApp;
import com.example.m_7el.logintesing.di.DaggerMyComponent;

public class MyAppTest extends MyApp {


    private MyComponentTest myComponent;

    @Override
    public void onCreate() {
        super.onCreate();
//        myComponent = DaggerMyComponent.builder().appModule()
    }

@Override
    public MyComponentTest getMyComponent() {
        return myComponent;
    }

}
