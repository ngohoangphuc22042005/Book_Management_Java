package quanlysach.persistence.AvgPriceByType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import quanlysach.persistence.DBConnection;

public class AvgPriceByTypeDAOImpl implements AvgPriceByTypeGateway {

    @Override
    public Double calculate(String type) {
        // Dùng toán tử = thay vì LIKE để tránh sai lệch
        String sql = "SELECT AVG(DonGia) AS avgPrice FROM Sach WHERE LoaiSach = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, type); // Truyền đúng loại sách (ví dụ: "ThamKhao")

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                double avg = rs.getDouble("avgPrice");
                if (rs.wasNull()) {
                    return 0.0; // Không có dữ liệu khớp
                }
                return avg;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0; // Fallback mặc định nếu có lỗi
    }
}
