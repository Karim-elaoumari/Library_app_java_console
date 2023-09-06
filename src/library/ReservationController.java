package library;

import java.util.List;
import java.util.Scanner;

import model.Borrower;
import model.Reservation;
import service.BorrowerService;
import service.ReservationService;
import service.implementation.ReservationServiceImpl;
import service.implementation.BorrowerServiceImpl;
import service.BookService;
import service.implementation.BookServiceImpl;
import model.Book;
import helper.ReservationHelper;



public class ReservationController {
    private static Scanner scanner = new Scanner(System.in);
    private static ReservationService reservationService = new ReservationServiceImpl();
    private static BorrowerService borrowerService = new BorrowerServiceImpl();
    private static BookService bookService = new BookServiceImpl();

    public static void addReservation() {
        ReservationHelper reservationHelper = new ReservationHelper();
        Book book = reservationHelper.findBook();
        if (book == null) {
            System.out.println("Reservation canceled.");
        }else{
            Borrower borrower = reservationHelper.createBorrower();
            if(borrower == null){
                System.out.println("Reservation canceled.");
            }else{
                java.sql.Date date = reservationHelper.getDueDate();
                if(date == null){
                    System.out.println("Reservation canceled.");
                }else{
                    reservationHelper.completeReservation(book, borrower, date);
                }
            }
        }
    }
    public static void returnBook(){
        System.out.println("Enter borrower CIN: ");
        String borrowerCIN = scanner.nextLine();
        List<Borrower> borrowers = borrowerService.getBorrowerByCIN(borrowerCIN);
        if(borrowers.isEmpty()){
            System.out.println("Borrower not found.");
        }else{
            List<Reservation> reservations = reservationService.getReservationsByBorrowerCIN(borrowers.get(0).getBorrower_CIN());
            if(reservations.isEmpty()){
                System.out.println("No reservations found for this borrower.");
            }else{
                System.out.println("Reservations:");
                for(int i = 0; i < reservations.size(); i++){
                    System.out.println(i+1+"-"+" Title : " + reservations.get(i).getBook().getTitle() + " | Author : " + reservations.get(i).getBook().getAutor().getName() + " | ISBN : " + reservations.get(i).getBook().getIsbn() + " | Quantity : " + reservations.get(i).getBook().getQuantity() + " | Language : " + reservations.get(i).getBook().getLanguage());
                }
                System.out.println("-----------------------------------------------------------------");
                System.out.println("Enter reservation number to return book or 0 to cancel: ");
                int reservationNumber = scanner.nextInt();
                if(reservationNumber == 0){
                    System.out.println("Operation canceled.");
                }else{
                    reservationService.changeStatusToReturned(reservations.get(reservationNumber-1));
                }
            }
        }

    }


}


