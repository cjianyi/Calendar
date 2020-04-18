package com.example.calendarandroid;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import org.w3c.dom.Text;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.chip.ChipGroup;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.ParseRelation;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.SaveCallback;

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
    private EventManager eventManager;
    private CheckBox repeat_event;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event);
        event_name = findViewById(R.id.eventname);
        eventManager = CalendarActivity.eventManager;
        event_description = findViewById(R.id.eventdescription);
        start_time = findViewById(R.id.starttime);
        end_time = findViewById(R.id.endtime);
        event_tags = findViewById(R.id.eventtags);
        back = findViewById(R.id.backbutton);
        add_event = findViewById(R.id.addeventbutton);
        hour = findViewById(R.id.hourbutton);
        day = findViewById(R.id.daybutton);
        week = findViewById(R.id.weekbutton);
        repeat_event = findViewById(R.id.repeatevent);

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

        String name = event_name.getText().toString();
        String tags = event_tags.getText().toString();
        ArrayList<String> tags_list = new ArrayList<>(Arrays.asList(tags.split(",")));
        eventManager.createEvent(CalendarActivity.currentCalendar, name, start, end, tags_list, null, null, 0, null);
    }


}
