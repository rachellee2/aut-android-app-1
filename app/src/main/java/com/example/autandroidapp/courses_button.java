package com.example.autandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.example.autandroidapp.*;

public class courses_button extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_button);

        Intent intent = getIntent();
    }
    public void artActivity(View view)
    {
        Intent intent = new Intent(this, art_button.class);
        startActivity(intent);
    }

    public void architectureActivity(View view)
    {
        Intent intent = new Intent(this, architecture_button.class);
        startActivity(intent);
    }
}
