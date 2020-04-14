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
import java.util.ArrayList;

public class WeekActivity extends AppCompatActivity implements View.OnClickListener {

    LocalDate currentDate;
    TextView weekNum;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);
        currentDate = LocalDate.now();

        /*ArrayList<String> days = new ArrayList<>();
        days.add(seven days)
         */
        Button back = findViewById(R.id.backward);
        Button front = findViewById(R.id.forward);
        weekNum = findViewById(R.id.num);

        back.setOnClickListener(this);
        front.setOnClickListener(this);


        RecyclerView recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //RecyclerViewAdapter23 adapter = new RecyclerViewAdapter23(days);
        //recyclerView.setAdapter(adapter);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        int num = Integer.parseInt(String.valueOf(weekNum));

        if (v.getId() == R.id.backward)
        {
            weekNum.setText(Integer.toString(num-1));
        }
        else if (v.getId() == R.id.forward)
        {
            weekNum.setText(Integer.toString(num+1));
        }
    }
}
