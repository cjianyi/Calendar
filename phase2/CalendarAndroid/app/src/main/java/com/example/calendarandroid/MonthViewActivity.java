package com.example.calendarandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxItemDecoration;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import org.w3c.dom.Text;

import java.time.LocalDate;
import java.util.ArrayList;

public class MonthViewActivity extends AppCompatActivity {

    RecyclerView month;
    RecyclerView day;
    TextView t;
    ArrayList<Month> months = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_view);
        t = findViewById(R.id.monthName);
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);

        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        month = findViewById(R.id.calView);
        month.setHasFixedSize(true);
        month.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.HORIZONTAL));
        month.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

        GridLayoutManager g = new GridLayoutManager(this, 7);

        month.setLayoutManager(g);


        Month m = new Month(LocalDate.now());
        ArrayList<Day> arr = new ArrayList<>();
        if(!months.contains(m)) {
            months.add(m);
            arr = m.getMonth();
        }
        t.setText(m.getMonthName());
        MonthViewAdapter adapter = new MonthViewAdapter(this, arr);
        month.setAdapter(adapter);



    }
}
