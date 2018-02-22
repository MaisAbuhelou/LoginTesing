package com.example.m_7el.logintesing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.m_7el.logintesing.modules.UserInformation;

import java.util.Locale;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();
        UserInformation userInformation = intent.getParcelableExtra("userInfo");
        TextView userName = findViewById(R.id.user_name);
        userName.setText(String.format(Locale.ENGLISH, "%s  %s ", userInformation.getFirstName(), userInformation.getLastName()));
    }
}