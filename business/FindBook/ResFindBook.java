package quanlysach.business.FindBook;

import java.util.List;
import quanlysach.business.BookViewDTO;

public class ResFindBook {
    public final List<BookViewDTO> results;
    public ResFindBook(List<BookViewDTO> results) {
        this.results = results;
    }
}