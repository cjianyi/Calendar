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

import org.w3c.dom.Text;

public class modifytag extends AppCompatActivity implements View.OnClickListener{
    public static Event e;
    private EditText tag_name;
    private Button back_edit;
    private Button remove_tag;
    private Button add_tag;
    private TextView wrong_tag;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifytag);
        tag_name = findViewById(R.id.edtagname);
        back_edit  = findViewById(R.id.backtoedevent);
        remove_tag = findViewById(R.id.removetag);
        add_tag = findViewById(R.id.addtag);
        e = EditeventActivity.get_e();
        wrong_tag = findViewById(R.id.wrong_tag);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addtag:
                add_tag();
            case R.id.removetag:
                boolean a = remove_tag();
                if (a == false){
                    wrong_tag.setText(R.id.wrong_tag);
                }
        }
    }



    public void add_tag(){
        String name = tag_name.getText().toString();
        e.addTag(name);
    }

    public boolean remove_tag(){
        String name = tag_name.getText().toString();
        boolean flag = false;
        for (String temp : e.getTags()){
            if (temp.equals(name)){
                e.removeTag(name);
                flag = true;
                break;
            }
        }
        return flag;
    }



}
