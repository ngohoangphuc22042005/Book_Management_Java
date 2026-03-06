package quanlysach.persistence.SumByType;

import java.sql.*;
import java.util.*;
import quanlysach.business.SumByType.SumByTypeDTO;
import quanlysach.persistence.DBConnection;

public class SumByTypeDAOImpl implements SumByTypeGateway {
    @Override
    public List<SumByTypeDTO> calculate() {
        List<SumByTypeDTO> list = new ArrayList<>();
        String sql = "SELECT LoaiSach, " +
                     "SUM(SoLuong) AS TongSoLuong, " +
                     "SUM(SoLuong * DonGia) AS TongTien " +
                     "FROM Sach " +
                     "GROUP BY LoaiSach";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new SumByTypeDTO(
                    rs.getString("LoaiSach"),
                    rs.getInt("TongSoLuong"),
                    rs.getDouble("TongTien")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
