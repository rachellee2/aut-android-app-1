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
 * This tests if a click of a faq button will display the textView (answer).
 */
public class faq_buttonTest {
    // To launch the mentioned activity under testing
    @Rule
    public ActivityTestRule<faq_button> faqActivityActivityTestRule = new ActivityTestRule<>(faq_button.class);
    private faq_button faqActivity = null;

    @Before
    public void setUp() throws Exception{
        faqActivity = faqActivityActivityTestRule.getActivity();
    }

    // Test single click to display the corresponding textView
    @Test
    public void testTextviewVisible(){
        onView(withId(R.id.q1)).perform(click());
        onView(withId(R.id.a1)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @After
    public void tearDown() throws Exception {
        faqActivity = null;
    }
}