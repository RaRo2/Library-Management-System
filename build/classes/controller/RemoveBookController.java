package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.Book;
import model.Catalogue;

public class RemoveBookController extends Controller<Catalogue> {
    public final Catalogue getCatalogue() { return model; }
    private Book getBook() { return booksLv.getSelectionModel().getSelectedItem(); }
    
    @FXML ListView<Book> booksLv;
    @FXML Button removeBtn;
    
    @FXML public void initialize() {
        booksLv.setItems(getCatalogue().getAllBooks());
        booksLv.getSelectionModel(). selectedItemProperty ().addListener(
            (o, oldBook, newBook) -> removeBtn.setDisable(getBook() == null));

    }
    
    @FXML private void handleRemove() {
        getCatalogue().removeBook(getBook());
    }
    
    @FXML private void handleClose() { stage.close(); }
    
    
}