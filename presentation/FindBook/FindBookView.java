package quanlysach.presentation.FindBook;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import quanlysach.presentation.*;

public class FindBookView extends JFrame implements Subscriber {
    private JTextField txtKeyword;
    private JButton btnSearch;
    private JTable table;
    private DefaultTableModel model;

    private final FindBookController controller;
    private FindBookViewModel vm;

    public FindBookView(FindBookController controller) {
        this.controller = controller;
        setTitle("Tìm kiếm sách");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtKeyword = new JTextField(20);
        btnSearch = new JButton("Tìm kiếm");
        top.add(txtKeyword);
        top.add(btnSearch);
        add(top, BorderLayout.NORTH);

        model = new DefaultTableModel(
            new String[]{"STT","Mã sách","Ngày nhập","Loại","Đơn giá","Số lượng","NXB","Thành tiền"},0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        btnSearch.addActionListener(e -> {
            String keyword = txtKeyword.getText().trim();
            controller.find(keyword);
        });
    }

    public void setViewModel(FindBookViewModel vm) {
        this.vm = vm;
        vm.addSubscriber(this);
    }

    @Override
    public void update() {
        model.setRowCount(0);
        if (vm.results != null) {
            for (BookViewItem item : vm.results) {
                model.addRow(new Object[]{
                    item.stt, item.id, item.importDate, item.type,
                    item.unitPrice, item.quantity, item.publisher, item.totalPrice
                });
            }
        }
    }
}