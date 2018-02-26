package com.example.m_7el.logintesing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.m_7el.logintesing.di.MyApp;
import com.example.m_7el.logintesing.modules.MySharedPreferences;

import javax.inject.Inject;

public class ProfileActivity extends AppCompatActivity {
    @Inject
    MySharedPreferences mySharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ((MyApp) getApplicationContext()).getMyComponent().inject(this);
        TextView userName=findViewById(R.id.user_name);
        userName.setText(mySharedPreferences.getData("LoginInfo"));

    }
}