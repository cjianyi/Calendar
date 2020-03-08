import com.sun.org.apache.xpath.internal.operations.Bool;

import java.time.LocalDateTime;
import java.util.*;

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

    public String alertFileFormatter() {
        StringBuilder s = new StringBuilder();
        s.append("{").append("'description':").append("'").append(this.description).append("',");
        s.append("'date':").append("'").append(this.date.toString()).append("'}");

        return s.toString();
    }


    public void editAlert(String new_description){
        this.description =  new_description;
    }
    public void editDate(LocalDateTime new_date){
        this.date = new_date;
    }

}
