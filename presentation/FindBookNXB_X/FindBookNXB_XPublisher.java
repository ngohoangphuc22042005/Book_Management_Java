package quanlysach.presentation.FindBookNXB_X;

import java.util.ArrayList;
import java.util.List;

public class FindBookNXB_XPublisher {
    private final List<FindBookNXB_XSubscriber> subscribers = new ArrayList<>();

    public void addSubscriber(FindBookNXB_XSubscriber sub) {
        subscribers.add(sub);
    }

    public void notifySubscribers() {
        for (FindBookNXB_XSubscriber s : subscribers) {
            s.update();
        }
    }
}
