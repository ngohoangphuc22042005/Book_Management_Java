package quanlysach.persistence.OpenEditBookForm;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import quanlysach.persistence.DBConnection;
import quanlysach.persistence.OpenAddBookForm.BookTypeDTO;

public class OpenEditBookFormDAO implements OpenEditBookFormGateway {

    @Override
    public EditBookDTO findBookById(String id) {
        EditBookDTO dto = null;
        String sql = "SELECT MaSach, NgayNhap, DonGia, SoLuong, NhaXuatBan, LoaiSach, Thue, TinhTrang " +
                     "FROM Sach WHERE MaSach = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    dto = new EditBookDTO();
                    dto.id = rs.getString("MaSach");
                    Date d = rs.getDate("NgayNhap");
                    if (d != null) dto.importDate = new java.util.Date(d.getTime());
                    dto.unitPrice = rs.getDouble("DonGia");
                    dto.quantity = rs.getInt("SoLuong");
                    dto.publisher = rs.getString("NhaXuatBan");
                    dto.typeName = rs.getString("LoaiSach"); // "GiaoKhoa" | "ThamKhao"
                    Object taxObj = rs.getObject("Thue");
                    dto.tax = (taxObj != null) ? rs.getDouble("Thue") : null;
                    dto.status = rs.getString("TinhTrang");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dto;
    }

    @Override
    public List<BookTypeDTO> getAllTypes() {
        // giữ y chang OpenAddBookFormDAO: hard-code 2 loại
        List<BookTypeDTO> list = new ArrayList<>();
        list.add(new BookTypeDTO(1, "GiaoKhoa", "Sách giáo khoa cho học sinh."));
        list.add(new BookTypeDTO(2, "ThamKhao", "Sách tham khảo cho mọi đối tượng."));
        return list;
    }
}
