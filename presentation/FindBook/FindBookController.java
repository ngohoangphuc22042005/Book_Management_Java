package quanlysach.presentation.FindBook;

import quanlysach.business.FindBook.*;
import quanlysach.presentation.BookViewItem;

import java.text.SimpleDateFormat;
import java.util.*;

public class FindBookController {
    private final FindBookUseCase useCase;
    private final FindBookViewModel model;

    public FindBookController(FindBookUseCase useCase, FindBookViewModel model) {
        this.useCase = useCase;
        this.model = model;
    }

    public void find(String keyword) {
        ReqFindBook req = new ReqFindBook(keyword);
        ResFindBook res = useCase.execute(req);

        List<BookViewItem> items = new ArrayList<>();
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        int stt = 1;
        for (var dto : res.results) {
            BookViewItem item = new BookViewItem();
            item.stt = stt++;
            item.id = dto.id;
            item.importDate = fmt.format(dto.importDate);
            item.unitPrice = dto.unitPrice;
            item.quantity = dto.quantity;
            item.publisher = dto.publisher;
            item.type = dto.type;
            item.totalPrice = dto.totalPrice;
            items.add(item);
        }

        model.results = items;
        model.notifySubscribers();
    }
}