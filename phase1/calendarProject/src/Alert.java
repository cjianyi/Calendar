import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.*;

public class Alert implements{

    private String description;
    private String date;
    private Boolean repeat;

    public Alert(String description, String date, Boolean repeat){
        this.description = description;
        this.date = date;
        this.repeat = repeat;


    }



    public void editAlert(String new_description){
        this.description =  new_description;


    }

}
