import java.util.Date;

public class Duration_series extends Series {
    private String series_name;
    private int num_series;
    private Date duration;
    private String frequency;

    public Duration_series(String series_name, int num_series, Date duration, String frequency){
        super(series_name);
        this.num_series = num_series;
        this.duration = duration;
        this.frequency = frequency;
    }
}
