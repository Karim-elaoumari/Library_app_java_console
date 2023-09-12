package service;
import  model.Autor;
import model.Result;

import java.util.List;

public interface AutorService {
    Result addAutor(Autor autor);
    List<Autor>  getAutors();
    List<Autor> getAutorByName(String name);

    Result deleteAutor(Autor autor);
    Result editAutor(Autor autor);
    Autor  getAutorBooks(Autor autor);



}
