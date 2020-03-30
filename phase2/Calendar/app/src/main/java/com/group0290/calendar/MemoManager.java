package com.group0290.calendar;

import java.util.ArrayList;

public class MemoManager {

    /** An ArrayList of Memos created by this class. */
    private ArrayList<Memo> memos;

    public MemoManager() {
        memos = new ArrayList<>();
    }

    /**
     * Create a new Memo and add to the list of Memos.
     *
     * @param idNum An id number to associate with the Memo.
     * @param text The text of the new Memo.
     */
    public void createMemo(int idNum, String text) {
        memos.add(new Memo(text));
    }

    /**
     * Get the list of memos created.
     *
     * @return ArrayList of Memos.
     */
    public ArrayList<Memo> showMemos() {
        return memos;
    }

    /**
     * Search for a memo by id. Throw exception if not found.
     *
     * @param idNum the id number of a Memo.
     * @return a Memo with the given id number if it is found.
     */
    public Memo getMemo(int idNum) {
        for (Memo m : memos) {
            if (m.getId() == idNum) {
                return m;
            }
        }
        throw new RuntimeException("No memo was found.");
    }

    /**
     * Remove the given memo from the ArrayList of Memos.
     *
     * @param m The Memo to be deleted.
     */
    public void deleteMemo(Memo m) {
        memos.remove(m);
    }
}
