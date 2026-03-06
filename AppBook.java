package quanlysach;

import java.sql.SQLException;
import quanlysach.business.BookListViewUseCase;
import quanlysach.persistence.BookListViewDAO;
import quanlysach.presentation.BookListViewController;
import quanlysach.presentation.BookListViewUI;
import quanlysach.presentation.BookViewModel;

public class AppBook {

    public static void main(String[] args) {
        BookListViewController controller = null;
        BookViewModel model = new BookViewModel();
        BookListViewUseCase listViewUseCase = null;

        try {
            // Sử dụng DAO cũ cho chức năng xem danh sách
            BookListViewDAO listViewDAO = new BookListViewDAO();
            listViewUseCase = new BookListViewUseCase(listViewDAO);

            controller = new BookListViewController(model);
            controller.setListViewUseCase(listViewUseCase);
            
            BookListViewUI view = new BookListViewUI(controller);
            view.setViewModel(model);
            
            controller.execute();
            view.setVisible(true);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}