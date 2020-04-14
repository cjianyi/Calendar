package com.example.calendarandroid;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DateActivity extends AppCompatActivity {

    Calendar currentCalendar;
    LocalDateTime date;
    DateListAdopter eLAdapter;
    RecyclerView recyclerView;
    TextView dateText;
    TextView noEvents;
    Format formatter;

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
        dateText = (TextView) findViewById(R.id.this_date);
        noEvents = (TextView) findViewById(R.id.empty_ls);

        // TODO: change this so date refers to whichever date was clicked on in MonthView
        date = LocalDateTime.now();
        formatter = new SimpleDateFormat("MMM. dd", Locale.CANADA);
        dateText.setText(formatter.format(date));


        List<Event> input = currentCalendar.search("any", date);
        if (input.size() == 0) {
            noEvents.setVisibility(View.VISIBLE);
        } else {
            noEvents.setVisibility(View.INVISIBLE);
        }
        // define an adapter
        eLAdapter = new DateListAdopter(input);
        recyclerView.setAdapter(eLAdapter);
    }

    public void nextDate(View v) {
        // change the date to the next one
        date = date.plusDays(1);
        dateText.setText(formatter.format(date));
        List<Event> input = currentCalendar.search("any", date);
        if (input.size() == 0) {
            noEvents.setVisibility(View.VISIBLE);
        } else {
            noEvents.setVisibility(View.INVISIBLE);
        }
        eLAdapter.updateList(input);
    }

    public void previousDate(View v) {
        // change the date to the previous one
        date = date.minusDays(1);
        dateText.setText(formatter.format(date));
        List<Event> input = currentCalendar.search("any", date);
        if (input.size() == 0) {
            noEvents.setVisibility(View.VISIBLE);
        } else {
            noEvents.setVisibility(View.INVISIBLE);
        }
        eLAdapter.updateList(input);
    }

    public void backToCal(View v) {
        finish();
    }
}
