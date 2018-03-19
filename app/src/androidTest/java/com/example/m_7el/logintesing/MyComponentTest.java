package com.example.m_7el.logintesing;


import com.example.m_7el.logintesing.di.AppModule;
import com.example.m_7el.logintesing.di.MyComponent;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface MyComponentTest extends MyComponent {

}
