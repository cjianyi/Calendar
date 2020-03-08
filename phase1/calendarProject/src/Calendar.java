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
                eventGetter.add(line);
                line = br.readLine();
            }
        }catch (IOException e){}

        return eventGetter;
    }

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

    private void loadTags(JsonArray arr, ArrayList<String> str){
        for(int i = 0; i<arr.length(); i++){
            str.add(arr.get(i).toString());
        }
    }

    private void loadAlerts(JsonArray arr, ArrayList<Alert> al){
        for(int i = 0; i<arr.length(); i++){
            JsonObject o = arr.getJsonObject(i);
            String description = o.getString("description");
            LocalDateTime d = LocalDateTime.parse(o.getString("date"));
            Alert alert = new Alert(description, d);
            al.add(alert);
        }
    }

    private void loadMemos(JsonArray arr, ArrayList<Memo> m){
        for(int i = 0; i<arr.length(); i++){
            Memo memo = new Memo(i, arr.getString(i));
            m.add(memo);
        }
    }

    //Event editor menu
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
    //Event editor menu
    public void deleteEvent(Event e) {
        if (events.contains(e)) {
                this.events.remove(e);
        }
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

    public ArrayList<Alert> getAlerts() {
        ArrayList<Alert> alertArrayList = new ArrayList<>();
        for (Event e: events)
        {
            alertArrayList.addAll(e.getAlerts());
        }
        return alertArrayList;
    }
    //phase2
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
    //phase 2
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
