package com.example.calendarandroid;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.parse.LogInCallback;
import com.parse.ParseInstallation;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;

    private TextView passwordMessage;

    private Button loginButton;
    private Button signUpButton;

    public static UserManager userManager;

    public static User currentUser;

    public static Calendar currentCalendar;
    private boolean correct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ParseInstallation.getCurrentInstallation().saveInBackground();

        username = findViewById(R.id.edUserName);
        password = findViewById(R.id.edPassword);

        passwordMessage = findViewById(R.id.tvpasswordinfo);
        loginButton = findViewById(R.id.btnLogin);
        signUpButton = findViewById(R.id.btnSignUp);

        userManager = new UserManager(this);
    }
    String data = "";



    public void login(View view){
        final String currentUserName = username.getText().toString();
        final String currentPassword = password.getText().toString();
        this.correct = false;
//        currentUser = userManager.logIn(currentUserName, currentPassword);
//        if(currentUser != null){
//            passwordMessage.setText(R.string.correct_password_message);
//            currentCalendar = currentUser.getCalendars().get(0);
//            currentCalendar.loadEvents(currentUser.getUsername());
//            Intent intent = new Intent(this, MonthViewActivity.class);
//            startActivity(intent);
//        }else{
//            passwordMessage.setText(R.string.incorrect_password_message);
//        }


        ParseUser.logInInBackground(currentUserName, currentPassword, new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if (parseUser != null) {

                    Log.d("login", "success");
                    log(currentUserName, currentPassword);
//                    ParseUser.logOut();
                    correct = true;

                } else {

                    ParseUser.logOut();
                    Log.d("Login", "failed");
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void log(String username, String password){
//        currentUser = userManager.logIn(username, password);
//        if(currentUser != null){
//            passwordMessage.setText(R.string.correct_password_message);
//            currentCalendar = currentUser.getCalendars().get(0);
//            currentCalendar.loadEvents(currentUser.getUsername());
        currentCalendar = new Calendar("");
        Intent intent = new Intent(this, MonthViewActivity.class);
        startActivity(intent);
//        }else{
//            passwordMessage.setText(R.string.incorrect_password_message);
//        }
    }



    public void createAccount(View view){
        Intent intent = new Intent(this, createAccountActivity.class);
        startActivity(intent);
    }
}
