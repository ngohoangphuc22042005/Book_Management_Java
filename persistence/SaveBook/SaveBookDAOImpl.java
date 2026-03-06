package quanlysach.persistence.SaveBook;

import java.sql.Connection;
import java.sql.PreparedStatement;

import quanlysach.persistence.DBConnection;

public class SaveBookDAOImpl implements SaveBookGateway {
    @Override
    public void insert(BookInsertDTO dto) throws Exception {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO Sach (MaSach, NgayNhap, DonGia, SoLuong, NhaXuatBan, LoaiSach, Thue, TinhTrang) "
                       + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, dto.id);
            ps.setString(2, dto.importDate);
            ps.setDouble(3, dto.unitPrice);
            ps.setInt(4, dto.quantity);
            ps.setString(5, dto.publisher);
            ps.setString(6, dto.bookTypeId == 1 ? "GiaoKhoa" : "ThamKhao");
            if (dto.tax != null) ps.setDouble(7, dto.tax);
            else ps.setNull(7, java.sql.Types.DOUBLE);
            ps.setString(8, dto.status);
            ps.executeUpdate();
        }
    }
}
