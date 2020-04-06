package com.example.calendarandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MonthViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_view);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.calView);
        recyclerView.setLayoutManager(manager);
        ArrayList<Pair<String, Integer>> arr = Day.getCal();
        MonthViewAdapter adapter = new MonthViewAdapter(this, arr);
        recyclerView.setAdapter(adapter);

    }
}
