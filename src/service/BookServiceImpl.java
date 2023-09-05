package service;

import model.Book;
import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Autor;

// implementing the BookService interface
public class BookServiceImpl implements BookService {
    private Connection connection;
   // making an instance of the connection
    public BookServiceImpl() {
        connection = DBUtil.getConnection();
    }
    // adding a book to the database
    @Override
    public void addBook(Book book) {
        try {
            String query = "INSERT INTO books (title, author_id, isbn, quantity,language) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setInt(2, book.getAutor().getId());
            preparedStatement.setString(3, book.getIsbn());
            preparedStatement.setInt(4, book.getQuantity());
            preparedStatement.setString(5, book.getLanguage());
            preparedStatement.executeUpdate();
            System.out.println("Book added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
// getting all the available books from the database
    @Override

    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        try {
//            get books with author
            String query = "SELECT books.id, books.title, books.isbn, books.quantity, books.language,authors.id as author_id, authors.name, authors.country FROM books INNER JOIN authors ON books.author_id = authors.id";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Book book = new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        new Autor(
                                resultSet.getInt("author_id"),
                                resultSet.getString("name"),
                                resultSet.getString("country")
                        ),
                        resultSet.getString("isbn"),
                        resultSet.getInt("quantity"),
                        resultSet.getString("language")
                );

                availableBooks.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availableBooks;
    }
// searching for a book in the database
    @Override
    public List<Book> searchBooks(String keyword) {
        List<Book> searchResults = new ArrayList<>();
        try {
            String query = "SELECT books.id, books.title, books.isbn, books.quantity, books.language,authors.id as author_id, authors.name, authors.country FROM books INNER JOIN authors ON books.author_id = authors.id WHERE books.title LIKE ? OR books.isbn LIKE ? OR authors.name LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + keyword + "%");
            preparedStatement.setString(2, "%" + keyword + "%");
            preparedStatement.setString(3, "%" + keyword + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        new Autor(
                                resultSet.getInt("author_id"),
                                resultSet.getString("name"),
                                resultSet.getString("country")
                        ),
                        resultSet.getString("isbn"),
                        resultSet.getInt("quantity"),
                        resultSet.getString("language")
                );
                searchResults.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return searchResults;
    }
// deleting a book from the database
    @Override
    public void deleteBook(Book book) {
        try {
            String query = "DELETE FROM books WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, book.getId());
            preparedStatement.executeUpdate();
            System.out.println("Book deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
// editing a book in the database
    @Override
    public void editBook(Book book){
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
            System.out.println("Book edited successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
// getting a book by its ISBN or title

    @Override
    public  List<Book> getBookByIsbnOrTitle(String isbnOrTitle){
      List<Book> books = new ArrayList<Book>();

      try{
//
            String query = "SELECT books.id, books.title, books.isbn, books.quantity, books.language,authors.id as author_id, authors.name, authors.country FROM books INNER JOIN authors ON books.author_id = authors.id WHERE books.title = ? OR books.isbn = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, isbnOrTitle);
            preparedStatement.setString(2, isbnOrTitle);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                    Book book = new Book(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            new Autor(
                                    resultSet.getInt("author_id"),
                                    resultSet.getString("name"),
                                    resultSet.getString("country")
                            ),
                            resultSet.getString("isbn"),
                            resultSet.getInt("quantity"),
                            resultSet.getString("language")
                    );
                    books.add(book);
                }
      }catch (SQLException e) {
            e.printStackTrace();
      }
      return books;

    }
}
