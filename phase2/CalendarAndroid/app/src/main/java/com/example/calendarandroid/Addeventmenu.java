package com.example.calendarandroid;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Addeventmenu extends AppCompatActivity{
    private EditText event_name;
    public static Event e;
    private EditText event_description;
    private EditText start_time;
    private EditText end_time;
    private EditText event_tags;
    private Button back;
    private Button add_event;
    private CheckBox hour;
    private CheckBox day;
    private CheckBox week;
    private CheckBox repeatDay;
    private CheckBox repeatWeek;
    private CheckBox repeatMonth;
    private CheckBox repeatYear;
    private EditText series;
    private EventManager eventManager;
    private CheckBox repeat_event;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event);
        event_name = findViewById(R.id.eventname);
        eventManager = CalendarActivity.eventManager;
        event_description = findViewById(R.id.eventtags);
        start_time = findViewById(R.id.starttime);
        end_time = findViewById(R.id.endtime);
        event_tags = findViewById(R.id.eventtags);
        back = findViewById(R.id.backbutton);
        add_event = findViewById(R.id.addeventbutton);
        hour = findViewById(R.id.hourbutton);
        day = findViewById(R.id.daybutton);
        week = findViewById(R.id.weekbutton);
        repeat_event = findViewById(R.id.repeatevent_day);
        repeatDay = findViewById(R.id.repeatevent_day);
        repeatWeek = findViewById(R.id.repreatevent_week);
        repeatMonth = findViewById(R.id.repretevent_month);
        repeatYear = findViewById(R.id.repeatevent_year);
        series = findViewById(R.id.event_series_end);

        Intent intent = new Intent(this, CalendarActivity.class);
        add_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.addeventbutton:


                        if (hour.isChecked()) {
                            add_Event("hours");

                        } else if (day.isChecked()) {
                            add_Event("days");
                        } else if (week.isChecked()) {
                            add_Event("weeks");
                        } else {
                            add_Event("None");
                        }
                        if (repeat_event.isChecked()) {


                        }case R.id.backbutton:
                        startActivity(intent);

                }


            }

        });



    }

    public void add_Event(String alerts){
        String startTime = start_time.getText().toString();
        String endTime = end_time.getText().toString();
        LocalDateTime start;
        start = LocalDateTime.parse(startTime);
        LocalDateTime end;
        end = LocalDateTime.parse(endTime);
        LocalDateTime endSeries = LocalDateTime.now();
        String name = event_name.getText().toString();
        String tags = event_tags.getText().toString();
        ArrayList<String> tags_list = new ArrayList<>(Arrays.asList(tags.split(",")));
        int repeat = 0;
        if(repeatDay.isChecked()){
            repeat = 1;
            endSeries = LocalDateTime.parse(series.getText().toString());
        }
        if(repeatWeek.isChecked()){
            repeat = 2;
            endSeries = LocalDateTime.parse(series.getText().toString());
        }
        if(repeatMonth.isChecked()){
            repeat = 3;
            endSeries = LocalDateTime.parse(series.getText().toString());
        }
        if(repeatYear.isChecked()){
            repeat = 4;
            endSeries = LocalDateTime.parse(series.getText().toString());
        }

        eventManager.createEvent(CalendarActivity.currentCalendar, name, start, end, tags_list, new ArrayList<>(), new ArrayList<>(), repeat, endSeries);
    }


}
