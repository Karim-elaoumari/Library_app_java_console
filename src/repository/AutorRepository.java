package repository;

import model.Autor;
import model.Book;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutorRepository {
    private Connection connection;
    public AutorRepository(){connection = DBUtil.getConnection();}
    public boolean insertAutor(Autor autor){
        try{
            String query = "INSERT INTO authors (name, country) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, autor.getName());
            preparedStatement.setString(2, autor.getCountry());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public List<Autor> getAutors() {
        List<Autor> autors = new ArrayList<>();
        try {
            String query = "SELECT * FROM authors";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet =  preparedStatement.executeQuery();
            while (resultSet.next()) {
                Autor autor = new Autor(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("country")
                );
                autors.add(autor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autors;
    }
    public boolean deleteAutor(Autor autor){
            try {
                String query = "DELETE FROM authors WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, autor.getId());
                preparedStatement.executeUpdate();
               return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
    }
    public boolean editAutor(Autor autor){
        try{
            String query = "UPDATE authors SET name = ?, country = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, autor.getName());
            preparedStatement.setString(2, autor.getCountry());
            preparedStatement.setInt(3, autor.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public List<Autor> getAutorByName(String name){
        List<Autor> autors = new ArrayList<>();
        try{
            String query = "SELECT * FROM authors WHERE name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Autor autor = new Autor(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("country")
                );
                autors.add(autor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autors;
    }
    public List<Book> getAutorBooks(Autor autor){
        List<Book> books = new ArrayList<>();
        try{
            String query = "SELECT * FROM books WHERE author_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, autor.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book(resultSet.getInt("id"), resultSet.getString("title"),
                        autor, resultSet.getString("isbn"), resultSet.getInt("quantity"),
                        resultSet.getString("language")
                );
                books.add(book);
            }
        }
        catch (SQLException e) {e.printStackTrace();}
        return books;
    }
}
