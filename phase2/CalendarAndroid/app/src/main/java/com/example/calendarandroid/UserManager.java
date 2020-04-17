package com.example.calendarandroid;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.io.*;

public class UserManager  {
    /** This is an array list that stores all the users of the program. */
    private ArrayList<User> users;
    Context context;
    /**
     * Constructor for UserManager. It creates an arrayList of users and it loads
     * users' username, email, and password stored in users.txt to the program.
     */
    UserManager(Context context)  {
        this.context = context;

    }

    /**
     * Creates a user's account and stores the account to users.txt.
     * <p>
     * The method is called when a user wants to create an account or when the program
     * is loading users' information stored in users.txt.
     * </p>
     * @param username the username of an user
     *
     * @param password the password of an user
     */
    public boolean createAccount(String username,  String password)  {
        final ParseUser user = new ParseUser();
        // Set the user's username and password, which can be obtained by a forms
        user.setUsername(username);
        user.setPassword(password);
        try {
            user.signUp();
            ParseUser.logIn(username, password);
            createCalendar(1);
            Log.d("account creation", "failed");
            return true;
        } catch (ParseException e) {
            Log.d("acoount creation", "failed");
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public void createCalendar(int calNum){
        final ParseUser u = ParseUser.getCurrentUser();
        final ParseObject entity = new ParseObject("Calendar");
        entity.put("calendarName", u.getUsername() + String.valueOf(calNum));
        entity.put("userID", ParseUser.getCurrentUser());
        ParseRelation<ParseObject> r = u.getRelation("Calendars");


        try {
            entity.save();
            r.add(entity);
            u.save();
        }catch (ParseException e){
            Log.d("calendar creation", "save failed");
        }

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
     * Checks if the user inputted the correct username and password to login. Both username and
     * password must match the username and password stored in the user object for that user.
     *
     * @param username the username that the user inputted to login
     * @param password the password that the user inputted to login
     * @return true if both the username and password match; false otherwise.
     */
    public boolean logIn(String username, String password) {
        try{
            ParseUser user = ParseUser.logIn(username, password);
            Log.d("login", "success");
            return true;
        }catch(ParseException e){
            Log.d("login", "failed");
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }


    }


}
