//package com.example.autandroidapp;
//
//import android.os.Looper;
//import androidx.test.rule.ActivityTestRule;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//
//import static androidx.test.espresso.Espresso.onView;
//import static androidx.test.espresso.action.ViewActions.click;
//import static androidx.test.espresso.matcher.ViewMatchers.withId;
//import static org.junit.Assert.*;
//
//public class architecture_buttonTest {
//
//    @Rule
//    public ActivityTestRule<architecture_button> architectureTestRule = new ActivityTestRule<architecture_button>(architecture_button.class);
//    private architecture_button archActivity = null;
//
//    //method to get architecture activity for testing
//    @Before
//    public void setUp() throws Exception {
//        archActivity = architectureTestRule.getActivity();
//        Looper.prepare();
//    }
//
//    //a method to test the home button being pressed
//    @Test
//    public void homeActivity()throws Throwable {
//        onView(withId(R.id.action_home)).perform(click());
//        assertEquals(archActivity.getCurrentFocus(), new MainActivity().getCurrentFocus());
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        archActivity = null;
//    }
//}