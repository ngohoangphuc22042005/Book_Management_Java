package quanlysach.business.DeleteBook;

import quanlysach.persistence.DeleteBook.DeleteBookGateway;

public class DeleteBookUseCase {
    private final DeleteBookGateway gateway;

    public DeleteBookUseCase(DeleteBookGateway gateway) {
        this.gateway = gateway;
    }

    public ResDeleteBook execute(ReqDeleteBook req) {
        if (req.bookId == null || req.bookId.trim().isEmpty()) {
            return new ResDeleteBook(false, "⚠️ Mã sách không hợp lệ.");
        }

        if (!gateway.existsById(req.bookId)) {
            return new ResDeleteBook(false, "❌ Không tìm thấy sách với mã: " + req.bookId);
        }

        boolean deleted = gateway.deleteById(req.bookId);
        return new ResDeleteBook(deleted, deleted ? "✅ Xóa thành công." : "⚠️ Không thể xóa sách.");
    }
}
