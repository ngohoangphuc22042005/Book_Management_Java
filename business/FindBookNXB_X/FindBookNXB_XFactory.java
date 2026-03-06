package quanlysach.business.FindBookNXB_X;

import quanlysach.persistence.FindBookNXB_X.FindBookNXB_XDAOImpl;
import quanlysach.presentation.FindBookNXB_X.FindBookNXB_XController;
import quanlysach.presentation.FindBookNXB_X.FindBookNXB_XViewModel;

public class FindBookNXB_XFactory {
    public static FindBookNXB_XController createController(FindBookNXB_XViewModel vm) {
        FindBookNXB_XUseCase uc = new FindBookNXB_XUseCase(new FindBookNXB_XDAOImpl());
        return new FindBookNXB_XController(uc, vm);
    }
}
