package com.example.autandroidapp;

import android.os.Looper;
import android.view.View;
import android.widget.TextView;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Objects;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

/**
 * Class for Testing if the message received from the server is one of the selected
 */
public class ChatbotActivity_Test
{
    @Rule
    public ActivityTestRule<ChatbotActivity> chatbotActivityActivityTestRule = new ActivityTestRule<ChatbotActivity>(ChatbotActivity.class);
    private ChatbotActivity chatbotActivity = null;
    TextView userMsg = null;
    Boolean hit;
    String[] option = {"Hi there, friend!","Hi!","Hey!","Hey there!","Good day!","Hello!","Greetings!"}; //all options from the msg hello

    @Before
    //This method sets up data for testing
    public void setUp() throws Exception
    {
        chatbotActivity = chatbotActivityActivityTestRule.getActivity();
        userMsg = chatbotActivity.findViewById(R.id.editText);
        Looper.prepare();
    }

    @After
    //This method frees data associated with test
    public void tearDown()
    {
        userMsg = null;
        chatbotActivity = null;
        hit=null;
    }


    @Test
    //This method tests if the data received from dialog flow matches any of their given response to Hello
    //COMMENT OUT FOR TEST, both thread sleeps in Chatbot Activity to run
    public void sendMessage() throws InterruptedException {
        chatbotActivity.sendMessageTest("Hello");
        sleep(3000); //to wait for the message to be received from dialogflow and inserted
        ChatMsgList res = chatbotActivity.msgList.get(chatbotActivity.msgList.size()-1);
        String result = res.getMsgContent();
        for (int i = 0;i<option.length;i++) //compare if the result matches one of the options from dialog flow
        {
            if(result.equals(option[i]))
            {
                hit= true;
            }
        }
        assertNotNull(hit);
    }
}