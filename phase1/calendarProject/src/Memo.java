import java.util.ArrayList;

public class Memo {

    private ArrayList<Event> associatedEvents;
    private ArrayList<Memo> associatedMemos;
    private String text;
    private int idNum;

    public Memo(int idNum, String message) {
        associatedEvents = new ArrayList<>();
        associatedMemos = new ArrayList<>();
        this.text = message;
        this.idNum = idNum;

    }

    public String getText(){
        return this.text;
    }

    public int getId() {
        return this.idNum;
    }

    public void addAssociate(Event e) {
        this.associatedEvents.add(e);
        e.addMemo(this);
    }

    public void addAssociate(Memo m) {
        this.associatedMemos.add(m);
    }

    public void removeAssociate(Event e) {
        this.associatedEvents.remove(e);
        e.removeMemo(this);
    }

    public void removeAssociate(Memo m) {
        this.associatedMemos.remove(m);
    }

    public void editText(String newMessage) {
        this.text = newMessage;
    }

    public ArrayList<Event> getAssociatedEvents() {
        return associatedEvents;
    }

    public ArrayList<Memo> getAssociatedMemos() {
        return associatedMemos;
    }

}
