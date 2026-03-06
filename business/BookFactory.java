package quanlysach.business;

import quanlysach.persistence.BookDTO;


public class BookFactory {
    public static Book createBook(BookDTO dto) {
        if ("GiaoKhoa".equalsIgnoreCase(dto.type)) {
            return new Textbook(dto.id, dto.importDate, dto.unitPrice,
                                dto.quantity, dto.publisher, dto.status);
        } else if ("ThamKhao".equalsIgnoreCase(dto.type)) {
            return new ReferenceBook(dto.id, dto.importDate, dto.unitPrice,
                                     dto.quantity, dto.publisher, dto.tax != null ? dto.tax : 0.0);
        }
        return null;
    }
}

