package quanlysach.presentation.SumByType;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import quanlysach.business.SumByType.SumByTypeDTO;
import quanlysach.presentation.*;

public class SumByTypeView extends JFrame implements Subscriber {
    private JTable table;
    private DefaultTableModel model;
    private SumByTypeViewModel vm;
    private SumByTypeController controller;

    public SumByTypeView(SumByTypeController controller) {
        this.controller = controller;
        setTitle("Tổng hợp theo loại sách");
        setSize(600, 300);
        setLocationRelativeTo(null);

        model = new DefaultTableModel(new String[]{"Loại sách","Tổng số lượng","Tổng tiền"},0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void setViewModel(SumByTypeViewModel vm) {
        this.vm = vm;
        vm.addSubscriber(this);

        // ✅ Gọi execute sau khi gắn ViewModel
        controller.execute();
    }

    @Override
    public void update() {
        model.setRowCount(0);
        if (vm.results != null) {
            for (SumByTypeDTO dto : vm.results) {
                model.addRow(new Object[]{dto.type, dto.totalQuantity, dto.totalPrice});
            }
        }
    }
}
