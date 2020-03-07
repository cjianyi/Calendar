import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;
public class Controller {
    Scanner in;
    static java.lang.reflect.Method method;
    UserManager userManager;
    User currentUser;
    Calendar currentCalendar;
    static Stack<String> menuStack = new Stack<String>();


    private static String username = "";
    private static String password = "";
    private static String email = "";
    private static boolean exit = false;
    private static boolean loggedIn = false;
    public Controller() throws IOException {
        this.in = new Scanner(System.in);
        this.userManager = new UserManager();
        menuStack.push("mainMenu");
    }

    public void displayMenu() throws IOException {
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
                    menuStack.pop();
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
        System.out.println("\nCalendar Menu\nPress 1 to open event editor\n Press 2 to open to events");
        String choice = this.in.nextLine();
        if(choice.equals("1")){
            menuStack.push("editorMenu");
        }else if(choice.equals("2")){
            menuStack.push("eventMenu");
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
    }

    public void deleteEventMenu(){

    }

    public void editEventMenu(){

    }

    public void linkEventMenu(){

    }

    public void eventMenu(){
        System.out.println("\nEvent menu\n Press 1 to view past event\nPress 2 to view current events" +
                "\nPress 3 to view future event\nPress 4 to view all events\nPress 5 open search menu");
        String choice = this.in.nextLine();
        switch(choice){
            case "1":
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                break;
            case "5":
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

    private void accountGetter() throws IOException {
        boolean valid = false;
        boolean available = false;
        do {
            System.out.println("Enter a username");
            username = this.in.nextLine();
            username.trim();
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

    public void mainMenu() throws IOException {
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


    private void logInMenu() throws IOException {
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
