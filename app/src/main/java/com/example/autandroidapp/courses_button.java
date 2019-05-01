package com.example.autandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

    public void business_Activity(View view)
    {
        Intent intent = new Intent(this, business_button.class);
        startActivity(intent);
    }

    public void comm_Activity(View view)
    {
        Intent intent = new Intent(this, comm_button.class);
        startActivity(intent);
    }

    public void compsciActivity(View view)
    {
        Intent intent = new Intent(this, compsci_button.class);
        startActivity(intent);
    }

    public void creativetechActivity(View view)
    {
        Intent intent = new Intent(this, creativetech_button.class);
        startActivity(intent);
    }

    public void educationActivity(View view)
    {
        Intent intent = new Intent(this, education_button.class);
        startActivity(intent);
    }

    public void engineeringActivity(View view)
    {
        Intent intent = new Intent(this, engineering_button.class);
        startActivity(intent);
    }

    public void healthActivity(View view)
    {
        Intent intent = new Intent(this, health_button.class);
        startActivity(intent);
    }

    public void langActivity(View view)
    {
        Intent intent = new Intent(this, lang_button.class);
        startActivity(intent);
    }

    public void lawActivity(View view)
    {
        Intent intent = new Intent(this, law_button.class);
        startActivity(intent);
    }

    public void maoriActivity(View view)
    {
        Intent intent = new Intent(this, maori_button.class);
        startActivity(intent);
    }

    public void mathActivity(View view)
    {
        Intent intent = new Intent(this, math_button.class);
        startActivity(intent);
    }

    public void scienceActivity(View view)
    {
        Intent intent = new Intent(this, science_button.class);
        startActivity(intent);
    }

    public void socialsciActivity(View view)
    {
        Intent intent = new Intent(this, socialsci_button.class);
        startActivity(intent);
    }

    public void sportActivity(View view)
    {
        Intent intent = new Intent(this, sport_button.class);
        startActivity(intent);
    }

    public void tourismActivity(View view)
    {
        Intent intent = new Intent(this, tourism_button.class);
        startActivity(intent);
    }


}
