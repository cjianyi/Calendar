package com.example.calendarandroid;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.KeyStore;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class modifymemo extends AppCompatActivity implements View.OnClickListener {
    private Button backed;
    private TextView wrong_memo;
    private EditText memo_id;
    public static Memo m;
    private Button remove;
    private Button add;
    private Button edit;
    public static Event e;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifymemo);
        backed = findViewById(R.id.edback);
        wrong_memo = findViewById(R.id.wrongmemoname);
        memo_id = findViewById(R.id.edidmemo);
        remove = findViewById(R.id.removememo);
        add = findViewById(R.id.addmemo);
        //edit = findViewById(R.id.edidmemo);
        e = EditeventActivity.get_e();

        TextWatcher memo = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                boolean flag = verifyname();
                if (flag == false){
               //     wrong_memo.setText(R.string.wrong_memo_id);
                }
            }
        };
        memo_id.addTextChangedListener(memo);
    }



    public boolean verifyname(){
        try{
            String id_s = memo_id.getText().toString();
            int id = Integer.parseInt(id_s);
            for (Memo m1: e.getMemos()){
                if (m1.getId() == id){
                    m = m1;
                    return true;
                }
            }
            return false;
        }catch (NumberFormatException n){
            return false;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.removememo:
                boolean flag = verifyname();
                if (flag == true) {
                    e.removeMemo(m);
                }
            case R.id.addmemo:
                Intent ine = new Intent(this, addmemo.class);
            case R.id.editmemo:
                flag = verifyname();
                if (flag == true) {
                    Intent in = new Intent(this, editmemoactivity.class);
                    startActivity(in);
                }
                wrong_memo.setText(R.string.wrong_memo);
        }

    }
    public static Memo get_m(){return m;}

}
