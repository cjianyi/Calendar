package com.example.calendarandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;

    private TextView passwordMessage;

    private Button loginButton;
    private Button signUpButton;

    public static UserManager userManager;
    
    public static User currentUser;

    public static Calendar currentCalendar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.edUserName);
        password = findViewById(R.id.edPassword);

        passwordMessage = findViewById(R.id.tvpasswordinfo);
        loginButton = findViewById(R.id.btnLogin);
        signUpButton = findViewById(R.id.btnSignUp);

        userManager = new UserManager(this);
    }
    String data = "";



    public void login(View view){
        String currentUserName = username.getText().toString();
        String currentPassword = password.getText().toString();
        currentUser = userManager.logIn(currentUserName, currentPassword);
        if(currentUser != null){
            passwordMessage.setText(R.string.correct_password_message);
            currentCalendar = currentUser.getCalendars().get(0);
            currentCalendar.loadEvents(currentUser.getUsername());
        }else{
            passwordMessage.setText(R.string.incorrect_password_message);
        }
    }

    public void createAccount(View view){
        Intent intent = new Intent(this, createAccountActivity.class);

        startActivity(intent);
    }
}
