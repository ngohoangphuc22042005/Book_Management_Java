package quanlysach.business.EditBook;

public class ReqEditBook {
    public String originalId;   // Mã sách gốc (dùng để xác định bản ghi cần sửa)
    public String id;           // Mã sách mới (nếu người dùng sửa)
    public String importDate;   // Ngày nhập sách (dd/MM/yyyy)
    public String unitPrice;    // Đơn giá
    public String quantity;     // Số lượng
    public String publisher;    // Nhà xuất bản
    public String bookTypeId;   // Mã loại sách
    public String tax;          // Thuế
    public String status;       // Tình trạng sách

    public ReqEditBook(String originalId, String id, String importDate, String unitPrice, String quantity,
                       String publisher, String bookTypeId, String tax, String status) {
        this.originalId = originalId;
        this.id = id;
        this.importDate = importDate;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.publisher = publisher;
        this.bookTypeId = bookTypeId;
        this.tax = tax;
        this.status = status;
    }
    public ReqEditBook() {}
}
