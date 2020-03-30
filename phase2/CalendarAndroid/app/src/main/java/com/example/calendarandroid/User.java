package com.example.calendarandroid;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class User {
    /** The username of a user. */
    private String username;
    /** The email address of a user. */
    private String emailAddress;
    /** The password of a user. */
    private String password;
    /** An array list that stores all the calendars that the user has. */
    private ArrayList<Calendar> calendars;

    /**
     * Constructor for User.
     * <p>
     * The username, emailAddress and password are from users.txt
     * if the program is loading information stored in that text file or from the user when
     * he or she is trying to create an account.
     * </p>
     *
     * @param username the username of an user
     * @param emailAddress the email address of an user
     * @param password the password of an user
     */
    public User(String username, String emailAddress, String password) {
        calendars = new ArrayList<>();
        this.username = username;
        this.emailAddress = emailAddress;
        this.password = password;
        this.calendars = new ArrayList<>();
        this.createCalendar("Calendar1");
    }

    /**
     * Gets the username of an user.
     *
     * @return returns the username of an user.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Gets the password of an user.
     *
     * @return returns the password of an user.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Gets the email address of an user.
     *
     * @return returns the emailAddress of an user.
     */
    public String getEmailAddress() {
        return this.emailAddress;
    }

    /**
     * Creates a calendar for a user.
     * <p>
     * Adds the calendar to the list of calendars that user has. Creates a new file
     * which stores the information of this calendar.
     * </p>
     *
     * @param name the name of that calendar
     */
    public void createCalendar(String name) {
        Calendar cal = new Calendar (name);
        this.calendars.add(cal);
        String fileName = "src\\" + this.username + "calendar" + name + ".txt";
        File file = new File(fileName);
        boolean exists;
        exists = file.exists();
        try {
            if (!exists) {
                System.out.println("test");
                file.createNewFile();
            }
        }catch(IOException e){}

    }

    /**
     * Gets all the calendars that the user has.
     *
     * @return returns an array list of calendars that the user has.
     */
    public ArrayList<Calendar> getCalendars(){
        return this.calendars;
    }

}
