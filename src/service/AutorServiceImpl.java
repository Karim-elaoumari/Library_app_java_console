package service;

import java.sql.Connection;
import java.util.List;
import model.Autor;
import model.Book;
import service.AutorService;
import util.DBUtil;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;



public class AutorServiceImpl  implements AutorService{
    private Connection connection;
    public AutorServiceImpl(){
        connection = DBUtil.getConnection();
    }
    @Override
    public void addAutor(Autor autor) {
        try{
            String query = "INSERT INTO authors (name, country) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, autor.getName());
            preparedStatement.setString(2, autor.getCountry());
            preparedStatement.executeUpdate();
            System.out.println("Autor added successfully!");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Override
    public List<Autor> getAutors() {
        List<Autor> autors = new ArrayList<>();
        try {
            String query = "SELECT * FROM authors";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
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
    @Override
    public void deleteAutor(Autor autor) {
        try {
            String query = "DELETE FROM authors WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, autor.getId());
            preparedStatement.executeUpdate();
            System.out.println("Autor deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void editAutor(Autor autor){
        try{
            String query = "UPDATE authors SET name = ?, country = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, autor.getName());
            preparedStatement.setString(2, autor.getCountry());
            preparedStatement.setInt(3, autor.getId());
            preparedStatement.executeUpdate();
            System.out.println("Autor edited successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public  List<Autor> getAutorByName(String name){
        List<Autor> autors = new ArrayList<>();
        try {
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
    @Override
    public Autor getAutorBooks(Autor autor){
        List<Book> books = new ArrayList<>();
        try{
            String query = "SELECT * FROM books WHERE author_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, autor.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        autor,
                        resultSet.getString("isbn"),
                        resultSet.getInt("quantity"),
                        resultSet.getString("language")
                );
                books.add(book);
            }
        }
        catch (SQLException e) {e.printStackTrace();}
        autor.setBooks(books);
        return autor;
    }
}
