package com.example.autandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class faq_button extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq_button);

        onBClick();
        Intent intent = getIntent();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //when home button (action_home) is pressed, the page is redirected to the homepage (MainActivity)
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                Intent homePageIntent = new Intent(this, MainActivity.class);
                this.startActivity(homePageIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    public void onBClick() {
        findViewById(R.id.q1).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                (findViewById(R.id.a1)).setVisibility(((findViewById(R.id.a1)).getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);
            }
        });

        findViewById(R.id.q2).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                (findViewById(R.id.a2)).setVisibility(((findViewById(R.id.a2)).getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);
            }
        });
        findViewById(R.id.q3).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                (findViewById(R.id.a3)).setVisibility(((findViewById(R.id.a3)).getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);
            }
        });
        findViewById(R.id.q4).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                (findViewById(R.id.a4)).setVisibility(((findViewById(R.id.a4)).getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);
            }
        });

        findViewById(R.id.q5).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                (findViewById(R.id.a5)).setVisibility(((findViewById(R.id.a5)).getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);
            }
        });

        findViewById(R.id.q6).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                (findViewById(R.id.a6)).setVisibility(((findViewById(R.id.a6)).getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);
            }
        });
        findViewById(R.id.q7).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                (findViewById(R.id.a7)).setVisibility(((findViewById(R.id.a7)).getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);
            }
        });
        findViewById(R.id.q8).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                (findViewById(R.id.a8)).setVisibility(((findViewById(R.id.a8)).getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);
            }

        });
        findViewById(R.id.q9).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                (findViewById(R.id.a9)).setVisibility(((findViewById(R.id.a9)).getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);
            }

        });
        findViewById(R.id.q10).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                (findViewById(R.id.a10)).setVisibility(((findViewById(R.id.a10)).getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);
            }

        });
        findViewById(R.id.q11).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                (findViewById(R.id.a11)).setVisibility(((findViewById(R.id.a11)).getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);
            }

        });
        findViewById(R.id.q12).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                (findViewById(R.id.a12)).setVisibility(((findViewById(R.id.a12)).getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);
            }

        });
        findViewById(R.id.q13).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                (findViewById(R.id.a13)).setVisibility(((findViewById(R.id.a13)).getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);
            }

        });
        findViewById(R.id.q14).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                (findViewById(R.id.a14)).setVisibility(((findViewById(R.id.a14)).getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);
            }

        });
        findViewById(R.id.q15).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                (findViewById(R.id.a15)).setVisibility(((findViewById(R.id.a15)).getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);
            }

        });
        findViewById(R.id.q16).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                (findViewById(R.id.a16)).setVisibility(((findViewById(R.id.a16)).getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);
            }

        });
        findViewById(R.id.q17).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                (findViewById(R.id.a17)).setVisibility(((findViewById(R.id.a17)).getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);
            }

        });
    }
}
