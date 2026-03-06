package quanlysach.presentation.EditBook;

import java.util.ArrayList;
import java.util.List;

public class EditBookViewModel {
    public boolean success;
    public String message;

    private final List<Runnable> subscribers = new ArrayList<>();

    public void addSubscriber(Runnable r) {
        if (r != null && !subscribers.contains(r)) subscribers.add(r);
    }

    public void notifySubscribers() {
        for (Runnable r : new ArrayList<>(subscribers)) {
            try { r.run(); } catch (Exception ignored) {}
        }
    }
}
