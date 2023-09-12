package service.implementation;

import model.Book;
import model.Result;
import repository.BookRepository;
import service.BookService;

import java.sql.*;
import java.util.List;

// implementing the BookService interface
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository = new BookRepository();
    // adding a book to the database
    @Override
    public Result addBook(Book book){
        if(book.getIsbn()==null || book.getTitle()==null || book.getIsbn().length()==0
                || book.getTitle().length()==0 || book.getQuantity()==null || book.getQuantity()<0
                || book.getLanguage()==null || book.getLanguage().length()==0
                || book.getAutor()==null || book.getAutor().getId()<0){
            Result result = new Result(400,"invalid inputs!");
            return result;
        }
        if(bookRepository.getBookByIsbnOrTitle(book.getIsbn()).size()>0){
            Result result = new Result(400,"Book already exists!");
            return result;
        }
        else if(bookRepository.insertBook(book)){
            Result result = new Result(201,"Book added successfully!");
            return result;
        }
        else {
            Result result = new Result(500,"Book not added!");
            return result;
        }

    }
// getting all the available books from the database
    @Override
    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = bookRepository.getAvailableBooks();
        return availableBooks;
    }
    @Override
    public List<Book> searchBooks(String keyword) {
        List<Book> searchResults =bookRepository.searchBooks(keyword);
        return searchResults;
    }
// deleting a book from the database
    @Override
    public Result deleteBook(Book book) {
        if(book.getId()<0){
            Result result = new Result(400,"invalid inputs!");
            return result;
        }
        if (bookRepository.deleteBook(book)) {
            Result result = new Result(200, "Book deleted successfully!");
            return result;
        }
        else {
            Result result = new Result(500, "Book not deleted!");
            return result;
        }
    }
// editing a book in the database
    @Override
    public Result editBook(Book book){
        if(book.getId()<0 || book.getIsbn()==null || book.getTitle()==null || book.getIsbn().length()==0
                || book.getTitle().length()==0 || book.getQuantity()==null || book.getQuantity()<0
                || book.getLanguage()==null || book.getLanguage().length()==0
                || book.getAutor()==null || book.getAutor().getId()<0){
            Result result = new Result(400,"invalid inputs!");
            return result;
        }
       if(bookRepository.editBook(book)){
              Result result = new Result(200,"Book edited successfully!");
              return result;
       }
       else {
              Result result = new Result(500,"Book not edited!");
              return result;
       }
    }
// getting a book by its ISBN or title
    @Override
    public  List<Book> getBookByIsbnOrTitle(String isbnOrTitle){
        if(isbnOrTitle==null || isbnOrTitle.length()==0){
            System.out.println("invalid inputs!");
            return null;
        }
          List<Book> books = bookRepository.getBookByIsbnOrTitle(isbnOrTitle);
           return books;
    }
    public List<Book> getStates(){
        List<Book> books =  bookRepository.getAvailableBooks();
        try{
            if(books.size()>0){
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
