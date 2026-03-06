package quanlysach.business.DeleteBook;

import quanlysach.persistence.DeleteBook.DeleteBookDAOImpl;
import quanlysach.presentation.DeleteBook.DeleteBookController;

public class DeleteBookFactory {
    public static DeleteBookController createController() {
        DeleteBookUseCase useCase = new DeleteBookUseCase(new DeleteBookDAOImpl());
        return new DeleteBookController(useCase);
    }
}
