import com.restfb.json.JsonArray;
import com.restfb.json.JsonObject;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Calendar {
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

    /**
     * Loads all of the events of a user stored in a file.
     *
     * @param username the user's username
     * @return an array list of strings that contain the information of each event that the user has
     */
    private ArrayList<String> loadEventsFile(String username){
        File file = new File("src\\" + username + "calendar" +  this.calendarName + ".txt");
        ArrayList<String> eventGetter = new ArrayList<>();
        BufferedReader br;
        try{
            br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while(line!=null){
                eventGetter.add(line);
                line = br.readLine();
            }
        }catch (IOException e){}

        return eventGetter;
    }

    /**
     * Convert the events from loadEventsFile(username) which is an array list of strings to appropriate data types
     * so that the program cna use the information.
     *
     * @param username the user with this username should have those events
     */
    public void loadEvents(String username){
        ArrayList<String>  events = this.loadEventsFile(username);
        JsonArray array;
        String eventName = "";
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now();
        ArrayList<Alert> alerts2 = new ArrayList<>();
        ArrayList<String> tags = new ArrayList<>();
        ArrayList<Series> series = new ArrayList<>();
        ArrayList<Memo> memo = new ArrayList<>();

        for(String event:events){

            JsonObject e = new JsonObject(event);

            eventName = e.getString("name");
            startDate = LocalDateTime.parse(e.getString("startTime"));
            endDate = LocalDateTime.parse(e.getString("endTime"));
            JsonArray a = e.getJsonArray("tags");
            this.loadTags(a, tags);
            JsonArray b = e.getJsonArray("alerts");
            this.loadAlerts(b, alerts2);
            Event p = new Event(startDate, endDate, eventName, tags, alerts2, series);
            this.alerts.addAll(alerts2);
            this.addEvent(p, username);
            JsonArray me = e.getJsonArray("memos");
            this.loadMemos(me, memo);
            for(Memo m: memo){
                p.addMemo(m);
                m.addAssociate(p);
            }
        }

    }

    /**
     * Loads all the tags stored in a file.
     *
     * @param arr an array of tags to be loaded
     * @param str an array of tags that are converted to strings
     */
    private void loadTags(JsonArray arr, ArrayList<String> str){
        for(int i = 0; i<arr.length(); i++){
            str.add(arr.get(i).toString());
        }
    }

    /**
     * Loads all the alerts stored in a file.
     *
     * @param arr an array of alerts to be loaded
     * @param al an array of alerts that have type Alert
     */
    private void loadAlerts(JsonArray arr, ArrayList<Alert> al){
        for(int i = 0; i<arr.length(); i++){
            JsonObject o = arr.getJsonObject(i);
            String description = o.getString("description");
            LocalDateTime d = LocalDateTime.parse(o.getString("date"));
            Alert alert = new Alert(description, d);
            al.add(alert);
        }
    }

    /**
     * Loads all the memos in a file
     *
     * @param arr an array of memos to be loaded
     * @param m an array of memos that have type Memo
     */
    private void loadMemos(JsonArray arr, ArrayList<Memo> m){
        for(int i = 0; i<arr.length(); i++){
            Memo memo = new Memo(arr.getString(i));
            m.add(memo);
        }
    }

    /**
     * Add an event to a list of events in this calendar and into a user's calendar text file.
     *
     * @param e the event that will be added to that user's calendar
     * @param username the username of that user
     */
    public void addEvent(Event e, String username) {
        this.events.add(e);

        try {
           FileWriter fw = new FileWriter("src\\" + username + "calendar" + this.calendarName + ".txt");
            for (Event event : this.events) {
                fw.write(event.eventFileFormatter() + "\n");
            }
            fw.close();

        }catch(IOException ex){
            System.out.println("error");
        }

    }

    /**
     * Gets all the alerts in the calendar that happens on a particular date.
     *
     * @param date a particular date
     * @return an array list of alerts that happen on the date specified by the method parameter.
     */
    public ArrayList<Alert> getAlerts(LocalDateTime date){
        ArrayList<Alert> e = new ArrayList<>();
        for(Alert alert:this.alerts){

            if(alert.getDate().toLocalDate().equals(date.toLocalDate())){
                e.add(alert);
            }
        }
        return e;
    }

    /**
     * Shows all the events inside this calendar.
     *
     * @return a string that will contain all the event names of the events in this calendar.
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
     * @return a string that will contain all the memo names and memo ids that
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
     * Shows all the alerts inside this calendar.
     *
     * @return a string that will contain all the alerts. Each alert occupies one line.
     */
    public String showAllAlerts() {
        String allAlerts = "";
        for (Event e: events)
        {
            for (Alert a: e.getAlerts())
            {
                allAlerts = allAlerts + a.getAlert() + "\n";
            }
        }
        return allAlerts;
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

    /**
     * Deletes an event from the calendar.
     *
     * @param e the event that will be deleted.
     */
    public void deleteEvent(Event e) {
        if (events.contains(e)) {
                this.events.remove(e);
        }
    }

    /**
     * Checks if an event is tagged.
     *
     * @param e the event that will be checked
     * @param info the tag that is being searched
     * @return true if the event has the tag info; false otherwise.
     */
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

    /**
     * Checks if an event is in a series.
     *
     * @param e the event that will be checked
     * @param info the series that will be checked whether the event is in that series or not
     * @return true if event is in that series named info; false otherwise.
     */
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

    /**
     * Checks if an event has that event name.
     *
     * @param e the event that will be checked
     * @param info the name that we will check if it is the same as the event's name.
     * @return true if info and event name are the same; false otherwise.
     */
    public boolean isEventNameEqual (Event e, String info)
    {
        return e.getName().equalsIgnoreCase(info);
    }

    /**
     * Searches all the events that satisfies the user input.
     * @param input variable that indicates if we are searching all events, by tag, by series name, or by event name.
     * @param info the user input and the search is based on that input
     * @return a list of events that satisfies the searches
     */
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

    /**
     * Checks if the event is a current event.
     *
     * @param startTime the start time of the event
     * @param endTime the end time of the event
     * @param date the date that the event will be compared to.
     * @return true if the event is happening on that date; false otherwise.
     */
    public boolean isEventCurrent (LocalDateTime startTime, LocalDateTime endTime, LocalDateTime date)
    {
        return startTime.isBefore(date) && endTime.isAfter(date);
    }

    /**
     * Checks if the event has happened/is going to happen on that day.
     * <p>
     * This is different from a current event because a current event is happening right now.
     * </p>
     *
     * @param startTime the start time of that event
     * @param endTime the end time of that event
     * @param date the date that we are comparing the event date to
     * @return true if the event has happened/is going to happen on that day; false otherwise.
     */
    public boolean isEventAny (LocalDateTime startTime, LocalDateTime endTime, LocalDateTime date)
    {
        return startTime.toLocalDate().isEqual(date.toLocalDate()) || endTime.toLocalDate().isEqual(date.toLocalDate());
    }

    /**
     * Checks if the event has happened already.
     *
     * @param endTime the end time of the event
     * @param date the date that we compare the end time of the event to
     * @return true if the date is after the end time of the event; false otherwise
     */
    public boolean isEventPast (LocalDateTime endTime, LocalDateTime date)
    {
        return endTime.isBefore(date);
    }

    /**
     * Checks if the event is going to happen in the future.
     *
     * @param startTime the start time of the event
     * @param date the date to which we are comparing the start time
     * @return true if the date is after the start time of the event; false otherwise
     */
    public boolean isEventFuture (LocalDateTime startTime, LocalDateTime date)
    {
        return startTime.isAfter(date);
    }

    /**
     * Another search method that searches events that satisfy the user input
     *
     * @param input tells us if we want current events, future events, past events, or events that happen
     *              on a particular date.
     * @param date  the date that we will base the search on
     * @return a list of events that happen before a certain date, on a certain date, after a certain date,
     * or right now depending on the input string. The if statements decide that.
     */
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

    /**
     * Getter that gets all the alerts in a calendar.
     *
     * @return a list of alerts
     */
    public ArrayList<Alert> getAlerts() {
        ArrayList<Alert> alertArrayList = new ArrayList<>();
        for (Event e: events)
        {
            alertArrayList.addAll(e.getAlerts());
        }
        return alertArrayList;
    }

    /**
     * Override toString() method so that print calendar will give the name of the calendar.
     *
     * @return the name of this calendar
     */
    @Override
    public String toString() {
        return this.calendarName;
    }
}
