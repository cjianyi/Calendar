import java.util.ArrayList;

public class Memo {

    private ArrayList<Event> associatedEvents;
    private String text;

    public Memo(String message) {
        associatedEvents = new ArrayList<>();
        this.text = message;
    }

    public void addAssociate(Event e) {
        this.associatedEvents.add(e);
        e.addMemo(this);
    }

    // not yet implemented in Event
    public void removeAssociate(Event e) {
        this.associatedEvents.remove(e);
        e.removeMemo(this);
    }

    public void editText(String newMessage) {
        this.text = newMessage;
    }

    public ArrayList<Event> getAssociatedEvents() {
        return associatedEvents;
    }

}
