package com.example.m_7el.logintesing;


import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.m_7el.logintesing.context.MyApp;
import com.example.m_7el.logintesing.di.AppModule;
import com.example.m_7el.logintesing.di.AppModuleTest;
import com.example.m_7el.logintesing.di.DaggerMyComponent;
import com.example.m_7el.logintesing.di.MyComponent;
import com.example.m_7el.logintesing.modules.LoginInfo;
import com.example.m_7el.logintesing.modules.MySharedPreferences;
import com.example.m_7el.logintesing.net.login.LoginApi;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
@RunWith(AndroidJUnit4.class)

public class Mocking {
    @Rule
    public ActivityTestRule<LoginActivity> activityRule =
            new ActivityTestRule<>(LoginActivity.class);
    private LoginApi loginApiImp;
    private MySharedPreferences mySharedPreferencesImp;
    private LoginInfo loginInfo;


    @Before
    public void init() {
        //getting the application class
        MyApp myApp = (MyApp) InstrumentationRegistry
                .getInstrumentation()
                .getTargetContext()
                .getApplicationContext();

        //building the app component with the mocked module
        MyComponent mockedComponent = DaggerMyComponent.builder()
                .appModule(new AppModule(myApp)).build();

        //setting the component with the mocked module as the main app component
        myApp.setMyAppComponent(mockedComponent);
    }

    @Test
    public void validLogin() throws Exception {
        loginApiImp =  activityRule.getActivity().loginApiImp;
         mySharedPreferencesImp =activityRule.getActivity().mySharedPreferences;




    }
    private void login() {
        onView(withId(R.id.email)).perform(typeText("yazan@harri.com"));
        onView(withId(R.id.password)).perform(typeText("123123"));
        closeSoftKeyboard();
        onView(withId(R.id.login)).perform(click());
        loginInfo = new LoginInfo(false, false, "yazan@harri.com",
                "123123", false, "yazan@harri.com");
    }
    }
