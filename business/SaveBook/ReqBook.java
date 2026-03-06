package quanlysach.business.SaveBook;

public class ReqBook {
    public String id;
    public String importDate;
    public String unitPrice;
    public String quantity;
    public String publisher;
    public String bookTypeId;
    public String tax;
    public String status;

    public ReqBook(String id, String importDate, String unitPrice, String quantity,
                   String publisher, String bookTypeId, String tax, String status) {
        this.id = id;
        this.importDate = importDate;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.publisher = publisher;
        this.bookTypeId = bookTypeId;
        this.tax = tax;
        this.status = status;
    }
}
