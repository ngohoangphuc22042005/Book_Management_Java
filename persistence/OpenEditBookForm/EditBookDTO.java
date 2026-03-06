package quanlysach.persistence.OpenEditBookForm;

import java.util.Date;

public class EditBookDTO {
    public String id;
    public Date importDate;
    public double unitPrice;
    public int quantity;
    public String publisher;
    public String typeName; // "GiaoKhoa" | "ThamKhao"
    public Double tax;
    public String status;
}
