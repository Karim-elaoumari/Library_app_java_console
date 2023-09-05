package service;
import model.Book;
import  java.util.List;
public interface BookService {
    void addBook(Book book);
    List<Book> getAvailableBooks();
    List<Book> searchBooks(String keyword);
    void deleteBook(Book book);
    void editBook(Book book);
    List<Book> getBookByIsbnOrTitle(String isbnOrTitle);

}
