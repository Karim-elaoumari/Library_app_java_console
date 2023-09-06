package service.implementation;

import java.sql.Connection;
import java.util.List;
import model.Autor;
import model.Book;
import repository.AutorRepository;
import service.AutorService;
import util.DBUtil;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AutorServiceImpl  implements AutorService{
    public AutorRepository autorRepository = new AutorRepository();
    @Override
    public void addAutor(Autor autor){
       if(autorRepository.insertAutor(autor)==true){
             System.out.println("Autor added successfully!");
       }
       else{
           System.out.println("Autor not added!");
       }
    }
    @Override
    public List<Autor> getAutors() {
        List<Autor> autors = new ArrayList<>();
        ResultSet resultSet = autorRepository.getAutors();
        if(resultSet!=null){
            try {
                while (resultSet.next()) {
                    Autor autor = new Autor(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("country")
                    );
                    autors.add(autor);
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        else{
            System.out.println("No autors found!");
        }
        return autors;
    }
    @Override
    public void deleteAutor(Autor autor) {
        if(autorRepository.deleteAutor(autor)==true){
            System.out.println("Autor deleted successfully!");
        }
        else{
            System.out.println("Autor not deleted!");
        }
    }
    @Override
    public void editAutor(Autor autor){
        if(autorRepository.editAutor(autor)==true){
            System.out.println("Autor edited successfully!");
        }
        else{
            System.out.println("Autor not edited!");
        }
    }
    @Override
    public  List<Autor> getAutorByName(String name){
        List<Autor> autors = new ArrayList<>();
        ResultSet resultSet = autorRepository.getAutorByName(name);
        if(resultSet!=null){
            try {
                while (resultSet.next()) {
                    Autor autor = new Autor(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("country")
                    );
                    autors.add(autor);
                }
            } catch (SQLException e){e.printStackTrace();}
        }
        else{System.out.println("No autors found!");}
        return autors;
    }
    @Override
    public Autor getAutorBooks(Autor autor){
        List<Book> books = new ArrayList<>();
        ResultSet resultSet = autorRepository.getAutorBooks(autor);
        if (resultSet != null) {
            try{
                while (resultSet.next()) {
                    Book book = new Book(resultSet.getInt("id"), resultSet.getString("title"),
                            autor, resultSet.getString("isbn"), resultSet.getInt("quantity"),
                            resultSet.getString("language")
                    );
                    books.add(book);
                }
            }
            catch (SQLException e) {e.printStackTrace();}
        }
        else{System.out.println("No books found!");}
        autor.setBooks(books);
        return autor;
    }
}
