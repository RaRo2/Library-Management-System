package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Book;
import model.Catalogue;
import model.Patron;

public class BorrowController extends Controller<Catalogue>{
    public final Catalogue getCatalogue() { return model; }
    private int getId() { return Integer.parseInt(idTf.getText()); }
    private Patron getPatron() { return getCatalogue().getPatron(getId()); }
    private Book getBook() { return booksLv.getSelectionModel().getSelectedItem(); }
    
    @FXML TextField idTf;
    @FXML Button patBtn;
    @FXML ListView<Book> booksLv;
    @FXML Button borrowBtn;
    
    @FXML public void initialize() {
       idTf.textProperty().addListener(
            (o, oldPatron, newPatron) -> patBtn.setDisable(idTf.getText().isEmpty()));
       booksLv.getSelectionModel().selectedItemProperty().addListener(
            (o,oldBook,newBook) -> borrowBtn.setDisable(getBook() == null));
    }
    
    @FXML private void handlePatron() {
        booksLv.setItems(getCatalogue().getBorrowableBooks(getPatron()));
    }
    
    @FXML private void handleBorrow() {
        getCatalogue().loanBookToPatron(getBook(), getPatron());
        booksLv.setItems(getCatalogue().getBorrowableBooks(getPatron()));
    }
    
    @FXML private void handleClose() { stage.close(); }
}
