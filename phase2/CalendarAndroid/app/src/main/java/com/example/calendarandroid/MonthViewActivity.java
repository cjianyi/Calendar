package com.example.calendarandroid;

import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxItemDecoration;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import org.w3c.dom.Text;

import java.time.LocalDate;
import java.util.ArrayList;

public class MonthViewActivity extends AppCompatActivity implements MonthViewAdapter.OnDayClickListener {

    RecyclerView month;
    TextView t;

    LocalDate currentDate;
    Month currentMonth;
    MonthViewAdapter adapter;
    ArrayList<Day> currentMonthDays;
    LocalDate monthViewDate;
    Calendar currentCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_view);
        t = findViewById(R.id.monthName);
        currentDate = LocalDate.now();
        monthViewDate = LocalDate.now();
//        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
//
//        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        month = findViewById(R.id.calView);
        month.setHasFixedSize(true);
        month.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.HORIZONTAL));
        month.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

        GridLayoutManager g = new GridLayoutManager(this, 7);

        month.setLayoutManager(g);

        currentCalendar= MainActivity.currentCalendar;

        currentMonth = currentCalendar.getCurrentMonth();
        currentMonth.getMonth().get(0).addEvent(new Event("b"));
        currentMonth.getMonth().get(0).addEvent(new Event("b"));
        currentMonth.getMonth().get(0).addEvent(new Event("c"));
        currentMonth.getMonth().get(0).addEvent(new Event("d"));
        currentMonthDays = currentMonth.getMonth();
        t.setText(currentMonth.getMonthName());
        adapter = new MonthViewAdapter(this, currentMonthDays, this);
        month.setAdapter(adapter);
    }
    public void nextMonth(View view){
        monthViewDate = monthViewDate.plusMonths(1);
        currentMonth = currentCalendar.getNextMoth();
        for(int i = 0; i <=41; i++){
            currentMonthDays.set(i, currentMonth.getMonth().get(i));
        }
        adapter.notifyDataSetChanged();
        t.setText(currentMonth.getMonthName());
    }

    public void prevMonth(View view){
        monthViewDate = monthViewDate.minusMonths(1);
        currentMonth = currentCalendar.getPrevMonth();
        for(int i = 0; i <=41; i++){
            currentMonthDays.set(i, currentMonth.getMonth().get(i));
        }
        adapter.notifyDataSetChanged();
        t.setText(currentMonth.getMonthName());
    }


    @Override
    public void onDayClick(int position) {
        Log.d("day click", Integer.toString(position));
    }

    @Override
    public void onDayClick() {
        Log.d("day click", "l");
    }
}
