public class Series implements Event {
    private String series_name;
    public Series(String series_name){
        this.series_name = series_name;
    }

    public String get_event_name(){
        return this.series_name;
    }

    public void set_series_name(String series_name){
        this.series_name = series_name;
    }

    public void editEvent(String series_name){
        set_series_name(series_name);
    }
}