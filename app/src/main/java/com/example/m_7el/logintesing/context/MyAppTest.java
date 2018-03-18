package com.example.m_7el.logintesing.context;

import com.example.m_7el.logintesing.di.AppModuleTest;
import com.example.m_7el.logintesing.di.DaggerMyComponent;
import com.example.m_7el.logintesing.di.MyComponent;


public class MyAppTest extends MyApp {

    @Override
    protected MyComponent initializeAppComponent() {
        return DaggerMyComponent.builder()
                .appModule(new AppModuleTest(this))
                .build();
    }

}
