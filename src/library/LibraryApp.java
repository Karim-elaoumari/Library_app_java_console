package library;

import java.util.List;
import java.util.Scanner;
import service.AutorService;
import service.implementation.AutorServiceImpl;
import model.Autor;
import helper.ConsoleHelper;

public class LibraryApp {
    private static Scanner scanner = new Scanner(System.in);
    private static AutorService autorService = new AutorServiceImpl();
    public static void main(String[] args){
        ConsoleHelper.printHelloMessage();
        while (true) {
            ConsoleHelper.showMenuOptions();
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
                    getStates();
                    break;
                case 14:
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
        ConsoleHelper.retrunToMenu();
    }
    private static void listAvailableBooks() { BookController.listAvailableBooks(); ConsoleHelper.retrunToMenu();}
    private static void searchBooks() { BookController.searchBooks(); ConsoleHelper.retrunToMenu();}
    private static void editBook() { BookController.editBook(); ConsoleHelper.retrunToMenu();}
    private static void deleteBook() { BookController.deleteBook(); ConsoleHelper.retrunToMenu();}
    private static void listAuthors() { AutorController.listAuthors(); ConsoleHelper.retrunToMenu();}
    private static void addAuthor() { AutorController.addAuthor(); ConsoleHelper.retrunToMenu();}
    private static  void deleteAuthor(){ AutorController.deleteAuthor(); ConsoleHelper.retrunToMenu();}
    private static void editAuthor(){ AutorController.editAuthor(); ConsoleHelper.retrunToMenu();}
    private static void getAutorBooks(){ AutorController.getAutorBooks(); ConsoleHelper.retrunToMenu();}
    private static void addReservation(){ ReservationController.addReservation(); ConsoleHelper.retrunToMenu();}
    private static  void returnBook(){ ReservationController.returnBook(); ConsoleHelper.retrunToMenu();}
    private static  void getStates(){ BookController.getStatesLibrary(); ConsoleHelper.retrunToMenu();}


}
