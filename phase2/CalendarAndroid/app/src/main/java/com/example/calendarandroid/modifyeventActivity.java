package com.example.calendarandroid;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;


public class modifyeventActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText new_name;
    private EditText new_start;
    private EditText new_end;
    private EditText new_start_time;
    private EditText new_end_time;
    private TextView wrong_time;
    private Button back_main;
    private Button back_edit;
    private Event e;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_event);
        new_name = findViewById(R.id.enternewstart);
        new_start = findViewById(R.id.enternewstart);
        new_end = findViewById(R.id.enternewend);
        new_start_time  = findViewById(R.id.ednewstarttime);
        new_end_time = findViewById(R.id.ednewendtime);
        back_edit = findViewById(R.id.button7);
        wrong_time = findViewById(R.id.wrong_start);
        this.e = EditeventActivity.get_e();


        TextWatcher event_name = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                set_name();
            }

        };
        new_name.addTextChangedListener(event_name);

        TextWatcher start_time  = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                set_start();
            }
        };
        new_start_time.addTextChangedListener(start_time);
        new_start.addTextChangedListener(start_time);

        TextWatcher end_time = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                set_end();
            }
        };
        new_end.addTextChangedListener(end_time);
        new_end_time.addTextChangedListener(end_time);


    }
    public void set_name() {
        String name = new_name.getText().toString();
        e.setName(name);
    }

    public void set_start(){
        String start_time = new_start_time.getText().toString();
        String start_date = new_start.getText().toString();
        LocalDateTime start;
        try {
            start = LocalDateTime.parse(start_date + "T" + start_time);
            e.setStartTime(start);
        }catch (DateTimeParseException x){
            wrong_time.setText(R.string.wrong_time);
        }
    }

    public void set_end(){
        String end_time = new_end_time.getText().toString();
        String end_date = new_end.getText().toString();
        LocalDateTime end;
        try {
            end = LocalDateTime.parse(end_date + "T" + end_time);
            e.setStartTime(end);
        }catch (DateTimeParseException x){
            wrong_time.setText(R.string.wrong_time);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button7:
                Intent in = new Intent(this, EditeventActivity.class);
                startActivity(in);
        }
    }
}
