package quanlysach.business.DeleteBook;

public class ResDeleteBook {
    public final boolean success;
    public final String message;

    public ResDeleteBook(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
