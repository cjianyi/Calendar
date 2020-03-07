import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class EventManager {
    // A lot of these methods have an event parameter.
    // Maybe a variable keeping track of one event?

    public void createEvent(Calendar cal, String name, LocalDateTime start, LocalDateTime end) {
        Event newEvent = new Event(start, end, name);
        cal.addEvent(newEvent);
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
            case ("memo") :
                event.setMemo(new Memo(load));
                event.getMemo().addAssociate(event);
                break;
        }
    }

    public void removeFromEvent(Event event, String type, String name) {
        switch (type) {
            case ("tag") :
                event.removeTag(name);
                break;
            case ("memo") :
                event.setMemo(null);
                break;
        }
    }

    public void addAlert(Event event, String description, String date, Boolean repeat) {
        event.addAlert(new Alert(description, date, repeat));
    }

    // Instead of a frequency alert, maybe just add multiple alerts using the frequency
    public void addAlert(Event event, String description, String date, Boolean repeat, String frequency) {
        event.addAlert(new FrequencyAlert(description, date, repeat, frequency));
    }

    public void removeAlert(Event event, Alert alert) {
        event.removeAlert(alert);
    }

    public void addSeries(Event event, String name, ArrayList<Event> events) {
        event.addSeries(new Linked_series(name, events));
    }

    // Change Date to LocalDateTime
    public void addSeries(Event event, String name, Integer num_series, Date duration, String frequency) {
        event.addSeries(new Duration_series(name, num_series, duration, frequency));
    }

    public void removeSeries(Event event, Series series) {
        event.removeSeries(series);
    }


}
