import java.util.ArrayList;

public class Memo {

    private ArrayList<Event> associatedEvents;
    private String text;

    public Memo(String message) {
        associatedEvents = new ArrayList<>();
        this.text = message;
    }

    public void addAssociate(Event e) {
        associatedEvents.add(e);
    }

    public void removeAssociate(Event e) {
        associatedEvents.remove(e);
    }

    public void editText(String newMessage) {
        this.text = newMessage;
    }

    public ArrayList<Event> getAssociatedEvents() {
        return associatedEvents;
    }

}
