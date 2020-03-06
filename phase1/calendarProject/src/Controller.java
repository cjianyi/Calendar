import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.IOException;
import java.util.Scanner;
public class Controller {
    Scanner in;
    UserManager userManager;
    User currentUser;

    private static String username = "";
    private static String password = "";
    private static String email = "";
    private static boolean exit = false;
    private static boolean loggedIn = false;
    public Controller() throws IOException {
        this.in = new Scanner(System.in);
        this.userManager = new UserManager();
    }

    public void calendarMenu(){
        System.out.println("Your calendar");
        System.out.println("Press 1 to use the current date\n Press 2 to enter a date to view \n Press 3 to view all" +
                " past events\n Press 4 to view all future events\n Press 5 to view all events\n Press 6 to search " +
                "events by memo\n Press 7 to search events by tag\n Press 8 to search events by series title\n" +
                " Press 9 to search events by name");
    }

    private void accountGetter() throws IOException {
        boolean valid = false;
        do {
            System.out.println("Enter a username");
            username = this.in.nextLine();
            valid = userManager.userNameAvailable(username);
            if(!valid){
                System.out.println("Username taken");
            }
        }while(!valid);
        do {
            System.out.println("Enter your email");
            email = this.in.nextLine();
            valid = userManager.emailAvailable(email);
            if(!valid){
                System.out.println("email taken");
            }
        }while(!valid);
        do {
            System.out.println("Enter a password");
            password = this.in.nextLine();
            valid = password.matches("^[^,][a-zA-Z0-9!@#$%&*]*$");
        }while(!valid);
        userManager.createAccount(username, email, password);
    }

    public void mainMenu() throws IOException {
        exit = false;
        while(!exit) {
            System.out.println("Enter 1 to log in, 2 to create new account, -1 to exit");
            String log = this.in.nextLine();
            if (log.equals("1")) {
                this.logInMenu();
            } else if (log.equals("2")) {
                accountGetter();
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
