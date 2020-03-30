//package com.example.calendarandroid;
//
////import javax.sound.midi.Soundbank;
//import java.sql.SQLOutput;
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.time.format.DateTimeParseException;
//import java.util.*;
//
//
//import java.time.LocalDateTime;
//import java.util.Calendar;
//
//public class Controller {
//    Scanner in;
//
//    UserManager userManager;
//    static EventManager eventManager = new EventManager();
//    User currentUser;
//    Calendar currentCalendar;
//    LocalDateTime currentDate = LocalDateTime.now();
//
//    static Stack<String> menuStack = new Stack<>();
//
//    private static String username = "";
//    private static String password = "";
//    private static String email = "";
//
//    private static boolean loggedIn = false;
//
//    private static String eventName = "";
//    private static int duration = 0;
//    private static String seriesName = "";
//    private static LocalDateTime startDate;
//    private static LocalDateTime endDate;
//    private static LocalDateTime endSeriesTime;
//    private static ArrayList<String> tags = new ArrayList<>();
//    private static ArrayList<Alert> alerts = new ArrayList<>();
//    private static ArrayList<Memo> memos = new ArrayList<>();
//    private static ArrayList<Linked_series> linkedSeries = new ArrayList<>();
//    private static ArrayList<String> series = new ArrayList<>();
//
//    public Controller() {
//        ArrayList<Integer> test = new ArrayList<>();
//        Integer i = 5;
//        test.add(i);
//        i = new Integer(6);
//        test.add(i);
//        System.out.println(test);
//
//        in = new Scanner(System.in);
//        this.userManager = new UserManager();
//        menuStack.push("mainMenu");
//        System.out.println(currentDate);
//    }
//
//    public void displayMenu() {
//        while (!menuStack.peek().equals("exit")) {
//            switch (menuStack.peek()) {
//                case "mainMenu":
//                    mainMenu();
//                    break;
//                case "logInMenu":
//                    logInMenu();
//                    break;
//                case "accountGetter":
//                    accountGetter();
//                    menuStack = new Stack<String>();
//                    menuStack.push("mainMenu");
//                    break;
//                case "calendarMenu":
//                    calendarMenu();
//                    break;
//                case "editorMenu":
//                    editorMenu();
//
//                    break;
//                case "viewEventMenu":
//                    viewEventMenu();
//                    break;
//                case "searchMenu":
//                    searchMenu();
//                    menuStack.pop();
//                    break;
//                case "createEventMenu":
//                    createEventMenu();
//                    menuStack.pop();
//                    break;
//                case "deleteEventMenu":
//                    deleteEventMenu();
//                    menuStack.pop();
//                    break;
//                case "editEventMenu":
//                    editEventMenu();
//                    menuStack.pop();
//                    break;
//                case "linkEventMenu":
//                    linkEventMenu();
//                    menuStack.pop();
//                    break;
//            }
//            reset_parameters();
//        }
//    }
//
//    private void reset_parameters() {
//        eventName = "";
//        tags = new ArrayList<>();
//        memos = new ArrayList<>();
//        alerts = new ArrayList<>();
//        linkedSeries = new ArrayList<>();
//        series = new ArrayList<>();
//    }
//
//    private LocalDateTime varifyStartDate(){
//        boolean exit = false;
//        LocalDateTime date = LocalDateTime.now();
//        do {
//            try {
//                System.out.println("Enter a start date (yyyy-mm-dd)");
//                String startDay = in.nextLine();
//
//                System.out.println("Enter a start time (hh:mm)");
//                String startTime = in.nextLine();
//                date = LocalDateTime.parse(startDay + "T" + startTime);
//                exit = true;
//            } catch (DateTimeParseException e) {
//                System.out.println("wrong format");
//            }
//        } while (!exit);
//        return date;
//    }
//
//    private LocalDateTime varifyEndDate(){
//        boolean exit = false;
//        LocalDateTime date = LocalDateTime.now();
//        do {
//            try {
//                System.out.println("Enter an end date (yyyy-mm-dd)");
//                String endDay = in.nextLine();
//
//                System.out.println("Enter an end time (hh:mm)");
//                String endTime = in.nextLine();
//                date = LocalDateTime.parse(endDay + "T" + endTime);
//                if(startDate.isBefore(endDate) || startDate.isEqual(endDate)){
//                    exit = true;
//                }else{
//                    System.out.println("End date cannot be before start date");
//                    exit = false;
//                }
//            } catch (DateTimeParseException e) {
//                System.out.println("wrong format");
//            }
//        } while (!exit);
//        return date;
//    }
//
//    public void calendarMenu() {
//        System.out.println("\nCalendar Menu\nPress 1 to open event editor\n Press 2 to view events\n Press 3 to set " +
//                "the current date to a day other than today");
//        String choice = in.nextLine();
//        switch (choice) {
//            case "1":
//                menuStack.push("editorMenu");
//                break;
//            case "2":
//                menuStack.push("viewEventMenu");
//                break;
//            case "3":
//                boolean exit = false;
//                do {
//                    System.out.println("Enter a date to set (yyyy/mm/dd)");
//                    String date = in.nextLine();
//                    try {
//                        currentDate = LocalDateTime.parse(date + "T" + "00:00:00");
//                        exit = true;
//                    } catch (DateTimeParseException e) {
//                        System.out.println("wrong format");
//                        exit = false;
//                    }
//                } while (!exit);
//                break;
//        }
//    }
//
//    public void editorMenu(){
//        System.out.println("\nEditor Menu\n Press 1 to create an event\nPress 2 to delete an event" +
//                "\nPress 3 to edit an event\nPress 4 to link events\nPress 5 to go back");
//        String choice = in.nextLine();
//        switch (choice) {
//            case "1":
//                menuStack.push("createEventMenu");
//                break;
//            case "2":
//                menuStack.push("deleteEventMenu");
//                break;
//            case "3":
//                menuStack.push("editEventMenu");
//                break;
//            case "4":
//                menuStack.push("linkEventMenu");
//                break;
//            case "5":
//                menuStack.pop();
//                break;
//        }
//
//    }
//
//    public void createEventMenu(){
//        //date, time, tag, memo, seriesame, alert, freq, duration
//        System.out.println("Do you want to create an individual event, or does this event repeat?" +
//                " (Note that individual events can last multiliple consecutive days " +
//                " \n Press 1 for individual\nPress 2 for repeated");
//        String choice = "";
//        choice = in.nextLine();
//        if(choice.equals("2")){
//            repeatedEventMenu(false);
//        }
//        else {
//            duration = 0;
//            System.out.println("Enter a name");
//            eventName = in.nextLine();
//            startDate = varifyStartDate();
//            endDate = varifyEndDate();
//        }
//            System.out.println("Enter a tag(s) for the event, separated by commas");
//            String tag = in.nextLine();
//            String[] tagged = tag.split("\\s*,\\s*");
//            tags = new ArrayList<String>();
//            Collections.addAll(tags, tagged);
//
//            System.out.println("Would you like to add alert(s) to the event (y/n)");
//            choice = in.nextLine();
//            if (choice.equals("y")) {
//                alertMenu(false);
//            }
//
//
//            eventManager.createEvent(this.currentCalendar, this.currentUser.getUsername(), eventName,
//                    startDate, endDate, tags, alerts, series, duration, endSeriesTime);
//
//    }
//
//    public void alertMenu(boolean edit){
//        String choice;
//        do {
//            System.out.println("Enter a description for the alert");
//            String description = in.nextLine();
//            LocalDateTime datetime = varifyStartDate();
//            System.out.println("Do you want it to repeat? (y/n");
//            choice = in.nextLine();
//            if (choice.equals("y")) {
//                repeatedAlertMenu(description, datetime);
//            }else{
//                Alert alert = new Alert(description, datetime);
//                alerts.add(alert);
//            }
//            System.out.println("Enter y for adding another alert, n for stop");
//            choice = in.nextLine();
//        }while(choice.equals("y"));
//    }
//
//    public void repeatedAlertMenu(String description, LocalDateTime datetime){
//        System.out.println("Press 1 for daily\nPress 2 for weekly\nPress 3 for monthly\nPress 4 for yearly");
//        String choice = in.nextLine();
//        LocalDateTime repeat = endDate.plusDays(0);
//
//        switch (choice){
//            case "1":
//                while(datetime.isBefore(endDate)|| datetime.toLocalDate().equals(endDate.toLocalDate())){
//                    Alert alert = new Alert(description, datetime);
//                    alerts.add(alert);
//                    datetime = datetime.plusDays(1);
//                }
//                break;
//            case "2":
//                while(datetime.isBefore(endDate)){
//                    Alert alert = new Alert(description, datetime);
//                    alerts.add(alert);
//                    datetime = datetime.plusDays(7);
//                }
//            case "3":
//                while (datetime.isBefore(endDate)){
//                    Alert alert = new Alert(description, datetime);
//                    alerts.add(alert);
//                    datetime = datetime.plusMonths(1);
//                }
//                break;
//            case "4":
//                while (datetime.isBefore(endDate)){
//                    Alert alert = new Alert(description, datetime);
//                    alerts.add(alert);
//                    datetime = datetime.plusYears(1);
//                }
//                break;
//        }
//    }
//
//    public void deleteAlertMenu(){
//        String choice;
//        do{
//            System.out.println("Enter the description of the alert to be deleted");
//            String des  = in.nextLine();
//            int max = alerts.size();
//            for (int i = 0; i < max; i++){
//                if (des.equals(alerts.get(i).getAlert())){
//                    alerts.remove(i);
//                }
//            }
//            System.out.println("Enter y for deleting a new alert or n for stop");
//            choice = in.nextLine();
//        }while(choice.equals("y"));
//    }
//
//    public void editAlertMenu(){
//        String choice;
//        do{
//            System.out.println("Enter the description of the alert to be deleted");
//            String des  = in.nextLine();
//            System.out.println("Enter 1 for changing description\nEnter 2 for changing date");
//            choice = in.nextLine();
//
//            for (Alert alert : alerts) {
//                if (des.equals(alert.getAlert())) {
//                    if (choice.equals("1")) {
//                        System.out.println("Enter new description");
//                        des = in.nextLine();
//                        alert.editAlert(des);
//                    }
//                }
//                if (choice.equals("2")) {
//
//                    LocalDateTime datetime = varifyStartDate();
//                    alert.editDate(datetime);
//                }
//            }
//            System.out.println("Enter y for edit a new alert or n for stop");
//            choice = in.nextLine();
//        }while(choice.equals("y"));
//    }
//
//
//    public void memoMenu(boolean edit){
//        String answer;
//        do {
//            System.out.println("Enter the text for this memo");
//            String text = this.in.nextLine();
//            Integer id =currentCalendar.getMemos().size();
//            ArrayList<Event> events;
//            ArrayList<Memo> memos;
//            Memo m = new Memo(text);
//            currentCalendar.getMemos().add(m);
//            // selecting events
//            do {
//                System.out.println(currentCalendar.showAllEvents());
//                System.out.println("Choose a new Event");
//                String event_name = this.in.nextLine();
//                events = currentCalendar.getEvents();
//                for (int i = 0; i < events.size(); i++) {
//                    if (event_name == events.get(i).getName()) {
//                        m.addAssociate(events.get(i));
//                        events.get(i).addMemo(m);
//                        break;
//                    }
//                }
//                System.out.println("Enter y for choosing a new event or enter n for end choosing event ");
//                answer = this.in.nextLine();
//            } while (answer.equals("y"));
//            //selecting memos
//            do {
//                System.out.println(currentCalendar.showAllMemos());
//                System.out.println("Choose a new memo");
//                String memo_Id_1 = this.in.nextLine();
//                int memo_id = Integer.parseInt(memo_Id_1);
//                memos = currentCalendar.getMemos();
//                for (int i = 0; i < memos.size(); i++) {
//                    if (memo_id == memos.get(i).getId()) {
//                        m.addAssociate(memos.get(i));
//                        break;
//                    }
//                }
//                System.out.println("Enter y for choosing a new memo or enter n for end choosing memo ");
//                answer = this.in.nextLine();
//            } while (answer.equals("y"));
//            System.out.println("Enter y for creating a new memo or n for ending creating " );
//            answer = this.in.nextLine();
//        }while (answer == "y");
//    }
//
//    public void deleteMemoMenu(){
//        String choice;
//        do{
//            System.out.println(currentCalendar.showAllMemos());
//            System.out.println("Enter the id of the memo to be deleted");
//            String memo_1  = in.nextLine();
//            int memo_id = Integer.parseInt(memo_1);
//            for (int i = 0; i < memos.size(); i++){
//                if (memo_id == memos.get(i).getId()){
//                    memos.remove(i);
//                }
//            }
//            System.out.println("Enter y for deleting a new memo or n for stop");
//            choice = in.nextLine();
//        }while(choice == "y");
//    }
//
//    public void editMemoMenu(){
//        String choice;
//        do{
//            System.out.println(currentCalendar.showAllMemos());
//            System.out.println("Enter the id of the memo to be deleted");
//            String memo_1  = in.nextLine();
//            int memo_id = Integer.parseInt(memo_1);
//            System.out.println("Enter 1 for changing text\nEnter 2 for changing event\nEnter 3 for changing memo");
//            choice = in.nextLine();
//
//            for (int i = 0; i < memos.size(); i++){
//                if (memo_id == memos.get(i).getId()) {
//                    if (choice == "1") {
//                        System.out.println("Enter new text");
//                        String text = in.nextLine();
//                        memos.get(i).editText(text);
//                    }
//                    if (choice == "2") {
//
//                        System.out.println("Enter 1 for deleting event\nEnter 2 for adding event");
//                        String choice_1 = in.nextLine();
//                        switch (choice_1) {
//                            case "1":
//                                System.out.println(memos.get(i).showAllEvents());
//                                System.out.println("Select an event");
//                                String event_name = in.nextLine();
//                                for (int j = 0; j < memos.get(i).getAssociatedEvents().size(); j++) {
//                                    if (event_name == memos.get(i).getAssociatedEvents().get(j).getName()) {
//                                        memos.get(i).getAssociatedEvents().get(j).removeMemo(memos.get(i));
//                                        memos.get(i).getAssociatedEvents().remove(j);
//                                    }
//                                }
//                            case "2":
//                                System.out.println(currentCalendar.showAllEvents());
//                                System.out.println("Select an event");
//                                event_name = in.nextLine();
//                                for (int j = 0; j < currentCalendar.getEvents().size(); j++) {
//                                    if (event_name == currentCalendar.getEvents().get(j).getName()) {
//                                        memos.get(i).addAssociate(currentCalendar.getEvents().get(j));
//                                        currentCalendar.getEvents().get(j).addMemo(memos.get(i));
//                                    }
//
//                                }
//                        }
//                    }if (choice == "3"){
//                        System.out.println("Enter 1 for deleting memo\nEnter 2 for adding memo");
//                        String choice_1 = in.nextLine();
//                        switch (choice_1){
//                            case "1":
//                                System.out.println(memos.get(i).showAllMemos());
//                                System.out.println("Select a memo");
//                                String memo_temp = in.nextLine();
//                                int memo_id_2 = Integer.parseInt(memo_temp);
//                                for (int j = 0; j < memos.get(i).getAssociatedMemos().size(); j++){
//                                    if (memo_id_2 == memos.get(i).getAssociatedMemos().get(j).getId()){
//                                        memos.get(i).getAssociatedMemos().get(j).removeAssociate(memos.get(i));
//                                        memos.get(i).removeAssociate(memos.get(i).getAssociatedMemos().get(j));
//                                    }
//
//                                }
//                            case "2":
//                                System.out.println(currentCalendar.showAllMemos());
//                                System.out.println("Select a memo");
//                                memo_temp = in.nextLine();
//                                memo_id_2 = Integer.parseInt(memo_temp);
//                                for (int j = 0; j < currentCalendar.getMemos().size(); j++){
//                                    if (memo_id_2 == currentCalendar.getMemos().get(j).getId()){
//                                        memos.get(i).addAssociate(currentCalendar.getMemos().get(j));
//                                        currentCalendar.getMemos().get(j).addAssociate(memos.get(i));
//                                    }
//                                }
//                        }
//                    }
//                }
//            }
//            System.out.println("Enter y for edit a new alert or n for stop");
//            choice = in.nextLine();
//        }while(choice == "y");
//    }
//
//
//    public void alertAnnoucements() {
//        System.out.println("---Alerts---");
//        for (int i = 0; i < currentCalendar.getAlerts(currentDate).size(); i++) {
//            System.out.println(currentCalendar.getAlerts(currentDate).get(i).getAlert());
//        }
//        System.out.println("-------------");
//
//    }
//
//    public void repeatedEventMenu(boolean edit) {
//        System.out.println("Enter the name of the event");
//        eventName = in.nextLine();
//       boolean exit = false;
//        startDate = varifyStartDate();
//        do{
//            System.out.println("Enter the time this event ends (same day as start day)");
//            String time = in.nextLine();
//           try{
//               endSeriesTime = LocalDateTime.parse(startDate.toLocalDate().toString() + "T" + time);
//               exit = true;
//           }catch(DateTimeParseException e){
//               exit = false;
//               System.out.println("Wrong format");
//            }
//        }while(!exit);
//        exit = false;
//        do{
//            System.out.println("Enter the day this event ends");
//            String time = in.nextLine();
//            try{
//                endDate = LocalDateTime.parse(time + "T" + "00:00");
//                exit = true;
//            }catch(DateTimeParseException e){
//                exit = false;
//                System.out.println("Wrong format");
//            }
//        }while(!exit);
//        System.out.println("Enter a name to call this collection of events");
//        String name = in.nextLine();
//        series.add(name);
//        System.out.println("When do you want it to repeat? \nPress 1 for daily\nPress 2 for weekly" +
//                "\nPress 3 for monthly\nPress 4 for yearly\n");
//        duration = Integer.parseInt(in.nextLine());
//    }
//
//    public void deleteEventMenu(){
//        System.out.println("Please type the name of the event that will be deleted\n" +
//                "Press 1 to go back to editorMenu");
//        System.out.println(currentCalendar.showAllEvents());
//        String choice = in.nextLine();
//        do {
//            if (choice.equals("1"))
//            {
//                break;
//            }
//            boolean switcher = false;
//                for (Event e : currentCalendar.getEvents()) {
//                    if (e.getName().equalsIgnoreCase(choice)) {
//                        eventManager.deleteEvent(currentCalendar, e);
//                        switcher = true;
//                        System.out.println("Event is deleted successfully!");
//                        break;
//                    }
//                }
//                if (!switcher) {
//                    System.out.println("Event does not exist.");
//                }
//                System.out.println("Please type the name of the event that will be deleted or press 1 to go " +
//                    "back to editorMenu");
//                choice = in.nextLine();
//        }while(!choice.equals("1"));
//    }
//    //startTime, endTime, name, addTags removeTags, addAlerts, removeAlerts, addseries, removeseries
//    //assume the event name typed by the user is valid.
//    public void editEventMenu() {
//        System.out.println("Please type the name of the event that will be edited\nPress 1 to go back to editorMenu");
//        System.out.println(currentCalendar.showAllEvents());
//        String choice = in.nextLine();
//        do {
//            if (choice.equals("1")) {
//                break;
//            }
//            Event ev = null;
//            System.out.println("Press 1 to edit event name\nPress 2 to edit event startTime\nPress 3 to edit event " +
//                    "endTime\nPress 4 to add a tag for the event\nPress 5 to remove a tag fo the event" +
//                    "\nPress 6 to add an alert for the event\nPress 7 to remove an " +
//                    "alert for the event\nPress 8 to remove event from a series.\nPress 9 to go back to editorMenu.");
//            String change = in.nextLine();
//            for (Event e : currentCalendar.getEvents()) {
//                if (e.getName().equals(choice)) {
//                    ev = e;
//                    break;
//                }
//            }
//            if (ev != null) {
//                switch (change) {
//                    case "1":
//                        System.out.println("Please type the name that you want to change the event's name to: ");
//                        change = in.nextLine();
//                        eventManager.addToEvent(ev, "name", change);
//                        break;
//                    case "2": {
//                        System.out.println("You are changing the start time of your event.");
//                        System.out.println("Enter a date (yyyy-mm-dd)");
//                        String date = in.nextLine();
//                        System.out.println("Enter a time (hh:mm)");
//                        String time = in.nextLine();
//                        //LocalDateTime datetime = LocalDateTime.parse(date + "T" + time);
//                        //ev.setStartTime(datetime);
//                        eventManager.addToEvent(ev, "startTime", date + "T" + time);
//                        break;
//                    }
//                    case "3": {
//                        System.out.println("You are changing the end time of your event.");
//                        System.out.println("Enter a date (yyyy-mm-dd)");
//                        String date = in.nextLine();
//                        System.out.println("Enter a time (hh:mm)");
//                        String time = in.nextLine();
//                        //LocalDateTime datetime = LocalDateTime.parse(date + "T" + time);
//                        //ev.setEndTime(datetime);
//                        eventManager.addToEvent(ev, "endTime", date + "T" + time);
//                        break;
//                    }
//                    case "4":
//                        System.out.println("You are trying to add a tag to the event.");
//                        change = in.nextLine();
//                        eventManager.addToEvent(ev, "tag", change);
//                        break;
//                    case "5":
//                        System.out.println("You are trying to remove a tag from the event.");
//                        change = in.nextLine();
//                        eventManager.removeTag(ev, change);
//                        break;
//                    case "6": {
//                        System.out.println("You are trying to add an alert to the event.");
//                        System.out.println("Enter a date");
//                        String date = in.nextLine();
//                        System.out.println("Enter a time");
//                        String time = in.nextLine();
//                        System.out.println("Please enter a description of the alert.");
//                        String description = in.nextLine();
//                        System.out.println("Please enter the frequency of the alert.");
//                        String frequency = in.nextLine();
//                        LocalDateTime datetime = LocalDateTime.parse(date + "T" + time);
//                        eventManager.addAlert(ev, description, datetime, frequency);
//                        break;
//                    }
//                    case "7":
//                        System.out.println("You are trying to delete an alert from the event.");
//                        System.out.println("Please input the alert's description: ");
//                        System.out.println(currentCalendar.showAllAlerts());
//                        change = in.nextLine();
//                        ArrayList<Alert> temp = currentCalendar.getAlerts();
//                        for (Alert alert : temp) {
//                            if (alert.getAlert().equalsIgnoreCase(change)) {
//                                eventManager.removeAlert(ev, alert);
//                            }
//                        }
//                        break;
//                    case "8":
//                        System.out.println("You are trying to remove this event from a series.");
//                        System.out.println("Please enter series name: ");
//                        String series = in.nextLine();
//                        for (String se : ev.getSeries()) {
//                            if (se.equals(series)) {
//                                eventManager.removeSeries(ev, se);
//                            }
//                        }
//                        break;
//                    case "9":
//                        choice = "1";
//                        break;
//                }
//            }
//            }
//            while (!choice.equals("1")) ;
//        }
//
//
//    public void linkEventMenu(){
//
//        Linked_series existingSeries = null;
//        System.out.println("Press 1 to create a new group of linked events, Press 2 to an existing set of linked events");
//        String input_1 = in.nextLine();
//        if (input_1 == "1"){
//            System.out.println("Enter the name of the new group");
//            String input_2 = in.next();
//
//            ArrayList<Event> new_array = new ArrayList<>();
//            Linked_series new_link = new Linked_series(input_2, new_array);
//            ArrayList<Event> events = currentCalendar.getEvents();
//            System.out.println("Enter the names of two or more events below that you want to link separated by commas.");
//            System.out.println(currentCalendar.showAllEvents());
//            String input = in.nextLine();
//            String[] eventNames = input.split("\\w*,\\w*");
//
//            for (String name : eventNames) {
//                for (Event event : events) {
//                    if (name.equals(event.getName())) {
//                        new_link.add_events(event);
//                        event.addSeries(input_2);
//                    }
//                }
//            }
//            currentCalendar.addSeries(new_link);
//
//        } else {
//            System.out.println("Enter the name of existing Linked Event");
//            String input_3 = in.nextLine();
//
//            for (int i = 0; i < currentCalendar.getSeries().size(); i++) {
//                if (currentCalendar.getSeries().get(i).getSeriesName().equals(input_3)) {
//                    existingSeries = linkedSeries.get(i);
//                    break;
//                }
//            }
//
//            if (existingSeries == null){ //name does not exist
//                System.out.println("Name does not exist");
//
//            }else{
//                ArrayList<Event> events = currentCalendar.getEvents();
//                System.out.println("Enter the names of two or more events below that you want to link separated by commas.");
//                System.out.println(currentCalendar.showAllEvents());
//                String input = in.nextLine();
//                String[] eventNames = input.split(",");
//
//                for (String name : eventNames) {
//                    for (Event event : events) {
//                        if (name.equals(event.getName())) {
//                            existingSeries.add_events(event);
//                            event.addSeries(input_3);
//                        }
//                    }
//                }
//                currentCalendar.addSeries(existingSeries);
//            }
//            }
//
//
//            }
//
//
//
//
//    public void viewEventMenu(){
//        System.out.println("\nEvent menu\n Press 1 to view past event\nPress 2 to view current events" +
//                "\nPress 3 to view today's events \nPress 4 to view future event" +
//                "\nPress 5 to view all events\nPress 6 open search menu\nPress 7 to go back");
//        String choice = in.nextLine();
//        ArrayList<Event> events;
//        switch(choice){
//            case "1":
//                //this.currentCalendar.search("past", )
//                events = this.currentCalendar.search("past", currentDate);
//                break;
//            case "2":
//                events = this.currentCalendar.search("current", currentDate);
//                break;
//            case "3":
//                events = this.currentCalendar.search("any", currentDate);
//                break;
//            case "4":
//                events = this.currentCalendar.search("future", currentDate);
//                break;
//            case "5":
//                events = this.currentCalendar.search("all", "");
//                break;
//            case "6":
//                menuStack.push("searchMenu");
//                break;
//            case "7":
//                menuStack.pop();
//                break;
//        }
//    }
//
//    public void searchMenu(){
//        System.out.println("\nSearch menu\n Press 1 to search by day\nPress 2 to search by tag\n" +
//                "Press 3 to search by series name\n Press 4 to search events by name");
//        String choice = in.nextLine();
//        ArrayList<Event> events;
//        boolean format = false;
//        LocalDateTime date = LocalDateTime.now();
//        switch(choice){
//            case "1":
//                do {
//                    try {
//
//                        System.out.println("Enter the day you want to search (yyyy-mm-dd)");
//                        choice = in.nextLine();
//                        date = LocalDateTime.parse(choice);
//                        format = true;
//                    } catch (DateTimeParseException e) {
//                        System.out.println("Wrong format");
//                        format = false;
//                    }
//                }while(!format);
//                 events = currentCalendar.search("date", date);
//                break;
//            case "2":
//                System.out.println("Enter the tag you want to search");
//                choice = in.nextLine();
//                events = currentCalendar.search("tag", choice);
//                break;
//            case "3":
//                System.out.println("Enter the series name you want to search");
//                choice = in.nextLine();
//                events = currentCalendar.search("series_name", choice);
//                break;
//        }
//
//        System.out.println("\nPress any key to continue");
//        choice = in.nextLine();
//    }
//
//    private void accountGetter()  {
//        boolean valid = false;
//        boolean available = false;
//        do {
//            System.out.println("Enter a username");
//            username = in.nextLine();
//            username = username.trim();
//            available = userManager.userNameAvailable(username);
//            valid = username.matches("^[^,]\\w+[^,]$");
//            if(!valid){
//                System.out.println("Wrong format");
//            }
//            if(!available){
//                System.out.println("Username taken");
//            }
//        }while(!available || !valid);
//        do {
//            System.out.println("Enter your email");
//            email = in.nextLine();
//            available = userManager.emailAvailable(email);
//            valid = email.matches("^[^,]\\w+@\\w+\\.(ca|com|net|org)$");
//            if(!valid){
//                System.out.println("Wrong format");
//            }
//            if(!available){
//                System.out.println("email taken");
//            }
//        }while(!available || !valid);
//        do {
//            System.out.println("Enter a password. Must be longer than 7 character");
//            password = in.nextLine();
//            valid = password.matches("^[^,][a-zA-Z0-9!@#$%&*]{6,}$");
//            if(!valid){
//                System.out.println("Wrong format");
//            }
//        }while(!valid);
//        userManager.createAccount(username, email, password);
//    }
//
//    public void mainMenu()  {
//            System.out.println("\nMain Menu\nEnter 1 to log in, 2 to create new account, -1 to exit");
//            String log = in.nextLine();
//        switch (log) {
//            case "1":
//                menuStack.push("logInMenu");
//                break;
//            case "2":
//                menuStack.push("accountGetter");
//                break;
//            case "-1":
//                menuStack.push("exit");
//                break;
//        }
//    }
//
//
//    private void logInMenu()  {
//        System.out.println("\nLogin Menu\nEnter your username, or enter -1 to go back to the main menu");
//        username = in.nextLine();
//        if (username.equals("-1")) {
//            menuStack = new Stack<String>();
//            menuStack.push("mainMenu");
//        } else {
//
//            System.out.println("Enter your password");
//            password = in.nextLine();
//
//            User user = this.userManager.logIn(username, password);
//            if (user != null) {
//                System.out.println("Log in success!");
//                this.currentUser = user;
//                menuStack.pop();
//                this.currentCalendar = this.currentUser.getCalendars().get(0);
//                this.currentCalendar.loadEvents(this.currentUser.getUsername());
//                menuStack.push("calendarMenu");
//                alertAnnoucements();
//            } else {
//                System.out.println("The username or password you entered is incorrect");
//            }
//        }
//    }
//}
