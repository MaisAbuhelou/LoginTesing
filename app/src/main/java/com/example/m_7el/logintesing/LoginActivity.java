package com.example.m_7el.logintesing;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.m_7el.logintesing.databinding.ActivityMainBinding;
import com.example.m_7el.logintesing.context.MyApp;
import com.example.m_7el.logintesing.modules.LoginInfo;
import com.example.m_7el.logintesing.modules.MySharedPreferences;
import com.example.m_7el.logintesing.modules.ResponseData;
import com.example.m_7el.logintesing.modules.UserInformation;
import com.example.m_7el.logintesing.net.ApiCallback;
import com.example.m_7el.logintesing.net.login.LoginApi;

import java.util.Map;

import javax.inject.Inject;


public class LoginActivity extends AppCompatActivity {

    private EditText userEmail;
    private EditText userPassword;
    private Button loginButton;

    private LoginInfo loginInfo;
    private ActivityMainBinding binding;

    @Inject
    LoginApi loginApiImp;
    @Inject
    MySharedPreferences mySharedPreferences;
    @Nullable
    private SimpleIdlingResource mIdlingResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApp) getApplicationContext()).getMyComponent().inject(this);

        if (mySharedPreferences.getData("LoginInfo") != null) {

            startActivity(new Intent(LoginActivity.this, ProfileActivity.class));
            finish();
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        userEmail = binding.email;
        userPassword = binding.password;
        loginButton = binding.login;
        login();
    }

    private void login() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.setLoading(true);
//                goToAnotherActivity();

                Boolean checkEmailValidity = LoginValidator.checkEmail(userEmail.getText().toString());
                if (checkEmailValidity) {
                    Boolean checkPasswordValidity = LoginValidator.checkPassword(userPassword.getText().toString());
                    if (checkPasswordValidity) {
                        loginInfo = new LoginInfo(false, false, userEmail.getText().toString(),
                                userPassword.getText().toString(), false, userEmail.getText().toString());
                        checkUser();
                    } else {
                        binding.setLoading(false);
                        userPassword.setError("check " + "password");
                    }

                } else {
                    binding.setLoading(false);
                    userEmail.setError("check your Email");

                }
            }
        });
    }

    public void checkUser() {

        mIdlingResource = (SimpleIdlingResource) getIdlingResource();
        if (mIdlingResource != null) {
            mIdlingResource.setIdleState(false);
        }

        loginApiImp.login(loginInfo, new ApiCallback<UserInformation, String>() {
            @Override
            public void onSuccess(UserInformation userInformation) {
                binding.setLoading(false);
                if (mIdlingResource != null) {
                    mIdlingResource.setIdleState(true);
                }
                if(userInformation!=null){
                    String name = userInformation.getFirstName()+" "+userInformation.getLastName();
                    Toast.makeText(getApplicationContext(),R.string.valid_login,Toast.LENGTH_LONG).show();
                    mySharedPreferences.putData("LoginInfo",name);
                    goToAnotherActivity();
                }


            }

            @Override
            public void onError(String s) {
                if (mIdlingResource != null) {
                    mIdlingResource.setIdleState(true);
                }
                binding.setLoading(false);
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();


            }
        } );

    }

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new SimpleIdlingResource();
        }
        return mIdlingResource;
    }

    public void goToAnotherActivity() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
        finish();
    }
}