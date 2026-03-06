package quanlysach.presentation.OpenEditBookForm;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import quanlysach.business.EditBook.EditBookUseCase;
import quanlysach.business.EditBook.ReqEditBook;
import quanlysach.persistence.EditBook.EditBookDAO;
import quanlysach.presentation.EditBook.EditBookController;
import quanlysach.presentation.OpenAddBookForm.BookTypeItem;
import quanlysach.presentation.SaveBook.SaveBookPublisher;
import quanlysach.presentation.Subscriber;

public class OpenEditBookFormView extends JFrame implements Subscriber {
    private JTextField txtId, txtImportDate, txtUnitPrice, txtQuantity, txtPublisher, txtTax, txtStatus;
    private JComboBox<BookTypeItem> cboBookType;
    private JButton btnSave, btnCancel;

    private JPanel pnlTax, pnlStatus;
    private OpenEditBookFormModel model;

    private SaveBookPublisher publisher;
    private String bookId;

    public OpenEditBookFormView(SaveBookPublisher publisher, String bookId) {
        this.publisher = publisher;
        this.bookId = bookId;
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
        setTitle("Sửa Sách");
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
            BookTypeItem selectedType = (BookTypeItem) cboBookType.getSelectedItem();
            ReqEditBook req = new ReqEditBook(
                bookId,
                txtId.getText(),
                txtImportDate.getText(),
                txtUnitPrice.getText(),
                txtQuantity.getText(),
                txtPublisher.getText(),
                selectedType != null ? selectedType.name : "", // ✅ sửa ở đây
                txtTax.getText(),
                txtStatus.getText()
            );

            EditBookController controller = new EditBookController(new EditBookUseCase(new EditBookDAO()), publisher);
            boolean success = controller.editBook(req);

            if (success) {
                JOptionPane.showMessageDialog(this, "Cập nhật sách thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật sách thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        cboBookType.addActionListener(e -> {
            BookTypeItem selectedType = (BookTypeItem) cboBookType.getSelectedItem();
            if (selectedType != null) {
                togglePanelsByType(selectedType.name);
            }
        });
    }

    private void togglePanelsByType(String typeName) {
        String t = typeName == null ? "" : typeName.trim().toLowerCase();
        if (t.equals("thamkhao") || t.equals("tham khảo")) {
            pnlTax.setVisible(true);
            pnlStatus.setVisible(false);
        } else if (t.equals("giaokhoa") || t.equals("giáo khoa")) {
            pnlTax.setVisible(false);
            pnlStatus.setVisible(true);
        } else {
            pnlTax.setVisible(false);
            pnlStatus.setVisible(false);
        }
        revalidate();
        repaint();
    }

    private void loadBookTypesAndSelect() {
        cboBookType.removeAllItems();
        if (model != null && model.bookTypeItems != null) {
            BookTypeItem toSelect = null;
            for (BookTypeItem item : model.bookTypeItems) {
                cboBookType.addItem(item);
                if (model.currentTypeName != null &&
                    item.name != null &&
                    item.name.equalsIgnoreCase(model.currentTypeName)) {
                    toSelect = item;
                }
            }
            if (toSelect != null) {
                cboBookType.setSelectedItem(toSelect);
                togglePanelsByType(toSelect.name);
            }
        }
    }

    public void setModel(OpenEditBookFormModel model) {
        this.model = model;
        model.addSubscriber(this);
    }

    @Override
    public void update() {
        txtId.setText(model.id != null ? model.id : "");
        txtImportDate.setText(model.importDate != null ? model.importDate : "");
        txtUnitPrice.setText(model.unitPrice != null ? model.unitPrice : "");
        txtQuantity.setText(model.quantity != null ? model.quantity : "");
        txtPublisher.setText(model.publisher != null ? model.publisher : "");
        txtTax.setText(model.tax != null ? model.tax : "");
        txtStatus.setText(model.status != null ? model.status : "");

        loadBookTypesAndSelect();
        setVisible(true);
    }
}
