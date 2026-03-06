package quanlysach.persistence.OpenAddBookForm;

import java.util.ArrayList;
import java.util.List;

public class OpenAddBookFormDAO implements OpenAddBookFormGateway {

    @Override
    public List<BookTypeDTO> getAll() {
        // Tạm thời hard-code danh sách loại sách
        List<BookTypeDTO> list = new ArrayList<>();
        list.add(new BookTypeDTO(1, "GiaoKhoa", "Sách giáo khoa cho học sinh."));
        list.add(new BookTypeDTO(2, "ThamKhao", "Sách tham khảo cho mọi đối tượng."));
        return list;
    }
}
