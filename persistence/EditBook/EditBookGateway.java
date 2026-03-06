package quanlysach.persistence.EditBook;

import quanlysach.business.EditBook.ReqEditBook;

public interface EditBookGateway {
    boolean updateBook(ReqEditBook req);
    boolean existsById(String id);
}
