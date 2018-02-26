package com.example.m_7el.logintesing;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(AndroidJUnit4.class)
public class IntentTest {

    @Rule
    public IntentsTestRule<LoginActivity> mActivityTestRule = new IntentsTestRule<>(LoginActivity.class);

//    final SharedPreferences sharedPrefs = Mockito.mock(SharedPreferences.class);
//    final Context context = Mockito.mock(Context.class);

    @Test
    public void checkIntent() {
        onView(withId(R.id.email)).perform(clearText()).perform(typeText("yazan@harri.com"));
        onView(withId(R.id.password)).perform(clearText()).perform(typeText("123123"));
        onView(withId(R.id.login)).perform(click());
//        Mockito.when(sharedPrefs.getString(anyString(), anyString())).thenReturn("LoginInfo");


    }



}