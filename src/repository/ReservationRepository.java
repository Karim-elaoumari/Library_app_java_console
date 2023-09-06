package repository;

import java.sql.*;

import model.Autor;
import model.Book;
import model.Borrower;
import model.Reservation;
import util.DBUtil;

public class ReservationRepository {
    private Connection connection;
    public ReservationRepository(){connection = DBUtil.getConnection();}


    public boolean addReservation(Reservation reservation){
        try {
            String query = "INSERT INTO reservation (book_id, borrower_id, borrow_date,status,due_date) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, reservation.getBook().getId());
            preparedStatement.setInt(2, reservation.getBorrower().getId());
            preparedStatement.setDate(3, reservation.getBorrow_date());
            preparedStatement.setString(4, reservation.getStatus());
            preparedStatement.setDate(5, reservation.getDueDate());
            preparedStatement.executeUpdate();
           return true;
        } catch (SQLException e) {e.printStackTrace();}
        return false;
    }
    public boolean deleteReservation(Reservation reservation){
        try{
            String query = "DELETE FROM reservation WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, reservation.getId());
            preparedStatement.executeUpdate();
          return true;
        }catch (SQLException e){e.printStackTrace();}
        return false;
    }
    public ResultSet displayReservations(){
        try{
            String query = "SELECT reservation.id AS reservation_id, books.id AS book_id, books.title ,books.isbn,books.quantity AS book_quantity,books.language authors.id AS author_id, authors.name AS author_name, borrowers.id AS borrower_id, borrowers.name AS borrower_name,reservstion.status,reservstion.due_date,reservation.borrow_date FROM reservation JOIN books ON reservation.book_id = books.id JOIN authors ON books.author_id = authors.id JOIN borrowers ON reservation.borrower_id = borrowers.id;";
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        }catch (SQLException e){e.printStackTrace();}
        return null;
    }
    public ResultSet getReservationsByBorrowerCIN(String cin){
        try{
            String query = "SELECT reservation.id AS reservation_id, books.id AS book_id, books.title ,books.isbn,books.quantity AS book_quantity,books.language, authors.id AS author_id, authors.name AS author_name,authors.country, borrowers.id AS borrower_id, borrowers.name AS borrower_name, borrowers.cin,reservation.status,reservation.due_date,reservation.borrow_date FROM reservation JOIN books ON reservation.book_id = books.id JOIN authors ON books.author_id = authors.id JOIN borrowers ON reservation.borrower_id = borrowers.id WHERE borrowers.cin = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cin);
            preparedStatement.executeQuery();
            return preparedStatement.getResultSet();
        }catch (SQLException e){e.printStackTrace();}
        return null;
    }
    public boolean changeStatusToReturned(Reservation reservation){
        try{
            String query = "UPDATE reservation SET status = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "returned");
            preparedStatement.setInt(2, reservation.getId());
            preparedStatement.executeUpdate();
            return true;
        }catch (SQLException e){e.printStackTrace();}
        return false;
    }



}
