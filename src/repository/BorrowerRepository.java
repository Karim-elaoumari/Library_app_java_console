package repository;

import model.Book;
import model.Borrower;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowerRepository {
    private Connection connection;
    public BorrowerRepository(){connection = DBUtil.getConnection();}


    public boolean addBorrower(Borrower borrower){
        try{
            String sql = "INSERT INTO borrowers (name, cin) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, borrower.getBorrower_name());
            preparedStatement.setString(2, borrower.getBorrower_CIN());
            preparedStatement.executeUpdate();
           return true;
        }catch (SQLException e){e.printStackTrace();}
        return false;
    }
    public boolean deleteBorrower(Borrower borrower){
        try{
            String sql = "DELETE FROM borrowers WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, borrower.getId());
            preparedStatement.executeUpdate();
           return true;
        }catch (SQLException e){e.printStackTrace();}
        return false;
    }
    public List<Borrower> displayBorrowers(){
        List<Borrower> borrowers = new ArrayList<>();
        try{
            String sql = "SELECT * FROM borrowers";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeQuery();
            ResultSet resultSet =  preparedStatement.getResultSet();
            while (resultSet.next()){
                Borrower borrower = new Borrower(
                        resultSet.getInt("id"), resultSet.getString("cin"), resultSet.getString("name")
                );
                borrowers.add(borrower);
            }
        }catch (SQLException e){e.printStackTrace();}
        return borrowers;
    }
    public List<Borrower> getBorrowerByCIN(String cin){
        List<Borrower> borrowers = new ArrayList<>();
        try{
            String sql = "SELECT * FROM borrowers WHERE cin = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cin);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()){
                Borrower borrower = new Borrower(
                        resultSet.getInt("id"), resultSet.getString("cin"), resultSet.getString("name")
                );
                borrowers.add(borrower);
            }
        }catch (SQLException e){e.printStackTrace();}
        return borrowers;
    }
}
