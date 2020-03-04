import java.util.ArrayList;

public class UserManager {
    //sdsdfsdfnsdfnb
    public ArrayList<User> users;

    public void createAccount(String username, String emailAddress, String password) {
        users.add(new User(username, emailAddress, password));

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
    //user picks 1, then change username
    //user picks 2, then change emailAddress
    //user picks 3, then change password
    public void editAccount(User u) {

    }

    public void accept(User u) {

    }


}
