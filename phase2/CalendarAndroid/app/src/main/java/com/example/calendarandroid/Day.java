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
    private static ArrayList<Pair<String, Integer>> day = getMonth(LocalDate.now());

    public Day(LocalDate date) {
        this.date = date;
        this.getMonth(date);
    }

    public static ArrayList<Pair<String, Integer>> getCal(){
        return day;
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

     private static ArrayList<Pair<String, Integer>> getMonth(LocalDate d){
        int m = d.getMonthValue();
        while (d.getDayOfMonth() > 1){
            d = d.minusDays(1);
        }
        while (m == d.getMonthValue()){
            Pair p = new Pair(d.getMonth().toString(), d.getDayOfMonth());

            day.add(p);
            d = d.plusDays(1);
        }
        return day;
    }
}
