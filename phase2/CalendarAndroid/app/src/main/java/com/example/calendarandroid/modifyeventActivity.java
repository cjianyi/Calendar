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


public class modifyeventActivity extends AppCompatActivity {

    private EditText new_name;
    private EditText new_start;
    private EditText new_end;
    private Button back_main;
    private Button back_edit;
    private Event e;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_event);
        new_name = findViewById(R.id.enternewstart);
        new_start = findViewById(R.id.enternewstart);
        new_end = findViewById(R.id.enternewend);
        back_main = findViewById(R.id.button8);
        back_edit = findViewById(R.id.button8);
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

            }
        };





    }
    public void set_name() {
        String name = new_name.getText().toString();
        e.setName(name);
    }

    public void set_start(){

    }

}
