package quanlysach.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookListViewDAO {

    public List<BookDTO> getAll() throws SQLException, ClassNotFoundException {
        List<BookDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM Sach";
        // Sử dụng try-with-resources để tự động đóng kết nối
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                BookDTO dto = new BookDTO();
                dto.id = rs.getString("MaSach");
                dto.importDate = rs.getDate("NgayNhap");
                dto.unitPrice = rs.getDouble("DonGia");
                dto.quantity = rs.getInt("SoLuong");
                dto.publisher = rs.getString("NhaXuatBan");
                dto.type = rs.getString("LoaiSach");
                dto.status = rs.getString("TinhTrang");
                dto.tax = rs.getObject("Thue") != null ? rs.getDouble("Thue") : null;
                list.add(dto);
            }
        }
        return list;
    }
}