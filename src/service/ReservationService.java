package service;
import model.Reservation;
import model.Result;

import java.util.List;
public interface ReservationService {
    Result addReservation(Reservation reservation);
    Result deleteReservation(Reservation reservation);
    List<Reservation> displayReservations();
    List<Reservation> getReservationsByBorrowerCIN(String cin);
    Result changeStatusToReturned(Reservation reservation);
}
