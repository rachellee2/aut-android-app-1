package com.example.autandroidapp;

/**
 * Class used to store user messages
 */
public class ChatbotMessage
{
    private String msgText, msgUser;// msgName;

    //blank constructor to create variables without value, needed for firebase
    public ChatbotMessage(){}

    //constructor providing both variables input
    public ChatbotMessage(String msgText, String msgUser )//String msgName)
    {
        this.msgText = msgText;
        this.msgUser = msgUser;
        //this.msgName = msgName;
    }



    //Getter and Setter for msgText
    public String getMsgText()
    {
        return msgText;
    }
    public void setMsgText(String msgText)
    {
        this.msgText = msgText;
    }

    //Getter and Setter for msgUser
    public String getMsgUser() { return msgUser; }
    public void setMsgUser(String msgUser)
    {
        this.msgUser = msgUser;
    }

    //Getter and Setter for msgName
    //public String getMsgName() { return msgName; }
    //public void setMsgName(String msgName) { this.msgName = msgName; }
}
