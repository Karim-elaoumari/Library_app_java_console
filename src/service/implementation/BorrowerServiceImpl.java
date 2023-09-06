package service.implementation;

import model.Borrower;
import repository.BorrowerRepository;
import service.BorrowerService;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class BorrowerServiceImpl implements BorrowerService {
    private BorrowerRepository borrowerRepository = new BorrowerRepository();
    @Override
    public void addBorrower(Borrower borrower){
        if (borrowerRepository.addBorrower(borrower)){System.out.println("Borrower added successfully!");
        }else{System.out.println("Borrower not added!");}
    }
    public void deleteBorrower(Borrower borrower){
        if (borrowerRepository.deleteBorrower(borrower)){System.out.println("Borrower deleted successfully!");
        }else{System.out.println("Borrower not deleted!");}
    }
    public List<Borrower> displayBorrowers(){
        List<Borrower> borrowers = new ArrayList<>();
        try{
            ResultSet resultSet = borrowerRepository.displayBorrowers();
            if (resultSet!=null){
                while (resultSet.next()){
                    Borrower borrower = new Borrower(
                            resultSet.getInt("id"), resultSet.getString("cin"), resultSet.getString("name")
                    );
                    borrowers.add(borrower);
                }
            }else{System.out.println("No borrowers found!");}
        }catch (SQLException e){e.printStackTrace();}
        return borrowers;

    }
    public List<Borrower> getBorrowerByCIN(String cin){
        List<Borrower> borrowers = new ArrayList<>();
        try{
            ResultSet resultSet = borrowerRepository.getBorrowerByCIN(cin);
            if (resultSet!=null){
                while (resultSet.next()){
                    Borrower borrower = new Borrower(
                            resultSet.getInt("id"), resultSet.getString("cin"), resultSet.getString("name")
                    );
                    borrowers.add(borrower);
                }
            }else{System.out.println("No borrowers found!");}
        }catch (SQLException e){e.printStackTrace();}
         return borrowers;
    }
}
