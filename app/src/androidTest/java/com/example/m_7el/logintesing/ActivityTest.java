package com.example.m_7el.logintesing;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
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
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class ActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<>(LoginActivity.class);


    @Test
    public void ErrorMessageTesting() {
        String[] emails = {"emaill_9@.com", "mai s@gmail.com", "@mais@gmail.com"};
        String[] passwords = {"mmmm", " 9 ", "192ujd"};

        // check email error
        for (int i = 0; i < emails.length; i++) {
            onView(withId(R.id.email)).perform(clearText()).perform(typeText(emails[i]));
            onView(withId(R.id.password)).perform(clearText()).perform(typeText("1234567890"));
            onView(withId(R.id.login)).perform(click());
            onView(withId(R.id.email)).check(matches(hasErrorText("check your Email")));
        }
        //check password error
        for (int i = 0; i < passwords.length; i++) {
            onView(withId(R.id.email)).perform(clearText()).perform(typeText("mais@gmail.com"));
            onView(withId(R.id.password)).perform(clearText()).perform(typeText(passwords[i]));
            onView(withId(R.id.login)).perform(click());
            onView(withId(R.id.password)).check(matches(hasErrorText("check password")));
        }

    }


}