package quanlysach.business.SaveBook;

import quanlysach.persistence.SaveBook.SaveBookGateway;
import quanlysach.persistence.SaveBook.SaveBookDAOImpl;

public class SaveBookFactoryImpl {
    public SaveBookUseCase createUseCase() {
        SaveBookGateway gateway = new SaveBookDAOImpl();
        return new SaveBookUseCase(gateway);
    }
}
