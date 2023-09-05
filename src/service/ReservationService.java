package service;
import model.Reservation;
import java.util.List;
public interface ReservationService {
    void addReservation(Reservation reservation);
    void deleteReservation(Reservation reservation);
    void editReservation(Reservation reservation);
    List<Reservation> displayReservations();
    List<Reservation> getReservationsByBorrowerCIN(String cin);
    void changeStatusToReturned(Reservation reservation);
}
