package quanlysach.business.SaveBook;

import java.text.SimpleDateFormat;
import java.util.Date;

import quanlysach.persistence.SaveBook.SaveBookGateway;
import quanlysach.persistence.SaveBook.BookInsertDTO;

public class SaveBookUseCase {
    private SaveBookGateway gateway;

    public SaveBookUseCase(SaveBookGateway gateway) {
        this.gateway = gateway;
    }

    public boolean execute(ReqBook req) {
        try {
            BookInsertDTO dto = new BookInsertDTO();
            dto.id = req.id;

            // Chuyển dd/MM/yyyy sang yyyy-MM-dd
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(req.importDate);
            dto.importDate = new SimpleDateFormat("yyyy-MM-dd").format(date);

            dto.unitPrice = Double.parseDouble(req.unitPrice);
            dto.quantity = Integer.parseInt(req.quantity);
            dto.publisher = req.publisher;
            dto.bookTypeId = Integer.parseInt(req.bookTypeId);
            dto.tax = (req.tax != null && !req.tax.trim().isEmpty()) ? Double.parseDouble(req.tax) : null;
            dto.status = req.status;

            gateway.insert(dto);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
