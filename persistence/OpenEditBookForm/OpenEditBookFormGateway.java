package quanlysach.persistence.OpenEditBookForm;

import java.util.List;
import quanlysach.persistence.OpenAddBookForm.BookTypeDTO;

public interface OpenEditBookFormGateway {
    EditBookDTO findBookById(String id);
    List<BookTypeDTO> getAllTypes();
}
