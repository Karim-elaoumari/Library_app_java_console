package repository;

import model.Book;
import model.Borrower;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public ResultSet displayBorrowers(){
        try{
            String sql = "SELECT * FROM borrowers";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeQuery();
            return preparedStatement.getResultSet();
        }catch (SQLException e){e.printStackTrace();}
        return null;
    }
    public ResultSet getBorrowerByCIN(String cin){
        try{
            String sql = "SELECT * FROM borrowers WHERE cin = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cin);
            preparedStatement.executeQuery();
            return preparedStatement.getResultSet();
        }catch (SQLException e){e.printStackTrace();}
        return null;
    }
}
