package com.example.m_7el.logintesing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.m_7el.logintesing.di.MyApp;
import com.example.m_7el.logintesing.modules.ResponseData;
import com.example.m_7el.logintesing.modules.LoginInfo;
import com.example.m_7el.logintesing.net.ApiCallback;
import com.example.m_7el.logintesing.net.login.LoginApi;

import javax.inject.Inject;


public class LoginActivity extends AppCompatActivity {

    private EditText userEmail;
    private EditText userPassword;
    @Inject
    LoginApi loginApiImp;
    private LoginInfo loginInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((MyApp) getApplicationContext()).getMyComponent().inject(this);

        initializeViews();
    }

    private void initializeViews() {
        userEmail = findViewById(R.id.email);
        userPassword = findViewById(R.id.password);
        final Button btnLogin = (findViewById(R.id.login));
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Boolean checkEmailValidity = LoginValidator.checkEmail(userEmail.getText().toString());
                if (checkEmailValidity) {
                    Boolean checkPasswordValidity = LoginValidator.checkPassword(userPassword.getText().toString());
                    if (checkPasswordValidity) {
                        loginInfo = new LoginInfo(false, false, userEmail.getText().toString(),
                                userPassword.getText().toString(), false, userEmail.getText().toString());

                        checkUser();
                    } else {
                        userPassword.setError("check password");
                    }


                } else {

                    userEmail.setError("check your Email");
                }
            }
        });
    }

    private void checkUser() {
        loginApiImp.getUserInfo(loginInfo, new ApiCallback<ResponseData, String>() {
            @Override
            public void onSuccess(ResponseData responseData) {
                if (responseData.getStatus().equals("Success")) {

                    Toast.makeText(getApplicationContext(), "welcome", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                    intent.putExtra("userInfo", responseData.getUserInformation());
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), responseData.getUserInformation().getError(), Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onError(String s) {

            }
        });
    }

}

