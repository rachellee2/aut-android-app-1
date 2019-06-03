package com.example.autandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class TimetableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
    }

    //create the menu area for the home button to go
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //when home button (action_home) is pressed, the page is redirected to the homepage (MainActivity)
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_home:
                Intent homePageIntent= new Intent(this, MainActivity.class);
                this.startActivity(homePageIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void mondayActivity(View view) {
        Intent intent = new Intent(this, MondayActivity.class);
        startActivity(intent);
    }

    public void tuesdayActivity(View view) {
        Intent intent = new Intent(this, TuesdayActivity.class);
        startActivity(intent);
    }
    public void wednesdayActivity(View view) {
        Intent intent = new Intent(this, WednesdayActivity.class);
        startActivity(intent);
    }
    public void thursdayActivity(View view) {
        Intent intent = new Intent(this, ThursdayActivity.class);
        startActivity(intent);
    }
    public void fridayActivity(View view) {
        Intent intent = new Intent(this, FridayActivity.class);
        startActivity(intent);
    }

}
