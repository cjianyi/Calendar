package com.group0290.calendar;

import java.time.LocalDateTime;
import java.util.Date;

public class Duration_series extends Series {
    /**
     * This class representing the duration class
     * @para series_name the name for the series
     * @para num_series total number of series in that duration
     * @para starttime
     * @para endtime
     * starttime and endtime together representing the duration
     * @para frequency representing the frequency of the series
     */
    private String series_name;
    private Integer num_series;
    private LocalDateTime starttime;
    private LocalDateTime endtime;
    private String frequency;

    public Duration_series(String series_name, int num_series, LocalDateTime starttime,
                           LocalDateTime endtime,  String frequency){
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

    public LocalDateTime get_starttime(){
        return this.starttime;
    }

    public LocalDateTime get_endtime(){
        return this.endtime;
    }

    public void set_starttime(LocalDateTime starttime){
        this.starttime = starttime;
    }

    public void set_endtime(LocalDateTime endtime){
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
                set_starttime((LocalDateTime) user_change);
                break;
            case 4:
                set_endtime((LocalDateTime) user_change);
                break;
            case 5:
                set_frequency((String) user_change);
                break;
            }
        }
    }



