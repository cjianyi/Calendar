import java.io.*;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.*;
import com.restfb.json.*;

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

    private ArrayList<String> loadEventsFile(String username){
        File file = new File("src\\" + username + "calendar" +  this.calendarName + ".txt");
        ArrayList<String> eventGetter = new ArrayList<>();
        BufferedReader br;
        try{
            br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while(line!=null){
                if(line.charAt(line.length()-1) == ','){
                    StringBuilder s = new StringBuilder(line);
                    s.replace(s.length() - 1, s.length(), "]");
                    line = s.toString();
                }
                if(line.charAt(0) == '{'){
                    StringBuilder s = new StringBuilder(line);
                    s.replace(0, 1, "[{");
                    line = s.toString();
                }
                eventGetter.add(line);
                line = br.readLine();
            }
        }catch (IOException e){}
        return eventGetter;
    }

    public void loadEvents(String username){
        ArrayList<String>  events = this.loadEventsFile(username);
        for(String event:events){
            
        }

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
    //Event editor menu
    public void editEvent(Event e) {

    }

    public boolean isEventTagged (Event e, String information)
    {
        for (String tag: e.getTags())
        {
            if (tag.equals(information))
            {
                return true;
            }
        }
        return false;
    }

    public boolean isEventInSeries (Event e, String information)
    {
        for (Series ser: e.getSeries())
        {
            if (ser.get_event_name().equalsIgnoreCase(information))
            {
                return true;
            }
        }
        return false;
    }

    public boolean isEventNameEqual (Event e, String information)
    {
        return e.getName().equalsIgnoreCase(information);
    }

    public ArrayList<Event> search(String inputString, String information){
        ArrayList<Event> temp = new ArrayList<>();
        if (inputString.equals("all"))
        {
            return events;
        }
        for (Event e: events) {
            if ((inputString.equals("tag") && isEventTagged(e, information)) ||
                    (inputString.equals("series_name") && isEventInSeries(e, information)) ||
                        (inputString.equals("name") && isEventNameEqual(e, information))) {
                        temp.add(e);
            }
        }
        return temp;
    }

    public boolean isEventCurrent (LocalDateTime startTime, LocalDateTime endTime,
                                   String inputString, LocalDateTime date)
    {
        return inputString.equals("current") && startTime.isBefore(date) && endTime.isAfter(date);
    }

    public boolean isEventAny (LocalDateTime startTime, LocalDateTime endTime,
                               String inputString, LocalDateTime date)
    {
        return inputString.equals("any") && (startTime.toLocalDate().isEqual(date.toLocalDate())
                || endTime.toLocalDate().isEqual(date.toLocalDate()));
    }

    public boolean isEventPast (LocalDateTime endTime,
                                String inputString, LocalDateTime date)
    {
        return inputString.equals("past") && endTime.isBefore(date);
    }

    public boolean isEventFuture (LocalDateTime startTime, String inputString, LocalDateTime date)
    {
        return inputString.equals ("future") && startTime.isAfter(date);
    }

    public ArrayList<Event> search(String inputString, LocalDateTime date)
    {
        LocalDateTime startTime;
        LocalDateTime endTime;
        ArrayList<Event> temp = new ArrayList<>();
        for (Event e: events) {
            startTime = e.getStartTime();
            endTime = e.getEndTime();
            if (isEventCurrent(startTime, endTime, inputString, date) ||
                    isEventFuture(startTime, inputString, date) ||
                        isEventPast(endTime, inputString, date) ||
                            isEventAny(startTime, endTime, inputString, date))
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
