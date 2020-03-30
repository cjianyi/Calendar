package com.example.calendarandroid;

import java.util.ArrayList;

public class Memo {

    /** An ArrayList of Events this memo is associated with. */
    private ArrayList<Event> associatedEvents;
    /** An ArrayList of Memos this memo is associated with. */
    private ArrayList<Memo> associatedMemos;
    /** The content of this Memo. */
    private String text;
    /** The id number associated with this Memo. */
    private int idNum;

    /** A counter for instanced of this class created. */
    private static int idCounter;

    public Memo(String message) {
        associatedEvents = new ArrayList<>();
        associatedMemos = new ArrayList<>();
        this.text = message;
        idCounter += 1;
        this.idNum = idCounter;

    }

    /**
     * Get the contents of this Memo.
     *
     * @return the text in this Memo.
     */
    public String getText(){
        return this.text;
    }

    /**
     * Get the id of this memo.
     *
     * @return The id of this memo.
     */
    public int getId() {
        return this.idNum;
    }

    /**
     * Add an Event to associate with this Memo.
     *
     * @param e An Event.
     */
    public void addAssociate(Event e) {
        this.associatedEvents.add(e);
        e.addMemo(this);
    }

    /**
     * Add a Memo to associate with this Memo.
     *
     * @param m A memo.
     */
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

    public String showAllEvents() {
        String allEvents = "";
        for (Event e: associatedEvents)
        {
            allEvents = allEvents + e.getName() + "\n";
        }
        return allEvents;
    }

    public String showAllMemos() {
        String allEvents = "";
        for (Memo e: associatedMemos)
        {
            allEvents = allEvents + e.getId() + ": " + e.getText() + "\n";
        }
        return allEvents;
    }

}
