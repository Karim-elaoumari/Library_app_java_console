package service.implementation;
import model.Reservation;
import java.util.List;

import model.Result;
import repository.ReservationRepository;
import service.ReservationService;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import repository.reservationHelper.CreateReservationHelper;
public class ReservationServiceImpl implements ReservationService {
   private ReservationRepository reservationRepository = new ReservationRepository();
    @Override
    public Result addReservation(Reservation reservation) {
        if(reservation.getBook()==null || reservation.getBorrower()==null || reservation.getDueDate()==null || reservation.getBook().getId()<0 || reservation.getBorrower().getId()<0 ){
            Result result = new Result(400,"invalid inputs!");
            return result;
        }
        if(reservationRepository.addReservation(reservation)==true){
            Result result = new Result(200,"Reservation added successfully!");
            return result;
        } else{
            Result result = new Result(500,"Reservation not added!");
            return result;
        }
    }
    @Override
    public  Result deleteReservation(Reservation reservation){
        if(reservation.getId()<0){
            Result result = new Result(400,"invalid inputs!");
            return result;
        }
        if(reservationRepository.deleteReservation(reservation)==true){
            Result result = new Result(200,"Reservation deleted successfully!");
            return result;
        }else{
            Result result = new Result(500,"Reservation not deleted!");
            return result;
        }
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
    public Result changeStatusToReturned(Reservation reservation){
         if(reservation.getId()<0){
            Result result = new Result(400,"invalid inputs!");
            return result;
         }
       if (reservationRepository.changeStatusToReturned(reservation)==true){
            Result result = new Result(200,"Reservation status changed successfully!");
            return result;
        }else{
            Result result = new Result(500,"Reservation status not changed!");
            return result;
        }
     }


}
