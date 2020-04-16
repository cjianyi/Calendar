package com.example.calendarandroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    RadioGroup radioGroup1;
    RadioButton radioButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        radioGroup1 = (RadioGroup) findViewById(R.id.radioGroup1);
        radioButton = (RadioButton) findViewById(R.id.month_view);
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Intent in;
                switch (checkedId)
                {
                    case R.id.month_view:
                        in = new Intent(getBaseContext(), MonthViewActivity.class);
                        startActivity(in);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.week_view:
                        in = new Intent(getBaseContext(), WeekActivity.class);
                        startActivity(in);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.day_view:
                        in = new Intent(getBaseContext(), DateActivity.class);
                        startActivity(in);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.search_view:
                        in = new Intent(getBaseContext(), EditeventActivity.class);
                        startActivity(in);
                        overridePendingTransition(0, 0);
                        break;
                    default:
                        break;
                }
            }
        });
    }

}
