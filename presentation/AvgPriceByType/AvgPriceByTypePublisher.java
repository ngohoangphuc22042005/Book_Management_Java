package quanlysach.presentation.AvgPriceByType;

import java.util.ArrayList;
import java.util.List;

public class AvgPriceByTypePublisher {
    private List<AvgPriceByTypeSubscriber> subs = new ArrayList<>();

    public void addSubscriber(AvgPriceByTypeSubscriber sub) {
        subs.add(sub);
    }

    public void notifySubscribers() {
        for (AvgPriceByTypeSubscriber s : subs) {
            s.update();
        }
    }
}