package com.example.m_7el.logintesing;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;


import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;


@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class ActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<>(LoginActivity.class);

    private SharedPreferences mSharedPreferences = Mockito.mock(SharedPreferences.class);


    @Before
    public void setUp() throws Exception {
        Context context = getInstrumentation().getTargetContext();
        mSharedPreferences = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);


    }

    @Test
    public void test1_Logout() {

        if (mSharedPreferences.getString("LoginInfo", null) != null) {
            onView(withId(R.id.logout)).perform(click());
        }
    }

    @Test
    public void test2_wrongEmailFormat() {

// if email doesn't match  email pattern
        onView(withId(R.id.email)).perform(clearText()).perform(typeText("m m @email.com"));
        onView(withId(R.id.password)).perform(clearText()).perform(typeText("1234567890"));
        closeSoftKeyboard();
        onView(withId(R.id.login)).perform(click());
        onView(withId(R.id.email)).check(matches(hasErrorText("check your Email")));
    }

    @Test
    public void test3_wrongPasswordFormat() {
        // if  password less than 6 digits

        onView(withId(R.id.email)).perform(clearText()).perform(typeText("mais@gmail.com"));
        onView(withId(R.id.password)).perform(clearText()).perform(typeText("1234"));
        closeSoftKeyboard();
        onView(withId(R.id.login)).perform(click());
        onView(withId(R.id.password)).check(matches(hasErrorText("check password")));

    }

    @Test
    public void test4_unRegisteredEmailFormat() {
        // if email match email pattern but has no  authentication
        onView(withId(R.id.email)).perform(clearText()).perform(typeText("mais@gmail.com"));
        onView(withId(R.id.password)).perform(clearText()).perform(typeText("1234333"));
        closeSoftKeyboard();
        onView(withId(R.id.login)).perform(click());
        onView(withId(R.id.progress)).check((matches(isDisplayed())));
        onView(withText(R.string.error_data_message))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void test5_wrongPassword() {
        // if email match email pattern but has no  authentication

        onView(withId(R.id.email)).perform(clearText()).perform(typeText("yazan@harri.com"));
        onView(withId(R.id.password)).perform(clearText()).perform(typeText("123124"));
        closeSoftKeyboard();
        onView(withId(R.id.login)).perform(click());
        onView(withId(R.id.progress)).check((matches(isDisplayed())));
        onView(withText(R.string.error_data_message))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void test6_successfulLogin() {

        onView(withId(R.id.email)).perform(clearText()).perform(typeText("yazan@harri.com"));
        onView(withId(R.id.password)).perform(clearText()).perform(typeText("123123"));
        closeSoftKeyboard();
        onView(withId(R.id.login)).perform(click());
        onView(withId(R.id.progress)).check((matches(isDisplayed())));
        onView(withText(R.string.TOAST_STRING))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));

    }

    @Test
    public void test7_sharedPreferencesData() {
        String testUsername = mSharedPreferences.getString("LoginInfo", null);
        if (testUsername != null) {
            onView(withId(R.id.user_name))
                    .check(matches(withText(testUsername)));
        }
    }


    @After
    public void tearDown() throws Exception {
    }

}