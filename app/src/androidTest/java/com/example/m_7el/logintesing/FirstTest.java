package com.example.m_7el.logintesing;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FirstTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<>(LoginActivity.class);



    @Test
    public void testErrorMessage() {
        // check email error
        onView(withId(R.id.email)).perform(typeText("mais @gmail.com"));
        onView(withId(R.id.password)).perform(typeText("1234567890"));
        onView(withId(R.id.login)).perform(click());
        onView(withId(R.id.email)).check(matches(hasErrorText("check your Email")));
        //check password error

        onView(withId(R.id.email)).perform(clearText()).perform(typeText("mais@gmail.com"));
        onView(withId(R.id.password)).perform(clearText()).perform(typeText("12"));
        onView(withId(R.id.login)).perform(click());
        onView(withId(R.id.password)).check(matches(hasErrorText("check password")));


    }

    @Test
    public void checkIntent() {
        onView(withId(R.id.email)).perform(clearText()).perform(typeText("mais@gmail.com"));
        onView(withId(R.id.password)).perform(clearText()).perform(typeText("1234567890"));
        onView(withId(R.id.login)).perform(click());
        intended(hasComponent(ProfileActivity.class.getName()));
    }


}