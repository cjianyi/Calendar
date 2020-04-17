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


public class Creationalertmenu extends AppCompatActivity implements View.OnClickListener {

    public static Event e;
    private Button backed;
    private EditText alert_name;
    private EditText date;
    private EditText time;
    private EditText frequency;
    private TextView wrong_time;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creationalert);
        backed = findViewById(R.id.backed1);
        alert_name = findViewById(R.id.alertname);
        date = findViewById(R.id.eddate);
        time = findViewById(R.id.edtime);
        frequency = findViewById(R.id.frequencyentered);
        wrong_time = findViewById(R.id.wrongalerttime);
        e  = EditeventActivity.get_e();
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backed1:
                Intent intent = new Intent(this, EditeventActivity.class);
                startActivity(intent);
            case R.id.createalert:
                String name = alert_name.getText().toString();
                String f = frequency.getText().toString();
                if (!((f == "1") | (f== "2")| (f == "3") | (f == "4"))){
                    LocalDateTime t = (LocalDateTime) set_time();
                    if (t != null){
                        Alert a = new Alert(name, t);
                        e.addAlert(a);
                    }
                }else{
                    set_f();
                }
        }
    }


    public Object set_time(){
        String start_time = time.getText().toString();
        String start_date = date.getText().toString();
        LocalDateTime start;
        try {
            start = LocalDateTime.parse(start_date + "T" + start_time);
            return start;
        }catch (DateTimeParseException x){
            wrong_time.setText(R.string.wrong_time);
            return null;
        }
    }

    public void set_f(){
        String name = alert_name.getText().toString();
        String f = frequency.getText().toString();
        LocalDateTime t = (LocalDateTime) set_time();
        switch (f){
            case "1":
                while(t.isBefore(e.getEndTime())|| t.toLocalDate().equals(e.getEndTime().toLocalDate())){
                    Alert alert = new Alert(name, t);
                    e.addAlert(alert);
                    t = t.plusDays(1);
                }
                break;
            case "2":
                while(t.isBefore(e.getEndTime())|| t.toLocalDate().equals(e.getEndTime().toLocalDate())){
                    Alert alert = new Alert(name, t);
                    e.addAlert(alert);
                    t = t.plusDays(7);
                }
                break;
            case "3":
                while(t.isBefore(e.getEndTime())|| t.toLocalDate().equals(e.getEndTime().toLocalDate())){
                    Alert alert = new Alert(name, t);
                    e.addAlert(alert);
                    t = t.plusMonths(1);
                }
                break;
            case "4":
                while(t.isBefore(e.getEndTime())|| t.toLocalDate().equals(e.getEndTime().toLocalDate())){
                    Alert alert = new Alert(name, t);
                    e.addAlert(alert);
                    t = t.plusYears(1);
                }
                break;
        }
    }
}
