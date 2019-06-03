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
 * This class tests if the loadPaperInformation method from CourseActivity loads correct papers from selected degree.
 */
public class CoursesActivity_loadPaperInformation_Test {
    @Rule
    public ActivityTestRule<CoursesActivity> coursesActivity_getDegrees_testActivityTestRule = new ActivityTestRule<CoursesActivity>(CoursesActivity.class);
    private CoursesActivity coursesActivity = null;
    ArrayList<HashMap<String, String>> papersList;
    String fileName, degreeName, expectedPaper;

    // This method sets up the necessary data for testing. degreeName is BCIS and expectedPaper contains a paper that is only offered in BCIS.
    @Before
    public void setUp() throws Exception {
        coursesActivity = coursesActivity_getDegrees_testActivityTestRule.getActivity();
        fileName = "Engineering, computer and mathematical sciences.json";
        degreeName = "Bachelor of Computer and Information Sciences";
        expectedPaper = "COMP706";
        coursesActivity.loadDegree(fileName);
        papersList = new ArrayList<HashMap<String, String>>();
    }

    // This method checks if received paperList actually contains expected paper "COMP706".
    @Test
    public void testLoadPapers(){
        boolean test = false;
        papersList = coursesActivity.loadPaperInformation(degreeName);
        for(int i = 0; i < this.papersList.size(); i++){
            HashMap<String, String> currentPaper = this.papersList.get(i);
            if (currentPaper.get("paper_code").equalsIgnoreCase(expectedPaper)) {
                test = true;
            }
        }
        assertEquals(test, true);
    }

    @After
    public void tearDown() throws Exception {
        coursesActivity = null;
        papersList = null;
        expectedPaper = null;
        fileName = null;
        degreeName = null;
    }
}