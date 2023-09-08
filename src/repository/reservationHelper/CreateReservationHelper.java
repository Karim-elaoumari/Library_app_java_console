package repository.reservationHelper;

import model.Autor;
import model.Book;
import model.Borrower;
import model.Reservation;

import java.sql.ResultSet;

public class CreateReservationHelper {

    public static Reservation createReservation(ResultSet resultSet) {
        try {
            Reservation reservation = new Reservation(
                    resultSet.getInt("reservation_id"),
                    new Book(resultSet.getInt("book_id"), resultSet.getString("title"),
                            new Autor(
                                    resultSet.getInt("author_id"), resultSet.getString("author_name"), resultSet.getString("country")
                            ),
                            resultSet.getString("isbn"), resultSet.getInt("book_quantity"), resultSet.getString("language")
                    ),
                    new Borrower(
                            resultSet.getInt("borrower_id"), resultSet.getString("borrower_name"), resultSet.getString("cin")
                    ),
                    resultSet.getDate("due_date"), resultSet.getDate("borrow_date"), resultSet.getString("status")
            );return reservation;
        } catch (Exception e) {e.printStackTrace();}
        return null;
    }
}
