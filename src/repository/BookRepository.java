package repository;

import java.sql.Connection;

import repository.bookHelper.CreateBookHelper;
import util.DBUtil;
import model.Book;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private Connection connection;
    public BookRepository(){
        connection = DBUtil.getConnection();
    }
    public boolean insertBook(Book book){
        try{
            String query = "INSERT INTO books (title, author_id, quantity,isbn,language) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setInt(2, book.getAutor().getId());
            preparedStatement.setInt(3, book.getQuantity());
            preparedStatement.setString(4, book.getIsbn());
            preparedStatement.setString(5, book.getLanguage());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e){e.printStackTrace();}
        return false;
    }
    public List<Book> getAvailableBooks(){
        List<Book> availableBooks = new ArrayList<>();
        try{
            String query = "SELECT books.id, books.title, books.isbn, books.quantity, books.language,authors.id as author_id, authors.name, authors.country FROM books INNER JOIN authors ON books.author_id = authors.id";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                availableBooks.add(CreateBookHelper.createBook(resultSet));
            }
        } catch (SQLException e){e.printStackTrace();}
        return availableBooks;
    }
    public List<Book> searchBooks(String keyword){
        List<Book> searchResults = new ArrayList<>();
        try{
            String query = "SELECT books.id, books.title, books.isbn, books.quantity, books.language,authors.id as author_id, authors.name, authors.country FROM books INNER JOIN authors ON books.author_id = authors.id WHERE books.title LIKE ? OR books.isbn LIKE ? OR authors.name LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + keyword + "%");
            preparedStatement.setString(2, "%" + keyword + "%");
            preparedStatement.setString(3, "%" + keyword + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                searchResults.add(CreateBookHelper.createBook(resultSet));
            }
        } catch (SQLException e){e.printStackTrace();}
        return searchResults;
    }
    public boolean deleteBook(Book book){
        try {
            String query = "DELETE FROM books WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, book.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {e.printStackTrace();}
        return false;
    }
    public boolean editBook(Book book){
        try{
            String query = "UPDATE books SET title = ?, author_id = ?, isbn = ?, quantity = ?, language = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setInt(2, book.getAutor().getId());
            preparedStatement.setString(3, book.getIsbn());
            preparedStatement.setInt(4, book.getQuantity());
            preparedStatement.setString(5, book.getLanguage());
            preparedStatement.setInt(6, book.getId());
            preparedStatement.executeUpdate();
           return true;
        } catch (SQLException e) {e.printStackTrace();}
        return false;
    }
    public List<Book> getBookByIsbnOrTitle(String isbnOrTitle){
        List<Book> books = new ArrayList<Book>();
      try{
            String query = "SELECT books.id, books.title, books.isbn, books.quantity, books.language,authors.id as author_id, authors.name, authors.country FROM books INNER JOIN authors ON books.author_id = authors.id WHERE books.title = ? OR books.isbn = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, isbnOrTitle);
            preparedStatement.setString(2, isbnOrTitle);
            ResultSet resultSet =  preparedStatement.executeQuery();
              while (resultSet.next()) {
                  books.add(CreateBookHelper.createBook(resultSet));
              }
      } catch (SQLException e){e.printStackTrace();}
      return books;
    }
    public ResultSet getBooksWithStatus(String status){
        try{
            String query = "SELECT books.id as id_book,count(reservation.id) as status_quantity FROM books join reservation on books.id = reservation.book_id WHERE reservation.status = ? GROUP by books.id;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, status);
            return preparedStatement.executeQuery();
        } catch (SQLException e){e.printStackTrace();}
        return null;
    }
}
