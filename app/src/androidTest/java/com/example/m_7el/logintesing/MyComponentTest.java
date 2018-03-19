package com.example.m_7el.logintesing;


import com.example.m_7el.logintesing.di.MyComponent;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModuleTest.class})
public interface MyComponentTest extends MyComponent {
    void inject(NewTest newTest);

}
