package com.example.autandroidapp;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * This class tests if findButtonTextview() in ServicesActivity initializes the buttons correctly by their id.
 */
public class ServicesActivity_findButtonTextview_Test {
    @Rule
    public ActivityTestRule<ServicesActivity> servicesActivityActivityTestRule = new ActivityTestRule<ServicesActivity>(ServicesActivity.class);
    private ServicesActivity servicesActivity = null;
    HashMap<Integer, Integer> expectedBtnID = new HashMap<>();

    // This method sets up the necessary data for testing. expectedBtnID contains buttons' indices and their corresponding id
    @Before
    public void setUp() throws Exception {
        servicesActivity = servicesActivityActivityTestRule.getActivity();
        expectedBtnID.put(0, R.id.btnServiceOption1);
        expectedBtnID.put(2, R.id.btnServiceOption3);
        expectedBtnID.put(4, R.id.btnServiceOption5);
        expectedBtnID.put(6, R.id.btnServiceOption7);
        expectedBtnID.put(8, R.id.btnServiceOption9);
        expectedBtnID.put(10, R.id.btnServiceOption11);
    }

    // Loops through every other button, get their IDs and compare them to IDs from expectedBtnID.
    // If all the IDs from both variables match, this test is passed.
    @Test
    public void testFindButtonTextview(){
        boolean test = false;
        for(int i = 0; i < servicesActivity.btn.size(); i+=2){
            if(servicesActivity.btn.get(i).getId() == expectedBtnID.get(i)) {
                test = true;
            }
        }
        assertTrue(test);
    }

    @After
    public void tearDown() throws Exception {
        servicesActivity = null;
        expectedBtnID = null;
    }
}