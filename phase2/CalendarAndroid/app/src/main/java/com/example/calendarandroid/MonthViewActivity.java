package com.example.calendarandroid;

import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxItemDecoration;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.ParseUser;

import org.w3c.dom.Text;

import java.time.LocalDate;
import java.util.ArrayList;

public class MonthViewActivity extends MenuActivity implements MonthViewAdapter.OnDayClickListener {

    RecyclerView month;
    TextView t;

    LocalDate currentDate;
    Month currentMonth;
    MonthViewAdapter adapter;
    ArrayList<Day> currentMonthDays;
    LocalDate monthViewDate;
    Calendar currentCalendar;
    int whichCal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // set up view with menu bar
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        navigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);


        t = findViewById(R.id.monthName);
        whichCal = 1;
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
        currentMonthDays = new ArrayList<>();
        ArrayList<Day> day = new ArrayList<>();
        LocalDate dd = LocalDate.now();
        Day d1 = new Day(dd);
        Day d2 = new Day(dd.plusDays(1));
        Day d3 = new Day(dd.plusDays(2));
        d1.addEvent(new Event("a"));
        d1.addEvent(new Event("b"));
        d1.addEvent(new Event("c"));
        day.add(d1);
        day.add(d2);
        day.add(d3);


        currentMonth.getMonth().get(0).addEvent(new Event("b"));
        currentMonth.getMonth().get(0).addEvent(new Event("b"));
        currentMonth.getMonth().get(0).addEvent(new Event("c"));
        currentMonth.getMonth().get(0).addEvent(new Event("d"));
        for(int i = 0; i <=41; i++){
            currentMonthDays.add(currentMonth.getMonth().get(i));
        }

        t.setText(currentMonth.getMonthName());
        adapter = new MonthViewAdapter(this, currentMonthDays, this);
        month.setAdapter(adapter);
    }
    public void nextMonth(View view){
        monthViewDate = monthViewDate.plusMonths(1);
        currentMonth = currentMonth.getNext();
        currentMonthDays.clear();
        for(int i = 0; i <=41; i++){
            currentMonthDays.add(currentMonth.getMonth().get(i));
        }

        adapter.notifyDataSetChanged();
        t.setText(currentMonth.getMonthName() + Integer.toString(currentMonth.getMonth().get(0).getDay().getYear()));
    }

    public void prevMonth(View view){
        monthViewDate = monthViewDate.minusMonths(1);
        currentMonth = currentMonth.getPrev();
        if(currentMonth == null){
            Log.d("null", "why :(");
        }
        currentMonthDays.clear();
        for(int i = 0; i <=41; i++){
            currentMonthDays.add(currentMonth.getMonth().get(i));
        }
        adapter.notifyDataSetChanged();
        t.setText(currentMonth.getMonthName() + Integer.toString(currentMonth.getMonth().get(0).getDay().getYear()));
    }


    @Override
    public void onDayClick(int position) {
        Log.d("day click", Integer.toString(position));
        Intent intent = new Intent(this, ViewEventsOnClickActivity.class);
        Day d = this.currentMonthDays.get(position);
        int len = d.getEvents().size();
        ArrayList<Event> events = d.getEvents();
        String[] eventNames = new String[len];
        String[] eventStartDate = new String[len];
        String[] eventEndDate = new String[len];
        String calName = ParseUser.getCurrentUser().getUsername() + this.whichCal;
        for(int i = 0; i < len; i++){
            eventNames[i] = events.get(i).getName();
            eventStartDate[i] = events.get(i).getStartTime().toString();
            eventEndDate[i] = events.get(i).getEndTime().toString();
        }
        intent.putExtra("name", eventNames);
        intent.putExtra("startDate", eventStartDate);
        intent.putExtra("endDate", eventEndDate);
        intent.putExtra("calendar", calName);
        startActivity(intent);


    }

    public void setCal(int i){
        this.whichCal = i;
    }

//    @Override
//    public void onDayClick() {
//        Log.d("day click", "l");
//    }

    public void logout(View view){
        ParseUser.logOut();
        finish();
    }

    public void createEvent(View view){
        Intent intent = new Intent(this, Addeventmenu.class);
        startActivity(intent);
    }

    @Override
    int getContentViewId() {
        // set to this activity's layout
        return R.layout.activity_month_view;
    }

    @Override
    int getNavigationMenuItemId() {
        // the id of the menu button
        return R.id.action_month;
    }

}
