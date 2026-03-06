package quanlysach.presentation.AvgPriceByType;

import java.awt.*;
import javax.swing.*;

public class AvgPriceByTypeView extends JFrame implements AvgPriceByTypeSubscriber {
    private JLabel lblResult;
    private AvgPriceByTypeController controller;
    private AvgPriceByTypeViewModel vm;
    private String bookType;

    public AvgPriceByTypeView(AvgPriceByTypeController controller, String bookType) {
        super("Trung bình cộng đơn giá");
        this.controller = controller;

        // ✅ Sửa lại tên loại sách cho đúng với DB
        this.bookType = normalizeType(bookType);

        setSize(450, 200);
        setLocationRelativeTo(null);

        lblResult = new JLabel("Đang tính trung bình cho loại: " + this.bookType, SwingConstants.CENTER);
        lblResult.setFont(new Font("Arial", Font.BOLD, 16));

        add(lblResult);
    }

    public void setViewModel(AvgPriceByTypeViewModel vm) {
        this.vm = vm;
        vm.addSubscriber(this);

        // ✅ Gọi controller sau khi đã gắn ViewModel
        controller.execute(bookType);
    }

    @Override
    public void update() {
        if (vm.avgPrice != null) {
            lblResult.setText("Trung bình cộng đơn giá (" + bookType + "): " + vm.avgPrice);
        } else {
            lblResult.setText("Không có dữ liệu cho loại: " + bookType);
        }
    }

    // ✅ Hàm chuẩn hóa tên loại sách
    private String normalizeType(String raw) {
        if (raw == null) return "";
        return raw.trim().replaceAll("\\s+", "").replaceAll("[^a-zA-Z0-9]", "");
    }
}
