package quanlysach.business.OpenEditBookForm;

import java.util.Date;

public class ResEditBookDTO {
    public String id;
    public Date importDate;
    public double unitPrice;
    public int quantity;
    public String publisher;
    public String typeName; // "GiaoKhoa" | "ThamKhao"
    public Double tax;      // chỉ dùng nếu ThamKhao
    public String status;   // chỉ dùng nếu GiaoKhoa
}
