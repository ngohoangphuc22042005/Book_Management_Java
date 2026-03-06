package quanlysach.business.EditBook;

import quanlysach.persistence.EditBook.EditBookGateway;

public class EditBookUseCase {
    private final EditBookGateway gateway;

    public EditBookUseCase(EditBookGateway gateway) {
        this.gateway = gateway;
    }

    public ResEditBook execute(ReqEditBook req) {
        if (!gateway.existsById(req.originalId)) {
            return new ResEditBook(false, "❌ Không tìm thấy sách với mã: " + req.originalId);
        }

        try {
            boolean updated = gateway.updateBook(req);
            return new ResEditBook(updated, updated ? " Cập nhật thành công" : " Không có dòng nào bị ảnh hưởng");
        } catch (Exception e) {
            return new ResEditBook(false, "Lỗi khi cập nhật: " + e.getMessage());
        }
    }
}
