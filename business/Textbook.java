package quanlysach.business;

import java.util.Date;

public class Textbook extends Book {
    private String status; // "mới" hoặc "cũ"

    public Textbook(String id, Date importDate, double unitPrice, int quantity, String publisher, String status) {
        super(id, importDate, unitPrice, quantity, publisher);
        this.status = status.toLowerCase();
    }

    @Override
    public double calculateTotalPrice() {
        if ("moi".equalsIgnoreCase(status)) {
            return unitPrice * quantity;
        } else if ("cu".equalsIgnoreCase(status)) {
            return unitPrice * quantity * 0.5;
        } else {
            return 0.0;
        }
    }



    @Override
    public String getType() {
        return "Sách giáo khoa";
    }

    public String getStatus() {
        return status;
    }
}
