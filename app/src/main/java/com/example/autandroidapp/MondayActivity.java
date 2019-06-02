package com.example.autandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MondayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monday);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        menu.findItem(R.id.addclass).setVisible(true);
        menu.findItem(R.id.editcourse).setVisible(true);
        menu.findItem(R.id.deletecourse).setVisible(true);
        menu.findItem(R.id.viewcourse).setVisible(true);
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
            case R.id.addclass:
                Intent addActivityIntent= new Intent(this, AddCourseActivity.class);
                this.startActivity(addActivityIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addcourseActivity(View view) {
        Intent intent = new Intent(this, AddCourseActivity.class);
        startActivity(intent);
    }
}
