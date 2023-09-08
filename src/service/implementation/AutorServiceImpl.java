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
//        validate inputs first
        if(autor.getName()!=null && autor.getCountry()!=null && autor.getName().length()>0 && autor.getCountry().length()>0){
            if(autorRepository.insertAutor(autor)==true){
                System.out.println("Autor added successfully!");
            }
            else{System.out.println("Autor not added!");}
        }
        else{System.out.println("invalid inputs!");}

    }
    @Override
    public List<Autor> getAutors() {
        List<Autor> autors = autorRepository.getAutors();
        return autors;
    }
    @Override
    public void deleteAutor(Autor autor) {
//        valudate autor id
        if(autor.getId()>0){
            if(autorRepository.deleteAutor(autor)==true){
                System.out.println("Author deleted successfully!");
            }
            else{System.out.println("Author not deleted!");}
        }
        else{System.out.println("invalid Author!");}
    }
    @Override
    public void editAutor(Autor autor){
//       validate inputs
        if(autor.getId()>0 && autor.getName()!=null && autor.getCountry()!=null && autor.getName().length()>0 && autor.getCountry().length()>0){
            if(autorRepository.editAutor(autor)==true){
                System.out.println("Author updated successfully!");
            }
            else{System.out.println("Author not updated!");}
        }
        else{System.out.println("invalid inputs!");}
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
