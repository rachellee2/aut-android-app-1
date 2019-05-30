package com.example.autandroidapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

//this class includes the activities of all the buttons on the homepage,
//once the buttons are pressed, the activity will be executed.
public class MainActivity extends AppCompatActivity {
    private MediaPlayer main;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(main == null) //Media players are space intensive so we only want one, create if not already created.
        {
            main = MediaPlayer.create(this,R.raw.blop_mark_diangelo);
        }
    }

    //this method includes the activity of the chatbot button on the homepage,
    //once the chatbot button is pressed, the activity will be executed.
    public void chatbotActivity(View view) {
        messagePop();
        Intent intent = new Intent(this, ChatbotActivity.class);
        startActivity(intent);
    }

    //this method includes the activity of the courses button on the homepage,
    //once the courses button is pressed, the activity will be executed.
    public void coursesActivity(View view) {
        messagePop();
        Intent intent = new Intent(this, CoursesActivity.class);
        startActivity(intent);
    }

    //this method includes the activity of the help button on the homepage,
    //once the help button is pressed, the activity will be executed.
    public void helpActivity(View view) {
        messagePop();
        Intent intent = new Intent(this, help_button.class);
        startActivity(intent);
    }

    //this method includes the activity of the services button on the homepage,
    //once the services button is pressed, the activity will be executed.
    public void servicesActivity(View view) {
        messagePop();
        Intent intent = new Intent(this, ServicesActivity.class);
        startActivity(intent);
    }

    //this method includes the activity of the calender button on the homepage,
    //once the calender button is pressed, the activity will be executed.
    public void CalendarActivity(View view) {
        messagePop();
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }

    //this method includes the activity of the calender button on the homepage,
    //once the map button is pressed, if the service is available, the activity will be executed.
    public void GoogleMapActivity(View view){
        if(isServiceAvailable()){
            messagePop();
            Intent intent = new Intent(this, MapActivity.class);
            startActivity(intent);
        }
    }

    //to play the sound
    public void messagePop()
    {
        getMain().start();
    }

    //Getter for the  media player
    public MediaPlayer getMain() {
        return main;
    }

    //checks whether the google play services is up to date and available.
    public boolean isServiceAvailable(){
        Log.d(TAG,"isServiceAvailable: checking google services verision");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if(available == ConnectionResult.SUCCESS){
            Log.d(TAG, "isServiccAvailable: Google Play services is working");
            return true;
        }
        else{
            Toast.makeText(this, "You can't make map requests", Toast. LENGTH_SHORT).show();
        }
        return false;
    }
}
