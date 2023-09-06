package repository;

import java.sql.Connection;
import util.DBUtil;
import model.Book;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

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
    public ResultSet getAvailableBooks(){
        try{
            String query = "SELECT books.id, books.title, books.isbn, books.quantity, books.language,authors.id as author_id, authors.name, authors.country FROM books INNER JOIN authors ON books.author_id = authors.id";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException e){e.printStackTrace();}
        return null;
    }
    public ResultSet searchBooks(String keyword){
        try{
            String query = "SELECT books.id, books.title, books.isbn, books.quantity, books.language,authors.id as author_id, authors.name, authors.country FROM books INNER JOIN authors ON books.author_id = authors.id WHERE books.title LIKE ? OR books.isbn LIKE ? OR authors.name LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + keyword + "%");
            preparedStatement.setString(2, "%" + keyword + "%");
            preparedStatement.setString(3, "%" + keyword + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException e){e.printStackTrace();}
        return null;
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
    public ResultSet getBookByIsbnOrTitle(String isbnOrTitle){
      try{
            String query = "SELECT books.id, books.title, books.isbn, books.quantity, books.language,authors.id as author_id, authors.name, authors.country FROM books INNER JOIN authors ON books.author_id = authors.id WHERE books.title = ? OR books.isbn = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, isbnOrTitle);
            preparedStatement.setString(2, isbnOrTitle);
            return preparedStatement.executeQuery();
      } catch (SQLException e){e.printStackTrace();}
      return null;
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
