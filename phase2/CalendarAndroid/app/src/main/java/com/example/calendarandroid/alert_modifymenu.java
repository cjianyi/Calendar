package com.example.calendarandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;


public class alert_modifymenu extends AppCompatActivity{
    private Button backed;
    private EditText alert_name;
    private EditText alert_date;
    private EditText alert_time;
    private TextView wrong_time;
    public static Alert a;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_modify_menu);
        backed = findViewById(R.id.backed1);
        alert_name = findViewById(R.id.edname);
        alert_date = findViewById(R.id.eddate);
        alert_time = findViewById(R.id.edtime);
        wrong_time = findViewById(R.id.wrongalerttime);
        a = modifyalert.getter_alert();
        TextWatcher name = new TextWatcher() {
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
        alert_name.addTextChangedListener(name);

        TextWatcher time = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                set_time();
            }
        };
        alert_date.addTextChangedListener(time);
        alert_time.addTextChangedListener(time);
    }



    public void set_name(){
        String des = alert_name.getText().toString();
        a.editAlert(des);
    }



    public void set_time(){
        String start_time = alert_time.getText().toString();
        String start_date = alert_date.getText().toString();
        LocalDateTime start;
        try {
            start = LocalDateTime.parse(start_date + "T" + start_time);
            a.editDate(start);
        }catch (DateTimeParseException x){
            wrong_time.setText(R.string.wrong_time);
        }
    }

}
