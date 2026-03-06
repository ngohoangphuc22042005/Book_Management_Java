package quanlysach.persistence.FindBookNXB_X;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import quanlysach.persistence.BookDTO;
import quanlysach.persistence.DBConnection;

public class FindBookNXB_XDAOImpl implements FindBookNXB_XGateway {

    @Override
    public List<BookDTO> findAll() {
        List<BookDTO> list = new ArrayList<>();
        // 🔎 Giả định NXB cần tìm là "NXB X"
        String sql = "SELECT * FROM Sach WHERE `LoaiSach` = 'GiaoKhoa' AND `NhaXuatBan` = 'NXB X'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
