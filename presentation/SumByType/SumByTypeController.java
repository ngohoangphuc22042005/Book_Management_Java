package quanlysach.presentation.SumByType;

import quanlysach.business.SumByType.*;

public class SumByTypeController {
    private final SumByTypeUseCase useCase;
    private final SumByTypeViewModel vm;

    public SumByTypeController(SumByTypeUseCase useCase, SumByTypeViewModel vm) {
        this.useCase = useCase;
        this.vm = vm;
    }

    public void execute() {
        ResSumByType res = useCase.execute(new ReqSumByType());
        vm.results = res.list;
        vm.notifySubscribers();
    }
}
