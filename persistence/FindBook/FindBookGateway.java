package quanlysach.persistence.FindBook;

import java.util.List;
import quanlysach.persistence.BookDTO;

public interface FindBookGateway {
    List<BookDTO> findByKeyword(String keyword);
}
