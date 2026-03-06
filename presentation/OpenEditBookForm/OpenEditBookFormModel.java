package quanlysach.presentation.OpenEditBookForm;

import java.util.List;
import quanlysach.presentation.Publisher;
import quanlysach.presentation.OpenAddBookForm.BookTypeItem;

public class OpenEditBookFormModel extends Publisher {
    public String id;
    public String importDate; // dd/MM/yyyy
    public String unitPrice;
    public String quantity;
    public String publisher;
    public String tax;
    public String status;

    public String currentTypeName; // "GiaoKhoa" | "ThamKhao"
    public java.util.List<BookTypeItem> bookTypeItems;
}
