package com.example.autandroidapp;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

/**
 * View recycler for chat messages
 */
public class ChatbotRecycler extends RecyclerView.ViewHolder
{
    TextView leftText, rightText;

    //creating the item views for the recycler
    public ChatbotRecycler(View itemView)
    {
        super(itemView);
        leftText = (TextView)itemView.findViewById(R.id.leftText);
        rightText = (TextView)itemView.findViewById(R.id.rightText);
    }
}
