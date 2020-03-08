import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.*;

public class Calendar implements Comparator {
    /** The name of a calendar. */
    private String calendarName;
    /** An array list that stores all the events in a calendar. */
    private ArrayList<Event> events;
    /** An array list that stores all the alerts in a calendar. */
    private ArrayList<Alert> alerts;

    /**
     * Constructor for a calendar. Creates an empty calendar with a name.
     *
     * @param name The name of a calendar.
     */
    public Calendar (String name) {
        this.calendarName = name;
        this.events = new ArrayList<>();
        this.alerts = new ArrayList<>();
    }


    //Event editor menu
    public void addEvent(Event e, String username) {
        this.events.add(e);
        try {
            FileWriter fw = new FileWriter("src\\" + username + "calendar" + this.calendarName + ".txt");
            for (Event event : this.events) {
                fw.write(e.eventFileFormatter());
            }
            fw.close();
        }catch(IOException ex){
            System.out.println("error");
        }
    }

    /**
     * Shows all the events inside this calendar.
     *
     * @return returns a string that will contain all the event names of the events in this calendar.
     */
    public String showAllEvents() {
        String allEvents = "";
        for (Event e: events)
        {
            allEvents = allEvents + e.getName() + "\n";
        }
        return allEvents;
    }

    /**
     * Shows all the memos inside this calendar.
     *
     * @return returns a string that will contain all the memo names and memo ids that
     * are associated with the events in this calendar.
     */
    public String showAllMemos() {
        String allMemos = "";
        for (Event e: events)
        {
            for (Memo m: e.getMemos()) {
                allMemos = allMemos + m.getId() + ": " + m.getText() + "\n";
            }
        }
        return allMemos;
    }

    /**
     * Gets all the memos in this calendar.
     *
     * @return all the memos in this calendar.
     */
    public ArrayList<Memo> getMemos(){
        ArrayList<Memo> memos = new ArrayList<>();
        for (Event e: events)
        {
            memos.addAll(e.getMemos());
        }
        return memos;
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
        for (Event e: events) {
            if (inputString.equals("tag")) {
                ArrayList<String> tags = e.getTags();
                for (String tag : tags) {
                    if (tag.equals(information)) {
                        temp.add(e);
                    }
                }
            }
            else if (inputString.equals("series_name")) {
                ArrayList<Series> series = e.getSeries();
                for (Series ser: series)
                {
                    if (ser.get_event_name().equalsIgnoreCase(information)) {
                        temp.add(e);
                    }
                }
            }
            else if (inputString.equals("name")) {
                String v = e.getName();
                if (v.equalsIgnoreCase(information)) {
                    temp.add(e);
                }
            }
            else if (inputString.equals("all")) {
                return events;
            }
        }
        return temp;
    }
    //calendar should be able to return a given day.
    //public Day
    //function overload so that it deals with all the search object with dates
    public ArrayList<Event> search(String inputString, LocalDateTime date)
    {
        LocalDateTime startTime;
        LocalDateTime endTime;
        //also add sameDay
        ArrayList<Event> temp = new ArrayList<>();
        for (Event e: events) {
            startTime = e.getStartTime();
            endTime = e.getEndTime();
            if ((inputString.equals("current"))
                    && (startTime.isBefore(date) && endTime.isAfter(date)))
            {
                temp.add(e);
            }
            else if (inputString.equals("any") && (startTime.toLocalDate().isEqual(date.toLocalDate())
            || endTime.toLocalDate().isEqual(date.toLocalDate())))
            {
                temp.add(e);
            }
            else if (inputString.equals("past") && endTime.isBefore(date)) {
                temp.add(e);
            }
            else if (inputString.equals("future") && startTime.isAfter(date)) {
                temp.add(e);
            }
        }
        return temp;
    }

    /**
     * Getter that gets all the events in a calendar.
     *
     * @return a list of events
     */
    public ArrayList<Event> getEvents(){
        return this.events;
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
