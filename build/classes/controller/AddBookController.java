package controller;

import au.edu.uts.ap.javafx.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.Catalogue;

public class AddBookController extends Controller<Catalogue> {
    public final Catalogue getCatalogue() { return model; }
    
    @FXML public void initialize() {}
    
    @FXML TextField titleTf;
    @FXML TextField authorTf;
    @FXML TextField genreTf;
    @FXML Text feedbackTxt;
    
    private String getTitle() { return titleTf.getText(); }
    private String getAuthor() { return authorTf.getText(); }
    private String getGenre() { return genreTf.getText(); }
    
    @FXML private void handleAddBook() {
        if (getCatalogue().hasBook(getTitle(), getAuthor())) {
            feedbackTxt.setText("That book is already in the Catalogue.");
        }
        else {
            getCatalogue().addBook(getTitle(),getAuthor(),getGenre());
            feedbackTxt.setText("Book added to Catalogue.");
        }
        
    }
    @FXML private void handleExit() {
        stage.close();
    }
}
