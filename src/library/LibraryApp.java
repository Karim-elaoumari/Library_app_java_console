package library;

import service.BookService;
import service.BookServiceImpl;
import model.Book;

import java.util.List;
import java.util.Scanner;
import service.AutorService;
import service.AutorServiceImpl;
import model.Autor;
import helper.ConsoleController;
import  library.BookController;
import library.AutorController;
public class LibraryApp {
    private static Scanner scanner = new Scanner(System.in);
    private static AutorService autorService = new AutorServiceImpl();
    public static void main(String[] args) {
        ConsoleController.printHelloMessage();
        while (true) {
            ConsoleController.showMenuOptions();
            int choice = scanner.nextInt();
            System.out.println("-----------------------------------------------------------------");
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    listAvailableBooks();
                    break;
                case 3:
                    searchBooks();
                    break;
                case 4:
                    editBook();
                    break;
                case 5:
                    deleteBook();
                    break;
                case 6:
                    listAuthors();
                    break;
                case 7:
                    addAuthor();
                    break;
                case 8:
                    deleteAuthor();
                    break;
                case 9:
                    editAuthor();
                    break;
                case 10:
                    getAutorBooks();
                    break;
                case 11:
                    addReservation();
                    break;
                case 12:
                    returnBook();
                    break;
                case 13:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
    private static void addBook() {
        System.out.print("Enter Author name: ");
        String author = scanner.nextLine();
        List<Autor> authors = autorService.getAutorByName(author);
        if (authors.isEmpty()) {
            System.out.println("No authors found matching the search criteria.");
            addBook();
        } else {
            System.out.println("Author found:");
            BookController.addBook(authors.get(0));
        }
        ConsoleController.retrunToMenu();
    }
    private static void listAvailableBooks() { BookController.listAvailableBooks(); ConsoleController.retrunToMenu();}
    private static void searchBooks() { BookController.searchBooks(); ConsoleController.retrunToMenu();}
    private static void editBook() { BookController.editBook(); ConsoleController.retrunToMenu();}
    private static void deleteBook() { BookController.deleteBook(); ConsoleController.retrunToMenu();}
    private static void listAuthors() { AutorController.listAuthors(); ConsoleController.retrunToMenu();}
    private static void addAuthor() { AutorController.addAuthor(); ConsoleController.retrunToMenu();}
    private static  void deleteAuthor(){ AutorController.deleteAuthor(); ConsoleController.retrunToMenu();}
    private static void editAuthor(){ AutorController.editAuthor(); ConsoleController.retrunToMenu();}
    private static void getAutorBooks(){ AutorController.getAutorBooks(); ConsoleController.retrunToMenu();}
    private static void addReservation(){ ReservationController.addReservation(); ConsoleController.retrunToMenu();}
    private static  void returnBook(){ ReservationController.returnBook(); ConsoleController.retrunToMenu();}


}
