package quanlysach.presentation;

import java.awt.*;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import quanlysach.business.AvgPriceByType.AvgPriceByTypeUseCase;
import quanlysach.business.DeleteBook.DeleteBookFactory;
import quanlysach.business.FindBook.FindBookUseCase;
import quanlysach.business.FindBookNXB_X.FindBookNXB_XFactory;
import quanlysach.business.OpenAddBookForm.OpenAddBookFormUseCase;
import quanlysach.business.OpenEditBookForm.OpenEditBookFormUseCase;
import quanlysach.business.SumByType.SumByTypeUseCase;
import quanlysach.persistence.AvgPriceByType.AvgPriceByTypeDAOImpl;
import quanlysach.persistence.FindBook.FindBookDAOImpl;
import quanlysach.persistence.OpenAddBookForm.OpenAddBookFormDAO;
import quanlysach.persistence.OpenAddBookForm.OpenAddBookFormGateway;
import quanlysach.persistence.OpenEditBookForm.OpenEditBookFormDAO;
import quanlysach.persistence.OpenEditBookForm.OpenEditBookFormGateway;
import quanlysach.persistence.SumByType.SumByTypeDAOImpl;
import quanlysach.presentation.AvgPriceByType.AvgPriceByTypeController;
import quanlysach.presentation.AvgPriceByType.AvgPriceByTypeView;
import quanlysach.presentation.AvgPriceByType.AvgPriceByTypeViewModel;
import quanlysach.presentation.FindBook.FindBookController;
import quanlysach.presentation.FindBook.FindBookView;
import quanlysach.presentation.FindBook.FindBookViewModel;
import quanlysach.presentation.FindBookNXB_X.FindBookNXB_XView;
import quanlysach.presentation.FindBookNXB_X.FindBookNXB_XViewModel;
import quanlysach.presentation.OpenAddBookForm.OpenAddBookFormController;
import quanlysach.presentation.OpenAddBookForm.OpenAddBookFormModel;
import quanlysach.presentation.OpenAddBookForm.OpenAddBookFormView;
import quanlysach.presentation.OpenEditBookForm.OpenEditBookFormController;
import quanlysach.presentation.OpenEditBookForm.OpenEditBookFormModel;
import quanlysach.presentation.OpenEditBookForm.OpenEditBookFormView;
import quanlysach.presentation.SaveBook.SaveBookPublisher;
import quanlysach.presentation.SumByType.SumByTypeController;
import quanlysach.presentation.SumByType.SumByTypeView;
import quanlysach.presentation.SumByType.SumByTypeViewModel;

public class BookListViewUI extends JFrame implements Subscriber {
    private JTable table;
    private DefaultTableModel model;
    private SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    private BookViewModel viewModel;
    private BookListViewController controller;

    public BookListViewUI(BookListViewController controller) {
        super("Danh sách sách thư viện");
        this.controller = controller;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 500);
        setLocationRelativeTo(null);

        JPanel top = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        JButton btnAdd = new JButton("Thêm");
        JButton btnEdit = new JButton("Sửa");
        JButton btnDelete = new JButton("Xóa");
        JButton btnFind = new JButton("Tìm kiếm");
        JButton btnSummary = new JButton("Tổng hợp");
        JButton btnAvgRef = new JButton("TB Tham Khảo");
        JButton btnTextbookX = new JButton("Giáo Khoa NXB X"); // ✅ nút mới

        top.add(btnAdd);
        top.add(btnEdit);
        top.add(btnDelete);
        top.add(btnFind);
        top.add(btnSummary);
        top.add(btnAvgRef);
        top.add(btnTextbookX); // ✅ thêm vào giao diện
        add(top, BorderLayout.NORTH);

        // Nút Thêm
        btnAdd.addActionListener(e -> {
            SaveBookPublisher publisher = new SaveBookPublisher();
            publisher.addSubscriber(() -> {
                try {
                    controller.execute();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            OpenAddBookFormView view = new OpenAddBookFormView(publisher);
            OpenAddBookFormModel model = new OpenAddBookFormModel();
            OpenAddBookFormGateway gateway = new OpenAddBookFormDAO();
            OpenAddBookFormUseCase uc = new OpenAddBookFormUseCase(gateway);
            OpenAddBookFormController addController = new OpenAddBookFormController(model, uc);
            view.setModel(model);
            addController.execute();
        });

        // Nút Sửa
        btnEdit.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một sách để sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String bookId = model.getValueAt(selectedRow, 1).toString();

            SaveBookPublisher publisher = new SaveBookPublisher();
            publisher.addSubscriber(() -> {
                try {
                    controller.execute();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            OpenEditBookFormView view = new OpenEditBookFormView(publisher, bookId);
            OpenEditBookFormModel editModel = new OpenEditBookFormModel();
            OpenEditBookFormGateway editGateway = new OpenEditBookFormDAO();
            OpenEditBookFormUseCase editUC = new OpenEditBookFormUseCase(editGateway);
            OpenEditBookFormController editController = new OpenEditBookFormController(editModel, editUC);
            view.setModel(editModel);
            editController.execute(bookId);
        });

        // Nút Xóa
        btnDelete.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một sách để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String bookId = model.getValueAt(selectedRow, 1).toString();

            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa sách này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) return;

            var deleteController = DeleteBookFactory.createController();
            var res = deleteController.deleteBook(bookId);

            JOptionPane.showMessageDialog(this, res.message);

            if (res.success) {
                try {
                    controller.execute();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Nút Tìm kiếm
        btnFind.addActionListener(e -> {
            FindBookViewModel findVM = new FindBookViewModel();
            FindBookUseCase findUC = new FindBookUseCase(new FindBookDAOImpl());
            FindBookController findController = new FindBookController(findUC, findVM);

            FindBookView findView = new FindBookView(findController);
            findView.setViewModel(findVM);
            findView.setVisible(true);
        });

        // Nút Tổng hợp
        btnSummary.addActionListener(e -> {
            SumByTypeViewModel vm = new SumByTypeViewModel();
            SumByTypeUseCase uc = new SumByTypeUseCase(new SumByTypeDAOImpl());
            SumByTypeController ctl = new SumByTypeController(uc, vm);

            SumByTypeView view = new SumByTypeView(ctl);
            view.setViewModel(vm);
            view.setVisible(true);
        });

        // Nút TB Tham Khảo
        btnAvgRef.addActionListener(e -> {
            AvgPriceByTypeViewModel vm = new AvgPriceByTypeViewModel();
            AvgPriceByTypeUseCase uc = new AvgPriceByTypeUseCase(new AvgPriceByTypeDAOImpl());
            AvgPriceByTypeController ctl = new AvgPriceByTypeController(uc, vm);

            AvgPriceByTypeView view = new AvgPriceByTypeView(ctl, "ThamKhao");
            view.setViewModel(vm);
            view.setVisible(true);
        });

        // ✅ Nút Giáo Khoa NXB X
        btnTextbookX.addActionListener(e -> {
            FindBookNXB_XViewModel vm = new FindBookNXB_XViewModel();
            var ctl = FindBookNXB_XFactory.createController(vm);
            var view = new FindBookNXB_XView(ctl, vm);
            view.setVisible(true);
        });

        // Bảng sách
        String[] cols = {"STT", "Mã sách", "Ngày Nhập", "Loại Sách", "Đơn Giá", "Số Lượng", "Nhà Xuất Bản", "Thành Tiền"};
        model = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void setViewModel(BookViewModel viewModel) {
        this.viewModel = viewModel;
        viewModel.addSubscriber(this);
    }

        private void showList(BookViewModel bookViewModel) {
        model.setRowCount(0);
        if (bookViewModel.bookList != null) {
            for (BookViewItem item : bookViewModel.bookList) {
                Object[] row = {
                    item.stt,
                    item.id,
                    item.importDate,
                    item.type,
                    item.unitPrice,
                    item.quantity,
                    item.publisher,
                    item.totalPrice
                };
                model.addRow(row);
            }
        }
    }

    @Override
    public void update() {
        this.showList(viewModel);
    }
}
