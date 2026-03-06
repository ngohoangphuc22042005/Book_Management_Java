package quanlysach.business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import quanlysach.persistence.BookDTO;
import quanlysach.persistence.BookListViewDAO;

public class BookListViewUseCase {
    private BookListViewDAO listViewDAO;

    public BookListViewUseCase(BookListViewDAO listViewDAO) {
        this.listViewDAO = listViewDAO;
    }

    public List<BookViewDTO> execute() throws SQLException, ClassNotFoundException {
        List<BookDTO> listDTO = listViewDAO.getAll();
        List<Book> books = convertToBusinessObjects(listDTO);
        return convertToViewDTO(books);
    }

    private List<Book> convertToBusinessObjects(List<BookDTO> dtos) {
        List<Book> books = new ArrayList<>();
        for (BookDTO dto : dtos) {
            Book book = BookFactory.createBook(dto);
            if (book != null) books.add(book);
        }
        return books;
    }



    private List<BookViewDTO> convertToViewDTO(List<Book> books) {
        List<BookViewDTO> viewList = new ArrayList<>();
        for (Book book : books) {
            BookViewDTO dto = new BookViewDTO();
            dto.id = book.getId();
            dto.importDate = book.getImportDate();
            dto.unitPrice = book.getUnitPrice(); // Gán giá trị
            dto.quantity = book.getQuantity();   // Gán giá trị
            dto.publisher = book.getPublisher(); // Gán giá trị
            dto.totalPrice = book.calculateTotalPrice(); // Gán giá trị

            if (book instanceof Textbook) {
                Textbook textbook = (Textbook) book;
                dto.type = "GiaoKhoa";
                dto.status = textbook.getStatus();
            } else if (book instanceof ReferenceBook) {
                ReferenceBook referenceBook = (ReferenceBook) book;
                dto.type = "ThamKhao";
            }
            viewList.add(dto);
        }
        return viewList;
    }

    
}
