import java.util.ArrayList;

public class UserManager {

    public ArrayList<User> users;

    public UserManager(){
        users = new ArrayList<>();
    }

    public void createAccount(String username, String emailAddress, String password) {
        users.add(new User(username, emailAddress, password));
    }

    public boolean userNameAvailable(String username){
        //place holder to make code run
        return true;
    }

    public boolean emailAvailable(String email){
        //place holder to make code run
        return true;
    }

    public boolean passwordValid(String password){
        //place holder to make code run
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

    public User logIn(String username, String passworrd){
        //place holder to make code run
        return new User(username, "holder", passworrd);
    }
    //user picks 1, then change username
    //user picks 2, then change emailAddress
    //user picks 3, then change password
    public void editAccount(User u) {

    }

    public void accept(User u) {

    }


}
