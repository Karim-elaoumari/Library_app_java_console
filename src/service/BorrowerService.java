package service;

import model.Borrower;

import java.util.List;

public interface BorrowerService {
    void addBorrower(Borrower borrower);
    void deleteBorrower(Borrower borrower);
    List<Borrower> displayBorrowers();
    List<Borrower> getBorrowerByCIN(String cin);


}
