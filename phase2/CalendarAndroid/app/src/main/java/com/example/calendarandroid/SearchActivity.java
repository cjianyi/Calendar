package com.example.calendarandroid;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchActivity extends Fragment {

    TextView tag;
    TextView date;
    TextView series;
    Calendar currentCalendar;
    ArrayList<Event> results;
    RecyclerView recyclerView;
    DateListAdopter resultAdopter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_search, container, false);
        Button byTag = view.findViewById(R.id.search_tag_btn);
        Button byDate = view.findViewById(R.id.search_date_btn);
        Button bySeries = view.findViewById(R.id.search_series_btn);
        tag = view.findViewById(R.id.search_tag);
        date = view.findViewById(R.id.search_date);
        series = view.findViewById(R.id.search_series);
        byTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = tag.getText().toString();
                results = currentCalendar.search("tag", data);
                Log.d("Results",  results.toString());
                Toast.makeText(getActivity(),"Results in Console",Toast.LENGTH_SHORT).show();
            }
        });
        byDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = date.getText().toString();
                try {
                    LocalDateTime searchDate = LocalDateTime.parse(data);
                } catch (DateTimeParseException e) {
                    System.out.println("Wrong format");
                }
                results = currentCalendar.search("date", data);
                Log.d("Results",  results.toString());
                Toast.makeText(getActivity(),"Results in Console",Toast.LENGTH_SHORT).show();
            }
        });
        bySeries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = series.getText().toString();
                results = currentCalendar.search("series_name", data);
                Log.d("Results",  results.toString());
                Toast.makeText(getActivity(),"Results in Console",Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        currentCalendar = CalendarActivity.currentCalendar;


    }
}
