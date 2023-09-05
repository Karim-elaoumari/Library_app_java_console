package service;
import  model.Autor;
import model.Book;

import java.util.List;

public interface AutorService {
    void addAutor(Autor autor);
    List<Autor>  getAutors();
    List<Autor> getAutorByName(String name);

    void deleteAutor(Autor autor);
    void editAutor(Autor autor);
    Autor  getAutorBooks(Autor autor);



}
