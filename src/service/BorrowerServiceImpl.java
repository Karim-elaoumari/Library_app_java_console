package service;

import model.Borrower;
import util.DBUtil;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class BorrowerServiceImpl implements BorrowerService{
    private Connection connection;
    // making an instance of the connection
    public BorrowerServiceImpl() {
        connection = DBUtil.getConnection();
    }
    @Override
    public void addBorrower(Borrower borrower){
        // add borrower to the database
        String sql = "INSERT INTO borrowers (name, cin) VALUES (?, ?)";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, borrower.getBorrower_name());
            preparedStatement.setString(2, borrower.getBorrower_CIN());
            preparedStatement.executeUpdate();
            System.out.println("Borrower added successfully!");
        }catch (SQLException e){
            e.printStackTrace();

        }

    }
    public void deleteBorrower(Borrower borrower){
        String sql = "DELETE FROM borrowers WHERE id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, borrower.getId());
            preparedStatement.executeUpdate();
            System.out.println("Borrower deleted successfully!");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public List<Borrower> displayBorrowers(){
        List<Borrower> borrowers = new ArrayList<>();
        String sql = "SELECT * FROM borrowers";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeQuery();
            while (preparedStatement.getResultSet().next()){
                Borrower borrower = new Borrower(
                        preparedStatement.getResultSet().getInt("id"),
                        preparedStatement.getResultSet().getString("cin"),
                        preparedStatement.getResultSet().getString("name")
                );
                borrowers.add(borrower);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return borrowers;

    }
    public List<Borrower> getBorrowerByCIN(String cin){
        List<Borrower> borrowers = new ArrayList<>();
        String sql = "SELECT * FROM borrowers WHERE cin = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cin);
            preparedStatement.executeQuery();
            while (preparedStatement.getResultSet().next()){
                Borrower borrower = new Borrower(
                        preparedStatement.getResultSet().getInt("id"),
                        preparedStatement.getResultSet().getString("cin"),
                        preparedStatement.getResultSet().getString("name")
                );
                borrowers.add(borrower);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return borrowers;
    }



}
