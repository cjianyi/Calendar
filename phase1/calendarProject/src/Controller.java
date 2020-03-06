import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.Scanner;
public class Controller {
    Scanner in;
    UserManager userManager;
    User currentUser;

    private static String username = "";
    private static String password = "";
    private static String email = "";


    public Controller() {
        this.in = new Scanner(System.in);
        this.userManager = new UserManager();
    }

    private void accountGetter(){
        do {
            System.out.println("Enter a username");
            username = this.in.nextLine();
        }while(!userManager.userNameAvailable(username));
        do {
            System.out.println("Enter your email");
            email = this.in.nextLine();
        }while(!userManager.emailAvailable(email));
        do {
            System.out.println("Enter a password");
            password = this.in.nextLine();
        }while(!userManager.passwordValid(password));
        userManager.createAccount(username, email, password);
    }

    public void mainMenu(){
        System.out.println("Enter 1 to log in, 2 to create new account");
        String log = this.in.nextLine();
        if(log.equals("1")) {
            this.logInMenu();
        }else if(log.equals("2")){
            accountGetter();
        }
    }

    private void logInGetter(){
        boolean exit = false;
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

    private void logInMenu(){
        boolean exit = false;
        while(!exit) {
            logInGetter();
            User user = this.userManager.logIn(username, password);
            if (user!= null) {
                System.out.println("Log in success!");
                this.currentUser = user;
                exit = true;
            } else {
                System.out.println("The username or password you entered is incorrect");
            }
        }
    }

}
