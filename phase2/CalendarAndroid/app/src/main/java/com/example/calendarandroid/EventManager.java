package com.example.calendarandroid;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class EventManager {
    // A lot of these methods have an event parameter.
    // Maybe a variable keeping track of one event?

    // too many variables. Could probably move some up or down hierarchy
    public void createEvent(Calendar cal, String user, String name, LocalDateTime start, LocalDateTime end,
                            ArrayList<String> tags, ArrayList<Alert> alerts, ArrayList<String> series,
                            int duration, LocalDateTime endSeriesTime) {
        if (duration == 0){
            Event newEvent = new Event(start, end, name, tags, alerts, series);
            cal.addEvent(newEvent, user);
        }else {
            ArrayList<Event> eventList = new ArrayList<>();
            Duration_series durationSeries = new Duration_series(series.get(0), 0, start,
                    endSeriesTime, 0, eventList);
            switch (duration) {
                case 1:
                    while (start.toLocalDate().isBefore(end.toLocalDate()) || start.toLocalDate().isEqual(end.toLocalDate())) {
                        Event event = new Event(start, endSeriesTime, name, tags, alerts, series);
                        cal.addEvent(event, user);
                        durationSeries.addEvent(event);
                        start = start.plusDays(1);
                        System.out.println(start);
                        endSeriesTime = endSeriesTime.plusDays(1);
                    }
                    break;
                case 2:
                    while (start.toLocalDate().isBefore(end.toLocalDate()) || start.toLocalDate().isEqual(end.toLocalDate())) {
                        Event event = new Event(start, endSeriesTime, name, tags, alerts, series);
                        cal.addEvent(event, user);
                        durationSeries.addEvent(event);
                        start = start.plusDays(7);
                        endSeriesTime = endSeriesTime.plusDays(7);
                    }
                    break;
                case 3:
                    while (start.toLocalDate().isBefore(end.toLocalDate()) || start.toLocalDate().isEqual(end.toLocalDate())) {
                        Event event = new Event(start, endSeriesTime, name, tags, alerts, series);
                        cal.addEvent(event, user);
                        durationSeries.addEvent(event);
                        start = start.plusMonths(1);
                        endSeriesTime = endSeriesTime.plusMonths(1);
                    }
                    break;
                case 4:
                    while (start.toLocalDate().isBefore(end.toLocalDate()) || start.toLocalDate().isEqual(end.toLocalDate())) {
                        Event event = new Event(start, endSeriesTime, name, tags, alerts, series);
                        cal.addEvent(event, user);
                        durationSeries.addEvent(event);
                        start = start.plusYears(1);
                        endSeriesTime = endSeriesTime.plusYears(1);
                    }
                    break;
            }
            cal.addSeries(durationSeries);
        }
    }

    // Need way of getting optional parameters (Builder?)
    public void createEvent() {

    }

    public void deleteEvent(Calendar cal, Event event) {
        cal.deleteEvent(event);
    }

    // Load is kinda dependant on controller (Might want to split up method)
    public void addToEvent(Event event, String type, String load) {
        switch (type) {
            case ("startTime") :
                // Datetime strings are formatted as 2007-12-03T10:15:30 (for example)
                event.setStartTime(LocalDateTime.parse(load));
                break;
            case ("endTime") :
                event.setEndTime(LocalDateTime.parse(load));
                break;
            case ("name") :
                event.setName(load);
                break;
            case ("tag") :
                event.addTag(load);
                break;
        }
    }

    public void removeTag(Event event, String tag) {
        event.removeTag(tag);
    }

    // Frequency should affect how many alerts are added...
    public void addAlert(Event event, String description, LocalDateTime date, String frequency) {
        event.addAlert(new Alert(description, date));
    }

    public void removeAlert(Event event, Alert alert) {
        event.removeAlert(alert);
    }

    public void addSeries(Event event, String name, ArrayList<Event> events) {
        event.addSeries(name);
    }



    public void removeSeries(Event event, String name) {
        event.removeSeries(name);
    }


}
