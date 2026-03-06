package quanlysach.business.FindBookNXB_X;

public class ResBookNXB_XDTO {
    public String id;
    public String type;
    public String publisher;
    public double unitPrice;
    public int quantity;

    public ResBookNXB_XDTO(String id, String type, String publisher, double unitPrice, int quantity) {
        this.id = id;
        this.type = type;
        this.publisher = publisher;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }
}
