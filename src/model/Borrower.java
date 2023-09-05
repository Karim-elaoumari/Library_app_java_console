package model;

public class Borrower {
    private int id;
    private String borrower_CIN;
    private String borrower_name;

    public Borrower(int id, String borrower_CIN, String borrower_name) {
        this.id = id;
        this.borrower_CIN = borrower_CIN;
        this.borrower_name = borrower_name;
    }
    public Borrower(String borrower_CIN, String borrower_name) {
        this.borrower_CIN = borrower_CIN;
        this.borrower_name = borrower_name;
    }

    public int getId() {
        return id;
    }

    public String getBorrower_CIN() {
        return borrower_CIN;
    }

    public String getBorrower_name() {
        return borrower_name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBorrower_CIN(String borrower_CIN) {
        this.borrower_CIN = borrower_CIN;
    }

    public void setBorrower_name(String borrower_name) {
        this.borrower_name = borrower_name;
    }

}
