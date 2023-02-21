package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import model.Book;
import model.Catalogue;

public class ShowAvailableBooksController extends Controller<Catalogue>{
    public final Catalogue getCatalogue() { return model; }
    private Book getAvailableBook() { return booksTv.getSelectionModel().getSelectedItem(); }
    
    @FXML private TableView<Book> booksTv;
            
    @FXML public void initialize() {
        booksTv.setItems(getCatalogue().getBooksOnShelf());
    }
    
    @FXML private void handleClose() { stage.close(); }
}
