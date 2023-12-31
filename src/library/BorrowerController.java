package library;

import java.util.List;
import java.util.Scanner;

import helper.ConsoleHelper;
import model.Borrower;
import service.BorrowerService;
import service.implementation.BorrowerServiceImpl;
public class BorrowerController {
    private static Scanner scanner = new Scanner(System.in);
    private static BorrowerService borrowerService = new BorrowerServiceImpl();

    public static void displayBorrowers(){
        List<Borrower> borrowers = borrowerService.displayBorrowers();
        if (borrowers.isEmpty()) {
            System.out.println("No borrowers in the library.");
        } else {
            System.out.println("Borrowers:");

            System.out.println("--------------------------------------------------------------------------");
            System.out.printf("%-4s | %-30s | %-15s%n", "No.", "Name", "CIN");
            System.out.println("--------------------------------------------------------------------------");

            for (int i = 0; i < borrowers.size(); i++) {
                System.out.printf("%-4d | %-30s | %-15s%n",
                        (i + 1),
                        borrowers.get(i).getBorrower_name(),
                        borrowers.get(i).getBorrower_CIN()
                );
                System.out.println("--------------------------------------------------------------------------");
            }
        }
        ConsoleHelper.retrunToMenu();
    }

}
