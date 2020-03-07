import java.util.ArrayList;
import java.io.*;

public class UserManager {

    public ArrayList<User> users;

    public UserManager() throws IOException {
        this.users = new ArrayList<>();
        File file = new File("src\\users.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        String[] user = new String[0];
        while ((st = br.readLine()) != null) {
            user = st.split("\\s*,");
            this.createAccount(user[0], user[1], user[2]);
        }
        br.close();
    }


    public void createAccount(String username, String emailAddress, String password) throws IOException {
        users.add(new User(username, emailAddress, password));
        FileWriter fw=new FileWriter("src\\users.txt");
        for(User user: users){
            fw.write(user.getUsername() + " ," + user.getEmailAddress() + " ," + user.getPassword() + "\n");
        }
        fw.close();
    }

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



    public void deleteAccount(User u) {
        for (int i = 0; i < users.size(); i++)
        {
            if (users.get(i) == u)
            {
                users.remove(u);
                break;
            }
        }
    }

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
    //phase 2
    //user picks 1, then change username
    //user picks 2, then change emailAddress
    //user picks 3, then change password
    public void editAccount(User u) {

    }
    //phase 2
    public void accept(User u) {

    }


}
