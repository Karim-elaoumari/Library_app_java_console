package service.implementation.bookHelper;

import model.Autor;
import model.Book;

import java.sql.ResultSet;

public class CreateBookHelper {

    public static Book createBook(ResultSet resultSet){
        try{
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
            return book;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
