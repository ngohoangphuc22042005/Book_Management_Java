package quanlysach.business.OpenEditBookForm;

import java.util.ArrayList;
import java.util.List;

import quanlysach.persistence.OpenAddBookForm.BookTypeDTO;
import quanlysach.persistence.OpenEditBookForm.EditBookDTO;
import quanlysach.persistence.OpenEditBookForm.OpenEditBookFormGateway;
import quanlysach.business.OpenAddBookForm.ResBookTypeDTO;

public class OpenEditBookFormUseCase {
    private final OpenEditBookFormGateway gateway;

    public OpenEditBookFormUseCase(OpenEditBookFormGateway gateway) {
        this.gateway = gateway;
    }

    public OpenEditBookFormResponse execute(String bookId) {
        EditBookDTO dto = gateway.findBookById(bookId);
        List<BookTypeDTO> typeDTOs = gateway.getAllTypes();

        // convert book
        ResEditBookDTO book = new ResEditBookDTO();
        if (dto != null) {
            book.id = dto.id;
            book.importDate = dto.importDate;
            book.unitPrice = dto.unitPrice;
            book.quantity = dto.quantity;
            book.publisher = dto.publisher;
            book.typeName = dto.typeName; // "GiaoKhoa" | "ThamKhao"
            book.tax = dto.tax;
            book.status = dto.status;
        }

        // convert types
        List<ResBookTypeDTO> types = new ArrayList<>();
        for (BookTypeDTO t : typeDTOs) {
            ResBookTypeDTO x = new ResBookTypeDTO();
            x.id = t.id;
            x.name = t.name;
            x.description = t.description;
            types.add(x);
        }

        OpenEditBookFormResponse res = new OpenEditBookFormResponse();
        res.book = book;
        res.bookTypes = types;
        return res;
    }
}
