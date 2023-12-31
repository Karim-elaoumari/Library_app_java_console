package model;

import java.util.Objects;

public class Book {
    private int id;
    private String title;
    private Autor autor;
    private String isbn;
    private Integer quantity;
    private String language;
    private Integer quantityBorrowed = 0;
    private Integer quantityLosted = 0;
    public Book(int id, String title, Autor autor, String isbn, Integer quantity, String language, Integer quantityBorrowed, Integer quantityLosted) {
        this.id = id;
        this.title = title;
        this.autor = autor;
        this.isbn = isbn;
        this.quantity = quantity;
        this.language = language;
        this.quantityBorrowed = quantityBorrowed;
        this.quantityLosted = quantityLosted;
    }
    public Book(String title, Autor autor, String isbn, Integer quantity, String language, Integer quantityBorrowed, Integer quantityLosted) {
        this.title = title;
        this.autor = autor;
        this.isbn = isbn;
        this.quantity = quantity;
        this.language = language;
        this.quantityBorrowed = quantityBorrowed;
        this.quantityLosted = quantityLosted;
    }
    public Book(int id, String title, Autor autor, String isbn, Integer quantity, String language) {
        this.id = id;
        this.title = title;
        this.autor = autor;
        this.isbn = isbn;
        this.quantity = quantity;
        this.language = language;
    }
    public Book(String title, Autor autor, String isbn, Integer quantity, String language) {
        this.title = title;
        this.autor = autor;
        this.isbn = isbn;
        this.quantity = quantity;
        this.language = language;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Autor getAutor() {
        return autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getLanguage() {
        return language;
    }

    public Integer getQuantityBorrowed() {
        return quantityBorrowed;
    }

    public Integer getQuantityLosted() {
        return quantityLosted;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setQuantityBorrowed(Integer quantityBorrowed){
        this.quantityBorrowed = quantityBorrowed;
    }
    public void setQuantityLosted(Integer quantityLosted){
        this.quantityLosted = quantityLosted;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && Objects.equals(title, book.title) && Objects.equals(autor, book.autor) && Objects.equals(isbn, book.isbn) && Objects.equals(quantity, book.quantity) && Objects.equals(language, book.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, autor, isbn, quantity, language);
    }



}




