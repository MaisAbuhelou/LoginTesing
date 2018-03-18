package com.example.m_7el.logintesing;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.m_7el.logintesing.modules.LoginInfo;
import com.example.m_7el.logintesing.modules.ResponseData;
import com.example.m_7el.logintesing.modules.UserInformation;
import com.example.m_7el.logintesing.net.RetrofitInterface;
import com.example.m_7el.logintesing.net.login.LoginApiImp;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.Mockito.mock;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class ActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<>(LoginActivity.class);

    private SharedPreferences mSharedPreferences = mock(SharedPreferences.class);
    private boolean isConnected;
    private Context context;
    private LoginApiImp loginApiImp;
    private LoginInfo loginInfo;
    private IdlingResource mIdlingResource;


    @Before
    public void setUp() {
        Intents.init();
        mIdlingResource = mActivityRule.getActivity().getIdlingResource();
        // To prove that the test fails, omit this call:
        Espresso.registerIdlingResources(mIdlingResource);
        context = getInstrumentation().getTargetContext();
        loginApiImp = (LoginApiImp) mActivityRule.getActivity().loginApiImp;
        context = getInstrumentation().getTargetContext();
        mSharedPreferences = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        checkNetwork();
        logout();
    }

    @Test
    public void test1_wrongEmailFormat() {
        // if email doesn't match  email pattern
        onView(withId(R.id.email)).perform(clearText()).perform(typeText("m m @email.com"));
        onView(withId(R.id.password)).perform(clearText()).perform(typeText("1234567890"));
        closeSoftKeyboard();
        onView(withId(R.id.login)).perform(click());
        onView(withId(R.id.email)).check(matches(hasErrorText("check your Email")));
    }

    @Test
    public void test2_wrongPasswordFormat() {
        // if  password less than 6 digits
        onView(withId(R.id.email)).perform(clearText()).perform(typeText("mais@gmail.com"));
        onView(withId(R.id.password)).perform(clearText()).perform(typeText("1234"));
        closeSoftKeyboard();
        onView(withId(R.id.login)).perform(click());
        onView(withId(R.id.password)).check(matches(hasErrorText("check password")));

    }

    @Test
    public void test3_nonExistentEmail() {
        // if email match email pattern but has no  authentication
        onView(withId(R.id.email)).perform(clearText()).perform(typeText("mais@gmail.com"));
        onView(withId(R.id.password)).perform(clearText()).perform(typeText("1234333"));
        closeSoftKeyboard();
        onView(withId(R.id.login)).perform(click());
        if (isConnected) {
            onView(withText("error"))
                    .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                    .check(matches(isDisplayed()));
        }
    }

    @Test
    public void test4_wrongPasswordForAnExistingEmail() {
        // if email match email pattern but has no  authentication
        onView(withId(R.id.email)).perform(clearText()).perform(typeText("yazan@harri.com"));
        onView(withId(R.id.password)).perform(clearText()).perform(typeText("123124"));
        closeSoftKeyboard();
        onView(withId(R.id.login)).perform(click());
        if (isConnected) {

            onView(withText("error"))
                    .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                    .check(matches(isDisplayed()));
        }
    }

    @Test
    public void test5_successfulLogin() {
        login();
        if (isConnected)
            onView(withText(R.string.valid_login))
                    .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                    .check(matches(isDisplayed()));

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
                ResponseData responseData = response.body();
                if (responseData != null) {
                    assertTrue(response.isSuccessful() && responseData.getStatusCode() == 200);
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
        Intents.release();
        if (mIdlingResource != null) {
            Espresso.unregisterIdlingResources(mIdlingResource);
        }
    }


}
