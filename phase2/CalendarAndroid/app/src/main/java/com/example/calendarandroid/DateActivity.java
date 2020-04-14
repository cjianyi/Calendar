package com.example.calendarandroid;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DateActivity extends AppCompatActivity {

    Calendar currentCalendar;
    LocalDateTime date;
    DateListAdopter eLAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);
        recyclerView = (RecyclerView) findViewById(R.id.event_list);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        currentCalendar = MainActivity.currentCalendar;

        // TODO: change this so date refers to whichever date was clicked on in MonthView
        date = LocalDateTime.now();


        List<Event> input = currentCalendar.search("any", date);
        // define an adapter
        eLAdapter = new DateListAdopter(input);
        recyclerView.setAdapter(eLAdapter);
    }

    public void nextDate() {
        // change the date to the next one

    }
}
