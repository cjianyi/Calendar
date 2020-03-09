import java.util.*;
public class Linked_series extends Series {
    /**
     * This class dealing with linked_series
     * @para series_name representing the name for linked events
     * @para events list of events linked together under the series name
     */
    private String series_name;
    private ArrayList<Event> events;

    public Linked_series(String series_name, ArrayList<Event> events){
        super(series_name);
        this.series_name = series_name;
        this.events = events;
    }

    public ArrayList get_events(){
        return this.events;
    }

    public String getlinkedSeriesname(){
        return this.series_name;
    }

    public void add_events(Event e){
        this.events.add(e);
    }

    public Object remove_event(Event e){
        for (int i = 0; i < this.events.size(); i++ ){
            if (this.events.get(i) == e){
                events.remove(i);
                return null;
            }
        }
        return ("Entered event is not in the series");
    }

    //user picks 1, then change series_name
    //user picks 2, then add an event
    //user picks 3, then remove an event
    public void editEvent(int user_input, Object user_change){
        switch (user_input){
            case 1:
                set_series_name((String) user_change);
            case 2:
                add_events((Event) user_change);
            case 3:
                remove_event((Event) user_change);
        }
    }




}
