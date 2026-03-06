package quanlysach.persistence.EditBook;

import java.sql.*;
import quanlysach.business.EditBook.ReqEditBook;
import quanlysach.persistence.DBConnection;

public class EditBookDAO implements EditBookGateway {

    @Override
    public boolean updateBook(ReqEditBook req) {
        String sql = "UPDATE sach SET masach=?, ngaynhap=?, dongia=?, soluong=?, nhaxuatban=?, loaisach=?, thue=?, tinhtrang=? WHERE masach=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            System.out.println("📌 Đang cập nhật sách:");
            System.out.println("   Mã gốc       : " + req.originalId);
            System.out.println("   Mã mới       : " + req.id);
            System.out.println("   Ngày nhập    : " + req.importDate);
            System.out.println("   Đơn giá      : " + req.unitPrice);
            System.out.println("   Số lượng     : " + req.quantity);
            System.out.println("   Nhà xuất bản : " + req.publisher);
            System.out.println("   Loại sách    : " + req.bookTypeId);
            System.out.println("   Thuế         : " + req.tax);
            System.out.println("   Tình trạng   : " + req.status);

            ps.setString(1, req.id);
            ps.setString(2, req.importDate);
            ps.setDouble(3, parseDoubleSafe(req.unitPrice));
            ps.setInt(4, parseIntSafe(req.quantity));
            ps.setString(5, req.publisher);
            ps.setString(6, req.bookTypeId);
            ps.setDouble(7, parseDoubleSafeOrZero(req.tax));
            ps.setString(8, normalizeStatus(req.status)); // ✅ sửa đúng tình trạng
            ps.setString(9, req.originalId);

            int affectedRows = ps.executeUpdate();
            System.out.println("✅ Số dòng bị ảnh hưởng: " + affectedRows);
            return affectedRows > 0;

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("❌ Lỗi khi cập nhật sách:");
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean existsById(String id) {
        String sql = "SELECT COUNT(*) FROM sach WHERE masach = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private double parseDoubleSafe(String s) {
        try {
            return Double.parseDouble(s);
        } catch (Exception e) {
            return 0d;
        }
    }

    private double parseDoubleSafeOrZero(String s) {
        if (s == null || s.trim().isEmpty()) return 0d;
        return parseDoubleSafe(s);
    }

    private int parseIntSafe(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return 0;
        }
    }

    private String normalizeStatus(String s) {
        if ("1".equals(s)) return "GiaoKhoa";
        if ("2".equals(s)) return "ThamKhao";
        return s;
    }
}
