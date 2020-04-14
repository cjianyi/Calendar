package com.example.calendarandroid;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.w3c.dom.Text;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.ParseRelation;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.SaveCallback;

public class createAccountActivity extends AppCompatActivity {



    private EditText username;
    private EditText password;
    private EditText confirmPassword;

    private TextView usernameMessage;
    private TextView passwordMessage;
    private TextView confirmPasswordMessage;
    private TextView passwordErrorMessage;

    private Button signUp;

    private UserManager userManager = MainActivity.userManager;
    private ArrayList<String> usernames;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        ParseQuery<ParseObject>  query = ParseQuery.getQuery("_User");

        try {
            usernames = new ArrayList<>();
            List<ParseObject> users =query.find();
            for(ParseObject u: users){
                usernames.add(u.get("username").toString());
            }
        }catch(ParseException p){
            Log.d("users", "could not find");
        }


        username = findViewById(R.id.edCreateUserName);
        password = findViewById(R.id.edCreatedPassword);
        confirmPassword = findViewById(R.id.edConfirmPassword);

        usernameMessage = findViewById(R.id.tvCreateUserNameMessage);
        passwordMessage = findViewById(R.id.tvCreatePasswordMessage);
        confirmPasswordMessage = findViewById(R.id.tvConfirmPasswordMessage);
        passwordErrorMessage = findViewById(R.id.tvPasswordErrorMessage);

        TextWatcher usernameWtacher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String name = username.getText().toString();
                if (usernameVaild()){

                    if (!usernameAvailable()){
                        usernameMessage.setText(R.string.username_not_available);
                    }else{
                        usernameMessage.setText(R.string.username_available);
                    }
                }else if(!usernameVaild()){
                    usernameMessage.setText(R.string.create_username_error_message);
                }
            }
        };
        username.addTextChangedListener(usernameWtacher);

        TextWatcher passwordWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (passwordInvalid()){
                    passwordErrorMessage.setText(R.string.password_not_correct_format);
                }else{
                    passwordErrorMessage.setText(R.string.password_correct_format);
                }
            }
        };
        password.addTextChangedListener(passwordWatcher);

        TextWatcher passwordMatchWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String pass = password.getText().toString();
                String comfirm = confirmPassword.getText().toString();
                if(passwordsMatch()){
                    confirmPasswordMessage.setText(R.string.passwords_match);
                }
                else{
                    confirmPasswordMessage.setText(R.string.passwords_dont_match);
                }
            }
        };
        confirmPassword.addTextChangedListener(passwordMatchWatcher);
    }

    private boolean passwordsMatch(){
        String pass = password.getText().toString();
        String confirm = confirmPassword.getText().toString();
        return pass.equals(confirm);
    }

    private boolean usernameVaild(){
        String name = username.getText().toString();
        return (name.matches("^[A-Za-z0-9]{5,}$"));
    }

    private boolean usernameAvailable(){
        String name = username.getText().toString();
        return !usernames.contains(name);
    }

    private boolean passwordInvalid(){
        String pass = password.getText().toString();
        return pass.matches("^([^,]{0,7}|[^,]{20,}|[^0-9]*|[^a-z]*|[^A-Z]*|[^!@#$%^&*]*)$");
    }

    public void createAccount(View view){
        final boolean exit = false;
        if (usernameVaild() && usernameAvailable() && !passwordInvalid() && passwordsMatch()){
//            userManager.createAccount(username.getText().toString(), "", password.getText().toString());

            final ParseUser user = new ParseUser();
// Set the user's username and password, which can be obtained by a forms
            user.setUsername(username.getText().toString());
            user.setPassword(password.getText().toString());
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {

                        Log.d("create account", "success");
                        ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
                            @Override
                            public void done(ParseUser parseUser, ParseException e) {
                                if (parseUser != null) {
                                    Log.d("login", "success");
                                    createCalendar();
                                    Log.d("calendar createed", "success");
                                    ParseUser.logOut();

                                } else {
                                    ParseUser.logOut();
                                    Toast.makeText(createAccountActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    } else {
                        Log.d("reate account", "failed");
                        ParseUser.logOut();
                        Toast.makeText(createAccountActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        finish();
    }

    private void createCalendar(){
        final ParseUser u = ParseUser.getCurrentUser();
        final ParseObject entity = new ParseObject("Calendar");
        entity.put("calendarName", u.getUsername() + "1");
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
}
