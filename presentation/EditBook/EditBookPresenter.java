package quanlysach.presentation.EditBook;

import quanlysach.business.EditBook.ResEditBook;

public class EditBookPresenter {
    private final EditBookViewModel vm;

    public EditBookPresenter(EditBookViewModel vm) {
        this.vm = vm;
    }

    public void present(ResEditBook res) {
        vm.success = res.success;
        vm.message = res.message;
        vm.notifySubscribers();
    }
}
