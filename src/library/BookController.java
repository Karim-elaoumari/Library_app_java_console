package library;

import model.Autor;
import model.Book;

import java.util.List;
import  service.BookService;
import service.BookServiceImpl;
import java.util.Scanner;

public class BookController {
    private static BookService bookService = new BookServiceImpl();
    private static Scanner scanner = new Scanner(System.in);
    public static void listAvailableBooks(){
        List<Book> availableBooks = bookService.getAvailableBooks();
        if (availableBooks.isEmpty()) {
            System.out.println("No available books in the library.");
        } else {
            System.out.println("Available Books:");

            for (int i = 0; i < availableBooks.size(); i++) {
                System.out.println( i+1+"-"+" Title : " + availableBooks.get(i).getTitle() + " | Author : " + availableBooks.get(i).getAutor().getName() + " | ISBN : " + availableBooks.get(i).getIsbn() + " | Quantity : " + availableBooks.get(i).getQuantity() + " | Language : " + availableBooks.get(i).getLanguage());
            }

        }

    }

    public static void addBook(Autor autor){
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Enter book quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter book language: ");
        String language = scanner.nextLine();
        Book newBook = new Book(
                title,
                autor,
                isbn,
                quantity,
                language
        );
        bookService.addBook(newBook);
    }
    public static void searchBooks() {
        System.out.print("Enter search keyword: ");
        String keyword = scanner.nextLine();

        List<Book> searchResults = bookService.searchBooks(keyword);
        if (searchResults.isEmpty()) {
            System.out.println("No books found matching the search criteria.");
        } else {
            System.out.println("Search Results:");
            for (int i = 0; i < searchResults.size(); i++) {
                System.out.println( i+1+"-"+" Title : " + searchResults.get(i).getTitle() + " | Author : " + searchResults.get(i).getAutor().getName() + " | ISBN : " + searchResults.get(i).getIsbn() + " | Quantity : " + searchResults.get(i).getQuantity() + " | Language : " + searchResults.get(i).getLanguage());
            }
        }
    }
    public static void editBook() {
        System.out.print("Enter book title or ISBN : ");
        String keyword = scanner.nextLine();
        List<Book> searchResults = bookService.getBookByIsbnOrTitle(keyword);
        if (searchResults.isEmpty()) {
            System.out.println("No book found matching the search criteria.");
        } else {
            System.out.println("Search Result:");
            System.out.println(1 + "-" + " Title : " + searchResults.get(0).getTitle() + " | Author : " + searchResults.get(0).getAutor().getName() + " | ISBN : " + searchResults.get(0).getIsbn() + " | Quantity : " + searchResults.get(0).getQuantity() + " | Language : " + searchResults.get(0).getLanguage());
            System.out.print("Do you want to edit Title  ? (y/n) : ");
            String choice = scanner.nextLine();
            if (choice.equals("y")) {
                System.out.print("Enter new Title : ");
                String newTitle = scanner.nextLine();
                searchResults.get(0).setTitle(newTitle);
            }
            System.out.print("Do you want to edit Quantity  ? (y/n) : ");
            choice = scanner.nextLine();
            if (choice.equals("y")) {
                System.out.print("Enter new Quantity : ");
                int newQuantity = scanner.nextInt();
                searchResults.get(0).setQuantity(newQuantity);
            }
            System.out.print("Do you want to edit Language  ? (y/n) : ");
            choice = scanner.nextLine();
            if (choice.equals("y")) {
                System.out.print("Enter new Language : ");
                String newLanguage = scanner.nextLine();
                searchResults.get(0).setLanguage(newLanguage);
            }
            bookService.editBook(searchResults.get(0));


        }
    }
    public static void deleteBook(){
        System.out.print("Enter book title or ISBN : ");
        String keyword = scanner.nextLine();
        List<Book> searchResults = bookService.getBookByIsbnOrTitle(keyword);
        if (searchResults.isEmpty()) {
            System.out.println("No book found matching the search criteria.");
        } else {
            System.out.println("Search Result:");
            System.out.println(1 + "-" + " Title : " + searchResults.get(0).getTitle() + " | Author : " + searchResults.get(0).getAutor().getName() + " | ISBN : " + searchResults.get(0).getIsbn() + " | Quantity : " + searchResults.get(0).getQuantity() + " | Language : " + searchResults.get(0).getLanguage());
            System.out.print("Do you want to delete this book ? (y/n) : ");
            String choice = scanner.nextLine();
            if (choice.equals("y")) {
                bookService.deleteBook(searchResults.get(0));
            }
        }
    }

}
