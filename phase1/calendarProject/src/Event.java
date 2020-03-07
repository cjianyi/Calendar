import java.time.LocalDateTime;
import java.util.ArrayList;

public class Event implements Comparable<Event>{
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String name;
    private ArrayList<String> tags;
    private ArrayList<Alert> alerts;
    private ArrayList<Series> series;
    private Memo memo;

    public Event(LocalDateTime start, LocalDateTime end, String name) {
        this.startTime = start;
        this.endTime = end;
        this.name = name;
        tags = new ArrayList<String>();
        alerts = new ArrayList<Alert>();
        series = new ArrayList<Series>();
    }
    public LocalDateTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getName() {
        return  this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getTags() {
        return new ArrayList<String>(this.tags);
    }
    public void addTag(String tag) {
        this.tags.add(tag);
    }
    public void removeTag(String tag) {
        this.tags.remove(tag);
    }

    public ArrayList<Alert> getAlerts() {
        return new ArrayList<Alert>(this.alerts);
    }
    public void addAlert(Alert alert) {
        this.alerts.add(alert);
        // point alert to this event
    }
    public void removeAlert(Alert alert) {
        this.alerts.remove(alert);
    }

    public ArrayList<Series> getSeries() {
        return new ArrayList<Series>(this.series);
    }
    public void addSeries(Series series) {
        this.series.add(series);
    }
    public void removeSeries(Series series) {
        this.series.remove(series);
    }
    public Memo getMemo() {
        return this.memo;
    }
    public void setMemo(Memo newMemo) {
        this.memo = newMemo;
    }


    @Override
    public int compareTo(Event e) {
        int equalStart = this.startTime.compareTo(e.startTime);
        int equalEnd = this.endTime.compareTo(e.endTime);
        if (equalStart != 0)
            return  equalStart;
        else if (equalEnd != 0)
            return equalEnd;
        else
            return this.name.compareTo(e.name);
    }
}
