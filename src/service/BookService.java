package service;
import model.Book;
import model.Result;

import  java.util.List;
public interface BookService {
    Result addBook(Book book);
    List<Book> getAvailableBooks();
    List<Book> searchBooks(String keyword);
    Result deleteBook(Book book);
    Result editBook(Book book);
    List<Book> getBookByIsbnOrTitle(String isbnOrTitle);
    List<Book> getStates();


}
