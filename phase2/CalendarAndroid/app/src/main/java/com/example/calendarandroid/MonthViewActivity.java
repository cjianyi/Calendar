package com.example.calendarandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
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
    LocalDate date;
    ArrayList<Day> arr;
    MonthViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_view);
        t = findViewById(R.id.monthName);
        date = LocalDate.now();
        arr = new ArrayList<>();
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

        Calendar cal = MainActivity.currentCalendar;

        Month m = new Month(date);

        if(!months.contains(m)) {
            months.add(m);
            arr = m.getMonth();
        }
        t.setText(m.getMonthName());
        adapter = new MonthViewAdapter(this, arr);
        month.setAdapter(adapter);
    }

    public void nextMonth(View view){
        date = date.plusMonths(1);
        Month m = new Month(date);
        for(int i = 0; i <=41; i++){
            arr.set(i, m.getMonth().get(i));
        }
        adapter.notifyDataSetChanged();
        t.setText(m.getMonthName());
    }

    public void prevMonth(View view){
        date = date.minusMonths(1);
        Month m = new Month(date);
        for(int i = 0; i <=41; i++){
            arr.set(i, m.getMonth().get(i));
        }
        adapter.notifyDataSetChanged();
        t.setText(m.getMonthName());
    }


}
