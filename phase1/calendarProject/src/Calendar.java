import java.lang.reflect.Array;
import java.util.Date;
import java.util.*;

public class Calendar implements Comparator {

    private String calendarName;
    private ArrayList<Event> events;
    private ArrayList<Alert> alerts;


    public Calendar (String name) {
        this.calendarName = name;
        this.events = new ArrayList<>();
        this.alerts = new ArrayList<>();

    }
    //Event editor menu
    public void addEvent(Event e) {
        this.events.add(e);
    }
    //Event editor menu
    public void deleteEvent(Event e) {
        if (events.contains(e)) {
                this.events.remove(e);
        }
    }
    //Event editor menu
    public void editEvent(Event e) {

    }
    //String input is to see whether it is tag, memo or, date, alert, series_name, or anything.
    public ArrayList<Event> search(String inputString, String information){
        ArrayList<Event> temp = new ArrayList<>();
        String v = "";
        ArrayList<String> tags;
        ArrayList<Series> series;
        for (Event e: events) {
            if (inputString.equals("tag")) {
                tags = e.getTags();
                for (String tag : tags) {
                    if (tag.equals(information)) {
                        temp.add(e);
                    }
                }
            }
            else if (inputString.equals("series_name")) {
                series = e.getSeries();
                for (Series ser: series)
                {
                    if (ser.get_event_name().equalsIgnoreCase(information)) {
                        temp.add(e);
                    }
                }
            }
            else if (inputString.equals("name")) {
                v = e.getName();
                if (v.equalsIgnoreCase(information)) {
                    temp.add(e);
                }
            }
        }
        return temp;
    }
    //calendar should be able to return a given day.
    //public Day
    //function overload so that it deals with all the search object with dates
    public ArrayList<Event> search(String inputString, Date date)
    {
        Date startTime;
        Date endTime;
        //also add sameDay
        ArrayList<Event> temp = new ArrayList<>();
        for (Event e: events) {
            startTime = e.getStartTime();
            endTime = e.getEndTime();
            if (((inputString.equals("current")) || (inputString.equals("any")))
                    && (startTime.before(date) && endTime.after(date)))
            {
                temp.add(e);
            }
            else if (inputString.equals("past") && endTime.before(date)) {
                temp.add(e);
            }
            else if (inputString.equals("future") && startTime.after(date)) {
                temp.add(e);
            }
        }
        return temp;
    }

    //argument Alert a, not a static method
    public static void addAlert(/*String description, String date, Boolean repeat*/) {
        //Alert a = new Alert(description, date, repeat);
    }
    //argument Alert a, not static
    public static void deleteAlert() {

    }
    //argument Alert a, not a static method
    public static void editAlert() {

    }

    public String getCalendarName() {
        return this.calendarName;
    }

    public String toString() {
        //print event.date(), event, alert.date(), alert.
        return this.calendarName;
    }

    @Override
    public int compare(Object o1, Object o2) {
        return 0;
    }
}
