package quanlysach.presentation.FindBookNXB_X;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import quanlysach.business.FindBookNXB_X.ResBookNXB_XDTO;

public class FindBookNXB_XView extends JFrame implements FindBookNXB_XSubscriber {
    private final FindBookNXB_XController controller;
    private FindBookNXB_XViewModel vm;
    private JTable table;
    private DefaultTableModel model;

    public FindBookNXB_XView(FindBookNXB_XController controller, FindBookNXB_XViewModel vm) {
        super("Sách Giáo Khoa của NXB X");
        this.controller = controller;
        this.vm = vm;
        vm.addSubscriber(this); // Đăng ký để nhận dữ liệu

        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        model = new DefaultTableModel(
            new Object[]{"Mã", "Loại", "NXB", "Đơn giá", "Số lượng"}, 0
        );
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        controller.execute(); // Gọi sau khi View đã đăng ký
    }

    @Override
    public void update() {
        model.setRowCount(0);
        if (vm.result != null) {
            for (ResBookNXB_XDTO dto : vm.result) {
                model.addRow(new Object[]{
                    dto.id,
                    dto.type,
                    dto.publisher,
                    dto.unitPrice,
                    dto.quantity
                });
            }
        }
    }
}
