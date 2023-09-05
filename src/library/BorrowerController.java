package library;

import java.util.List;
import java.util.Scanner;

import model.Borrower;
import service.BorrowerService;
import service.BorrowerServiceImpl;
public class BorrowerController {
    private static Scanner scanner = new Scanner(System.in);
    private static BorrowerService borrowerService = new BorrowerServiceImpl();

    public static void displayBorrowers(){
        List<Borrower> borrowers = borrowerService.displayBorrowers();
        if (borrowers.isEmpty()) {
            System.out.println("No borrowers in the library.");
        } else {
            System.out.println("Borrowers:");

            for (int i = 0; i < borrowers.size(); i++) {
                System.out.println( i+1+"-"+" Name : " + borrowers.get(i).getBorrower_name() + " | CIN : " + borrowers.get(i).getBorrower_CIN());
            }
        }
    }

}
