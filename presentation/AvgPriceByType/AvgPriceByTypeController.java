package quanlysach.presentation.AvgPriceByType;

import quanlysach.business.AvgPriceByType.*;

public class AvgPriceByTypeController {
    private final AvgPriceByTypeUseCase useCase;
    private final AvgPriceByTypeViewModel vm;

    public AvgPriceByTypeController(AvgPriceByTypeUseCase useCase, AvgPriceByTypeViewModel vm) {
        this.useCase = useCase;
        this.vm = vm;
    }

    public void execute(String type) {
        ResAvgPrice res = useCase.execute(new ReqAvgPrice(type));
        vm.avgPrice = res.avgPrice;
        vm.notifySubscribers();
    }
}
