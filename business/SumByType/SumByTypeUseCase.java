package quanlysach.business.SumByType;

import java.util.List;
import quanlysach.persistence.SumByType.SumByTypeGateway;

public class SumByTypeUseCase {
    private final SumByTypeGateway gateway;

    public SumByTypeUseCase(SumByTypeGateway gateway) {
        this.gateway = gateway;
    }

    public ResSumByType execute(ReqSumByType req) {
        List<SumByTypeDTO> result = gateway.calculate();
        return new ResSumByType(result);
    }
}
