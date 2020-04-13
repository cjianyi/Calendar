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

import org.w3c.dom.Text;


public class EditeventActivity extends AppCompatActivity{
    private  EditText event_name;
    public static Event e;
    private TextView event_message;
    private Button back_b;
    private Button event_b;
    private Button tag_b;
    private Button alert_b;
    private Button series_b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_event);
        event_name = findViewById(R.id.editeventname);
        event_message = findViewById(R.id.event_message);
        back_b = findViewById(R.id.button);
        event_b = findViewById(R.id.button2);
        tag_b = findViewById(R.id.button3);
        alert_b = findViewById(R.id.button4);
        series_b = findViewById(R.id.button5);


        TextWatcher name_watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (matched_name()) {
                    event_message.setText(R.string.wrong_event_name);
                } else {
                    event_message.setText(R.string.correct_event_name);
                }
            }
        };
        event_name.addTextChangedListener(name_watcher);
    }



    public boolean matched_name(){
        String name = event_name.getText().toString();
        boolean flag = false;
        for (Event e : MainActivity.currentCalendar.getEvents()){
            if (e.getName().equals(name)) {
                this.e = e;
                flag = true;
                break;
            }
        }
        if (flag == false) {
            this.e = null;
        }
        return (this.e.equals(null));
    }

    public static Event get_e(){
        return e;
    }



}
