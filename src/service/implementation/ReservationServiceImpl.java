package service.implementation;
import model.Reservation;
import java.util.List;
import repository.ReservationRepository;
import service.ReservationService;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import repository.reservationHelper.CreateReservationHelper;
public class ReservationServiceImpl implements ReservationService {
   private ReservationRepository reservationRepository = new ReservationRepository();
    @Override
    public void addReservation(Reservation reservation) {
        if(reservation.getBook()==null || reservation.getBorrower()==null || reservation.getDueDate()==null || reservation.getBook().getId()<0 || reservation.getBorrower().getId()<0 ){
            System.out.println("invalid inputs!");
            return;
        }
        if(reservationRepository.addReservation(reservation)==true){System.out.println("Reservation added successfully!");
        } else{System.out.println("Reservation not added!");}
    }
    @Override
    public  void deleteReservation(Reservation reservation){
        if(reservation.getId()<0){
            System.out.println("invalid inputs!");
            return;
        }
        if(reservationRepository.deleteReservation(reservation)==true){System.out.println("Reservation deleted successfully!");
        }else{System.out.println("Reservation not deleted!");}
    }
    @Override
    public  List<Reservation> displayReservations(){
        List<Reservation> reservations = reservationRepository.displayReservations();
        return reservations;
    }

    @Override
    public List<Reservation> getReservationsByBorrowerCIN(String cin){
        if(cin==null || cin.length()==0){
            System.out.println("invalid inputs!");
            return null;
        }
        List<Reservation> reservations = reservationRepository.getReservationsByBorrowerCIN(cin);
        return reservations;
    }
    @Override
    public void changeStatusToReturned(Reservation reservation){
         if(reservation.getId()<0){
              System.out.println("invalid inputs!");
              return;
         }
       if (reservationRepository.changeStatusToReturned(reservation)==true){System.out.println("Reservation status changed successfully!");
       }else{System.out.println("Reservation status not changed!");}
     }


}
