package quanlysach.presentation.SaveBook;

import java.util.ArrayList;
import java.util.List;

public class SaveBookPublisher {
    private static final List<SaveBookSubscriber> subscribers = new ArrayList<>();

    public void addSubscriber(SaveBookSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void removeSubscriber(SaveBookSubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void notifyBookSaved() {
        for (SaveBookSubscriber s : subscribers) {
            s.onBookSaved();
        }
    }
}
