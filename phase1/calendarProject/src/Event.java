import java.time.LocalDateTime;
import java.util.ArrayList;


public class Event implements Comparable<Event>{
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String name;
    private ArrayList<String> tags;
    private ArrayList<Alert> alerts;
    private ArrayList<String> series;
    private ArrayList<Memo> memos = new ArrayList<>();

    public Event(LocalDateTime start, LocalDateTime end, String name, ArrayList<String> tags,
                 ArrayList<Alert> alerts, String series) {
        this.startTime = start;
        this.endTime = end;
        this.name = name;
        this.tags = tags;
        this.alerts = alerts;
        this.series = new ArrayList<>();
        this.series.add(series);
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
        return new ArrayList<>(this.tags);
    }

    public void addTag(String tag) {
        this.tags.add(tag);
    }

    public void removeTag(String tag) {
        this.tags.remove(tag);
    }

    public ArrayList<Alert> getAlerts() {
        return new ArrayList<>(this.alerts);
    }

    public void addAlert(Alert alert) {
        this.alerts.add(alert);
        // point alert to this event
    }

    public void removeAlert(Alert alert) {
        this.alerts.remove(alert);
    }

    public ArrayList<String> getSeries() {
        return new ArrayList<>(this.series);
    }

    public void addSeries(String series) {
        this.series.add(series);
    }

    public void removeSeries(String name) {
        this.series.remove(name);
    }

    public ArrayList<Memo> getMemos() {
        return this.memos;
    }

    public void addMemo(Memo newMemo) {
        this.memos.add(newMemo);
    }
    
    public void removeMemo(Memo newMemo) {
        this.memos.remove(newMemo);
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

    /**
     *
     * @return a string representing the event in file
     */
    public String eventFileFormatter(){
        StringBuilder s = new StringBuilder();
        s.append("{ 'name': ").append("'").append(this.name).append("',");
        s.append("'startTime':").append("'").append(this.startTime.toString()).append("',");
        s.append("'endTime':").append("'").append(this.endTime.toString()).append("',");
        s.append("'alerts':[");
        for(Alert alert: this.alerts){
            s.append(alert.alertFileFormatter()).append(",");
        }
        if(s.charAt(s.length() - 1) == ',') {
            s.replace(s.length() - 1, s.length(), "");
        }
        s.append("],");
        s.append("'tags':[");
        for(String tag:this.tags){
            s.append(tag).append(",");
        }
        if(s.charAt(s.length() - 1) == ',') {
            s.replace(s.length() - 1, s.length(), "");
        }
        s.append("],");
        s.append("'memos':[");
        for(Memo memo:this.memos){
            s.append(memo.getId()).append(",");
        }
        if(s.charAt(s.length() - 1) == ',') {
            s.replace(s.length() - 1, s.length(), "");
        }
        s.append("],");
        s.append("'series':[");
        for(String serie: this.series){
            s.append(serie);
        }
        if(s.charAt(s.length() - 1) == ',') {
            s.replace(s.length() - 1, s.length(), "");
        }
        s.append("]}");
        return s.toString();
    }
}
