package com.example.calendarandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class createAccountActivity extends AppCompatActivity {



    private EditText username;
    private EditText password;
    private EditText confirmPassword;

    private TextView usernameMessage;
    private TextView passwordMessage;
    private TextView confirmPasswordMessage;

    private Button signUp;

    private UserManager userManager = MainActivity.userManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        username = findViewById(R.id.edCreateUserName);
        password = findViewById(R.id.edCreatedPassword);
        confirmPassword = findViewById(R.id.edConfirmPassword);

        usernameMessage = findViewById(R.id.tvCreateUserNameMessage);
        passwordMessage = findViewById(R.id.tvCreatePasswordMessage);
        confirmPasswordMessage = findViewById(R.id.tvConfirmPasswordMessage);

        TextWatcher t = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                usernameMessage.setText(R.string.create_username_message);
            }

            @Override
            public void afterTextChanged(Editable s) {
                usernameMessage.setText(R.string.create_username_message);
            }
        };
        username.addTextChangedListener(t);
    }
}
