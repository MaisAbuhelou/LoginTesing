package com.example.m_7el.logintesing;


import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class IntentTest {

    @Rule
    public IntentsTestRule<LoginActivity> mActivityTestRule = new IntentsTestRule<>(LoginActivity.class);


    @Test
    public void checkIntent() {
        onView(withId(R.id.email)).perform(clearText()).perform(typeText("mais@gmail.com"));
        onView(withId(R.id.password)).perform(clearText()).perform(typeText("1234567890"));
        onView(withId(R.id.login)).perform(click());
        intended(hasComponent(ProfileActivity.class.getName()));
    }


}