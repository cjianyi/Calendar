import java.util.ArrayList;

public class MemoManager {

    private ArrayList<Memo> memos;

    public MemoManager() {
        memos = new ArrayList<>();
    }

    public void createMemo(int idNum, String text) {
        memos.add(new Memo(text));
    }

    public ArrayList<Memo> showMemos() {
        return memos;
    }

    public Memo getMemo(int idNum) {
        for (Memo m : memos) {
            if (m.getId() == idNum) {
                return m;
            }
        }
        throw new RuntimeException("No memo was found.");
    }

    public void deleteMemo(Memo m) {
        memos.remove(m);
    }
}
