package library;

import java.util.List;

public class Member {
    private String name;
    private int memberID;
    private String address;
    private List<Book> borrowedBooks;
    public Member(String name, int memberID, String address) {
        this.name = name;
        this.memberID = memberID;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }
    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }
    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
}
