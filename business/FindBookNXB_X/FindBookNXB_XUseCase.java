package quanlysach.business.FindBookNXB_X;

import java.util.ArrayList;
import java.util.List;
import quanlysach.persistence.BookDTO;
import quanlysach.persistence.FindBookNXB_X.FindBookNXB_XGateway;

public class FindBookNXB_XUseCase {
    private final FindBookNXB_XGateway gateway;

    public FindBookNXB_XUseCase(FindBookNXB_XGateway gateway) {
        this.gateway = gateway;
    }

    public List<ResBookNXB_XDTO> execute() {
        List<BookDTO> rawList = gateway.findAll();
        List<ResBookNXB_XDTO> result = new ArrayList<>();
        for (BookDTO b : rawList) {
            result.add(new ResBookNXB_XDTO(
                b.id, b.type, b.publisher, b.unitPrice, b.quantity
            ));
        }
        return result;
    }
}
