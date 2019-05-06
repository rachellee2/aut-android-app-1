package com.example.autandroidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.view.View;
import android.widget.TextView;

/**
 * This class creates a connection between the Firebase database and the user, it takes input
 * that the user submits and sends it to the database, the changed data is then echoed back to the
 * user. This class has a constructor purely for testing the database connection.
 */
public class ChatbotActivity extends AppCompatActivity
{
    private DatabaseReference databaseReference; //Firebase reference

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                Intent homePageIntent = new Intent(this, MainActivity.class);
                this.startActivity(homePageIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);
        Intent intent = getIntent();

        //Creates a path in Firebase called Message
        databaseReference = FirebaseDatabase.getInstance().getReference("Message");

        //sends default msg to server
        databaseReference.setValue("Chat Bot Message Will Appear Here"); //SWAP TO TEST;
        //TEST; FOR TESTING AS LISTENER ISNT CALLED DURING TEST
        //databaseReference.setValue("Hello"); //TEST;

        //textMsgField is where the bots msg echos to
        final TextView msg = findViewById(R.id.textMsgField);

        //Change in the Firebase database on path Message will cause an event
        databaseReference.addValueEventListener(new ValueEventListener()
        {
            //If change occurs in the database the textview will be updated
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                msg.setText(dataSnapshot.getValue().toString());
            }
            //If change is cancelled in the database the textview will be updated
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                msg.setText("CANCELLED");
            }
        });
    }

    /**
     *    If user sends a message the text will be retrieved from the editor and sent to the database
     * @param view, text view that the user is editing
     */
    public void sendMessage(View view) {
        EditText userEdited = findViewById(R.id.editText);
        databaseReference.setValue(userEdited.getText().toString());
    }
}
