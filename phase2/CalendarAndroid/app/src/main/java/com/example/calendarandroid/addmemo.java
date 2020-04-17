package com.example.calendarandroid;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import javax.microedition.khronos.egl.EGLDisplay;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class addmemo extends AppCompatActivity implements View.OnClickListener {

    public static Event e;
    private Button back_ed;
    private EditText memo_des;
    private Button create_memo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creatememo);
        back_ed = findViewById(R.id.backed3);
        memo_des = findViewById(R.id.memo_message);
        create_memo = findViewById(R.id.creatememomess);
        e = EditeventActivity.get_e();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.creatememomess:
                String des = memo_des.getText().toString();
                Memo m = new Memo(des);
                e.addMemo(m);
                break;
            case R.id.backed3:
                Intent in = new Intent(this, EditeventActivity.class);
                startActivity(in);
        }
    }
}
