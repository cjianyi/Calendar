import java.util.*;

public class Calendar implements Comparator {

    private String calendarName;
    private Map<String, ArrayList<Event>> seriesMap;
    private Map<String, ArrayList<Event>> tagMap;
    private Map<String, ArrayList<Event>> memoMap;

    public Calendar (String name) {
        this.calendarName = name;
        this.seriesMap = new HashMap<>();
        this.tagMap = new HashMap<>();
        this.memoMap = new HashMap<>();
    }

    public void addEvent(Event e) {

    }

    public void deleteEvent(Event e) {

    }
    public void editEvent(Event e) {

    }
    //argument Alert a, not a static method
    public static void addAlert() {

    }
    //argument Alert a, not static
    public static void deleteAlert() {

    }
    //argument Alert a, not a static method
    public static void editAlert() {

    }

    public String toString() {
        return "";
    }

    @Override
    public int compare(Object o1, Object o2) {
        return 0;
    }
}
