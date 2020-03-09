import java.util.ArrayList;
import java.io.*;

public class UserManager {
    /** This is an array list that stores all the users of the program. */
    public ArrayList<User> users;

    /**
     * Constructor for UserManager. It creates an arrayList of users and it loads
     * users' username, email, and password stored in users.txt to the program.
     */
    public UserManager()  {
        this.users = new ArrayList<>();
        File file = new File("src\\users.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            String[] user = new String[0];
            while ((st = br.readLine()) != null) {
                user = st.split("\\s*,");
                this.createAccount(user[0], user[1], user[2]);
            }
            br.close();
        }catch(IOException e){}
    }

    /**
     * Creates a user's account and stores the account to users.txt.
     * <p>
     * The method is called when a user wants to create an account or when the program
     * is loading users' information stored in users.txt.
     * </p>
     * @param username the username of an user
     * @param emailAddress the email of an user
     * @param password the password of an user
     */
    public void createAccount(String username, String emailAddress, String password)  {
        this.users.add(new User(username, emailAddress, password));
        try {
            FileWriter fw = new FileWriter("src\\users.txt");
            for (User user : users) {
                fw.write(user.getUsername() + " ," + user.getEmailAddress() + " ," + user.getPassword() + "\n");
            }
            fw.close();
        }catch(IOException e){}
    }

    /**
     * Checks if the user can use a specific username. If this username is used by another
     * user, then returns false; if no previous user uses this username, then returns true.
     *
     * @param username the username that the user wants to use
     * @return true if the username available; false otherwise
     */
    public boolean userNameAvailable(String username){
        //place holder to make code run
        if (!users.isEmpty()) {
            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if the user can use a specific email. If this email is used by another user,
     * then returns false; otherwise, returns true.
     *
     * @param email the email that the user wants to use
     * @return true if user can use the email; false otherwise
     */
    public boolean emailAvailable(String email){
        //place holder to make code run
        if (!users.isEmpty()) {
            for (User user : users) {
                if (user.getEmailAddress().equalsIgnoreCase(email))
                {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if the user inputted the correct username and password to login. Both username and
     * password must match the username and password stored in the user object for that user.
     *
     * @param username the username that the user inputted to login
     * @param password the password that the user inputted to login
     * @return true if both the username and password match; false otherwise.
     */
    public User logIn(String username, String password){
        //place holder to make code run
        // return new User(username, "holder", password);
        if (!users.isEmpty()) {
            for (User user : users) {
                if (user.getUsername().equals(username) &&
                        user.getPassword().equals(password)) {

                    return user;
                }
            }
        }
        return null;
    }

}
