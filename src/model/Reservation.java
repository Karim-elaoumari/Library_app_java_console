package model;

import java.sql.Date;

public class Reservation {
    private int id;
    private  Book book;
    private  Borrower borrower;
    private Date dueDate;
    private Date borrow_date;
    private int quantity;
    private String status;

    public Reservation(int id, Book book, Borrower borrower, Date dueDate, Date borrow_date, String status) {
        this.id = id;
        this.book = book;
        this.borrower = borrower;
        this.dueDate = dueDate;
        this.borrow_date = borrow_date;
        this.status = status;
    }
    public Reservation(Book book, Borrower borrower, Date dueDate, Date borrow_date, String status) {
        this.id = id;
        this.book = book;
        this.borrower = borrower;
        this.dueDate = dueDate;
        this.borrow_date = borrow_date;

        this.status = status;
    }

    public int getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }
    public Borrower getBorrower() {
        return borrower;
    }
    public Date getDueDate() {
        return dueDate;
    }
    public Date getBorrow_date() {
        return borrow_date;
    }
    public String getStatus() {
        return status;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setBook(Book book) {
        this.book = book;
    }
    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    public void setBorrow_date(Date borrow_date) {
        this.borrow_date = borrow_date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
