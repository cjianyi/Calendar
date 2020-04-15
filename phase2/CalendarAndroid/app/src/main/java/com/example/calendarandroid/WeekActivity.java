package com.example.calendarandroid;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Locale;

public class WeekActivity extends AppCompatActivity implements View.OnClickListener {

    LocalDate currentDat;
    TextView weekNum;
    TextView yearNum;
    TextView mon, tue, wed, thu, fri, sat, sun;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);
        currentDat = LocalDate.now();

        /*ArrayList<String> days = new ArrayList<>();
        days.add(seven days)
         */
        Button back = findViewById(R.id.backward);
        Button front = findViewById(R.id.forward);
        weekNum = findViewById(R.id.num);
        yearNum = findViewById(R.id.year);
        yearNum.setText(currentDat.getYear());
        int weekN = currentDat.get(WeekFields.of(Locale.US).weekOfWeekBasedYear());
        weekNum.setText(weekN);
        back.setOnClickListener(this);
        front.setOnClickListener(this);

        mon = findViewById(R.id.mon);
        tue = findViewById(R.id.tue);
        wed = findViewById(R.id.wed);
        thu = findViewById(R.id.thu);
        fri = findViewById(R.id.fri);
        sat = findViewById(R.id.sat);
        sun = findViewById(R.id.sun);
        mon.setOnClickListener(this);
        tue.setOnClickListener(this);
        wed.setOnClickListener(this);
        thu.setOnClickListener(this);
        fri.setOnClickListener(this);
        sat.setOnClickListener(this);
        sun.setOnClickListener(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //RecyclerViewAdapter23 adapter = new RecyclerViewAdapter23(days);
        //recyclerView.setAdapter(adapter);
    }

    public String intenseAlgorithms(LocalDate ld, int i) {
        String temp="";
        System.out.println(ld.with(WeekFields.of(Locale.US).dayOfWeek(), i));
        /*
        for (Event e: events)
        {
            if (e.startTime > startDate)
                temp += e.name() + " " + e.date();
         */
        return temp;
    }


    @Override
    public void onClick(View v) {
        //int num = Integer.parseInt(String.valueOf(weekNum));

        if (v.getId() == R.id.backward)
        {
            currentDat.minusWeeks(1);
        }
        else if (v.getId() == R.id.forward)
        {
            currentDat.plusWeeks(1);
        }
        yearNum.setText(currentDat.getYear());
        int weekN = currentDat.get(WeekFields.of(Locale.US).weekOfWeekBasedYear());
        weekNum.setText(weekN);
        for (int i = 1; i < 8; i++)
        {
            String temp = intenseAlgorithms(currentDat, i);
            if (i == 1)
            {
                mon.setText(temp);
            }
            if(i == 2)
            {
                tue.setText(temp);
            }
            if (i == 3)
            {
                wed.setText(temp);
            }
            if (i == 4)
            {
                thu.setText(temp);
            }
            if(i == 5)
            {
                fri.setText(temp);
            }
            if(i == 6)
            {
                sat.setText(temp);
            }
            if(i == 7)
            {
                sun.setText(temp);
            }
        }
    }
}
