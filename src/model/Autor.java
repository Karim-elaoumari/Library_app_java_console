package model;
import java.util.List;
import java.util.Objects;

public class Autor {
    private int id;
    private String name;
    private String country;
    private List<Book> books;

    public Autor(int id, String name, String country, List<Book> books){
        this.id = id;
        this.name = name;
        this.country = country;
        this.books = books;
    }
    public Autor(int id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }
    public Autor(String name, String country){
        this.name = name;
        this.country = country;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getCountry() {
        return country;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public List<Book> getBooks() {
        return books;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }



}
