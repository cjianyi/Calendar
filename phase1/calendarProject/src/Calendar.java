import java.lang.reflect.Array;
import java.util.Date;
import java.util.*;

public class Calendar implements Comparator {

    private String calendarName;
    private Map<String, ArrayList<Event>> seriesMap;
    private Map<String, ArrayList<Event>> timeDayMap;
    private ArrayList<Event> events;
    private ArrayList<Alert> alerts;


    public Calendar (String name) {
        this.calendarName = name;
        this.seriesMap = new HashMap<>();
        this.timeDayMap = new HashMap<>();
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

            if (inputString.equals("tag")) {
                v = e.getTag();
            }
            //use start date, or end date if duration_series
            else if (inputString.equals("date")) {
                v = e.getDate();
            }
            else if (inputString.equals("series_name")) {
                v = e.getSeriesName();
            }
            else if (inputString.equals("name")) {
                v = e.getName();
            }
            /*else if (inputString.equals("past")) {
                //if ()
            }
            else if (inputString.equals("today")) {

            }
            else if (inputString.equals("future")) {

            }*/
        for (Event e: events) {
            if (v.equalsIgnoreCase(information)) {
                temp.add(e);
            }
        }
        return temp;
    }
    //calendar should be able to return a given day.
    //public Day

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
