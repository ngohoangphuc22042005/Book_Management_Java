package quanlysach.persistence.FindBook;

import java.sql.*;
import java.util.*;
import quanlysach.persistence.BookDTO;
import quanlysach.persistence.DBConnection;

public class FindBookDAOImpl implements FindBookGateway {
    @Override
    public List<BookDTO> findByKeyword(String keyword) {
        List<BookDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM Sach WHERE MaSach LIKE ? OR NhaXuatBan LIKE ? OR LoaiSach LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String kw = "%" + keyword + "%";
            ps.setString(1, kw);
            ps.setString(2, kw);
            ps.setString(3, kw);

            try (ResultSet rs = ps.executeQuery()) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
