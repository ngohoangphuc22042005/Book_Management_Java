package quanlysach.presentation.OpenEditBookForm;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import quanlysach.business.OpenAddBookForm.ResBookTypeDTO;
import quanlysach.business.OpenEditBookForm.OpenEditBookFormResponse;
import quanlysach.business.OpenEditBookForm.OpenEditBookFormUseCase;

import quanlysach.presentation.OpenAddBookForm.BookTypeItem;

public class OpenEditBookFormController {
    private final OpenEditBookFormModel model;
    private final OpenEditBookFormUseCase useCase;

    public OpenEditBookFormController(OpenEditBookFormModel model, OpenEditBookFormUseCase useCase) {
        this.model = model;
        this.useCase = useCase;
    }

    public void execute(String bookId) {
        OpenEditBookFormResponse res = useCase.execute(bookId);

        // Map Book
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        if (res.book != null) {
            model.id = res.book.id;
            model.importDate = (res.book.importDate != null) ? fmt.format(res.book.importDate) : "";
            model.unitPrice = String.valueOf(res.book.unitPrice);
            model.quantity = String.valueOf(res.book.quantity);
            model.publisher = res.book.publisher != null ? res.book.publisher : "";
            model.tax = (res.book.tax != null) ? String.valueOf(res.book.tax) : "";
            model.status = (res.book.status != null) ? res.book.status : "";
            model.currentTypeName = res.book.typeName != null ? res.book.typeName : "";
        } else {
            // không tìm thấy -> để trống
            model.id = "";
            model.importDate = "";
            model.unitPrice = "";
            model.quantity = "";
            model.publisher = "";
            model.tax = "";
            model.status = "";
            model.currentTypeName = "";
        }

        // Map Types
        model.bookTypeItems = convertToPresenter(res.bookTypes);

        model.notifySubscribers();
    }

    private List<BookTypeItem> convertToPresenter(List<ResBookTypeDTO> resList) {
        List<BookTypeItem> list = new ArrayList<>();
        if (resList != null) {
            for (ResBookTypeDTO dto : resList) {
                BookTypeItem item = new BookTypeItem();
                item.id = String.valueOf(dto.id);
                item.name = dto.name;  // "GiaoKhoa" | "ThamKhao"
                list.add(item);
            }
        }
        return list;
    }
}
