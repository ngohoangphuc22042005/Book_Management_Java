package quanlysach.presentation.FindBookNXB_X;

import java.util.List;
import quanlysach.business.FindBookNXB_X.ResBookNXB_XDTO;

public class FindBookNXB_XViewModel {
    public List<ResBookNXB_XDTO> result;
    private final FindBookNXB_XPublisher publisher = new FindBookNXB_XPublisher();

    public void setResult(List<ResBookNXB_XDTO> result) {
        this.result = result;
        publisher.notifySubscribers();
    }

    public void addSubscriber(FindBookNXB_XSubscriber sub) {
        publisher.addSubscriber(sub);
    }
}
