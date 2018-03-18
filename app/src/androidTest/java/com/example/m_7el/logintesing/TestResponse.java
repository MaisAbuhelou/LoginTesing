package com.example.m_7el.logintesing;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.intent.rule.IntentsTestRule;

import com.example.m_7el.logintesing.modules.LoginInfo;
import com.example.m_7el.logintesing.modules.ResponseData;
import com.example.m_7el.logintesing.modules.UserInformation;
import com.example.m_7el.logintesing.net.RetrofitInterface;
import com.example.m_7el.logintesing.net.login.LoginApiImp;
import com.google.gson.Gson;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Response;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;


public class TestResponse {
    private String validLogin;

    @Rule
    public IntentsTestRule<LoginActivity> mActivityRule = new IntentsTestRule<LoginActivity>(LoginActivity.class);

    private SharedPreferences mSharedPreferences = mock(SharedPreferences.class);
    private boolean isConnected;
    private Context context;
    private LoginApiImp loginApiImp;
    private LoginInfo loginInfo;
    private IdlingResource mIdlingResource;
    private String invalidUsername;
    private String invalidPassword;

    @Before
    public void registerIdlingResource() {

        context = getInstrumentation().getTargetContext();
        loginApiImp = (LoginApiImp) mActivityRule.getActivity().loginApiImp;
        mIdlingResource = mActivityRule.getActivity().getIdlingResource();
        // To prove that the test fails, omit this call:
        Espresso.registerIdlingResources(mIdlingResource);
        validLogin = readRawValues(R.raw.valid_login);
        invalidUsername=readRawValues(R.raw.invalid_user_name);
        invalidPassword=readRawValues(R.raw.invalid_password);
        mSharedPreferences = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        checkNetwork();
        logout();

    }

    @Test
    public void test6_sharedPreferencesData() throws InterruptedException {
        String testUsername;
        login();
        RetrofitInterface retrofitInterface = loginApiImp.initiateRetrofit();
        Call<ResponseData<UserInformation>> call = retrofitInterface.login(loginInfo);

        if (isConnected) {
            try {
                Response<ResponseData<UserInformation>> response = call.execute();
               ResponseData<UserInformation> responseData = new Gson().fromJson(validLogin,ResponseData.class);

                if (responseData != null) {
                    assertTrue(responseData.getStatusCode()==200);
                }
                intended(hasComponent(ProfileActivity.class.getName()));

            } catch (IOException e) {
                e.printStackTrace();
            }
            testUsername = mSharedPreferences.getString("LoginInfo", null);
            onView(withId(R.id.user_name)).check(matches(withText(testUsername)));
        }
    }

    private void checkNetwork() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        isConnected = activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void login() {
        onView(withId(R.id.email)).perform(typeText("yazan@harri.com"));
        onView(withId(R.id.password)).perform(typeText("123123"));
        closeSoftKeyboard();
        onView(withId(R.id.login)).perform(click());
        loginInfo = new LoginInfo(false, false, "yazan@harri.com",
                "123123", false, "yazan@harri.com");
    }

    private void logout() {
        if (mSharedPreferences.getString("LoginInfo", null) != null) {
            onView(withId(R.id.logout)).perform(click());
        }
    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            Espresso.unregisterIdlingResources(mIdlingResource);
        }
    }

    private String readRawValues(int value) {

        InputStream raw = context.getResources().openRawResource(value);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int i;
        try {
            i = raw.read();
            while (i != -1) {
                byteArrayOutputStream.write(i);
                i = raw.read();
            }
            raw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block

            e.printStackTrace();
        }


        return byteArrayOutputStream.toString();

    }
}
