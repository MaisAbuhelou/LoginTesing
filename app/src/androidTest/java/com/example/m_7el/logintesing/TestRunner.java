

package com.example.m_7el.logintesing;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;


public class TestRunner extends AndroidJUnitRunner {

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) throws InstantiationException,
            IllegalAccessException, ClassNotFoundException {
        String testApplicationClassName = MyAppTest.class.getCanonicalName();
        return super.newApplication(cl, testApplicationClassName, context);
    }
}
