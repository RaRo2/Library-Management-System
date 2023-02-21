
public class Genre {
    private String name;
     
    public Genre(String name) {
        this.name = name;
    }
    
    public String name() {
        return name;
    }
    
    public boolean matches(String enteredName) {
        return (enteredName == name); 
    }
    
    @Override
    public String toString() {
        return name; 
    }
}