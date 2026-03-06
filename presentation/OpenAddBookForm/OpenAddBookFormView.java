package quanlysach.presentation.OpenAddBookForm;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import quanlysach.business.SaveBook.ReqBook;
import quanlysach.presentation.SaveBook.SaveBookController;
import quanlysach.presentation.SaveBook.SaveBookPublisher;
import quanlysach.presentation.Subscriber;

public class OpenAddBookFormView extends JFrame implements Subscriber {
    private JTextField txtId, txtImportDate, txtUnitPrice, txtQuantity, txtPublisher, txtTax, txtStatus;
    private JComboBox<BookTypeItem> cboBookType;
    private JButton btnSave, btnCancel;
    private JPanel pnlTax, pnlStatus;

    private OpenAddBookFormModel model;
    private SaveBookPublisher publisher;

    public OpenAddBookFormView(SaveBookPublisher publisher) {
        this.publisher = publisher;
        initializeComponents();
        setupLayout();
        setupEventHandlers();
    }

    private JTextField createTextField() {
        JTextField txt = new JTextField();
        txt.setPreferredSize(new Dimension(150, 25));
        return txt;
    }

    private void initializeComponents() {
        txtId = createTextField();
        txtImportDate = createTextField();
        txtUnitPrice = createTextField();
        txtQuantity = createTextField();
        txtPublisher = createTextField();
        txtTax = createTextField();
        txtStatus = createTextField();

        cboBookType = new JComboBox<>();
        cboBookType.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof BookTypeItem) {
                    setText(((BookTypeItem) value).name);
                }
                return this;
            }
        });

        btnSave = new JButton("Lưu");
        btnCancel = new JButton("Hủy");

        pnlTax = createRowPanel("Thuế (Tham khảo):", txtTax);
        pnlStatus = createRowPanel("Tình trạng (Giáo khoa):", txtStatus);

        pnlTax.setVisible(false);
        pnlStatus.setVisible(false);
    }

    private JPanel createRowPanel(String label, JComponent field) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        JLabel lbl = new JLabel(label);
        lbl.setPreferredSize(new Dimension(130, 25));
        field.setPreferredSize(new Dimension(150, 25));
        row.add(lbl);
        row.add(field);
        return row;
    }

    private JPanel createRow(String label, JComponent field) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        JLabel lbl = new JLabel(label);
        lbl.setPreferredSize(new Dimension(130, 25));
        field.setPreferredSize(new Dimension(150, 25));
        row.add(lbl);
        row.add(field);
        return row;
    }

    private void setupLayout() {
        setTitle("Thêm Sách Mới");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(350, 400);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        mainPanel.add(createRow("Mã sách:", txtId));
        mainPanel.add(createRow("Ngày nhập (dd/MM/yyyy):", txtImportDate));
        mainPanel.add(createRow("Đơn giá:", txtUnitPrice));
        mainPanel.add(createRow("Số lượng:", txtQuantity));
        mainPanel.add(createRow("Nhà xuất bản:", txtPublisher));
        mainPanel.add(createRow("Loại sách:", cboBookType));
        mainPanel.add(pnlTax);
        mainPanel.add(pnlStatus);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        btnPanel.add(btnSave);
        btnPanel.add(btnCancel);

        mainPanel.add(btnPanel);
        add(mainPanel);
    }

    private void setupEventHandlers() {
        btnCancel.addActionListener(e -> dispose());

        btnSave.addActionListener(e -> {
            if (validateForm()) {
                BookTypeItem selectedType = (BookTypeItem) cboBookType.getSelectedItem();

                ReqBook req = new ReqBook(
                    txtId.getText(),
                    txtImportDate.getText(),
                    txtUnitPrice.getText(),
                    txtQuantity.getText(),
                    txtPublisher.getText(),
                    selectedType.id,
                    txtTax.getText(),
                    txtStatus.getText()
                );

                SaveBookController controller = new SaveBookController(publisher);
                controller.saveBook(req);
                clearForm();
            }
        });

        cboBookType.addActionListener(e -> {
            BookTypeItem selectedType = (BookTypeItem) cboBookType.getSelectedItem();
            if (selectedType != null) {
                String typeName = selectedType.name.trim().toLowerCase();
                if (typeName.equals("tham khảo") || typeName.equals("thamkhao")) {
                    pnlTax.setVisible(true);
                    pnlStatus.setVisible(false);
                } else if (typeName.equals("giáo khoa") || typeName.equals("giaokhoa")) {
                    pnlTax.setVisible(false);
                    pnlStatus.setVisible(true);
                } else {
                    pnlTax.setVisible(false);
                    pnlStatus.setVisible(false);
                }
                revalidate();
                repaint();
            }
        });
    }

    private void loadBookTypes() {
        cboBookType.removeAllItems();
        if (model != null && model.bookTypeItems != null) {
            for (BookTypeItem item : model.bookTypeItems) {
                cboBookType.addItem(item);
            }
        }
    }

    public void setModel(OpenAddBookFormModel model) {
        this.model = model;
        model.addSubscriber(this);
    }

    @Override
    public void update() {
        loadBookTypes();
        setVisible(true);
    }

    private boolean validateForm() {
        if (txtId.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã sách không được để trống!");
            return false;
        }
        if (txtImportDate.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ngày nhập không được để trống!");
            return false;
        }
        if (txtUnitPrice.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Đơn giá không được để trống!");
            return false;
        }
        if (txtQuantity.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số lượng không được để trống!");
            return false;
        }
        return true;
    }

    private void clearForm() {
        txtId.setText("");
        txtImportDate.setText("");
        txtUnitPrice.setText("");
        txtQuantity.setText("");
        txtPublisher.setText("");
        txtTax.setText("");
        txtStatus.setText("");
        cboBookType.setSelectedIndex(0);
        pnlTax.setVisible(false);
        pnlStatus.setVisible(false);
    }
}
