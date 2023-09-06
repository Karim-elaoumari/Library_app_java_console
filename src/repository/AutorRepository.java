package repository;

import model.Autor;
import model.Book;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public ResultSet getAutors() {
        try {
            String query = "SELECT * FROM authors";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
    public ResultSet getAutorByName(String name){
        try{
            String query = "SELECT * FROM authors WHERE name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public ResultSet getAutorBooks(Autor autor){
        try{
            String query = "SELECT * FROM books WHERE author_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, autor.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet;
        }
        catch (SQLException e) {e.printStackTrace();}
        return null;
    }
}
