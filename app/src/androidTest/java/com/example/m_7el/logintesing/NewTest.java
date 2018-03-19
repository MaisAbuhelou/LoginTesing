package com.example.m_7el.logintesing;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.telecom.Call;

import com.example.m_7el.logintesing.context.MyApp;
import com.example.m_7el.logintesing.modules.LoginInfo;
import com.example.m_7el.logintesing.modules.ResponseData;
import com.example.m_7el.logintesing.modules.UserInformation;
import com.example.m_7el.logintesing.net.MockLoginApi;
import com.example.m_7el.logintesing.net.login.LoginApiImp;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.io.IOException;


import retrofit2.Response;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class NewTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<>(LoginActivity.class);

    private SharedPreferences mSharedPreferences = mock(SharedPreferences.class);
    private boolean isConnected;
    private Context context;
    private LoginInfo loginInfo;
    private IdlingResource mIdlingResource;
    LoginApiImp loginApiImp;


    @Before
    public void setUp() {
        loginInfo = new LoginInfo(false, false, "yazan@harri.com",
                "123123", false, "yazan@harri.com");
        loginApiImp= (LoginApiImp) mActivityRule.getActivity().loginApiImp;



    }

    @Test
    public void moduling(){
        ((MyAppTest) mActivityRule.getActivity().getApplication()).getMyComponent().inject(this);


    }



}
