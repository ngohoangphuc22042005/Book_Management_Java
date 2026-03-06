package quanlysach.business.FindBook;

import java.util.*;
import quanlysach.business.*;
import quanlysach.persistence.BookDTO;
import quanlysach.persistence.FindBook.FindBookGateway;

public class FindBookUseCase {
    private final FindBookGateway gateway;

    public FindBookUseCase(FindBookGateway gateway) {
        this.gateway = gateway;
    }

    public ResFindBook execute(ReqFindBook req) {
        List<BookDTO> listDTO = gateway.findByKeyword(req.keyword);
        List<Book> books = new ArrayList<>();
        for (BookDTO dto : listDTO) {
            Book book = BookFactory.createBook(dto);
            if (book != null) books.add(book);
        }

        List<BookViewDTO> views = new ArrayList<>();
        for (Book book : books) {
            BookViewDTO v = new BookViewDTO();
            v.id = book.getId();
            v.importDate = book.getImportDate();
            v.unitPrice = book.getUnitPrice();
            v.quantity = book.getQuantity();
            v.publisher = book.getPublisher();
            v.totalPrice = book.calculateTotalPrice();
            v.type = book.getType();
            if (book instanceof Textbook) v.status = ((Textbook) book).getStatus();
            views.add(v);
        }
        return new ResFindBook(views);
    }
}