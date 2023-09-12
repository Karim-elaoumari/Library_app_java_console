package service.implementation;

import java.sql.Connection;
import java.util.List;
import model.Autor;
import model.Book;
import model.Result;
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
    public Result addAutor(Autor autor){
        if(autor.getName()!=null && autor.getCountry()!=null && autor.getName().length()>0 && autor.getCountry().length()>0){
            if(autorRepository.insertAutor(autor)==true){
                Result result = new Result(201,"Autor added successfully!");
                return result;
            }
            else{
                Result result = new Result(500,"Autor not added!");
                return result;
            }
        }
        else{
            Result result = new Result(400,"invalid inputs!");
            return result;
        }
    }
    @Override
    public List<Autor> getAutors() {
        List<Autor> autors = autorRepository.getAutors();
        return autors;
    }
    @Override
    public Result deleteAutor(Autor autor) {
        if (autor.getId() > 0) {
            if (autorRepository.deleteAutor(autor) == true) {
                Result result = new Result(200, "Autor deleted successfully!");
                return result;
            } else {
                Result result = new Result(500, "Autor not deleted!");
                return result;
            }
        } else {
            Result result = new Result(400, "invalid inputs!");
            return result;
        }
    }

    @Override
    public Result editAutor(Autor autor){
//       validate inputs
        if(autor.getId()>0 && autor.getName()!=null && autor.getCountry()!=null && autor.getName().length()>0 && autor.getCountry().length()>0){
            if(autorRepository.editAutor(autor)==true){
               Result result = new Result(200,"Autor updated successfully!");
                return result;
            }
            else{
                Result result = new Result(500,"Autor not updated!");
                return result;
            }
        }
        else{
            Result result = new Result(400,"invalid inputs!");
            return result;
        }
    }
    @Override
    public  List<Autor> getAutorByName(String name){
        if(name==null || name.length()==0){
            System.out.println("invalid Name!");
            return null;
        }
        List<Autor> autors = autorRepository.getAutorByName(name);
        return autors;
    }
    @Override
    public Autor getAutorBooks(Autor autor){
        if(autor==null || autor.getId()<=0 || autor.getName()==null || autor.getName().length()==0){
            System.out.println("invalid Author!");
            return null;
        }
        List<Book> books = autorRepository.getAutorBooks(autor);
        return autor;
    }
}
