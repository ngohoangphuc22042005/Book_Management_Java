package quanlysach.business.OpenAddBookForm;

import java.util.ArrayList;
import java.util.List;

import quanlysach.persistence.OpenAddBookForm.BookTypeDTO;
import quanlysach.persistence.OpenAddBookForm.OpenAddBookFormGateway;

public class OpenAddBookFormUseCase {
    private final OpenAddBookFormGateway gateway;

    public OpenAddBookFormUseCase(OpenAddBookFormGateway gateway) {
        this.gateway = gateway;
    }

    public List<ResBookTypeDTO> execute() {
        List<BookTypeDTO> listDTO = gateway.getAll();
        List<BookType> types = convertToBusiness(listDTO);
        return convertToResponseDTO(types);
    }

    private List<BookType> convertToBusiness(List<BookTypeDTO> listDTO) {
        List<BookType> list = new ArrayList<>();
        for (BookTypeDTO dto : listDTO) {
            list.add(new BookType(dto.id, dto.name, dto.description));
        }
        return list;
    }

    private List<ResBookTypeDTO> convertToResponseDTO(List<BookType> list) {
        List<ResBookTypeDTO> resList = new ArrayList<>();
        for (BookType type : list) {
            ResBookTypeDTO res = new ResBookTypeDTO();
            res.id = type.getId();
            res.name = type.getName();
            res.description = type.getDescription();
            resList.add(res);
        }
        return resList;
    }
}
