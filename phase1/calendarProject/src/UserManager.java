import java.util.ArrayList;

public class UserManager {

    public ArrayList<User> users;

    public UserManager() {
        this.users = new ArrayList<>();
    }

    public void createAccount(String username, String emailAddress, String password) {
        users.add(new User(username, emailAddress, password));
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

    public boolean passwordValid(String password){
        //place holder to make code
        if (!users.isEmpty()) {
            for (User user: users) {
                if (user.getPassword().equals(password))
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
    //user picks 1, then change username
    //user picks 2, then change emailAddress
    //user picks 3, then change password
    public void editAccount(User u) {

    }

    public void accept(User u) {

    }


}
