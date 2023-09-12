package library;

import helper.ConsoleHelper;
import model.Autor;
import model.Result;
import service.AutorService;
import service.implementation.AutorServiceImpl;

import java.util.List;
import java.util.Scanner;

public class AutorController {
    private static AutorService autorService = new AutorServiceImpl();
    private static Scanner scanner = new Scanner(System.in);
    public static void listAuthors() {
        List<Autor> authors = autorService.getAutors();
        if (authors.isEmpty()) {
            System.out.println("No authors in the library.");
        } else {
            System.out.println("Authors:");
            System.out.println("-------------------------------------------------------");
            System.out.printf("%-4s | %-30s | %-15s%n", "No.", "Name", "Country");
            System.out.println("-------------------------------------------------------");

            for (int i = 0; i < authors.size(); i++) {
                System.out.printf("%-4d | %-30s | %-15s%n",
                        (i + 1),
                        authors.get(i).getName(),
                        authors.get(i).getCountry()
                );
                System.out.println("-------------------------------------------------------");
            }
        }
        ConsoleHelper.retrunToMenu();
    }
    public static void addAuthor() {
        System.out.print("Enter author name: ");
        String name = scanner.nextLine();

        System.out.print("Enter author country: ");
        String country = scanner.nextLine();

        Autor newAuthor = new Autor(
                name,
                country
        );
        Result result = autorService.addAutor(newAuthor);
        if(result.isSuccess()){
            System.out.println(result.getMessage());
        }else{
            System.out.println(result.getMessage());
        }
        ConsoleHelper.retrunToMenu();
    }
    public static  void deleteAuthor(){
        System.out.print("Enter author name: ");
        String name = scanner.nextLine();
        List<Autor> authors = autorService.getAutorByName(name);
        if (authors.isEmpty()) {
            System.out.println("No authors found matching the search criteria.");
        } else {
           Result result =  autorService.deleteAutor(authors.get(0));
           if (result.isSuccess()){
               System.out.println(result.getMessage());
              }else{
                System.out.println(result.getMessage());
           }
        }
        authors = null;
        ConsoleHelper.retrunToMenu();
    }
    public static void editAuthor() {
        System.out.print("Enter author name: ");
        String name = scanner.nextLine();
        List<Autor> authors = autorService.getAutorByName(name);
        if (authors.isEmpty()) {
            System.out.println("No authors found matching the search criteria.");
        } else {
            System.out.println("Author found:");
            System.out.println("Name: " + authors.get(0).getName());
            System.out.println("Country: " + authors.get(0).getCountry());
            System.out.println("-----------------------");
            System.out.println(" do you want to edit the name of this author (Y/N) :");
//            check first the input if yes ot no
            String choice = scanner.nextLine();
            if (choice.equals("Y")) {
                System.out.print("Enter new author name: ");
                String newName = scanner.nextLine();
                authors.get(0).setName(newName);
            }
            System.out.println(" do you want to edit the  country of this author (Y/N) :");
            choice = scanner.nextLine();
            if (choice.equals("Y")) {
                System.out.print("Enter new author country: ");
                String newCountry = scanner.nextLine();
                authors.get(0).setCountry(newCountry);
            }
            ConsoleHelper.clearScreen();
            Result result = autorService.editAutor(authors.get(0));
            if (result.isSuccess()) {
                System.out.println(result.getMessage());
            } else {
                System.out.println(result.getMessage());
            }

        }
        authors = null;
        ConsoleHelper.retrunToMenu();
    }
    public static void getAutorBooks(){
        System.out.print("Enter author name: ");
        String name = scanner.nextLine();
        List<Autor> authors = autorService.getAutorByName(name);
        if (authors.isEmpty()) {
            System.out.println("No authors found matching the search criteria.");
        } else {
            System.out.println("Author found:");
            System.out.println("Name: " + authors.get(0).getName() + " | Country: " + authors.get(0).getCountry());
            System.out.println("-----------------------");
            System.out.println("Author Books:");
            autorService.getAutorBooks(authors.get(0));
            if(authors.get(0).getBooks().isEmpty()){
                System.out.println("No books found for this author.");
            }
            else{
                System.out.println(authors.get(0).toString());
                System.out.println("--------------------------------------------------------------------------------------");
                System.out.printf("%-4s | %-40s | %-15s | %-9s | %-20s%n", "No.", "Title", "ISBN", "Quantity", "Language");
                System.out.println("--------------------------------------------------------------------------------------");

                for (int i = 0; i < authors.get(0).getBooks().size(); i++) {
                    System.out.printf("%-4d | %-40s | %-15s | %-9d | %-20s%n",
                            (i + 1),
                            authors.get(0).getBooks().get(i).getTitle(),
                            authors.get(0).getBooks().get(i).getIsbn(),
                            authors.get(0).getBooks().get(i).getQuantity(),
                            authors.get(0).getBooks().get(i).getLanguage()
                    );
                    System.out.println("----------------------------------------------------------------------------------------");
                }
            }
        }
        authors = null;
        ConsoleHelper.retrunToMenu();

    }
}
