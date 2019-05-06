package com.example.autandroidapp;

import android.os.Looper;
import android.widget.TextView;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.*;

/**
 * Class for Testing if the message received from the server is the same one echo'd
 */
public class ChatbotActivity_Test
{
    @Rule
    public ActivityTestRule<ChatbotActivity> chatbotActivityActivityTestRule = new ActivityTestRule<ChatbotActivity>(ChatbotActivity.class);
    private ChatbotActivity chatbotActivity = null;
    TextView userMsg = null;
    TextView databaseMsg = null;
    boolean compare;

    @Before
    //This method sets up data for testing
    public void setUp() throws Exception
    {
        chatbotActivity = chatbotActivityActivityTestRule.getActivity();
        userMsg = chatbotActivity.findViewById(R.id.editText);
        databaseMsg = chatbotActivity.findViewById(R.id.textMsgField);
        Looper.prepare();
    }

    @After
    //This method frees data associated with test
    public void tearDown() throws Exception
    {
        userMsg = null;
        databaseMsg = null;
        chatbotActivity = null;
    }


    @Test
    //This method tests if the data sent and received from the database matches for the echo bot
    //Due to actionlistener not running in test after initialization this test wont run without
    //the TEST; code swapped out.
    public void sendMessage()
    {
        userMsg.setText("Hello");
        chatbotActivity.sendMessage(userMsg);
        compare = Objects.equals(userMsg.getText().toString(),databaseMsg.getText().toString());
        assertSame(compare, true);
    }
}