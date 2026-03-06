package quanlysach.business.AvgPriceByType;

import quanlysach.persistence.AvgPriceByType.AvgPriceByTypeGateway;

public class AvgPriceByTypeUseCase {
    private final AvgPriceByTypeGateway gateway;
    public AvgPriceByTypeUseCase(AvgPriceByTypeGateway gateway) {
        this.gateway = gateway;
    }

    public ResAvgPrice execute(ReqAvgPrice req) {
        Double avg = gateway.calculate(req.type);
        return new ResAvgPrice(avg);
    }
}
