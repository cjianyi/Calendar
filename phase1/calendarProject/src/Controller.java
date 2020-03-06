import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.Scanner;
public class Controller {
    Scanner in;
    UserManager userManager;
    User currentUser;

    private static String username = "";
    private static String password = "";
    public Controller() {
        this.in = new Scanner(System.in);
        this.userManager = new UserManager();
    }

    public void mainMenu(){
        System.out.println("Enter 1 to log in, 2 to create new account");
        String log = this.in.nextLine();
        if(log.equals("1")) {
            this.logInMenu();
        }
    }

    private void logInGetter(){
        boolean exit = false;
        System.out.println("Enter your username or email, or enter 1 to go back to the main menu");
        username = this.in.nextLine();
        if(username.equals("1")){
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
                this.currentUser = user;
                exit = true;
            } else {
                System.out.println("The username or password you entered is incorrect");

            }
        }
    }


    public void getInput() {
        System.out.print("Username: ");
        String s1 = in.nextLine();
        System.out.print("Password: ");
        String s2 = in.nextLine();
        System.out.println(s1);
        System.out.println(s2);
    }
}
