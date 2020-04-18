package com.example.calendarandroid;

import android.util.Log;
import android.widget.Toast;

import java.io.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;
import com.restfb.json.*;

public class Calendar {
    /** The name of a calendar. */
    private String calendarName;
    /** An array list that stores all the events in a calendar. */
    private ArrayList<Event> events;
    /** An array list that stores all the alerts in a calendar. */
    private ArrayList<Alert> alerts;
    private ArrayList<Series> series;
    private ArrayList<Month> months;
    private LocalDate d;

    private ArrayList<Day> day;

    private Month currentMonth;

    private ArrayList<String> totalDurationSeries = new ArrayList<>();
    private ArrayList<String> totalLinkedSeries = new ArrayList<>();
    private int calNum;

    /**
     * Constructor for a calendar. Creates an empty calendar with a name.
     *
     * @param calNum Specifies which calendar of the user to load
     */
    public Calendar (int calNum) {
        this.calNum = calNum;
        this.calendarName = ParseUser.getCurrentUser().getUsername() + String.valueOf(calNum);
        this.events = new ArrayList<>();
        this.alerts = new ArrayList<>();
        this.series = new ArrayList<>();
        d = LocalDate.now();

        months = new ArrayList<>();
        day = new ArrayList<>();
        d = d.minusYears(1);
        d = d.withMonth(1);
        d = d.withDayOfMonth(1);
        LocalDate max = d.plusYears(3);

        while(d.isBefore(max)){
            day.add(new Day(d));
            if(d.plusDays(1).getMonthValue() != d.getMonthValue()){
//                Log.d("new month!",  Integer.toString(d.getYear()) +  Integer.toString(d.getMonthValue()));
                Month m = new Month(day);
                this.months.add(m);
                day = new ArrayList<>();
            }
            d = d.plusDays(1);
        }

        this.setCurrentMonth(LocalDate.now());
        this.months.get(0).addDaysBefore(this.wrapBeforeFirstMonth());
        this.loadEvents(calNum);

        for(int i = 1; i < this.months.size(); i++){
            int wrap = this.months.get(i).getWrapBeforeSize();
            int size = this.months.get(i - 1).getMonth().size();
            if (wrap > 0) {
                this.months.get(i).addDaysBefore(this.months.get(i - 1).getMonth().subList(size - wrap, size));
            }

            this.months.get(i).setPrev(this.months.get(i - 1));
        }

        for(int i = 0; i < this.months.size() - 1; i++){
            int wrapStartIndex = this.months.get(i + 1).getWrapBeforeSize();
            int wrapLength = this.months.get(i).getWrapAfterSize();
            this.months.get(i).addDaysAfter(this.months.get(i + 1).getMonth().subList(wrapStartIndex, wrapStartIndex + wrapLength));
            this.months.get(i).setNext(this.months.get(i + 1));
        }
    }

    public void setMonths(){

    }

    Month getCurrentMonth(){
        return this.currentMonth;
    }

    private ArrayList<Day> wrapBeforeFirstMonth(){
        ArrayList<Day> dates = new ArrayList<>();
        LocalDate d = this.months.get(0).getMonth().get(0).getDay();
        while(d.getDayOfWeek().getValue() != 7){
            d = d.minusDays(1);
        }
        int m = d.getMonthValue();
        while (m == d.getMonthValue()){
            dates.add(new Day(d));
            d = d.plusDays(1);
        }

        return dates;

    }

    private void setCurrentMonth(LocalDate d){
        for(Month m: this.months){
            for(Day day: m.getMonth()){
                if(d.equals(day.getDay())){
                    this.currentMonth = m;
                    Log.d("month", "found");
                    break;
                }
            }
        }

    }

    private List<ParseObject> loadEventsFile(int calNum) {
//        File file = new File("src\\" + username + "calendar" +  this.calendarName + ".txt");
//        ArrayList<String> eventGetter = new ArrayList<>();
//        BufferedReader br;
//        try{
//            br = new BufferedReader(new FileReader(file));
//            String line = br.readLine();
//            while(line!=null){
//                eventGetter.add(line);
//                line = br.readLine();
//            }
//        }catch (IOException e){
//            Log.d("loading events", "could not find file");
//        }
//
//        return eventGetter;
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("Calendar");
        final ParseUser u = ParseUser.getCurrentUser();
        List<ParseObject> cal;
        ParseObject calendar;
        // Query Parameters
        ArrayList<String> event = new ArrayList<>();
        query.whereEqualTo("userID", u);
        query.whereEqualTo("calendarName", u.getUsername() + String.valueOf(calNum));
        try {
            cal = query.find();
            Log.d("test", "test");
            Log.d("length", Integer.toString(cal.size()));
            calendar = cal.get(0);
            for(ParseObject calendars: cal){
                Log.d("finding object", "found a few");
                if(calendars.get("calendarName").toString().equals(ParseUser.getCurrentUser().getUsername() + "1")){
                    calendar = calendars;

                    Log.d("Calenda", "found");
                    final ParseQuery<ParseObject> query2 =  calendar.getRelation("events").getQuery();
                    List<ParseObject> events = query2.find();
                    Log.d("events length", Integer.toString(events.size()));
                    return events;

                }
            }
        }catch(ParseException e){
            Log.d("error", ":(");

        }
        ArrayList<ParseObject> a = new ArrayList<>();
        return a;
    }

    public void loadEvents(int calNum){
        List<ParseObject>  events = this.loadEventsFile(calNum);
        String eventName = "";
        LocalDateTime startDate;
        LocalDateTime endDate;
        ArrayList<Alert> alerts2 = new ArrayList<>();
        ArrayList<String> tags = new ArrayList<>();
        String series = "";
        ArrayList<Memo> memo = new ArrayList<>();
        ArrayList<String> durationSeriesString = new ArrayList<>();
        ArrayList<String> linkedSeriesString = new ArrayList<>();

        totalDurationSeries = new ArrayList<>();
        totalLinkedSeries = new ArrayList<>();


        for(ParseObject event:events){
            tags = new ArrayList<>();
            alerts2 = new ArrayList<>();
            memo = new ArrayList<>();
            durationSeriesString = new ArrayList<>();
            linkedSeriesString = new ArrayList<>();

            JsonObject e = new JsonObject(event);

            eventName = event.get("eventName").toString();
            startDate = LocalDateTime.parse(event.get("startDate").toString());
            endDate = LocalDateTime.parse(event.get("endDate").toString());
            JsonArray a = new JsonArray(event.get("tags").toString());
            this.loadTags(a, tags);
            JsonArray b = new JsonArray(event.get("alerts").toString());
            this.loadAlerts(b, alerts2);
            JsonArray s = new JsonArray(event.get("series").toString());
            this.loadSeries(s, durationSeriesString, linkedSeriesString);
            //series = durationSeriesString.get(0);
            Event p = new Event(startDate, endDate, eventName, tags, alerts2, durationSeriesString);

            this.alerts.addAll(alerts2);
            this.addEvent(p);
            JsonArray me = new JsonArray(event.get("memos").toString());
            this.loadMemos(me, memo);
            for(Memo m: memo){
                p.addMemo(m);
                m.addAssociate(p);
            }
        }
        createDurationSeries(durationSeriesString);
        createLinkedSeries(linkedSeriesString);
        this.addEventsToDays();
    }

    private void addEventsToDays(){
        for(Month m: this.months){
            for(Day d: m.getMonth()){
                for(Event e: this.events){
                    if((e.getStartTime().toLocalDate().isBefore(d.getDay()) ||e.getStartTime().toLocalDate().isEqual(d.getDay()))
                            && (e.getEndTime().toLocalDate().isAfter(d.getDay())|| e.getEndTime().toLocalDate().isEqual(d.getDay())))
                    {
                        if (!d.getEvents().contains(e)) {
                            d.addEvent(e);
                        }
                    }
                }
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
            Memo memo = new Memo(arr.getString(i));
            m.add(memo);
        }
    }

    private void loadSeries(JsonArray arr, ArrayList<String> dura, ArrayList<String> li){
        JsonArray dur = arr.getJsonArray(0);
        JsonArray link = arr.getJsonArray(1);
        for(int i = 0; i < dur.length(); i++){
            String name = dur.get(i).toString();
            if(!totalDurationSeries.contains(name)){
                totalDurationSeries.add(name);
            }
            dura.add(name);
        }
        for(int i = 0; i< link.length(); i++){
            String name = link.get(i).toString();
            if(!totalLinkedSeries.contains(name)){
                totalLinkedSeries.add(name);
            }
            li.add(name);
        }
    }

    private void createDurationSeries(ArrayList<String> series){
        for(String s: totalDurationSeries){
            Duration_series d = new Duration_series(s);
            for(Event e: this.events){
                if(e.getDurationSeriesNames().contains(s)){
                    d.addEvent(e);
                }
            }
        }
    }


    private void createLinkedSeries(ArrayList<String> series){
        for(String s:totalLinkedSeries){
            Linked_series l = new Linked_series(s);
            for(Event e: this.events){
                if(e.getLinkedSeriesNames().contains(s)){
                    l.add_events(e);
                }
            }
        }

    }

    //Event editor menu
    public void addEvent(Event e) {
        this.events.add(e);
    }

    public void createEvent(Event e){
        this.events.add(e);
        ParseObject event = new ParseObject("Event");
        ArrayList<String> info = e.eventFileFormatter();
        event.put("eventName", info.get(0));
        event.put("startDate", info.get(1));
        event.put("endDate", info.get(2));
        event.put("alerts", info.get(3));
        event.put("tags", info.get(4));
        event.put("memos", info.get(5));
        event.put("series", info.get(6));
        try {
            event.save();
            ParseUser u = ParseUser.getCurrentUser();
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Calendar");
            query.whereEqualTo("calendarName", u.getUsername() + String.valueOf(this.calNum));

            ParseRelation<ParseObject> rel = query.find().get(0).getRelation("events");
            rel.add(event);
            u.save();
        }catch(ParseException ex){
            Log.d("add evenr", "failed");

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
    //TODO: change this to update the cal

    public void deleteEvent(Event e) {
        if (events.contains(e)) {
            this.events.remove(e);
        }
    }

    public void deleteEvent(String[] info){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Calendar");
        query.whereEqualTo("calendarName", this.calendarName);

        // Query parameters based on the item name
        try {
            List<ParseObject> l = query.find();

            ParseRelation<ParseObject> r =query.getFirst().getRelation("events");

            ParseQuery<ParseObject> query2 = r.getQuery();

            List<ParseObject> p = query2.find();
           for(ParseObject e: p){
               if (e.get("eventName").toString().equals(info[0])
                       && e.get("startDate").equals(info[1]) && e.get("endDate").toString().equals(info[2])){
                   e.delete();
                   Log.d("delete", "success");
                   break;
               }
           }


        }catch(ParseException e){
            Log.d("deletion", "error");
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
        for (String ser: e.getSeries())
        {
            if (ser.equalsIgnoreCase(info))
            {
                return true;
            }
        }
        return false;
    }

    public boolean isEventNameEqual (Event e, String info) {
        return e.getName().equalsIgnoreCase(info);
    }

    public void addSeries(Series s){
        this.series.add(s);
    }

    public ArrayList<Series> getSeries(){
        return this.series;
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
}
