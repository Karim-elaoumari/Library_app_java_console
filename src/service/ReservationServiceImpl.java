package service;
import model.Book;
import model.Reservation;
import model.Borrower;
import model.Autor;

import java.sql.Connection;
import java.util.List;
import  service.ReservationService;
import util.DBUtil;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
public class ReservationServiceImpl implements ReservationService {
    private Connection connection;
    public ReservationServiceImpl() {
        this.connection = DBUtil.getConnection();
    }
    @Override
    public void addReservation(Reservation reservation) {
        try {
            String query = "INSERT INTO reservation (book_id, borrower_id, borrow_date,status,due_date) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, reservation.getBook().getId());
            preparedStatement.setInt(2, reservation.getBorrower().getId());
            preparedStatement.setDate(3, reservation.getBorrow_date());
            preparedStatement.setString(4, reservation.getStatus());
            preparedStatement.setDate(5, reservation.getDueDate());
            preparedStatement.executeUpdate();
            System.out.println("Reservation added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public  void deleteReservation(Reservation reservation){
        try{
            String query = "DELETE FROM reservation WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, reservation.getId());
            preparedStatement.executeUpdate();
            System.out.println("Reservation deleted successfully!");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    @Override
    public void editReservation(Reservation reservation){
        try{
            String query = "UPDATE reservation SET book_id = ?, borrower_id = ?, borrow_date = ?, status = ?, due_date = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, reservation.getBook().getId());
            preparedStatement.setInt(2, reservation.getBorrower().getId());
            preparedStatement.setDate(3, reservation.getBorrow_date());
            preparedStatement.setString(4, reservation.getStatus());
            preparedStatement.setDate(5, reservation.getDueDate());
            preparedStatement.setInt(6, reservation.getId());
            preparedStatement.executeUpdate();
            System.out.println("Reservation edited successfully!");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    @Override
    public  List<Reservation> displayReservations(){
        List<Reservation> reservations = new ArrayList<>();
        try{


            String query = "SELECT reservation.id AS reservation_id, books.id AS book_id, books.title ,books.isbn,books.quantity AS book_quantity,books.language authors.id AS author_id, authors.name AS author_name, borrowers.id AS borrower_id, borrowers.name AS borrower_name,reservstion.status,reservstion.due_date,reservation.borrow_date FROM reservation JOIN books ON reservation.book_id = books.id JOIN authors ON books.author_id = authors.id JOIN borrowers ON reservation.borrower_id = borrowers.id;";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
Reservation reservation = new Reservation(
                        resultSet.getInt("reservation_id"),
                        new Book(
                                resultSet.getInt("book_id"),
                                resultSet.getString("title"),
                                new Autor(
                                        resultSet.getInt("author_id"),
                                        resultSet.getString("author_name"),
                                        resultSet.getString("country")
                                ),
                                resultSet.getString("isbn"),
                                resultSet.getInt("book_quantity"),
                                resultSet.getString("language")
                        ),
                        new Borrower(
                                resultSet.getInt("borrower_id"),
                                resultSet.getString("borrower_name"),
                                resultSet.getString("cin")
                        ),
                        resultSet.getDate("due_date"),
                        resultSet.getDate("borrow_date"),
                        resultSet.getString("status")
                );

                reservations.add(reservation);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return reservations;
    }

    @Override
    public List<Reservation> getReservationsByBorrowerCIN(String cin){
        List<Reservation> reservations = new ArrayList<>();
        try{
            String query = "SELECT reservation.id AS reservation_id, books.id AS book_id, books.title ,books.isbn,books.quantity AS book_quantity,books.language, authors.id AS author_id, authors.name AS author_name,authors.country, borrowers.id AS borrower_id, borrowers.name AS borrower_name, borrowers.cin,reservation.status,reservation.due_date,reservation.borrow_date FROM reservation JOIN books ON reservation.book_id = books.id JOIN authors ON books.author_id = authors.id JOIN borrowers ON reservation.borrower_id = borrowers.id WHERE borrowers.cin = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cin);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
Reservation reservation = new Reservation(
                        resultSet.getInt("reservation_id"),
                        new Book(
                                resultSet.getInt("book_id"),
                                resultSet.getString("title"),
                                new Autor(
                                        resultSet.getInt("author_id"),
                                        resultSet.getString("author_name"),
                                        resultSet.getString("country")
                                ),
                                resultSet.getString("isbn"),
                                resultSet.getInt("book_quantity"),
                                resultSet.getString("language")
                        ),
                        new Borrower(
                                resultSet.getInt("borrower_id"),
                                resultSet.getString("borrower_name"),
                                resultSet.getString("cin")
                        ),
                        resultSet.getDate("due_date"),
                        resultSet.getDate("borrow_date"),
                        resultSet.getString("status")
                );

                reservations.add(reservation);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return reservations;
    }
    @Override
    public void changeStatusToReturned(Reservation reservation){
        String query = "UPDATE reservation SET status = ? WHERE id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "returned");
            preparedStatement.setInt(2, reservation.getId());
            preparedStatement.executeUpdate();
            System.out.println("Reservation status changed to returned successfully!");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


}
