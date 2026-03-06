package quanlysach.presentation.OpenAddBookForm;

import java.util.ArrayList;
import java.util.List;

import quanlysach.business.OpenAddBookForm.OpenAddBookFormUseCase;
import quanlysach.business.OpenAddBookForm.ResBookTypeDTO;

public class OpenAddBookFormController {
    private OpenAddBookFormModel model;
    private OpenAddBookFormUseCase uc;

    public OpenAddBookFormController(OpenAddBookFormModel model, OpenAddBookFormUseCase uc) {
        this.model = model;
        this.uc = uc;
    }

    public void execute() {
        List<ResBookTypeDTO> resList = uc.execute();
        model.bookTypeItems = convertToPresenter(resList);
        model.notifySubscribers();
    }

    private List<BookTypeItem> convertToPresenter(List<ResBookTypeDTO> resList) {
        List<BookTypeItem> list = new ArrayList<>();
        for (ResBookTypeDTO dto : resList) {
            BookTypeItem item = new BookTypeItem();
            item.id = String.valueOf(dto.id);
            item.name = dto.name;
            list.add(item);
        }
        return list;
    }
}

