package com.pluralsight.Week2;

/**
 *  Represents a book within a library. Includes attributes of a book like
 *  it's ID number, isbn, title, whether it has been checked out or not, and who checked it out.
 *
 * @author Ravi Spigner
 */
public class Book {
    private int id;
    private String isbn;
    private String title;
    private boolean isCheckedOut;
    private String checkedOutTo;

    public Book() {
        this.id = 0;
        this.isbn = "";
        this.title = "";
        this.isCheckedOut = false;
        this.checkedOutTo = "";
    }

    public Book(int id, String isbn, String title, boolean isCheckedOut, String checkedOutTo) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.isCheckedOut = isCheckedOut;
        this.checkedOutTo = checkedOutTo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public void setIsCheckedOut(boolean checkedOut) {
        this.isCheckedOut = checkedOut;
    }

    public String getCheckedOutTo() {
        if (checkedOutTo.isEmpty() || checkedOutTo == null) {
            return "No one";
        } else {
            return checkedOutTo;
        }
    }

    public void setCheckedOutTo(String checkedOutTo) {
        this.checkedOutTo = checkedOutTo;
    }

    public void checkOut(String name) {
        setIsCheckedOut(true);
        setCheckedOutTo(name);
    }

    public void checkIn() {
        setIsCheckedOut(false);
        setCheckedOutTo("");
    }

    public void display() {
        System.out.println("Book ID: " + this.getId() +
                ", isbn: " + this.getIsbn() +
                ", title: "+  this.getTitle() + ", Is this book checked out?: " +
                this.isCheckedOut() + ", This book is checked out to: " +
                this.getCheckedOutTo() + ".");
    }
}
