package com.example.calendarandroid;

import android.icu.util.LocaleData;


import androidx.core.util.Pair;

import java.time.Month;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Collections;

public class Day  implements Comparable<Day>{
    private LocalDate date;
    private ArrayList<Event> events;
    private ArrayList<Alert> alerts;
    private String name;
    private String month;


    public Day(LocalDate date) {
        this.date = date;
        this.name = date.getDayOfWeek().name();
        this.month = Integer.toString(date.getDayOfMonth());

    }

    public String getDayName(){
        return this.name;
    }

    public int getWeekNum(){
        return this.date.getDayOfWeek().getValue();
    }

    public String getMonthDayNumber(){
        return this.month;
    }



    public void addEvent(Event event) {
        this.events.add(event);
        Collections.sort(this.events);
    }
    public void removeEvent(Event event) {
        this.events.remove(event);
    }
    public void addAlert(Alert alert) {
        this.alerts.add(alert);
    }
    public void removeAlert(Alert alert) {
        this.alerts.remove(alert);
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public ArrayList<Alert> getAlerts() {
        return alerts;
    }

    @Override
    public int compareTo(Day other) {
        return this.date.compareTo(other.date);
    }


}
