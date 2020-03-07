import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.*;

public class Alert implements Comparable{

    private String description;
    private String date;
    private Boolean repeat;

    public Alert(String description, String date, Boolean repeat){
        this.description = description;
        this.date = date;
        this.repeat = repeat;

    }


    //set alert
    public static void addAlert(){
        Calendar.addAlert();

    }
    public static void deleteAlert(){
        Calendar.deleteAlert();

    }
    public static void editAlert(){
        Calendar.editAlert();

    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
