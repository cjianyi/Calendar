package com.group0290.calendar;

import java.time.LocalDateTime;


public class Alert{

    private String description;
    private LocalDateTime date;


    public Alert(String description, LocalDateTime date){
        this.description = description;
        this.date = date;


    }


    public String getAlert(){
        return this.description;
    }

    public LocalDateTime getDate(){
        return this.date;
    }

    public String alertFileFormatter() {
        return "{" + "'description':" + "'" + this.description + "'," +
                "'date':" + "'" + this.date.toString() + "'}";
    }


    public void editAlert(String new_description){
        this.description =  new_description;
    }
    public void editDate(LocalDateTime new_date){
        this.date = new_date;
    }

}
