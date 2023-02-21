package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Book;
import model.Catalogue;
import model.Author;

public class ShowBooksByAuthorController extends Controller<Catalogue>{
    public final Catalogue getCatalogue() { return model; }
    private Author getAuthor() { return authorLv.getSelectionModel().getSelectedItem(); }
    
    @FXML private ListView<Author> authorLv;
    @FXML private ListView<Book> booksLv;
    
    @FXML public void initialize() {
        authorLv.setItems(getCatalogue().getAuthors());
    }
    @FXML private void handleDisplay() throws Exception{
        Author author = getAuthor();
        booksLv.setItems(getCatalogue().getBooksByAuthor(author));
    }
    @FXML private void handleClose() { stage.close(); }
}
