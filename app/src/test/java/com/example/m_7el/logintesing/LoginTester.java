package com.example.m_7el.logintesing;


import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class LoginTester {


    @Mock
    LoginValidator loginValidator;


    @Test
    public void emailValidity() {

        assertThat(loginValidator.checkEmail("name9_777@email.com"), is(true));
        assertThat(loginValidator.checkEmail("name"), is(false));
        assertThat(loginValidator.checkEmail("name 33@gmail.com"), is(false));
        assertThat(loginValidator.checkEmail("name_m@gmail.com"), is(true));
        assertThat(loginValidator.checkEmail("name"), is(false));

    }


    @Test
    public void passwordValidity() {

        assertThat(loginValidator.checkPassword("12345678"), is(true));
        assertThat(loginValidator.checkPassword("99"), is(false));

    }


}