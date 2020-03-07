import java.util.Date;

public class Duration_series extends Series {
    private String series_name;
    private Integer num_series;
    private Date starttime;
    private Date endtime;
    private String frequency;

    public Duration_series(String series_name, int num_series, Date starttime, Date endtime,  String frequency){
        super(series_name);
        this.num_series = num_series;
        this.starttime = starttime;
        this.endtime = endtime;
        this.frequency = frequency;
    }

    public int get_num_series(){
        return this.num_series;
    }

    public void set_num_series(Integer num_series){
        this.num_series = num_series;
    }

    public Date get_starttime(){
        return this.starttime;
    }

    public Date get_endtime(){
        return this.endtime;
    }

    public void set_starttime(Date starttime){
        this.starttime = starttime;
    }

    public void set_endtime(Date endtime){
        this.endtime = endtime;
    }


    public String get_frequency(){
        return this.frequency;
    }

    public void set_frequency(String frequency){
        this.frequency = frequency;
    }

    //user picks 1, then change series_name
    //user picks 2, then change num_series
    //user picks 3, then change starttime
    //user picks 4, then change frequency
    //user picks 5, then change endtime
    public void editEvent(int user_input, Object user_change){
        switch (user_input){
            case 1:
                set_series_name((String) user_change);
                break;
            case 2:
                set_num_series((Integer) user_change );
                break;
            case 3:
                set_starttime((Date) user_change);
                break;
            case 4:
                set_endtime((Date) user_change);
                break;
            case 5:
                set_frequency((String) user_change);
                break;
            }
        }
    }



