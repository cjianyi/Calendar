package com.example.calendarandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.ActionBar;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class CalendarActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    BottomNavigationView bottomNavigation;
    Fragment currentFragment;
    static Calendar currentCalendar= new Calendar(1);
    UserManager userManager;
    static EventManager eventManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        this.userManager = new UserManager(this);
        eventManager = new EventManager();
        bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        currentFragment = new MonthViewActivity();
        openFragment(new MonthViewActivity());
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            item -> {
                switch (item.getItemId()) {
                    case R.id.action_month:
                        openFragment(new MonthViewActivity());
                        return true;
                    case R.id.action_week:
                        openFragment(new WeekActivity());
                        return true;
                    case R.id.action_day:
                        openFragment(new DateActivity());
                        return true;
                    case R.id.action_search:

                        break;
                    case R.id.action_logout:
                        ParseUser.logOut();
                        finish();
                        break;
//                    case R.id.action_switch_calendars:
//                        break;
                }
                return false;
            };

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void createEvent(View view){
        Intent intent = new Intent(this, Addeventmenu.class);
        startActivity(intent);
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.tolol_bar, popup.getMenu());
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_cal:
                createCalendar();
                return true;
            case R.id.cal_switch:

                return true;
            default:
                return false;
        }
    }

    public void createCalendar(){
        this.userManager.createCalendar();
    }



}

