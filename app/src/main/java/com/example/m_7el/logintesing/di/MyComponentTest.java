package com.example.m_7el.logintesing.di;


import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface MyComponentTest extends  MyComponent{
}
