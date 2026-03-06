package quanlysach.business.SumByType;

public class SumByTypeDTO {
    public String type;
    public int totalQuantity;
    public double totalPrice;

    public SumByTypeDTO(String type, int totalQuantity, double totalPrice) {
        this.type = type;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
    }
}