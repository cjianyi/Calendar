package com.example.calendarandroid;

import android.util.Log;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Month implements Comparable<Month>{
    private ArrayList<Day> month;
    private int monthVal;
    private String monthName;
    private int wrapBeforeSize;
    private int wrapAfterSize;
    private Month next;
    private Month prev;

    Month(LocalDate d){
        this.month = new ArrayList<>();
        this.monthVal = d.getMonthValue();
        this.monthName = d.getMonth().toString();
        int m = d.getMonthValue();

        int currMonth = d.getDayOfMonth();
        while (d.getMonthValue() == m || d.getDayOfWeek().getValue() != 7){
            d = d.minusDays(1);
        }
        this.prev = this;
        this.next = this;
        //d.getMonthValue() <= m ||d.getDayOfWeek().getValue() != 5
        while(this.month.size() != 42){
            Day day = new Day(d);
            d = d.plusDays(1);
            month.add(day);
        }
    }

    public void setNext(Month m){
        this.next = m;
    }

    public void setPrev(Month m){
        this.prev = m;
    }

    public Month getNext(){
        return this.next;
    }

    public Month getPrev(){
        return this.prev;
    }

    public Month(ArrayList<Day> month){
        this.month = new ArrayList<>();
        this.month.addAll(month);
        this.wrapBeforeSize = this.findWrapBeforeSize();

        this.wrapAfterSize = this.findWrapAfterSize();

        this.monthName = month.get(0).getDay().getMonth().toString();
        this.monthVal = month.get(0).getDay().getMonthValue();


    }

    private int findWrapBeforeSize(){

        LocalDate d = this.month.get(0).getDay();
        int count = 0;
        while (d.getDayOfWeek().getValue() != 7){
            d = d.minusDays(1);
            count += 1;
        }

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


    @Override
    public int compareTo(Month o) {
        return this.getMonth().get(0).compareTo(o.getMonth().get(0));

    }
}
