package quanlysach.presentation;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import quanlysach.business.BookListViewUseCase;
import quanlysach.business.BookViewDTO;

public class BookListViewController {
    private BookViewModel model;
    private BookListViewUseCase listViewUseCase;

    public BookListViewController(BookViewModel model) {
        this.model = model;
    }

    public void setListViewUseCase(BookListViewUseCase listViewUseCase) {
        this.listViewUseCase = listViewUseCase;
    }

    public void execute() throws SQLException, ClassNotFoundException {
        List<BookViewDTO> dtoList = listViewUseCase.execute();
        List<BookViewItem> presenterList = convertToPresenter(dtoList);
        model.bookList = presenterList;
        model.notifySubscribers();
    }

    private List<BookViewItem> convertToPresenter(List<BookViewDTO> dtos) {
        List<BookViewItem> listViewItem = new ArrayList<>();
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        int stt = 1;
        for (BookViewDTO dto : dtos) {
            BookViewItem item = new BookViewItem();
            item.stt = stt++;
            item.id = dto.id;
            item.importDate = fmt.format(dto.importDate);
            item.unitPrice = dto.unitPrice;
            item.quantity = dto.quantity;
            item.publisher = dto.publisher;
            item.type = dto.type;
            item.totalPrice = dto.totalPrice;

            listViewItem.add(item);
        }
        return listViewItem;
    }
}