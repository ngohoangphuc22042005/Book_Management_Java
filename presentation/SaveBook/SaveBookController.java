package quanlysach.presentation.SaveBook;

import quanlysach.business.SaveBook.ReqBook;
import quanlysach.business.SaveBook.SaveBookUseCase;
import quanlysach.persistence.SaveBook.SaveBookDAOImpl;

public class SaveBookController {
    private SaveBookPublisher publisher;

    public SaveBookController(SaveBookPublisher publisher) {
        this.publisher = publisher;
    }

    public boolean saveBook(ReqBook req) {
        SaveBookUseCase useCase = new SaveBookUseCase(new SaveBookDAOImpl());
        boolean success = useCase.execute(req);

        if (success && publisher != null) {
            publisher.notifyBookSaved();
        }
        return success;
    }
}
