package quanlysach.persistence.DeleteBook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import quanlysach.persistence.DBConnection;

public class DeleteBookDAOImpl implements DeleteBookGateway {

    @Override
    public boolean deleteById(String id) {
        String sql = "DELETE FROM Sach WHERE MaSach = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean existsById(String id) {
        String sql = "SELECT COUNT(*) FROM Sach WHERE MaSach = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
