package com.example.m_7el.logintesing.di;


import com.example.m_7el.logintesing.LoginActivity;
import dagger.Component;

@Component(modules = {AppModule.class})
public interface MyComponent {
    void inject(LoginActivity loginActivity);

}
