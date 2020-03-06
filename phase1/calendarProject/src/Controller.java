import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import java.util.Stack;

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

    public void displayMenu() {
        while (true) {
            try {
                method = this.getClass().getMethod(menuStack.peek());
            } catch (SecurityException e) {
            } catch (NoSuchMethodException e) {
            }
            ;

            try {
                method.invoke(this);
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            }

            System.out.println("tsrhtrsh");
        }
    }

    public void calendarMenu(){
        System.out.println("Press 1 to open event editor\n Press 2 to open to events");
        String choice = this.in.nextLine();
        if(choice.equals("1")){
            editorMenu();
        }else if(choice.equals("2")){
            eventMenu();
        }
    }

    public void editorMenu(){
        System.out.println("Editor Menu\n Press 1 to create an event\nPress 2 to delete an event" +
                "\nPress 3 to edit an event");
    }

    public void eventMenu(){
        System.out.println("Event menu\n Press 1 to view past event\nPress 2 to view current events" +
                "\nPress 3 to view future event\nPress 4 to view all events\nPress 5 open search menu");
    }

    public void searchMenu(){
        System.out.println("Search menu\n Press 1 to search by day\nPress to search by tag\n" +
                "Press 3 to search by series name\n Press to search events by name");
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
        exit = false;
        while(!exit) {
            System.out.println("Enter 1 to log in, 2 to create new account, -1 to exit");
            String log = this.in.nextLine();
            if (log.equals("1")) {
                menuStack.push("logInMenu");
            } else if (log.equals("2")) {
                menuStack.push("accountGetter");
            }else if (log.equals("-1")){
                exit = true;
            }
        }
        if(loggedIn){
            calendarMenu();
        }
    }

    private void logInGetter() throws IOException {
        exit = false;
        System.out.println("Enter your username or email, or enter -1 to go back to the main menu");
        username = this.in.nextLine();
        if(username.equals("-1")){
            mainMenu();
            exit = true;
        }
        if(!exit) {
            System.out.println("Enter your password");
            password = this.in.nextLine();
        }
    }

    private void logInMenu() throws IOException {
        exit = false;
        while(!exit) {
            logInGetter();
            if (!exit) {
                User user = this.userManager.logIn(username, password);
                if (user != null) {
                    System.out.println("Log in success!");
                    this.currentUser = user;
                    loggedIn = true;
                    exit = true;
                } else {
                    System.out.println("The username or password you entered is incorrect");
                }
            }
        }
    }
}
