package service.implementation;

import model.Book;
import repository.BookRepository;
import service.BookService;
import service.implementation.bookHelper.CreateBookHelper;
import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Autor;

// implementing the BookService interface
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository = new BookRepository();
    // adding a book to the database
    @Override
    public void addBook(Book book){
        if(this.getBookByIsbnOrTitle(book.getIsbn()).size()>0){
            System.out.println("Book already exists!");
        }
        else if(bookRepository.insertBook(book)){
            System.out.println("Book added successfully!");
        } else {
            System.out.println("Book not added!");
        }
    }
// getting all the available books from the database
    @Override
    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        ResultSet resultSet = bookRepository.getAvailableBooks();
        if (resultSet != null){
            try {
                while (resultSet.next()) {
                    availableBooks.add(CreateBookHelper.createBook(resultSet));
                }
            } catch (SQLException e) {e.printStackTrace();}
        }
        else {System.out.println("No books found!");}
        return availableBooks;
    }
    @Override
    public List<Book> searchBooks(String keyword) {
        List<Book> searchResults = new ArrayList<>();
        try {
            ResultSet resultSet = bookRepository.searchBooks(keyword);
            if (resultSet != null){
                while (resultSet.next()) {
                    searchResults.add(CreateBookHelper.createBook(resultSet));
                }
            }
            else {System.out.println("No books found!");}
        } catch (SQLException e) {e.printStackTrace();}
        return searchResults;
    }
// deleting a book from the database
    @Override
    public void deleteBook(Book book) {
        if (bookRepository.deleteBook(book)) {
            System.out.println("Book deleted successfully!");
        } else {
            System.out.println("Book not deleted!");
        }
    }
// editing a book in the database
    @Override
    public void editBook(Book book){
       if(bookRepository.editBook(book)){
           System.out.println("Book edited successfully!");
       }else {
           System.out.println("Book not edited!");
       }
    }
// getting a book by its ISBN or title
    @Override
    public  List<Book> getBookByIsbnOrTitle(String isbnOrTitle){
      List<Book> books = new ArrayList<Book>();
      try{
           ResultSet resultSet = bookRepository.getBookByIsbnOrTitle(isbnOrTitle);
            if (resultSet != null) {
                while (resultSet.next()) {
                    books.add(CreateBookHelper.createBook(resultSet));
                }
            }else{System.out.println("No books found!");}
      }catch (SQLException e) {e.printStackTrace();}
       return books;
    }
    public List<Book> getStates(){
        List<Book> books = new ArrayList<>();
        try{
            ResultSet resultSet = bookRepository.getAvailableBooks();
            if(resultSet!=null){
                while (resultSet.next()){
                    books.add(CreateBookHelper.createBook(resultSet));
                }
                ResultSet resultBorrowed = bookRepository.getBooksWithStatus("borrowed");
                ResultSet resultLosted = bookRepository.getBooksWithStatus("losted");
                while (resultBorrowed.next()){
                         for(Book book:books){
                             if(book.getId()==resultBorrowed.getInt("id_book")){
                                 book.setQuantityBorrowed(resultBorrowed.getInt("status_quantity"));
                             }
                         }
                }
                while (resultLosted.next()){
                    for(Book book:books){
                        if(book.getId()==resultLosted.getInt("id_book")){
                            book.setQuantityLosted(resultLosted.getInt("status_quantity"));
                        }
                    }
                }
            }else{System.out.println("No books found!");}
        }catch (SQLException e) {e.printStackTrace();}
        return books;
    }
}
