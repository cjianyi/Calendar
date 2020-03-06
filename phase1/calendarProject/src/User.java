import java.util.ArrayList;

public class User {

    private String username;
    private String emailAddress;
    private String password;
    private ArrayList<Calendar> calendars;


    public User(String username, String emailAddress, String password) {
        calendars = new ArrayList<Calendar>();
        this.username = username;
        this.emailAddress = emailAddress;
        this.password = password;

    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    //Create a calendar
    public void createCalendar(String name) {
        Calendar cal = new Calendar (name);
        calendars.add(cal);
    }

    public void deleteCalendar(Calendar c) {
        for (int i = 0; i < calendars.size(); i++)
        {
            if (calendars.get(i) == c)
            {
                calendars.remove(i);
                break;
            }

        }
    }

    public void shareCalendar(Calendar c, User u) {
        u.calendars.add(c);
    }
}
