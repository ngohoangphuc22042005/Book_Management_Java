package quanlysach.presentation.DeleteBook;

import quanlysach.business.DeleteBook.DeleteBookUseCase;
import quanlysach.business.DeleteBook.ReqDeleteBook;
import quanlysach.business.DeleteBook.ResDeleteBook;

public class DeleteBookController {
    private final DeleteBookUseCase useCase;

    public DeleteBookController(DeleteBookUseCase useCase) {
        this.useCase = useCase;
    }

    public ResDeleteBook deleteBook(String bookId) {
        ReqDeleteBook req = new ReqDeleteBook(bookId);
        return useCase.execute(req);
    }
}
