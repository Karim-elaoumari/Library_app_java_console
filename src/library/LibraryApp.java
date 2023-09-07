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
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    BookController.listAvailableBooks();
                    break;
                case 3:
                    BookController.searchBooks();
                    break;
                case 4:
                    BookController.editBook();
                    break;
                case 5:
                    BookController.deleteBook();
                    break;
                case 6:
                    AutorController.listAuthors();
                    break;
                case 7:
                    AutorController.addAuthor();
                    break;
                case 8:
                    AutorController.deleteAuthor();
                    break;
                case 9:
                    AutorController.editAuthor();
                    break;
                case 10:
                    AutorController.getAutorBooks();
                    break;
                case 11:
                    ReservationController.addReservation();
                    break;
                case 12:
                    ReservationController.returnBook();
                    break;
                case 13:
                    BookController.getStatesLibrary();
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
        if (authors.isEmpty()) {System.out.println("No authors found matching the search criteria.");addBook();
        } else {System.out.println("Author found:");BookController.addBook(authors.get(0));}
        ConsoleHelper.retrunToMenu();
    }
}