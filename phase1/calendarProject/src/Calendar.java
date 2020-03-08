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

    private void loadEvents(){
        
    }


    //Event editor menu
    public void addEvent(Event e, String username) {
        this.events.add(e);
        try {
            FileWriter fw = new FileWriter("src\\" + username + "calendar" + this.calendarName + ".txt");
            fw.write("[");
            for (Event event : this.events) {
                fw.write(e.eventFileFormatter());
                if(!event.equals(this.events.get(this.events.size() - 1))){
                    fw.write(",\n");
                }

            }

            fw.write("]");
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

    public String getAllEventNames() {
        String allEventNames = "";
        for (Event e: events)
        {
            allEventNames += e.getName() + "\n";
        }
        return allEventNames;
    }
    //Event editor menu
    public void editEvent(Event e) {

    }

    public boolean isEventTagged (Event e, String info)
    {
        for (String tag: e.getTags())
        {
            if (tag.equals(info))
            {
                return true;
            }
        }
        return false;
    }

    public boolean isEventInSeries (Event e, String info)
    {
        for (Series ser: e.getSeries())
        {
            if (ser.get_event_name().equalsIgnoreCase(info))
            {
                return true;
            }
        }
        return false;
    }

    public boolean isEventNameEqual (Event e, String info)
    {
        return e.getName().equalsIgnoreCase(info);
    }

    public ArrayList<Event> search(String input, String info){
        ArrayList<Event> temp = new ArrayList<>();
        if (input.equals("all"))
        {
            return events;
        }
        for (Event e: events) {
            if ((input.equals("tag") && isEventTagged(e, info)) ||
                    (input.equals("series_name") && isEventInSeries(e, info)) ||
                        (input.equals("name") && isEventNameEqual(e, info))) {
                            temp.add(e);
            }
        }
        return temp;
    }

    public boolean isEventCurrent (LocalDateTime startTime, LocalDateTime endTime, LocalDateTime date)
    {
        return startTime.isBefore(date) && endTime.isAfter(date);
    }

    public boolean isEventAny (LocalDateTime startTime, LocalDateTime endTime, LocalDateTime date)
    {
        return startTime.toLocalDate().isEqual(date.toLocalDate()) || endTime.toLocalDate().isEqual(date.toLocalDate());
    }

    public boolean isEventPast (LocalDateTime endTime, LocalDateTime date)
    {
        return endTime.isBefore(date);
    }

    public boolean isEventFuture (LocalDateTime startTime, LocalDateTime date)
    {
        return startTime.isAfter(date);
    }

    public ArrayList<Event> search(String input, LocalDateTime date)
    {
        LocalDateTime startTime;
        LocalDateTime endTime;
        ArrayList<Event> temp = new ArrayList<>();
        for (Event e: events) {
            startTime = e.getStartTime();
            endTime = e.getEndTime();
            if ((input.equals("current") && isEventCurrent(startTime, endTime, date)) ||
                    (input.equals("future") && isEventFuture(startTime, date)) ||
                        (input.equals("past") && isEventPast(endTime, date)) ||
                            (input.equals("any") && isEventAny(startTime, endTime, date)))
                                temp.add(e);
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
