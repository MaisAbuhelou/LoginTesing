package com.example.m_7el.logintesing.di;


import com.example.m_7el.logintesing.LoginActivity;
import com.example.m_7el.logintesing.ProfileActivity;

import javax.inject.Singleton;

import dagger.Component;
@Singleton

@Component(modules = {AppModule.class})
public interface MyComponent {
    void inject(LoginActivity loginActivity);
    void inject(ProfileActivity profileActivity);
}
