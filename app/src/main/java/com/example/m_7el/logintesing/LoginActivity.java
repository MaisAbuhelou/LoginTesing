package com.example.m_7el.logintesing;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.m_7el.logintesing.databinding.ActivityMainBinding;
import com.example.m_7el.logintesing.di.MyApp;
import com.example.m_7el.logintesing.modules.MySharedPreferences;
import com.example.m_7el.logintesing.modules.ResponseData;
import com.example.m_7el.logintesing.modules.LoginInfo;
import com.example.m_7el.logintesing.net.ApiCallback;
import com.example.m_7el.logintesing.net.login.LoginApi;

import java.util.Map;

import javax.inject.Inject;


public class LoginActivity extends AppCompatActivity {

    private EditText userEmail;
    private EditText userPassword;

    private LoginInfo loginInfo;
    private ActivityMainBinding binding;
    @Inject
    LoginApi loginApiImp;
    @Inject
    MySharedPreferences mySharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApp) getApplicationContext()).getMyComponent().inject(this);

        if (mySharedPreferences.getData("LoginInfo") != null) {

            startActivity(new Intent(LoginActivity.this, ProfileActivity.class));
            finish();
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initializeViews();
    }


    private void initializeViews() {
        userEmail = findViewById(R.id.email);
        userPassword = findViewById(R.id.password);
        final Button btnLogin = (findViewById(R.id.login));
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.setLoading(true);
                Boolean checkEmailValidity = LoginValidator.checkEmail(userEmail.getText().toString());
                if (checkEmailValidity) {
                    Boolean checkPasswordValidity = LoginValidator.checkPassword(userPassword.getText().toString());
                    if (checkPasswordValidity) {
                        loginInfo = new LoginInfo(false, false, userEmail.getText().toString(),
                                userPassword.getText().toString(), false, userEmail.getText().toString());
                        checkUser();
                    } else {
                        binding.setLoading(false);

                        userPassword.setError("check password");

                    }


                } else {
                    binding.setLoading(false);

                    userEmail.setError("check your Email");

                }
            }
        });
    }

    private void checkUser() {
        loginApiImp.login(loginInfo, new ApiCallback<ResponseData, String>() {


            @Override
            public void onSuccess(ResponseData responseData) {

                if (responseData.getStatusCode() == 200) {

                    Map<String, Object> userMap = (Map<String, Object>) responseData.getData();
                    String name = userMap.get("first_name") + " " + userMap.get("last_name");
                    Toast.makeText(getApplicationContext(), R.string.TOAST_STRING, Toast.LENGTH_LONG).show();
                    mySharedPreferences.putData("LoginInfo", name);
                    Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                    startActivity(intent);
                    finish();



                } else if (responseData.getStatusCode() == 403) {
                    binding.setLoading(false);

                    Toast.makeText(getApplicationContext(), R.string.error_data_message, Toast.LENGTH_LONG).show();

                } else if (responseData.getStatusCode() == 600) {
                    binding.setLoading(false);

                    Toast.makeText(getApplicationContext(), "missing data", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onError(String s) {
                binding.setLoading(true);

                Toast.makeText(getApplicationContext(), R.string.error_message, Toast.LENGTH_LONG).show();

            }
        });

    }
}