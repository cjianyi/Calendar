package com.example.calendarandroid;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;


public class alert_modifymenu extends AppCompatActivity implements View.OnClickListener{
    private Button backed;
    private EditText alert_name;
    private EditText alert_date;
    private EditText alert_time;
    private TextView wrong_time;
    private Button set_name;
    private Button set_time;
    public static Alert a;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_modify_menu);
        backed = findViewById(R.id.backed1);
        alert_name = findViewById(R.id.edname);
        alert_date = findViewById(R.id.eddate);
        alert_time = findViewById(R.id.edtime);
        wrong_time = findViewById(R.id.wrongalerttime);
        set_name = findViewById(R.id.changalertname);
        set_time = findViewById(R.id.changealerttime);
        a = modifyalert.getter_alert();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backed1:
                Intent b = new Intent(this, EditeventActivity.class);
                startActivity(b);
            case R.id.changalertname:
                set_name();
            case R.id.changealerttime:
                set_time();
        }
    }
}
