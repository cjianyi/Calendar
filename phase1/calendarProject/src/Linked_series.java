import java.util.*;
public class Linked_series extends Series {
    private String series_name;
    private ArrayList<Event> events;
    private String linked_name;

    public Linked_series(String series_name, ArrayList<Event> events, String linked_name){
        super(series_name);
        this.events = events;
        this.linked_name = linked_name;
    }

}
