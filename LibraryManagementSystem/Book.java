import java.util.ArrayList;
import java.util.List;

public class Book {
    private String title;
    private Author author;
    private Genre genre;
    private ArrayList<Patron> holds;
    
    public Book(String title, Author author, Genre genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        holds = new ArrayList<Patron>(); 
    }
    
    public int currentlyHolding() {
        return holds.size();
    }
    
    public boolean noHolds() {
        return holds.isEmpty();
    }
    
    public boolean isFirstHolder(Patron p) {
        return p == holds.get(0);
    }
    
    public void moveHoldsDown(Patron p) {
        if (isFirstHolder(p)) {
            holds.remove(0);
        }
    }
    
    public Patron firstHolder() {
        return holds.get(0);
    }
    
    public void addHold(Patron p) {
        holds.add(p);
    }
    
    public boolean matches(String enteredTitle) {
        return (enteredTitle == (title)); 
    }
    
    public String title() {
        return title;
    }
    
    public Author author() {
        return author;
    }
    
    public Genre genre() {
        return genre;
    }
    
    @Override
    public String toString() {
        return author + ", " + title; 
    }
}