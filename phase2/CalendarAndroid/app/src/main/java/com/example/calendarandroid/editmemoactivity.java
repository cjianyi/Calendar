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
public class editmemoactivity extends AppCompatActivity implements View.OnClickListener{

    public static Memo m;
    private Button backed;
    private EditText memo_name;
    private Button edname;
    private EditText memo_memo;
    private Button addmemo;
    private Button removememo;
    private EditText memo_event;
    private Button addevent;
    private Button removeevent;
    private TextView wrongmemo;
    private TextView wrongevent;
    public static Calendar current;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editmemo);
        backed = findViewById(R.id.backed2);
        memo_name = findViewById(R.id.edmemoname);
        edname = findViewById(R.id.newmemoname);
        memo_memo = findViewById(R.id.edassociatedmemo);
        addmemo = findViewById(R.id.addmemo);
        removememo = findViewById(R.id.removememo);
        memo_event = findViewById(R.id.edassevent);
        addevent = findViewById(R.id.addassevent);
        removememo = findViewById(R.id.edremovevent);
        wrongmemo = findViewById(R.id.wrongmemoname);
        wrongevent = findViewById(R.id.wrongeventname);
        current = CalendarActivity.currentCalendar;
        m = modifymemo.get_m();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backed2:
                Intent intent = new Intent(this, EditeventActivity.class);
                startActivity(intent);
            case R.id.edname:
                String name = memo_name.getText().toString();
                m.editText(name);
            case R.id.addmemo:
                String id_s = memo_memo.getText().toString();
                try {
                    int id = Integer.parseInt(id_s);
                    for(Event e: current.getEvents()){
                        for (Memo m1 : e.getMemos()){
                            if (m1.getId() == id){
                                m.addAssociate(m1);
                                break;

                            }
                        }
                    }
                 //   wrongmemo.setText(R.string.wrong_memo);
                }catch (NumberFormatException nfe){
                 //   wrongmemo.setText(R.string.wrong_memo);
                    break;
                }

            case R.id.removememo:
                String id_1 = memo_memo.getText().toString();
                try {
                    int id = Integer.parseInt(id_1);
                    for (Memo m1 : m.getAssociatedMemos()){
                        if (m1.getId() == id){
                            m.removeAssociate(m1);
                            break;
                        }
                    }
                 //   wrongmemo.setText(R.string.wrong_memo);
                }catch (NumberFormatException nfe){
                 //   wrongmemo.setText(R.string.wrong_memo);
                    break;
                }

            case R.id.addassevent:
                String event_name = memo_event.getText().toString();
                for (Event e1 : current.getEvents()){
                    if (e1.getName() == event_name){
                        m.addAssociate(e1);
                        break;
                    }
                }
                wrongevent.setText(R.string.wrong_event_name);

            case R.id.edassevent:
                String e_name = memo_event.getText().toString();
                for (Event e2: m.getAssociatedEvents()){
                    if (e2.getName() == e_name){
                        m.removeAssociate(e2);
                        break;
                    }
                }
                wrongevent.setText(R.string.wrong_event_name);
        }
    }


}
