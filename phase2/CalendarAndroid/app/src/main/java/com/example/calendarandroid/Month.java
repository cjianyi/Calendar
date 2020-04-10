package com.example.calendarandroid;

import android.util.Log;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Month {
    private ArrayList<Day> month;
    private int monthVal;
    private String monthName;
    private int wrapBeforeSize;
    private int wrapAfterSize;

    Month(LocalDate d){
        this.month = new ArrayList<>();
        this.monthVal = d.getMonthValue();
        this.monthName = d.getMonth().toString();
        int m = d.getMonthValue();

        int currMonth = d.getDayOfMonth();
        while (d.getMonthValue() == m || d.getDayOfWeek().getValue() != 7){
            d = d.minusDays(1);
        }
        //d.getMonthValue() <= m ||d.getDayOfWeek().getValue() != 5
        while(this.month.size() != 42){
            Day day = new Day(d);
            d = d.plusDays(1);
            month.add(day);
        }
    }

    public Month(ArrayList<Day> month){
        this.month = new ArrayList<>();
        this.month.addAll(month);
        this.wrapBeforeSize = this.findWrapBeforeSize();

        this.wrapAfterSize = this.findWrapAfterSize();
        Log.d("month class", "wrap after");
        this.monthName = month.get(0).getDay().getMonth().toString();

    }

    private int findWrapBeforeSize(){

        LocalDate d = this.month.get(0).getDay();
        int count = 0;
        while (d.getDayOfWeek().getValue() != 7){
            d = d.minusDays(1);
            count += 1;
        }
        Log.d("month class", "wrap before end");
        return count;
    }

    private int findWrapAfterSize(){
        return 42 - this.month.size() - this.wrapBeforeSize;
    }

    public int getWrapBeforeSize(){
        return this.wrapBeforeSize;
    }

    public int getWrapAfterSize() {
        return wrapAfterSize;
    }

    String getMonthName(){
        return this.monthName;
    }

    public int getMonthVal(){
        return this.monthVal;
    }

    public ArrayList<Day> getMonth(){
        return this.month;
    }

    public void addDaysBefore(Collection days){
        this.month.addAll(0, days);
    }

    public void addDaysAfter(Collection days){
        this.month.addAll(days);
    }



}
