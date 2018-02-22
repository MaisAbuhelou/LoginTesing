package com.example.m_7el.logintesing.modules;


import com.google.gson.annotations.SerializedName;

public class LoginInfo {

    @SerializedName("user_candidate")
    private Boolean userCandidate;
    @SerializedName("user_employer")
    private Boolean userEmployer;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("social_login")
    private Boolean socialLogin;
    @SerializedName("username")
    private String userName;

    public LoginInfo(Boolean userCandidate, Boolean userEmployer, String email, String password, Boolean socialLogin, String userName) {
        this.userCandidate = userCandidate;
        this.userEmployer = userEmployer;
        this.email = email;
        this.password = password;
        this.socialLogin = socialLogin;
        this.userName = userName;
    }

}

