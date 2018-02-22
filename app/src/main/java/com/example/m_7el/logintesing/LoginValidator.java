package com.example.m_7el.logintesing;


class LoginValidator {


    static Boolean checkEmail(String userEmail) {

        String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        if (userEmail.matches(emailPattern)) {
            return true;
        } else {
            return false;
        }

    }


    public static Boolean checkPassword(String userPassword) {
        if (userPassword != null && userPassword.length() >= 8) {

            return true;
        } else {
            return false;
        }
    }
}
