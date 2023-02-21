import java.util.ArrayList;
import java.util.List;

public class Library {
    public static void main(String[] args) {
        Library lib = new Library();
        lib.use();
    }

    private Catalogue catalogue;
    private ArrayList<Patron> patrons;
     
    public Library() {
        patrons = new ArrayList<Patron>();
        catalogue = new Catalogue(this);
        patrons.add(new Patron(1, "George Jeffs"));
    }
    
    public void test() {
        System.out.println("patrons: " + patrons);
        for (Patron p : patrons) {
            System.out.println("Patron: " + p.name());
        }
    }
    
    public void use() {
        char choice;
        while((choice = readChoice1())!= 'X') {
            switch(choice) {
                case '1' : catalogue.catMenu(); break;
                case '2' : patronRecords(); break;
                case '3' : favourites(); break;
                case '4' : adminMenu(); break;
                default : help(); break;
            }
        }
    }
    
    public void holdBook() {
        System.out.println("");
        int validID = readPatronID();
        Patron patron = getPatron(validID);
        if (patron != null) {
            String title = readHoldTitle();
            if (catalogue.bookTitleExists(title)) {
                Book b = catalogue.getBook(title);
                b.addHold(patron);
                System.out.println("Book held.");
                System.out.println("");
                return;
            }
        }
        System.out.println("That book is not available or doesn't exist.");
    }
    
    public void borrowBook() {
        System.out.println("");
        int validID = readValidID();
        Patron patron = getPatron(validID);
        if (patron != null) {
            String title = readBorrowTitle();
            if (catalogue.bookTitleExists(title)) {
                Book b = catalogue.getBook(title); 
                if (!catalogue.currentlyLoaned(title) && (b.noHolds() || b.isFirstHolder(patron))) {
                    catalogue.loanBook(b); // removes book b from booksOnShelf array and adds to booksOnLoan array
                    patron.addBook(b); // adds book to currentlyBorrowing array
                    System.out.println("Book loaned.");
                    System.out.println("");
                    return;
                }
            }
        }
        System.out.println("That book is not available or doesn't exist.");
        System.out.println("");
    }
    
    public void returnBook() {
        System.out.println("");
        int validID = readValidID();
        Patron patron = getPatron(validID);
        if (patron != null) {
            System.out.println(patron.name() + " has the following books:");
            System.out.println("Books currently borrowed by " + patron.name() + ":");
            patron.currentlyBorrowed();
            if (patron.currentlyBorrowingEmpty()) {
                System.out.print("Currently borrowing no books. Could not return book.");
                System.out.println("");
                return;
            }
            String title = readReturnTitle();
            if (catalogue.bookTitleExists(title)) {
                Book b = catalogue.getBook(title);
                if (!b.noHolds()) {
                   b.moveHoldsDown(patron); 
                }
                catalogue.returnBook(b); // removes book b from booksOnLoan array and adds to booksOnShelf array
                patron.removeBook(b); // removes book from currentlyBorrowing array
                System.out.println(title + " has been returned.");
            }
            else
                System.out.println("The book, " + title + " could not be found. Could not return the book.");
        }
        else
            System.out.print("Invalid ID. Could not return the book.");
        System.out.println("");
    }
    
    public int readValidID() {
        System.out.print("Enter a valid patron ID: ");
        return In.nextInt();
    }
    
    public String readBorrowTitle() {
        System.out.print("Enter the title of the book you wish to borrow: ");
        return In.nextLine();
    }
    
    public String readReturnTitle() {
        System.out.print("Enter the title of the book you wish to return: ");
        return In.nextLine();
    }
    
    public String readHoldTitle() {
        System.out.print("Enter book title: ");
        return In.nextLine();
    }
    //---------------------------------------------------------------------------------------------------------------------------------------------------
    public int readPatronID() {
        System.out.print("Enter a patron ID: ");
        return In.nextInt();
    }
    
    public void patronRecords() {
        System.out.println("");
        int validID = readPatronID();
        Patron patron = getPatron(validID);
        if (patron != null) {
            System.out.println(patron);
            System.out.println("Books currently borrowed by "+ patron.name() + ":");
            patron.currentlyBorrowed();
            System.out.println(patron.name() + "'s borrowing history:");
            patron.borrowingHistory();
        }
        else {
            System.out.println("That patron does not exist.");
        }
        System.out.println("");
    }
    
    //---------------------------------------------------------------------------------------------------------------------------------------------------
    
    public Patron getPatron(int ID) {
        for (Patron patron : patrons) {
            if (patron.matches(ID))
                return patron;
        }
        return null;
    }
    
    public void favourites() {
        System.out.println(" ");
        int ID = readPatronID();
        Patron patron = getPatron(ID);
        if (patron != null) {
            System.out.println(patron.name() + "'s favourite books are: ");
            patron.favBooks();
        }
        else {
            System.out.println("ID could not be found.");
            System.out.println("Favourite books could not be displayed.");
        }
    }
    
    //---------------------------------------------------------------------------------------------------------------------------------------------------
    
    public void adminMenu() {
        char choice;
        while((choice = readChoice2())!= 'R') {
            switch(choice) {
                case '1' : addPatron(); break;
                case '2' : removePatron(); break;
                case '3' : catalogue.addBook(); break;
                case '4' : catalogue.removeBook(); break;
                default : help3(); break;
            }
        }
    }
    
   
    public void addPatron() {
        System.out.println("");
        System.out.println("Adding a new patron.");
        int ID = readID();
        String name = readName();
        for (Patron patron : patrons) {
            if (patron.matches(ID)) {
                System.out.println("ID already exists.");
                System.out.println("Patron could not be added.");
                System.out.println("");
                return;
            }
        }
        patrons.add(new Patron(ID,name));
        System.out.println("Patron added.");
        System.out.println("");
    }
    
    public void removePatron() {
        System.out.println("");
        System.out.println("Removing a patron.");
        int ID = readPatronID();
        for (Patron patron : patrons) {
            if (patron.ID() == ID) {
                patrons.remove(getPatron(ID));
                System.out.println("Patron removed.");
                System.out.println("");
                return;
            }
        }
        System.out.println("That patron does not exist.");
        System.out.println("");
    }
    
    //---------------------------------------------------------------------------------------------------------------------------------------------------
    
    public int readID() {
        System.out.print("Enter a new ID: ");
        return In.nextInt();
    }
    
    public String readName() {
        System.out.print("Enter the patron's name: ");
        return In.nextLine();
    }
        
    public char readChoice1() {
        System.out.println("Welcome to the Library! Please make a selection from the menu:");
        System.out.println("1. Explore the catalogue.");
        System.out.println("2. View your patron record.");
        System.out.println("3. Show you favourite books.");
        System.out.println("4. Enter Admin Mode.");
        System.out.println("X. Exit the system.");
        System.out.print("Enter a choice: ");
        return In.nextChar(); 
        }
        
    public char readChoice2() {
        System.out.println("Welcome to the administration menu:");
        System.out.println("1. Add a patron.");
        System.out.println("2. Remove a patron.");
        System.out.println("3. Add a book to the catalogue.");
        System.out.println("4. Remove a book from the catalogue.");
        System.out.println("R. Return to the previous menu.");
        System.out.print("Enter a choice: ");
        return In.nextChar(); 
    }
        
    public void help() {
        System.out.println("Please enter a number between 1 and 4, or press X to exit.");
    }
    public void help2() {
        System.out.println("Please enter a number between 1 and 9 or press R to return to the previous menu.");
    }
    public void help3() {
        System.out.println("Please enter a number between 1 and 4 or press R to return to the previous menu.");
    }

}

