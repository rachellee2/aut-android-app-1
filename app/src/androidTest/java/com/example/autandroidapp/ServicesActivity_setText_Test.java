package com.example.autandroidapp;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * This class tests if setText_ButtonTextview() in ServicesActivity displays correct texts in all the textViews.
 */
public class ServicesActivity_setText_Test {
    @Rule
    public ActivityTestRule<ServicesActivity> servicesActivityActivityTestRule = new ActivityTestRule<ServicesActivity>(ServicesActivity.class);
    private ServicesActivity servicesActivity = null;
    HashMap<Integer, String> expectedText = new HashMap<>();

    // This method sets up the necessary data for testing. expectedText contains textViews' indices and corresponding sample texts
    // from json file.
    @Before
    public void setUp() throws Exception {
        servicesActivity = servicesActivityActivityTestRule.getActivity();
        expectedText.put(0, "AUT has childcare centres on two campuses:");
        expectedText.put(1, "domestic and international students can get free");
        expectedText.put(2, "Disability Support team can support you");
        expectedText.put(3, "International students at AUT can see our friendly team");
        expectedText.put(4, "At AUT we’re dedicated to the success of our Māori students");
        expectedText.put(5, "AUT has medical centres for students at our City and North campuses");
        expectedText.put(6, "Pacific communities through education");
        expectedText.put(7, "At AUT, our gay, lesbian, transgender, bisexual and intersex (LGBTTI+)");
        expectedText.put(8, "AUT has people on campus who can help you settle in and stay on top");
        expectedText.put(9, "At AUT we take a 'multifaith' approach,");
        expectedText.put(10, "The AUT Student Hub is the one place to go for help on each campus");
        expectedText.put(11, "We support our elite student athletes");
    }

    // Loops through every textViews, get their texts in string and compare them to texts from expectedText.
    // If all the text from textViews contains expectedText, this test is passed.
    @Test
    public void testSetText(){
        boolean test = false;
        for(int i = 0; i < servicesActivity.txtView.size(); i++) {
            if(servicesActivity.txtView.get(i).getText().toString().contains(expectedText.get(i))){
                test = true;
            }
        }
        assertTrue(test);
    }

    @After
    public void tearDown() throws Exception {
        servicesActivity = null;
        expectedText = null;
    }
}