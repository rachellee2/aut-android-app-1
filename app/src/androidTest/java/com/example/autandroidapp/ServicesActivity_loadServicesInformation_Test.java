package com.example.autandroidapp;

import androidx.test.rule.ActivityTestRule;

import org.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * This class tests if loadServiceInformation in ServicesActivity loads service options from json file.
 */
public class ServicesActivity_loadServicesInformation_Test {
    @Rule
    public ActivityTestRule<ServicesActivity> servicesActivityActivityTestRule = new ActivityTestRule<ServicesActivity>(ServicesActivity.class);
    private ServicesActivity servicesActivity = null;
    ArrayList<HashMap<String, String>> testServiceList = new ArrayList<>();
    ArrayList<String> expectedOptions = new ArrayList<>();
    String fileName, option, description;

    // This method sets up the necessary data for testing. FileName is services.json and expectedOptions
    // contains names of service options.
    @Before
    public void setUp() throws Exception {
        servicesActivity = servicesActivityActivityTestRule.getActivity();
        fileName = "services.json";
        expectedOptions.add("Counselling and mental health support");
        expectedOptions.add("International student support");
        expectedOptions.add("Medical centres on campus");
        expectedOptions.add("Rainbow students (LGBTTI+)");
        expectedOptions.add("Spiritual and religious support");
        expectedOptions.add("Support for elite student athletes");
    }

    // This method loads information from json file and checks if received testServiceList actually contains
    // some of the correct service names.
    @Test
    public void testLoadServices() throws JSONException {
        testServiceList = servicesActivity.servicesList;
        boolean test = false;
        for(int i = 0; i < this.testServiceList.size(); i++){
            HashMap<String, String> currentPaper = this.testServiceList.get(i);
            for(int j = 0; j < expectedOptions.size(); j++) {
                if (currentPaper.get("option").contains(expectedOptions.get(j))) {
                    test = true;
                }
            }
        }
        assertEquals(test, true);
    }

    @After
    public void tearDown() throws Exception {
        servicesActivity = null;
        testServiceList = null;
        fileName = null;
        option = null;
        description = null;
    }
}