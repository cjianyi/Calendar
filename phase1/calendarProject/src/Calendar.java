import java.util.*;

public class Calendar implements Comparator {

    private String calendarName;
    private Map<String, ArrayList<Event>> seriesMap;
    private Map<String, ArrayList<Event>> tagMap;
    //private Map<String, ArrayList<Event>> memoMap;
    private Map<String, ArrayList<Event>> timeDayMap;
    private ArrayList<Event> events;
    private ArrayList<Alert> alerts;

    public Calendar (String name) {
        this.calendarName = name;
        this.seriesMap = new HashMap<>();
        this.tagMap = new HashMap<>();
        //this.memoMap = new HashMap<>();
        this.timeDayMap = new HashMap<>();
        this.events = new ArrayList<>();
        this.alerts = new ArrayList<>();
    }

    public void addEvent(Event e) {
        this.events.add(e);
    }

    public void deleteEvent(Event e) {
        if (events.contains(e)) {
                this.events.remove(e);
        }
    }
    public void editEvent(Event e) {

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

    public String toString() {
        //print event.date(), event, alert.date(), alert.
        return this.calendarName;
    }

    @Override
    public int compare(Object o1, Object o2) {
        return 0;
    }
}
