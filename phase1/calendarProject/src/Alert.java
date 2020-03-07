import com.sun.org.apache.xpath.internal.operations.Bool;

import java.time.LocalDateTime;
import java.util.*;

public class Alert{

    private String description;
    private LocalDateTime date;
    private String frequency; //Limited to None, Per hour, Per 24 hours, Per 7 days

    public Alert(String description, LocalDateTime date, String frequency){
        this.description = description;
        this.date = date;
        this.frequency = frequency;
    }
    public void editFrequency(String new_frequency){
        this.frequency = new_frequency;
    }
    public String getFrequency(){
        return this.frequency;
    }

    public String getAlert(){
        return this.description;

    }

    public String alertFileFormatter() {
        StringBuilder s = new StringBuilder();
        s.append("{").append("'description':").append("'").append(this.description).append("',");
        return s.toString();
    }


    public void editAlert(String new_description){
        this.description =  new_description;


    }

}
