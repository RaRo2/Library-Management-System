import java.util.ArrayList;
import java.util.List;

public class Catalogue {
    private Library library;
    private ArrayList<Book> booksOnShelf;
    private ArrayList<Book> booksOnLoan;
    private ArrayList<Genre> genres;
    private ArrayList<Author> authors;
    
    public Catalogue(Library library) {
        this.library = library;
        booksOnShelf = new ArrayList<Book>();
        booksOnLoan = new ArrayList<Book>();
        genres = new ArrayList<Genre>();
        authors = new ArrayList<Author>();
        authors.add(new Author("A"));
        genres.add(new Genre("G"));
        booksOnShelf.add(new Book("1", getAuthor("A") , getGenre("G")));
        booksOnShelf.add(new Book("2", getAuthor("A") , getGenre("G")));
        booksOnShelf.add(new Book("3", getAuthor("A") , getGenre("G")));
        booksOnShelf.add(new Book("4", getAuthor("A") , getGenre("G")));
    }
    
    public Book getBook(String name) {
        for (Book b : booksOnShelf) {
            if (b.title().matches(name)) {
                return b;
            }
        }
        for (Book b : booksOnLoan) {
            if (b.title().matches(name)) {
                return b;
            }
        }
        return null;
    }
    
    public Author getAuthor(String name) {
        for (Author author : authors) {
            if (author.name().matches(name))
                return author;
        }
        return null;
    }
    
    public Author getNewAuthor(String name) {
        Author existAuthor = getAuthor(name);
        if (existAuthor != null) {
            return existAuthor;
        }
        else {
            authors.add(new Author(name)); 
            return (getAuthor(name));
        }
    }
    
    public Genre getGenre(String name) {
        for (Genre genre : genres) {
            if (genre.name().matches(name))
                return genre;
        }
        return null;
    }
    
    public Genre getNewGenre(String name) {
        Genre existGenre = getGenre(name);
        if (existGenre != null) {
            return existGenre;
        }
        else {
            genres.add(new Genre(name)); 
            return (getGenre(name));
        }
    }
    
    public void catMenu() {
        char choice;
        while((choice = readChoice())!= 'R') {
            switch(choice) {
                case '1' : showBooks(); break;    //Display all books
                case '2' : showShelfBooks(); break;    //Display available books 
                case '3' : showGenres(); break;    //Display all genres
                case '4' : showBooksByGenre(); break;    //Display books in genre
                case '5' : showAuthors(); break;    //Display all authors
                case '6' : showBooksByAuthor(); break;    //Display books by author
                case '7' : library.borrowBook(); break;    //Borrrow a book
                case '8' : library.returnBook(); break;    //Return a book
                case '9' : library.holdBook(); break;    //Hold a book
                default : library.help2(); break;
            }
        }
    }
    
    public boolean currentlyLoaned(String name) {
        boolean match = false;
        for (Book b: booksOnLoan) {
            if (b.title().matches(name)) {
                match = true;
            }
        }
        return match;
    }

    public boolean bookTitleExists(String title) {
        boolean matches = false;
        for (Book b : booksOnShelf) {
            if (b.title().matches(title)) {
                matches = true;
                return matches;
            }
        }
        for (Book b : booksOnLoan) {
            if (b.title().matches(title)) {
                matches = true;
            }
        }
        return matches;
    }
    
    public void addBook() {
        System.out.println("");
        System.out.println("Adding a new book.");
        String title = readBook();
        String author = readAuthor();
        String genre = readGenre();
        if (bookTitleExists(title)) {
            System.out.println("Book already exists.");
            System.out.println("Book could not be added.");
            return;
        }
        booksOnShelf.add(new Book(title, getNewAuthor(author), getNewGenre(genre) ));
        System.out.println("Added "+ title + " to catalogue.");
        System.out.println("");
        
    }
    
    public void removeBook() {
        System.out.println("");        
        System.out.println("Removing a book.");
        String title = readBook();
        String author = readAuthor();
        if (bookTitleExists(title)) {
            for (Book b : booksOnShelf) {
                if (b.title().matches(title)) {
                    booksOnShelf.remove(b);
                    System.out.println(b+ " removed from catalogue.");
                    System.out.println("");
                    return;
                }
            }
            for (Book b : booksOnLoan) {
                if (b.title().matches(title)) {
                    booksOnShelf.remove(b);
                    System.out.println(b+ " removed from catalogue.");
                    System.out.println("");
                    return;
                }
            }
        }
        System.out.println("No such book found.");
        System.out.println("");
    }
    
    public String readBook() {
        System.out.print("Enter the title of the book: ");
        return In.nextLine();
    }
    
    public char readChoice() {
        System.out.println("Welcome to the Catalogue! Please make a selection from the menu:");
        System.out.println("1. Display all books.");
        System.out.println("2. Display all available books.");
        System.out.println("3. Display all genres.");
        System.out.println("4. Display books in a genre.");
        System.out.println("5. Display all authors.");
        System.out.println("6. Display all books by an author.");
        System.out.println("7. Borrow a book.");
        System.out.println("8. Return a book.");
        System.out.println("9. Place a hold.");
        System.out.println("R. Return to previous menu.");
        System.out.print("Enter a choice: ");
        return In.nextChar(); 
    }    
    
    public String readAuthor() {
        System.out.print("Enter the author's name: ");
        return In.nextLine();
    }

    public String readAAuthor() {
        System.out.print("Enter the name of an author: ");
        return In.nextLine();
    }
    
    public String readGenre() {
        System.out.print("Enter the genre: ");
        return In.nextLine();
    }

    public String readAGenre() {
        System.out.print("Enter a genre: ");
        return In.nextLine();
    }


    public void showBooks() {
        System.out.println("");
        System.out.println("The Library has the following books:");
        showBookFromList(booksOnShelf);
        showBookFromList(booksOnLoan);
        System.out.println("");
    }
    
    public void showBookFromList(ArrayList<Book> name) {
        for (Book b: name)
            System.out.println(b);
    }
    
    public void showShelfBooks() {
        System.out.println("");
        System.out.println("The following books are on the shelf:");
        showBookFromList(booksOnShelf);
        System.out.println("");
    }
    
    public void showGenres() {
        System.out.println("");
        System.out.println("The Library has books in the following genres:");
        for (Genre g : genres) {
            System.out.println(g.name().toLowerCase());
        }
        System.out.println("");
    }
    
    public void showBooksByGenre() {
        System.out.println("");
        String enteredGenre = readAGenre().toLowerCase();
        System.out.println("The library has the following books in that genre:");
        for (Book b : booksOnShelf) {
            if (b.genre().name().toLowerCase().matches(enteredGenre)) 
                System.out.println(b);
        }
        for (Book b : booksOnLoan) {
            if (b.genre().name().toLowerCase().matches(enteredGenre)) 
                System.out.println(b);
        }
        System.out.println("");
    }
    
    public void showBooksByAuthor() {
        System.out.println("");
        String enteredAuthor = readAAuthor().toLowerCase();
        System.out.println("The library has the following books by that author:");
        for (Book b : booksOnShelf) {
            if (b.author().name().toLowerCase().matches(enteredAuthor))
                System.out.println(b);
        }
        for (Book b : booksOnLoan) {
            if (b.author().name().toLowerCase().matches(enteredAuthor))
                System.out.println(b);
        }
        System.out.println("");
    }
    
    public void showAuthors() {
        System.out.println("");
        System.out.println("The following authors have books in this Library:");
        for (Author a : authors) {
            System.out.println(a);
        }
        System.out.println("");
    }
    
    public void loanBook(Book b) {
        booksOnShelf.remove(b);
        booksOnLoan.add(b);
    }
    
    public void returnBook(Book b) {
        booksOnShelf.add(b);
        booksOnLoan.remove(b);
    }
    
}



