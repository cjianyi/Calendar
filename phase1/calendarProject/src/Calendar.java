import java.util.*;

public class Calendar {

    private String calendarName;
    private Map<String, ArrayList<Object>> seriesMap;
    private Map<String, ArrayList<Object>> tagMap;
    private Map<String, ArrayList<Object>> memoMap;

    public Calendar (String name) {
        this.calendarName = name;
        this.seriesMap = new HashMap<>();
        this.tagMap = new HashMap<>();
        this.memoMap = new HashMap<>();
    }

    public void addEvent() {
    }

    public void deleteEvent() {

    }
    public void editEvent() {

    }

    public void addAlert() {

    }

    public void deleteAlert() {

    }

    public void editAlert() {

    }
}
