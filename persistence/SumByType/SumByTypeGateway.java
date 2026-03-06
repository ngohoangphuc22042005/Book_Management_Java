package quanlysach.persistence.SumByType;

import java.util.List;
import quanlysach.business.SumByType.SumByTypeDTO;

public interface SumByTypeGateway {
    List<SumByTypeDTO> calculate();
}