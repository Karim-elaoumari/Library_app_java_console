package service.implementation;

import model.Borrower;
import model.Result;
import repository.BorrowerRepository;
import service.BorrowerService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class BorrowerServiceImpl implements BorrowerService {
    private BorrowerRepository borrowerRepository = new BorrowerRepository();
    @Override
    public Result addBorrower(Borrower borrower){
        if(borrower.getBorrower_name()==null || borrower.getBorrower_CIN()==null || borrower.getBorrower_name().length()==0
                || borrower.getBorrower_CIN().length()==0){
            Result result = new Result(400,"invalid inputs!");
            return result;
        }
        if (borrowerRepository.addBorrower(borrower)){
            Result result = new Result(200,"Borrower added successfully!");
            return result;
        }else{
            Result result = new Result(500,"Borrower not added!");
            return result;
        }
    }
    public Result deleteBorrower(Borrower borrower){
        if(borrower.getBorrower_CIN()==null || borrower.getBorrower_CIN().length()==0){
            Result result = new Result(400,"invalid inputs!");
            return result;
        }
        if (borrowerRepository.deleteBorrower(borrower)){
            Result result = new Result(200,"Borrower deleted successfully!");
            return result;
        }else{
            Result result = new Result(500,"Borrower not deleted!");
            return result;
        }
    }
    public List<Borrower> displayBorrowers(){
        List<Borrower> borrowers = borrowerRepository.displayBorrowers();
        return borrowers;
    }
    public List<Borrower> getBorrowerByCIN(String cin){
        if(cin==null || cin=="" || cin.length()==0){
            System.out.println("invalid inputs!");
            return null;
        }
        List<Borrower> borrowers = borrowerRepository.getBorrowerByCIN(cin);
        return borrowers;
    }
}
