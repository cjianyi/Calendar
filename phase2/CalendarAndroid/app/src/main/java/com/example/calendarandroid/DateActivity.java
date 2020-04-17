package com.example.calendarandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DateActivity extends Fragment {

    Calendar currentCalendar;
    LocalDateTime date;
    DateListAdopter eLAdapter;
    RecyclerView recyclerView;
    TextView dateText;
    TextView noEvents;
    Format formatter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(getContentViewId());
//        navigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
//        navigationView.setOnNavigationItemSelectedListener(this);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_daily, container, false);
        Button fwd = view.findViewById(R.id.next_date);
        fwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // change the date to the next one
                date = date.plusDays(1);
                dateText.setText(formatter.format(date));
                List<Event> input = currentCalendar.search("any", date);
                if (input.size() == 0) {
                    noEvents.setVisibility(View.VISIBLE);
                } else {
                    noEvents.setVisibility(View.INVISIBLE);
                }
                eLAdapter.updateList(input);

            }
        });
        Button back = view.findViewById(R.id.prev_date);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // change the date to the previous one
                date = date.minusDays(1);
                dateText.setText(formatter.format(date));
                List<Event> input = currentCalendar.search("any", date);
                if (input.size() == 0) {
                    noEvents.setVisibility(View.VISIBLE);
                } else {
                    noEvents.setVisibility(View.INVISIBLE);
                }
                eLAdapter.updateList(input);
            }
        });


        return inflater.inflate(R.layout.activity_daily, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.event_list);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        currentCalendar = CalendarActivity.currentCalendar;
        dateText = (TextView) view.findViewById(R.id.this_date);
        noEvents = (TextView) view.findViewById(R.id.empty_ls);

        date = LocalDateTime.now();

        formatter = new SimpleDateFormat("MMM. dd", Locale.CANADA);
        dateText.setText(date.format(DateTimeFormatter.ofPattern("MMM. dd", Locale.CANADA)));


        List<Event> input = currentCalendar.search("any", date);
        if (input.size() == 0) {
            noEvents.setVisibility(View.VISIBLE);
        } else {
            noEvents.setVisibility(View.INVISIBLE);
        }
//        // define an adapter
        eLAdapter = new DateListAdopter(new ArrayList<Event>());
        recyclerView.setAdapter(eLAdapter);

    }
    public void nextDate(View v) {
        // change the date to the next one
        date = date.plusDays(1);
        dateText.setText(formatter.format(date));
        List<Event> input = currentCalendar.search("any", date);
        if (input.size() == 0) {
            noEvents.setVisibility(View.VISIBLE);
        } else {
            noEvents.setVisibility(View.INVISIBLE);
        }
        eLAdapter.updateList(input);
    }

    public void previousDate(View v) {
        // change the date to the previous one
        date = date.minusDays(1);
        dateText.setText(formatter.format(date));
        List<Event> input = currentCalendar.search("any", date);
        if (input.size() == 0) {
            noEvents.setVisibility(View.VISIBLE);
        } else {
            noEvents.setVisibility(View.INVISIBLE);
        }
        eLAdapter.updateList(input);
    }

    public void backToCal(View v) {
        getActivity().finish();
    }

//    @Override
//    int getContentViewId() {
//        return R.layout.activity_daily;
//    }
//
//    @Override
//    int getNavigationMenuItemId() {
//        return R.id.action_day;
//    }

}
