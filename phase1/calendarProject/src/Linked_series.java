import java.util.*;
public class Linked_series extends Series implements Event{
    private String series_name;
    private ArrayList<Event> events;

    public Linked_series(String series_name, ArrayList<Event> events){
        super(series_name);
        this.events = events;
    }

    //public Event find_event(String event_name){} // find by event name
    //public Event find_event(Date d){}// find by event date
    //public Event modify_event(Event e){}// modify one of the event in the series

    public ArrayList get_events(){
        return this.events;
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
