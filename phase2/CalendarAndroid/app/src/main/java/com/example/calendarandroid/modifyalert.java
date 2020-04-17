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

public class modifyalert extends AppCompatActivity implements View.OnClickListener {
    public static Event e;
    private Button back_edit;
    private Button add_alert;
    private EditText alert_name;
    private Button remove_alert;
    private Button modify_alert;
    private TextView wrong_alert;
    public static Alert a;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_alert);
        e = EditeventActivity.get_e();
        back_edit = findViewById(R.id.backtoedevent);
        add_alert = findViewById(R.id.addalert);
        alert_name = findViewById(R.id.edalertname);
        remove_alert = findViewById(R.id.removealert);
        modify_alert = findViewById(R.id.modifyalert);
        wrong_alert = findViewById(R.id.wrong_name);


        TextWatcher verify_alert = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Object a = get_alert();
                if (a == null) {
                    wrong_alert.setText(R.string.wrong_alert);
                }
            }
        };

        alert_name.addTextChangedListener(verify_alert);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.removealert:
                Object a = get_alert();
                if (a == null){
                    wrong_alert.setText(R.string.wrong_alert);
                }else{
                    e.removeAlert((Alert) a);
                }
            case R.id.addalert:
                Intent inte = new Intent(this, Creationalertmenu.class);
                startActivity(inte);
            case R.id.modifyalert:
                Intent intent = new Intent(this, alert_modifymenu.class);
                startActivity(intent);

        }

    }



    public Object get_alert(){
        String alertname = alert_name.getText().toString();
        for (Alert a: e.getAlerts()){
            if (a.getAlert() == alertname){
                this.a = a;
                return a;
            }
        }
        return null;
    }

    public static Alert getter_alert(){
        return a;
    }

}
