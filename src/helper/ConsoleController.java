package helper;
import java.util.Scanner;

public class ConsoleController {
    public static void clearScreen() {

            System.out.print("\033[H\033[2J");
            System.out.flush();

    }

    public static void printHelloMessage(){
        System.out.println("\033[1;32m" + "Hello, welcome to the library management system" + "\033[0m");
    }
    public static void showMenuOptions(){
        System.out.println("-----------------------------------------------------------------");
        System.out.println("1. Add Book");
        System.out.println("2. List Available Books");
        System.out.println("3. Search Books");
        System.out.println("4. edit Book");
        System.out.println("5. delete Book");
        System.out.println("6, List Authors");
        System.out.println("7. Add Author");
        System.out.println("8. Delete Author");
        System.out.println("9. Edit Author");
        System.out.println("10. Get Author Books");
        System.out.println("11. Borrow New Book");
        System.out.println("12. Return Book");
        System.out.println("13. Exit");
        System.out.print("Choose an option: ");

    }
    public static void retrunToMenu(){
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Press enter to return to the menu");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        ConsoleController.clearScreen();

    }


}
