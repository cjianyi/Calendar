package com.example.calendarandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

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
                if (name.matches("^[A-Za-z0-9_]{5,20}$")){
                    if (!userManager.userNameAvailable(name)){
                        usernameMessage.setText(R.string.username_not_available);
                    }else{
                        usernameMessage.setText(R.string.username_available);
                    }
                }else{
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
                String pass = password.getText().toString();
                if (pass.matches("^([^,]{0,7}|[^,]{20,}|[^0-9]*|[^a-z]*|[^A-Z]*|[^!@#$%^&*]*)$")){
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
                if(pass.equals(comfirm)){
                    confirmPasswordMessage.setText(R.string.passwords_match);
                }
                else{
                    confirmPasswordMessage.setText(R.string.passwords_dont_match);
                }
            }
        };
        confirmPassword.addTextChangedListener(passwordMatchWatcher);
    }
}
