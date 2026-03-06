package quanlysach.presentation.EditBook;

import java.text.SimpleDateFormat;
import java.util.Date;
import quanlysach.business.EditBook.EditBookUseCase;
import quanlysach.business.EditBook.ReqEditBook;
import quanlysach.business.EditBook.ResEditBook;
import quanlysach.presentation.SaveBook.SaveBookPublisher;

public class EditBookController {
    private final EditBookUseCase useCase;
    private final SaveBookPublisher publisher;

    public EditBookController(EditBookUseCase useCase, SaveBookPublisher publisher) {
        this.useCase = useCase;
        this.publisher = publisher;
    }

    public boolean editBook(ReqEditBook req) {
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(req.importDate);
            req.importDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        } catch (Exception e) {
            System.out.println("❌ Lỗi định dạng ngày nhập: " + req.importDate);
            e.printStackTrace();
            return false;
        }

        ResEditBook res = useCase.execute(req);
        System.out.println(res.message);
        if (res.success) {
            if (publisher != null) {
                publisher.notifyBookSaved();
            }
            return true;
        }
        return false;
    }
}
