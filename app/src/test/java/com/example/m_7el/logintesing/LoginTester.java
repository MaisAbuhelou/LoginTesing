package com.example.m_7el.logintesing;


import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class LoginTester {


    @Test
    public void emailValidity() {

        assertThat(LoginValidator.checkEmail("name9_777@email.com"), is(true));
        assertThat(LoginValidator.checkEmail("name"), is(false));

    }


    @Test
    public void passwordValidity() {

        assertThat(LoginValidator.checkPassword("12345678"), is(true));
        assertThat(LoginValidator.checkPassword("99"), is(false));

    }



}