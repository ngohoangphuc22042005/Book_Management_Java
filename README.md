# Book Management System (Java)

A simple Java application for managing books.  
This project demonstrates the use of **OOP principles**, **Clean Architecture**, and basic **design patterns** to organize business logic and data layers.

## Features

- Add new books
- Delete books
- Search books
- Edit book information
- Calculate statistics (average price, totals by type)

## Architecture

The project follows **Clean Architecture** principles:

Controller → UseCase → DAO → Data

Each feature is implemented using separate **UseCase classes** to keep business logic independent from UI and data layers.

## Design Concepts

- Object-Oriented Programming (OOP)
- Clean Architecture
- Factory Pattern
- DTO Pattern
- Dependency Injection (constructor-based)

## Technologies

- Java
- OOP
- MVC (presentation layer)
- Design Patterns

## Project Structure
```

quanlysach/
├── AppBook.java                  # Entry point
│
├── business/                     # Tầng nghiệp vụ (Use Case + Domain)
│   ├── Book.java
│   ├── Textbook.java
│   ├── ReferenceBook.java
│   ├── BookFactory.java
│   ├── BookViewDTO.java
│   ├── BookListViewUseCase.java
│   ├── DeleteBook/
│   │   ├── DeleteBookUseCase.java
│   │   ├── ReqDeleteBook.java
│   │   ├── ResDeleteBook.java
│   │   └── DeleteBookFactory.java
│   ├── EditBook/
│   │   ├── EditBookUseCase.java
│   │   ├── ReqEditBook.java
│   │   └── ResEditBook.java
│   ├── FindBook/
│   │   ├── FindBookUseCase.java
│   │   ├── ReqFindBook.java
│   │   └── ResFindBook.java
│   ├── FindBookNXB_X/
│   │   ├── FindBookNXB_XUseCase.java
│   │   ├── ResBookNXB_XDTO.java
│   │   └── FindBookNXB_XFactory.java
│   ├── OpenAddBookForm/
│   │   ├── BookType.java
│   │   ├── OpenAddBookFormUseCase.java
│   │   └── ResBookTypeDTO.java
│   ├── OpenEditBookForm/
│   │   ├── OpenEditBookFormUseCase.java
│   │   ├── OpenEditBookFormResponse.java
│   │   └── ResEditBookDTO.java
│   ├── SaveBook/
│   │   ├── SaveBookUseCase.java
│   │   ├── SaveBookFactoryImpl.java
│   │   └── ReqBook.java
│   ├── SumByType/
│   │   ├── SumByTypeUseCase.java
│   │   ├── SumByTypeDTO.java
│   │   ├── ReqSumByType.java
│   │   └── ResSumByType.java
│   └── AvgPriceByType/
│       ├── AvgPriceByTypeUseCase.java
│       ├── ReqAvgPrice.java
│       └── ResAvgPrice.java
│
├── persistence/                  # DAO/Gateway
│   ├── DBConnection.java
│   ├── BookDTO.java
│   ├── BookListViewDAO.java
│   ├── DeleteBook/
│   │   ├── DeleteBookDAOImpl.java
│   │   └── DeleteBookGateway.java
│   ├── EditBook/
│   │   ├── EditBookDAO.java
│   │   └── EditBookGateway.java
│   ├── AvgPriceByType/
│   │   ├── AvgPriceByTypeDAOImpl.java
│   │   └── AvgPriceByTypeGateway.java
│   ├── SaveBook/
│   │   ├── SaveBookDAOImpl.java
│   │   └── SaveBookGateway.java
│   ├── FindBook/
│   │   └── FindBookGateway.java
│   ├── FindBookNXB_X/
│   │   └── FindBookNXB_XGateway.java
│   ├── OpenAddBookForm/
│   │   ├── BookTypeDTO.java
│   │   └── OpenAddBookFormGateway.java
│   └── OpenEditBookForm/
│       ├── EditBookDTO.java
│       └── OpenEditBookFormGateway.java
│
├── presentation/                 # UI + Controller + ViewModel
│   ├── BookListViewController.java
│   ├── BookListViewUI.java
│   ├── BookViewModel.java
│   ├── DeleteBook/
│   │   └── DeleteBookController.java
│   ├── FindBookNXB_X/
│   │   ├── FindBookNXB_XController.java
│   │   └── FindBookNXB_XViewModel.java
│   ├── EditBook/
│   │   └── EditBookController.java
│   ├── SaveBook/
│   │   └── SaveBookController.java
│   ├── OpenAddBookForm/
│   │   └── OpenAddBookFormUI.java
│   ├── OpenEditBookForm/
│   │   └── OpenEditBookFormUI.java
│   └── ReportUI.java                                                                                                                                             ```

  
## How to Run

1. Clone the repository
      ```bash
   git clone https://github.com/ngohoangphuc22042005/book-management-java.git
   ```

3. Open the project in your IDE (IntelliJ / Eclipse)

4. Run the main application file

## Author

Student project for learning **Software Engineering and Clean Architecture**.
