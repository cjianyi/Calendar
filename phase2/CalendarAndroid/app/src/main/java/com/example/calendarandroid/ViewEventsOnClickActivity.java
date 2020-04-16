package com.example.calendarandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class ViewEventsOnClickActivity extends AppCompatActivity implements ViewEventsAdapter.OnEventClickListener {

    RecyclerView r;
    LinearLayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_events_on_click);

        r = findViewById(R.id.eventViewingRecycler);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

    }

    @Override
    public void onEventClick(int position) {
        //start activity to view individual event
    }
}
