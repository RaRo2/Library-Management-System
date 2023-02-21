package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Book;
import model.Catalogue;
import model.Patron;

public class ReturnController extends Controller<Catalogue>{
    public final Catalogue getCatalogue() { return model; }
    private int getId() { return Integer.parseInt(idTf.getText()); }
    private Patron getPatron() { return getCatalogue().getPatron(getId()); }
    private Book getBook() { return booksLv.getSelectionModel().getSelectedItem(); }

    
    @FXML TextField idTf;
    @FXML Button patBtn;
    @FXML ListView<Book> booksLv;
    @FXML Button returnBtn;
    
    @FXML public void initialize() {
    idTf.textProperty().addListener(
            (o, oldPatron, newPatron) -> patBtn.setDisable(idTf.getText().isEmpty()));
        booksLv.getSelectionModel().selectedItemProperty().addListener(
            (o,oldBook,newBook) -> returnBtn.setDisable(getBook() == null));
    }
    
    @FXML private void handlePatron() {
        booksLv.setItems(getPatron().currentlyBorrowed());
    }
    
    @FXML private void handleReturn() {
        getCatalogue().returnBookFromPatron(getBook(), getPatron());
    }
    
    @FXML private void handleClose() { stage.close(); }
}
