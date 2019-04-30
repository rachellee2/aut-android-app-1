package com.example.autandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

//this class includes the activities of all the buttons on the homepage,
//once the buttons are pressed, the activity will be executed.
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //this method includes the activity of the chatbot button on the homepage,
    //once the chatbot button is pressed, the activity will be executed.
    public void chatbotActivity(View view)
    {
        Intent intent = new Intent(this, chatbot_button.class);
        startActivity(intent);
    }
    //this method includes the activity of the courses button on the homepage,
    //once the courses button is pressed, the activity will be executed.
    public void coursesActivity(View view)
    {
        Intent intent = new Intent(this, courses_button.class);
        startActivity(intent);
    }

    //this method includes the activity of the help button on the homepage,
    //once the help button is pressed, the activity will be executed.
    public void helpActivity(View view)
    {
        Intent intent = new Intent(this, help_button.class);
        startActivity(intent);
    }

    //this method includes the activity of the services button on the homepage,
    //once the services button is pressed, the activity will be executed.
    public void servicesActivity(View view)
    {
        Intent intent = new Intent(this, services_button.class);
        startActivity(intent);
    }

    //this method includes the activity of the map button on the homepage,
    //once the map button is pressed, the activity will be executed.
    public void MapActivity(View view)
    {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }

    //this method includes the activity of the calender button on the homepage,
    //once the calender button is pressed, the activity will be executed.
    public void CalendarActivity(View view)
    {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }

}
