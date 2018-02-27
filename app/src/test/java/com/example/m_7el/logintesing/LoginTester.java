package com.example.m_7el.logintesing;


import android.content.Context;
import android.content.SharedPreferences;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)

public class LoginTester {



    @Test
    public void emailValidity() {

        assertThat(LoginValidator.checkEmail("name9_777@email.com"), is(true));
        assertThat(LoginValidator.checkEmail("name"), is(false));
        assertThat(LoginValidator.checkEmail("name 33@gmail.com"), is(false));
        assertThat(LoginValidator.checkEmail("name_m@gmail.com"), is(true));
        assertThat(LoginValidator.checkEmail("name"), is(false));

    }


    @Test
    public void passwordValidity() {

        assertThat(LoginValidator.checkPassword("12345678"), is(true));
        assertThat(LoginValidator.checkPassword("99"), is(false));

    }



}