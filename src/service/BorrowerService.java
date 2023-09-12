package service;

import model.Borrower;
import model.Result;

import java.util.List;

public interface BorrowerService {
    Result addBorrower(Borrower borrower);
    Result deleteBorrower(Borrower borrower);
    List<Borrower> displayBorrowers();
    List<Borrower> getBorrowerByCIN(String cin);


}
