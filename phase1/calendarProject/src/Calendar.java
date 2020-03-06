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

    public void addAlert(Event a) {

    }

    public void deleteAlert(Object a) {

    }

    public void editAlert(Object a) {

    }

    public String toString() {
        return "";
    }

    @Override
    public int compare(Object o1, Object o2) {
        return 0;
    }
}
