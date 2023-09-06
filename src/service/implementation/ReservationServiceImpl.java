package service.implementation;
import model.Reservation;
import java.util.List;
import repository.ReservationRepository;
import service.ReservationService;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import service.implementation.reservationHelper.CreateReservationHelper;
public class ReservationServiceImpl implements ReservationService {
   private ReservationRepository reservationRepository = new ReservationRepository();
    @Override
    public void addReservation(Reservation reservation) {
        if(reservationRepository.addReservation(reservation)==true){System.out.println("Reservation added successfully!");
        } else{System.out.println("Reservation not added!");}
    }
    @Override
    public  void deleteReservation(Reservation reservation){
        if(reservationRepository.deleteReservation(reservation)==true){System.out.println("Reservation deleted successfully!");
        }else{System.out.println("Reservation not deleted!");}
    }
    @Override
    public  List<Reservation> displayReservations(){
        List<Reservation> reservations = new ArrayList<>();
        try{
            ResultSet resultSet = reservationRepository.displayReservations();
            if(resultSet!=null){
                while (resultSet.next()){
                    reservations.add(CreateReservationHelper.createReservation(resultSet));
                }
            } else{System.out.println("No reservations found!");}
        }catch (SQLException e){e.printStackTrace();}
        return reservations;
    }

    @Override
    public List<Reservation> getReservationsByBorrowerCIN(String cin){
        List<Reservation> reservations = new ArrayList<>();
        try{
            ResultSet resultSet = reservationRepository.getReservationsByBorrowerCIN(cin);
            if(resultSet!=null){
                while (resultSet.next()){
                    reservations.add(CreateReservationHelper.createReservation(resultSet));
                }
            } else{System.out.println("No reservations found!");}
        }catch (SQLException e){e.printStackTrace();}
        return reservations;
    }
    @Override
    public void changeStatusToReturned(Reservation reservation){
       if (reservationRepository.changeStatusToReturned(reservation)==true){System.out.println("Reservation status changed successfully!");
       }else{System.out.println("Reservation status not changed!");}
     }


}
