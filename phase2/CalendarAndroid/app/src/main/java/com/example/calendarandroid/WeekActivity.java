package com.example.calendarandroid;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class WeekActivity extends AppCompatActivity implements View.OnClickListener {

    LocalDate currentDat;
    TextView weekNum;
    TextView yearNum;
    TextView mon, tue, wed, thu, fri, sat, sun;
    LinearLayout monmon, tuetue, wedwed, thuthu, frifri, satsat, sunsun;
    ArrayList<LinearLayout> layouts;
    ArrayList<String> days;
    ArrayList<TextView> alltexts;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);
        currentDat = LocalDate.now();
        days = new ArrayList<>();
        alltexts = new ArrayList<>();
        days.add("Sunday");
        days.add("Monday");
        days.add("Tuesday");
        days.add("Wednesday");
        days.add("Thursday");
        days.add("Friday");
        days.add("Saturday");

        /*ArrayList<String> days = new ArrayList<>();
        days.add(seven days)
         */
        Button back = findViewById(R.id.backward);
        Button front = findViewById(R.id.forward);
        weekNum = findViewById(R.id.num);
        yearNum = findViewById(R.id.year);
        yearNum.setText(currentDat.getYear());
        int weekN = currentDat.get(WeekFields.of(Locale.US).weekOfWeekBasedYear());
        weekNum.setText(weekN);
        back.setOnClickListener(this);
        front.setOnClickListener(this);

        /*mon = findViewById(R.id.mon);
        tue = findViewById(R.id.tue);
        wed = findViewById(R.id.wed);
        thu = findViewById(R.id.thu);
        fri = findViewById(R.id.fri);
        sat = findViewById(R.id.sat);
        sun = findViewById(R.id.sun);*/

        monmon = findViewById(R.id.monmon);
        tuetue = findViewById(R.id.tuetue);
        wedwed = findViewById(R.id.wedwed);
        thuthu = findViewById(R.id.thuthu);
        frifri = findViewById(R.id.frifri);
        satsat = findViewById(R.id.satsat);
        sunsun = findViewById(R.id.sunsun);
        layouts = new ArrayList<>();
        layouts.add(sunsun);
        layouts.add(monmon);
        layouts.add(tuetue);
        layouts.add(wedwed);
        layouts.add(thuthu);
        layouts.add(frifri);
        layouts.add(satsat);
        for (int i = 0; i < layouts.size(); i++)
        {
            layouts.get(i).removeAllViews();
            TextView t = new TextView(this);
            t.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            t.setText(days.get(i));
            t.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            t.setTypeface(null, Typeface.BOLD);
            layouts.get(i).addView(t);
        }
        for (int i = 1; i < 8; i++)
        {
            ArrayList<TextView> temp = intenseAlgorithms(currentDat, i);
            for (int j = 0; j < temp.size(); j++) {
                alltexts.add(temp.get(j));
                temp.get(j).setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                layouts.get(i).addView(temp.get(j));
            }
        }



        //RecyclerView recyclerView = findViewById(R.id.recyclerView2);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //RecyclerViewAdapter23 adapter = new RecyclerViewAdapter23(days);
        //recyclerView.setAdapter(adapter);
    }

    public ArrayList<TextView> intenseAlgorithms(LocalDate ld, int i) {
        ArrayList<TextView> temp = new ArrayList<>();
        System.out.println(ld.with(WeekFields.of(Locale.US).dayOfWeek(), i));
        /*
        for (Event e: events)
        {
            if (e.startTime > startDate && e.startTime < endDate)
                TextView t = new TextView(this);
                t.setText(e.name());
                t.setClickable(true);
                t.setOnClickListener(this);
                t.setId(pos);
                int pos = (int)(Math.random()*((200001))) + 100001;
                temp.add(t);

         */
        return temp;
    }


    @Override
    public void onClick(View v) {
        //int num = Integer.parseInt(String.valueOf(weekNum));
        if (v.getId() == R.id.backward)
        {
            currentDat.minusWeeks(1);
        }
        else if (v.getId() == R.id.forward)
        {
            currentDat.plusWeeks(1);
        }
        yearNum.setText(currentDat.getYear());
        int weekN = currentDat.get(WeekFields.of(Locale.US).weekOfWeekBasedYear());
        weekNum.setText(weekN);
        for (int k = 0; k < alltexts.size(); k++)
        {
            if (v.getId() == alltexts.get(k).getId())
            {
                //Intent intent = new Intent(this, ***.class);
                //startActivity(intent);
            }
        }
    }
}
