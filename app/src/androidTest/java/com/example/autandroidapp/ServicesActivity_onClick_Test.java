package com.example.autandroidapp;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

/**
 * This tests if a click of a services button will display the textView, and hide it after another click.
 */
public class ServicesActivity_onClick_Test {
    // To launch the mentioned activity under testing
    @Rule
    public ActivityTestRule<ServicesActivity> servicesActivityActivityTestRule = new ActivityTestRule<>(ServicesActivity.class);
    private ServicesActivity servicesActivity = null;

    @Before
    public void setUp() throws Exception{
        servicesActivity = servicesActivityActivityTestRule.getActivity();
    }

    // Test single click to display the corresponding textView
    @Test
    public void testTextviewVisible(){
        onView(withId(R.id.btnServiceOption1)).perform(click());
        onView(withId(R.id.txtViewServiceOption1)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    // Test double click to hide the displayed textView
    @Test
    public void testTextviewGone(){
        onView(withId(R.id.btnServiceOption1)).perform(click());
        onView(withId(R.id.btnServiceOption1)).perform(click());
        onView(withId(R.id.txtViewServiceOption1)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
    }

    @After
    public void tearDown() throws Exception {
        servicesActivity = null;
    }
}