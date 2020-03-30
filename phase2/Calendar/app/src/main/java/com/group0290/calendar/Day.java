package com.group0290.calendar;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Collections;

public class Day implements Comparable<Day>{
    private LocalDate date;
    private ArrayList<Event> events;
    private ArrayList<Alert> alerts;

    public Day(LocalDate date) {
        this.date = date;
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
