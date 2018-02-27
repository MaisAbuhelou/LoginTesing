package com.example.m_7el.logintesing;


import android.support.annotation.NonNull;

class LoginValidator {


    @NonNull
    static Boolean checkEmail(String userEmail) {

        String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        if (userEmail.matches(emailPattern)) {
            return true;
        } else {
            return false;
        }

    }


    @NonNull
     static Boolean checkPassword(String userPassword) {
        if ( userPassword.length() >= 6) {

            return true;
        } else {
            return false;
        }
    }
}
