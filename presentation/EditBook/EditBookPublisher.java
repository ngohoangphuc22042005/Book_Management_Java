package quanlysach.presentation.EditBook;

import java.util.ArrayList;
import java.util.List;

public class EditBookPublisher {
    private final List<EditBookSubscriber> subs = new ArrayList<>();

    public void addSubscriber(EditBookSubscriber s) {
        if (s != null && !subs.contains(s)) subs.add(s);
    }

    public void removeSubscriber(EditBookSubscriber s) {
        subs.remove(s);
    }

    public void notifyEdited() {
        for (EditBookSubscriber s : new ArrayList<>(subs)) {
            try { s.onEdited(); } catch (Exception ignored) {}
        }
    }
}
