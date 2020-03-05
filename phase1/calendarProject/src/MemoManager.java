import java.util.ArrayList;

public class MemoManager {

    private ArrayList<Memo> memos;

    public MemoManager() {
        memos = new ArrayList<>();
    }

    public void createMemo(String text) {
        memos.add(new Memo(text));
    }

    public ArrayList<Memo> showMemos() {
        return memos;
    }

    public void deleteMemo(Memo m) {
        memos.remove(m);
    }
}
