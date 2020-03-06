import java.util.Date;

public class Duration_series extends Series implements Event {
    private String series_name;
    private Integer num_series;
    private Date duration; // end date only for now
    private String frequency;

    public Duration_series(String series_name, int num_series, Date duration, String frequency){
        super(series_name);
        this.num_series = num_series;
        this.duration = duration;
        this.frequency = frequency;
    }

    //public Event find_event(String event_name){} // find by event name
    //public Event find_event(Date d){}// find by event date

    public int get_num_series(){
        return this.num_series;
    }

    public void set_num_series(Integer num_series){
        this.num_series = num_series;
    }

    public Date get_duration(){
        return this.duration;
    }

    public void set_duration(Date duration){
        this.duration = duration;
    }


    public String get_frequency(){
        return this.frequency;
    }

    public void set_frequency(String frequency){
        this.frequency = frequency;
    }

    //user picks 1, then change series_name
    //user picks 2, then change num_series
    //user picks 3, then change duration
    //user picks 4, then change frequency
    public void editEvent(int user_input, Object user_change){
        switch (user_input){
            case 1:
                set_series_name((String) user_change);
                break;
            case 2:
                set_num_series((Integer) user_change );
                break;
            case 3:
                set_duration((Date) user_change);
                break;
            case 4:
                set_frequency((String) user_change);
                break;
            }
        }
    }



