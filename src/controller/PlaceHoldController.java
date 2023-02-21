package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.Book;
import model.Catalogue;
import model.Patron;

public class PlaceHoldController extends Controller<Catalogue>{
    public final Catalogue getCatalogue() { return model; }
    private int getId() { return Integer.parseInt(idTf.getText()); }
    private Patron getPatron() { return getCatalogue().getPatron(getId()); }
    private Book getBook() { return booksLv.getSelectionModel().getSelectedItem(); }
    
    @FXML TextField idTf;
    @FXML ListView<Book> booksLv;
    @FXML Button patBtn;
    @FXML Button holdBtn;
    @FXML Text feedbackTxt;
    
    @FXML public void initialize() {
        idTf.textProperty().addListener(
            (o, oldPatron, newPatron) -> patBtn.setDisable(idTf.getText().isEmpty()));
        booksLv.getSelectionModel().selectedItemProperty().addListener(
            (o,oldBook,newBook) -> holdBtn.setDisable(getBook() == null));
    }
    
    @FXML private void handlePatron() {
        booksLv.setItems(getCatalogue().getAllBooks());
    }
    
    @FXML private void handleHold() {
        if (getBook().isHeldBy(getPatron())) {
            feedbackTxt.setText(getBook().getTitle() + " is already held by " + getPatron().getName());
        }
        else {
        getBook().addHold(getPatron());
        feedbackTxt.setText("Hold placed on "+ getBook().getTitle() + " for " + getPatron().getName());
        }
    }
    
    
    @FXML private void handleClose() { stage.close(); }
}
