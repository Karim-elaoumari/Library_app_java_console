package helper;

import model.Book;
import model.Borrower;
import model.Reservation;
import model.Result;
import service.BookService;
import service.BorrowerService;
import service.ReservationService;
import service.implementation.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class ReservationHelper {

    private  Scanner scanner = new Scanner(System.in);
    private ReservationService reservationService = new ReservationServiceImpl();
    private BorrowerService borrowerService = new BorrowerServiceImpl();
    private BookService bookService = new BookServiceImpl();

    public Book findBook(){
        while (true) {
            System.out.println("Enter book ISBN or Title or 1 to exit: ");
            String book = scanner.nextLine();
            if (book.equals("1")) {
                break;
            }
            List<Book> books = bookService.getBookByIsbnOrTitle(book);
            if(books==null){
                return null;
            }
            else if (books.isEmpty()) {
                System.out.println("No books found matching the search criteria.");
            } else if (books.get(0).getQuantity() == 0) {
                System.out.println("1-" + " Title : " + books.get(0).getTitle() + " | Author : " + books.get(0).getAutor().getName() + " | ISBN : " + books.get(0).getIsbn() + " | Quantity : " + books.get(0).getQuantity() + " | Language : " + books.get(0).getLanguage());
                System.out.println("-----------------------------------------------------------------");
                System.out.println("No available books quantity in the library for this moment.");

            } else {
                System.out.println("Search Results:");
                System.out.println("1-" + " Title : " + books.get(0).getTitle() + " | Author : " + books.get(0).getAutor().getName() + " | ISBN : " + books.get(0).getIsbn() + " | Quantity : " + books.get(0).getQuantity() + " | Language : " + books.get(0).getLanguage());
                System.out.println("-----------------------------------------------------------------");
                return books.get(0);
            }
        }
        return null;

    }
    public Borrower createBorrower(){
        System.out.println("Enter borrower CIN: ");
        String borrowerCIN = scanner.nextLine();
        List<Borrower> borrowers = borrowerService.getBorrowerByCIN(borrowerCIN);
        if(borrowers==null){
            return null;
        }
        else if(borrowers.isEmpty()){
            System.out.println("Enter borrower name: ");
            String borrowerName = scanner.nextLine();
            Borrower newBorrower = new Borrower(borrowerCIN,borrowerName);
            return newBorrower;
        }else{
            System.out.println("Borrower already exists.");
            System.out.print("Enter 1 to use this borrower and continue or 0 to cancel: ");
            if(scanner.nextInt() == 0){
                return null;
            }else{
                return borrowers.get(0);
            }
        }
    }
    public boolean completeReservation(Book book, Borrower borrower, Date sqlDate){
        System.out.println("Enter 1 to confirm reservation or 0 to cancel: ");
        int choice = scanner.nextInt();
        if (choice == 0) {
            return false;
        } else {
            if (borrower.getId() == 0) {
                Result result = borrowerService.addBorrower(borrower);
                if (result.getStatus() != 201) {
                    borrower.setId(borrowerService.getBorrowerByCIN(borrower.getBorrower_CIN()).get(0).getId());
                }else{
                    System.out.println(result.getMessage());
                }

            }
            Result result = reservationService.addReservation(new Reservation(book, borrower, sqlDate, new Date(System.currentTimeMillis()), "borrowed"));
            System.out.println(result.getMessage());
            return true;
        }
    }
    public java.sql.Date getDueDate(){
        while (true){
            scanner.nextLine();
            System.out.println("Enter return date dd/MM/yyyy: ");
            String due_date = scanner.nextLine();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            try {
                java.util.Date utilDate = dateFormat.parse(due_date);
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                return  sqlDate;

            } catch (ParseException e) {
                System.out.println("Error parsing date: " + e.getMessage());
                System.out.print("Enter 1 to try again or 0 to cancel: ");
                if(scanner.nextInt() == 0){
                    return null;
                }
            }

        }
    }
}
