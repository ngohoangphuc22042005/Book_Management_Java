package quanlysach.presentation.SaveBook;

public class SaveBookModel extends SaveBookPublisher {
    public String id;
    public String importDate;
    public String unitPrice;
    public String quantity;
    public String publisher;
    public String bookTypeId;
    public String tax;
    public String status;

    public void clear() {
        id = "";
        importDate = "";
        unitPrice = "";
        quantity = "";
        publisher = "";
        bookTypeId = "";
        tax = "";
        status = "";
        notifyBookSaved();
    }
}
