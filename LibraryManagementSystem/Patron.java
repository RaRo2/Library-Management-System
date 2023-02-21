import java.util.ArrayList;
import java.util.List;
import java.util.*;
public class Patron {
    private int ID;
    private String name;
    private ArrayList<Book> currentlyBorrowed;
    private ArrayList<Book> borrowingHistory;
        
    public Patron(int ID, String name) {
        this.ID = ID;
        this.name = name;
        currentlyBorrowed = new ArrayList<Book>();
        borrowingHistory = new ArrayList<Book>();
    }
        
    public boolean currentlyBorrowingEmpty() {
        return currentlyBorrowed.isEmpty();
    }

    public void addBook(Book b) {
        currentlyBorrowed.add(b);
        borrowingHistory.add(b);
    }
    
    public void removeBook(Book b) {
        currentlyBorrowed.remove(b);
    }
    
    public void currentlyBorrowed() {
        for (Book b : currentlyBorrowed) {
            System.out.println(b);
        }
    }
    
    public void borrowingHistory() {
        for (Book b : borrowingHistory) {
            System.out.println(b);
        }
    }
    // borrowingHistory = [1,1,2,1]
    public void favBooks() {
        ArrayList<Book> dupeBooks = new ArrayList<Book>();
        int count;
        int max;
        for (int i = 0; i < borrowingHistory.size() ; i++) {
            for (int j = i+1 ; j < borrowingHistory.size() ; j++) {
                if (borrowingHistory.get(i) == borrowingHistory.get(j)) {
                    dupeBooks.add(borrowingHistory.get(j));
                }
            }
        }
        Set<Book> distinctDupeBooks = new HashSet<>(borrowingHistory);
        for (Book b : distinctDupeBooks) {
            System.out.println(b);
        }
    }
    
    
    public int ID() {
        return ID;
    }
        
    public String name() {
        return name;
    }
        
    public boolean matches(int enteredID) {
        return (enteredID == ID);
    }
    
    @Override
    public String toString() {
        return ID + " " + name; 
    }
}
