package library;

import helper.ConsoleHelper;
import helper.FileCreator;
import model.Autor;
import model.Book;
import java.util.List;

import model.Result;
import service.BookService;
import service.implementation.BookServiceImpl;
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
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-4s | %-40s | %-20s | %-15s | %-9s | %-20s%n", "No.", "Title", "Author", "ISBN", "Quantity", "Language");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
            for (int i = 0; i < availableBooks.size(); i++) {
                System.out.printf("%-4d | %-40s | %-20s | %-15s | %-9d | %-20s%n",
                        (i + 1),
                        availableBooks.get(i).getTitle(),
                        availableBooks.get(i).getAutor().getName(),
                        availableBooks.get(i).getIsbn(),
                        availableBooks.get(i).getQuantity(),
                        availableBooks.get(i).getLanguage()
                );
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
            }
        }
        ConsoleHelper.retrunToMenu();
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
        Result result = bookService.addBook(newBook);
        if (result.isSuccess()) {
            System.out.println(result.getMessage());
        } else {
            System.out.println(result.getMessage());
        }
        ConsoleHelper.retrunToMenu();
    }
    public static void searchBooks() {
        System.out.print("Enter search keyword: ");
        String keyword = scanner.nextLine();

        List<Book> searchResults = bookService.searchBooks(keyword);
        if(searchResults==null){
            ConsoleHelper.retrunToMenu();
        }
        else if (searchResults.isEmpty()) {
            System.out.println("No books found matching the search criteria.");
        } else {
            System.out.println("Search Results:");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-4s | %-40s | %-20s | %-15s | %-9s | %-20s%n", "No.", "Title", "Author", "ISBN", "Quantity", "Language");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------");

            for (int i = 0; i < searchResults.size(); i++) {
                System.out.printf("%-4d | %-40s | %-20s | %-15s | %-9d | %-20s%n",
                        (i + 1),
                        searchResults.get(i).getTitle(),
                        searchResults.get(i).getAutor().getName(),
                        searchResults.get(i).getIsbn(),
                        searchResults.get(i).getQuantity(),
                        searchResults.get(i).getLanguage()
                );
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
            }
        }
        ConsoleHelper.retrunToMenu();
    }
    public static void editBook() {
        System.out.print("Enter book title or ISBN : ");
        String keyword = scanner.nextLine();
        List<Book> searchResults = bookService.getBookByIsbnOrTitle(keyword);
        if(searchResults==null){
            ConsoleHelper.retrunToMenu();
        } else if (searchResults.isEmpty()) {
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
                scanner.nextLine();
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
            Result result = bookService.editBook(searchResults.get(0));
            if (result.isSuccess()) {
                System.out.println(result.getMessage());
            } else {
                System.out.println(result.getMessage());
            }
        }
        ConsoleHelper.retrunToMenu();
    }
    public static void deleteBook(){
        System.out.print("Enter book title or ISBN : ");
        String keyword = scanner.nextLine();
        List<Book> searchResults = bookService.getBookByIsbnOrTitle(keyword);
        if(searchResults==null){
            ConsoleHelper.retrunToMenu();
        } else if (searchResults.isEmpty()) {
            System.out.println("No book found matching the search criteria.");
        } else {
            System.out.println("Search Result:");
            System.out.println(1 + "-" + " Title : " + searchResults.get(0).getTitle() + " | Author : " + searchResults.get(0).getAutor().getName() + " | ISBN : " + searchResults.get(0).getIsbn() + " | Quantity : " + searchResults.get(0).getQuantity() + " | Language : " + searchResults.get(0).getLanguage());
            System.out.print("Do you want to delete this book ? (y/n) : ");
            String choice = scanner.nextLine();
            if (choice.equals("y")) {
                Result result = bookService.deleteBook(searchResults.get(0));
                if (result.isSuccess()) {
                    System.out.println(result.getMessage());
                } else {
                    System.out.println(result.getMessage());
                }
            }
        }
        ConsoleHelper.retrunToMenu();
    }
    public static void getStatesLibrary(){
        System.out.println("This are the States Of the Library : ");
        List<Book> books = bookService.getStates();
        System.out.println("Total Books : "+books.size());
        System.out.println("Total Books Quantities Available : "+books.stream().mapToInt(Book::getQuantity).sum());
        System.out.println("Total Books Quantities Borrowed : "+books.stream().mapToInt(Book::getQuantityBorrowed).sum());
        System.out.println("Total Books Quantities Losted : "+books.stream().mapToInt(Book::getQuantityLosted).sum());
        System.out.println("Do you want to :");
        System.out.println("1. View List of books with states");
        System.out.println("2. Genearate Excel Report");
        String choice = scanner.nextLine();
        if (choice.equals("1")) {
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-4s | %-40s | %-20s | %-15s | %-9s | %-20s | %-18s | %-15s%n", "No.", "Title", "Author", "ISBN", "Quantity", "Language", "Quantity Borrowed", "Quantity Lost |");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            for (int i = 0; i < books.size(); i++) {
                System.out.printf("%-4d | %-40s | %-20s | %-15s | %-9d | %-20s | %-18d | %-15d%n",
                        (i + 1),
                        books.get(i).getTitle(),
                        books.get(i).getAutor().getName(),
                        books.get(i).getIsbn(),
                        books.get(i).getQuantity(),
                        books.get(i).getLanguage(),
                        books.get(i).getQuantityBorrowed(),
                        books.get(i).getQuantityLosted()
                );
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            }
        } else if (choice.equals("2")) {
            System.out.print("Enter the name of the file : ");
            String filename = scanner.nextLine();
            FileCreator.createExcelFile(books,filename);
        }else{
            System.out.println("Invalid Choice");
        }
        ConsoleHelper.retrunToMenu();
    }
}
