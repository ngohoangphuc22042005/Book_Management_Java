package quanlysach.presentation.FindBookNXB_X;

import quanlysach.business.FindBookNXB_X.FindBookNXB_XUseCase;

public class FindBookNXB_XController {
    private final FindBookNXB_XUseCase useCase;
    private final FindBookNXB_XViewModel vm;

    public FindBookNXB_XController(FindBookNXB_XUseCase useCase, FindBookNXB_XViewModel vm) {
        this.useCase = useCase;
        this.vm = vm;
    }

    public void execute() {
        vm.setResult(useCase.execute());
    }
}
