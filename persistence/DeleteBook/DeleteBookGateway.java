package quanlysach.persistence.DeleteBook;

public interface DeleteBookGateway {
    boolean deleteById(String id);
    boolean existsById(String id);
}

