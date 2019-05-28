package com.example.autandroidapp;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * This class tests if getDegree method from CoursesActivity loads correct names of degrees for selected department.
 */
public class CoursesActivity_getDegrees_Test {
    @Rule
    public ActivityTestRule<CoursesActivity> coursesActivity_getDegrees_testActivityTestRule = new ActivityTestRule<CoursesActivity>(CoursesActivity.class);
    private CoursesActivity coursesActivity = null;
    ArrayList<String> dgrList = new ArrayList<>();
    ArrayList<String> expectedDgrList = new ArrayList<>();
    String fileName = null;

    // This method sets up the necessary date for testing. fileName is science.json and expectedDgrList contains names of degrees in science department.
    @Before
    public void setUp() throws Exception {
        fileName = "science.json";
        coursesActivity = coursesActivity_getDegrees_testActivityTestRule.getActivity();
        expectedDgrList.add("Bachelor of Business and Bachelor of Science Conjoint Degrees");
        expectedDgrList.add("Bachelor of Medical Laboratory Science");
        expectedDgrList.add("Bachelor of Science");
        expectedDgrList.add("Certificate in Anaesthetic Technology");
        expectedDgrList.add("Certificate in Applied Science");
        expectedDgrList.add("Diploma in Applied Science");
        expectedDgrList.add("Graduate Certificate in Science");
        expectedDgrList.add("Graduate Diploma in Science");
    }

    // This method checks if received dgrList actually contains all the correct degree names for science department.
    @Test
    public void testGetDegrees(){
        coursesActivity.loadDegree(fileName);
        dgrList = coursesActivity.getDegrees();
        dgrList.remove(coursesActivity.dgrPrompt);
        assertEquals(dgrList, expectedDgrList);
    }

    @After
    public void tearDown() throws Exception {
        coursesActivity = null;
        fileName = null;
        dgrList = null;
        expectedDgrList = null;
    }
}