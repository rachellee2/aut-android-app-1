package com.example.autandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.app.AlertDialog;
import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCourseActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editName,editCode,editLocation ,editTime;
    Button btnAddData;
    Button btnviewAll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        myDb = new DatabaseHelper(this);
        editName = (EditText)findViewById(R.id.coursename);
        editCode = (EditText)findViewById(R.id.coursecode);
        editLocation = (EditText)findViewById(R.id.courselocation);
        editTime = (EditText)findViewById(R.id.time);
        btnAddData = (Button)findViewById(R.id.addcourse);
        btnviewAll = (Button)findViewById(R.id.viewcourse);

        AddData();
        viewAll();

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

    public  void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(editName.getText().toString(),
                                editCode.getText().toString(),
                                editLocation.getText().toString(),
                                editTime.getText().toString());
                        if(isInserted == true)
                            Toast.makeText(AddCourseActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AddCourseActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewAll() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Name :"+ res.getString(0)+"\n");
                            buffer.append("Code :"+ res.getString(1)+"\n");
                            buffer.append("Location :"+ res.getString(2)+"\n");
                            buffer.append("Time :"+ res.getString(3)+"\n\n");
                        }

                        // Show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }


    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}

