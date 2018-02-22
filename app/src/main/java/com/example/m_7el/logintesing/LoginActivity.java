package com.example.m_7el.logintesing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    private EditText userEmail;
    private EditText userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                    if (checkPasswordValidity){
                        btnLogin.setText("Successful");
                        Intent intent=new Intent(LoginActivity.this,ProfileActivity.class);
                        startActivity(intent);
                    }
                    else {
                        userPassword.setError("check password");
                    }


                } else {

                    userEmail.setError("check your Email");
                }
            }
        });
    }



}

