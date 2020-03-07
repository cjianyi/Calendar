import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.text.DateFormat;

import java.time.LocalDateTime;

public class Controller {
    Scanner in;
    static java.lang.reflect.Method method;
    UserManager userManager;
    static EventManager eventManager;
    User currentUser;
    Calendar currentCalendar;
    LocalDateTime currentDate = LocalDateTime.now();

    static Stack<String> menuStack = new Stack<String>();


    private static String username = "";
    private static String password = "";
    private static String email = "";
    private static boolean exit = false;
    private static boolean loggedIn = false;

    private static LocalDateTime startDate;
    private static LocalDateTime endDate;
    private static ArrayList<String> tags;
    private static ArrayList<Alert> alerts;
    private static ArrayList<Memo> memos;

    public Controller()  {
        this.in = new Scanner(System.in);
        this.userManager = new UserManager();
        menuStack.push("mainMenu");
        System.out.println(currentDate);
    }

    public void displayMenu() {
        while (true) {
            switch (menuStack.peek()) {
                case "mainMenu":
                    mainMenu();
                    break;
                case "logInMenu":
                    logInMenu();
                    break;
                case "accountGetter":
                    accountGetter();
                    menuStack = new Stack<String>();
                    menuStack.push("mainMenu");
                    break;
                case "calendarMenu":
                    calendarMenu();
                    break;
                case "editorMenu":
                    editorMenu();

                    break;
                case "eventMenu":
                    eventMenu();
                    break;
                case "searchMenu":
                    searchMenu();
                    menuStack.pop();
                    break;
                case "createEventMenu":
                    createEventMenu();
                    menuStack.pop();
                    break;
                case "deleteEventMenu":
                    deleteEventMenu();
                    menuStack.pop();
                    break;
                case "editEventMenu":
                    editEventMenu();
                    menuStack.pop();
                    break;
                case "linkEventMenu":
                    linkEventMenu();
                    menuStack.pop();
                    break;
            }
        }
    }

    public void calendarMenu(){
        System.out.println("\nCalendar Menu\nPress 1 to open event editor\n Press 2 to open to events\n Press 3 to set " +
                "the current date to a day other than today");
        String choice = this.in.nextLine();
        if(choice.equals("1")){
            menuStack.push("editorMenu");
        }else if(choice.equals("2")){
            menuStack.push("eventMenu");
        }else if(choice.equals("3")){
            System.out.println("Enter a date to set");
            String date = this.in.nextLine();
            currentDate = LocalDateTime.parse(date);
        }
    }

    public void editorMenu(){
        System.out.println("\nEditor Menu\n Press 1 to create an event\nPress 2 to delete an event" +
                "\nPress 3 to edit an event\nPress 4 to link events");
        String choice = this.in.nextLine();
        switch (choice) {
            case "1":
                menuStack.push("createEventMenu");
                break;
            case "2":
                menuStack.push("deleteEventMenu");
                break;
            case "3":
                menuStack.push("editEventMenu");
                break;
            case "4":
                menuStack.push("linkEventMenu");
                break;
        }

    }

    public void createEventMenu(){
        //date, time, tag, memo, seriesame, alert, freq, duration
        System.out.println("Enter a start date");
        String startDay = this.in.nextLine();

        System.out.println("Enter a start time");
        String startTime = this.in.nextLine();
        startDate = LocalDateTime.parse(startDay + "T" + startTime);
        System.out.println(startDate);
        System.out.println("Enter an end date");
        String endDay = this.in.nextLine();

        System.out.println("Enter an end time");
        String endTime = this.in.nextLine();
        endDate = LocalDateTime.parse(endDay +"T"+ endTime);

        System.out.println("Enter a tag(s) for the event, separated by commas");
        String tag = this.in.nextLine();
        String[] tagged = tag.split("\\s*,\\s*");
        tags = new ArrayList<String>();
        Collections.addAll(tags, tagged);

        System.out.println("Would you like to add alert(s) to the event (y/n)");
        String choice = this.in.nextLine();
        if(choice.equals("y")){
            alertMenu(false);
        }

        System.out.println("Would you like this event to repeat? (y/n)");
        choice = this.in.nextLine();
        if(choice.equals("y")){
            repeatedEventMenu(false);
        }

        eventManager.createEvent(this.currentCalendar, this.currentUser.getUsername());
    }

    public void alertMenu(boolean edit){
        String answer; // representing whether the user wants to add another alert
        do {
            System.out.println("Enter a description for the alert");
            String description = this.in.nextLine();
            System.out.println("Enter a date");
            String date = this.in.nextLine();
            System.out.println("Enter a time");
            String time = this.in.nextLine();
            LocalDateTime datetime = LocalDateTime.parse(date + "T" + time);
            System.out.println("Do you want it to repeat? (y/n");
            String choice = this.in.nextLine();
            if (choice.equals("y")) {
                repeatedAlertMenu(description, datetime);
            } else {
                Alert alert = new Alert(description, datetime, "");
                alerts.add(alert);
            }
            System.out.println("Enter y for creating a new alert and n for not creating another one");
            answer = this.in.nextLine();
        } while (answer.equals("y"));
    }

    public void repeatedAlertMenu(String description, LocalDateTime date){
        System.out.println("Press 1 for daily\nPress 2 for weekly\nPress 3 for monthly\nPress 4 for yearly");
        String choice = this.in.nextLine();
        switch (choice){
            case "1":
                while (date.isBefore(endDate) || date.equals(endDate)){
                    Alert alert = new Alert(description, date, "daily");
                    alerts.add(alert);
                    date.plusDays(1);
                }
                break;
            case "2":
                while (date.isBefore(endDate) || date.equals(endDate)){
                    Alert alert = new Alert(description, date, "weekly");
                    alerts.add(alert);
                    date.plusDays(7);
                }
                break;
            case "3":
                while (date.isBefore(endDate) || date.equals(endDate)){
                    Alert alert = new Alert(description, date, "monthly");
                    alerts.add(alert);
                    date.plusMonths(1);
                }
                break;
            case "4":
                while (date.isBefore(endDate) || date.equals(endDate)){
                    Alert alert = new Alert(description, date, "yearly");
                    alerts.add(alert);
                    date.plusYears(1);
                }
                break;
        }
    }

    public void memoMenu(boolean edit){
        System.out.println("Enter the text for this memo");
        String text = this.in.nextLine();
        do{
            System.out.println("Choose a new Event");
            String event_name = this.in.nextLine();
            System.out.println("Enter y for choosing a new event ");
        }while();
    }

    public void repeatedEventMenu(boolean edit){
        System.out.println("Press 1 for daily\nPress 2 for weekly\nPress 3 for monthly\nPress 4 for yearly");
        String choice = this.in.nextLine();
    }

    public void deleteEventMenu(){

    }

    public void editEventMenu(){

    }

    public void linkEventMenu(){

    }

    public void eventMenu(){
        System.out.println("\nEvent menu\n Press 1 to view past event\nPress 2 to view current events" +
                "\nPress 3 to view today's events \nPress 4 to view future event\nPress 5 to view all events\nPress 6 open search menu");
        String choice = this.in.nextLine();
        ArrayList<Event> events;
        switch(choice){
            case "1":
                //this.currentCalendar.search("past", )
                events = this.currentCalendar.search("past", currentDate);
                break;
            case "2":
                events = this.currentCalendar.search("current", currentDate);
                break;
            case "3":
                events = this.currentCalendar.search("any", currentDate);
                break;
            case "4":
                events = this.currentCalendar.search("future", currentDate);
                break;
            case "5":
                events = this.currentCalendar.search("all", "");
                break;
            case "6":
                menuStack.push("searchMenu");
                break;
        }
    }

    public void searchMenu(){
        System.out.println("\nSearch menu\n Press 1 to search by day\nPress to search by tag\n" +
                "Press 3 to search by series name\n Press 4 to search events by name");
        String choice = this.in.nextLine();
        ArrayList<Event> events;
        switch(choice){
            case "1":
                System.out.println("Enter the day you want to search");
                choice = this.in.nextLine();
                 events = currentCalendar.search("date", choice);
                break;
            case "2":
                System.out.println("Enter the tag you want to search");
                choice = this.in.nextLine();
                events = currentCalendar.search("tag", choice);
                break;
            case "3":
                System.out.println("Enter the series name you want to search");
                choice = this.in.nextLine();
                events = currentCalendar.search("series_name", choice);
                break;
        }

        System.out.println("\nPress any key to continue");
        choice = this.in.nextLine();
    }

    private void accountGetter()  {
        boolean valid = false;
        boolean available = false;
        do {
            System.out.println("Enter a username");
            username = this.in.nextLine();
            username = username.trim();
            available = userManager.userNameAvailable(username);
            valid = username.matches("^[^,]\\w+[^,]$");
            if(!available || !valid){
                System.out.println("Username taken");
            }
        }while(!available || !valid);
        do {
            System.out.println("Enter your email");
            email = this.in.nextLine();
            available = userManager.emailAvailable(email);
            valid = email.matches("^[^,]\\w+@\\w+\\.(ca|com|net|org)$");
            if(!available || !valid){
                System.out.println("email taken");
            }
        }while(!available || !valid);
        do {
            System.out.println("Enter a password");
            password = this.in.nextLine();
            valid = password.matches("^[^,][a-zA-Z0-9!@#$%&*]{7,}$");
        }while(!valid);
        userManager.createAccount(username, email, password);
    }

    public void mainMenu()  {
            System.out.println("\nMain Menu\nEnter 1 to log in, 2 to create new account, -1 to exit");
            String log = this.in.nextLine();
            if (log.equals("1")) {
                menuStack.push("logInMenu");
            } else if (log.equals("2")) {
                menuStack.push("accountGetter");
            }else if (log.equals("-1")){
                exit = true;
            }
    }


    private void logInMenu()  {
        System.out.println("\nLogin Menu\nEnter your username or email, or enter -1 to go back to the main menu");
        username = this.in.nextLine();
        if (username.equals("-1")) {
            menuStack = new Stack<String>();
            menuStack.push("mainMenu");
        } else {

            System.out.println("Enter your password");
            password = this.in.nextLine();

            User user = this.userManager.logIn(username, password);
            if (user != null) {
                System.out.println("Log in success!");
                this.currentUser = user;
                menuStack.pop();
                this.currentCalendar = this.currentUser.getCalendars().get(0);
                menuStack.push("calendarMenu");
            } else {
                System.out.println("The username or password you entered is incorrect");
            }
        }
    }

}
