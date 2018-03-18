package com.example.m_7el.logintesing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.m_7el.logintesing.modules.LoginInfo;
import com.example.m_7el.logintesing.modules.ResponseData;
import com.example.m_7el.logintesing.net.RetrofitInterface;
import com.example.m_7el.logintesing.net.login.LoginApiImp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowLooper;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)

public class Testing {

    private LoginActivity activity;
    private Button button;
    private RetrofitInterface retrofitInterface;
    private Call<ResponseData> call;
    private SharedPreferences mSharedPreferences = Mockito.mock(SharedPreferences.class);


    @Before
    public void setUp() throws Exception {

        mSharedPreferences = RuntimeEnvironment.application.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        activity = Robolectric.buildActivity(LoginActivity.class).create().start().visible().get();
        button = activity.findViewById(R.id.login);
        EditText email = activity.findViewById(R.id.email);
        EditText password = activity.findViewById(R.id.password);
        email.setText("yazan@harri.com");
        password.setText("123123");

        LoginInfo loginInfo = new LoginInfo(false, false, email.getText().toString(),
                password.getText().toString(), false, email.getText().toString());

        LoginApiImp loginApiImp = (LoginApiImp) activity.loginApiImp;
        retrofitInterface = loginApiImp.initiateRetrofit();
        call = retrofitInterface.login(loginInfo);
    }

    @Test
    public void retrofitTesting() {
        button.performClick();

        try {

            //Magic is here at .execute() instead of .enqueue()
            Response<ResponseData> response = call.execute();
            ResponseData responseData = response.body();
            assertTrue(response.isSuccessful() && responseData.getStatusCode() == 200);

            ShadowLooper.runUiThreadTasksIncludingDelayedTasks();

            ShadowActivity shadowActivity = shadowOf(activity);
            Intent actualIntent = shadowActivity.peekNextStartedActivity();
            Intent expectedIntent = new Intent(activity, ProfileActivity.class);
            assertTrue(actualIntent.filterEquals(expectedIntent));

            ProfileActivity profileActivity = Robolectric.setupActivity(ProfileActivity.class);
            TextView userName = profileActivity.findViewById(R.id.user_name);
            assertThat(userName).isNotNull();
            assertEquals(userName.getText(), mSharedPreferences.getString("LoginInfo", null));

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

