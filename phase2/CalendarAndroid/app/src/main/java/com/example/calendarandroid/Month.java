package com.example.calendarandroid;

import java.time.LocalDate;
import java.util.ArrayList;

public class Month {
    private ArrayList<Day> month;
    private int monthVal;
    private String monthName;

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
        this.month = month;
    }

    String getMonthName(){
        return this.monthName;
    }

    public int getMonthVal(){
        return this.monthVal;
    }

    ArrayList<Day> getMonth(){
        return this.month;
    }



}
