import java.util.Date;
public class Event {
    private String tag;
    private Date date; // end date only for duration series and start date for the rest types of event
    private String name;
    private String serieName;

    public String getTag(){
        return this.tag;
    }

    public Date getDate(){
        return this.date;
    }

    public String getName(){
        return this.name;
    }

    public String getSeriesName(){
        return this.serieName;
    }

}























