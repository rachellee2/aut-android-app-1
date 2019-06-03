package com.example.autandroidapp;

import android.app.Instrumentation;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

/**
 * This tests if a click of a button in autContact activity which will open the phone to make a call.
 */
public class autcontact_buttonTest {
    @Rule
    public ActivityTestRule<autcontact_button> AUTContactActivityActivityTestRule = new ActivityTestRule<>(autcontact_button.class);
    private autcontact_button AUTContactActivity = null;

    @Before
    public void setUp() throws Exception{
        AUTContactActivity = AUTContactActivityActivityTestRule.getActivity();
    }


    @Test//Tests the click of the button which calls the phone number
    public void testButton()
    {
        onView(withId(R.id.buttonCall_Hub)).perform(click());

    }


    @After
    public void tearDown() throws Exception {
        AUTContactActivity = null;
    }

}