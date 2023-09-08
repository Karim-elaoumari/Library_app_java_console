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
        if(borrower.getBorrower_name()==null || borrower.getBorrower_CIN()==null || borrower.getBorrower_name().length()==0
                || borrower.getBorrower_CIN().length()==0){
            System.out.println("invalid inputs!");
            return;
        }
        if (borrowerRepository.addBorrower(borrower)){System.out.println("Borrower added successfully!");
        }else{System.out.println("Borrower not added!");}
    }
    public void deleteBorrower(Borrower borrower){
        if(borrower.getBorrower_CIN()==null || borrower.getBorrower_CIN().length()==0){
            System.out.println("invalid inputs!");
            return;
        }
        if (borrowerRepository.deleteBorrower(borrower)){System.out.println("Borrower deleted successfully!");
        }else{System.out.println("Borrower not deleted!");}
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
