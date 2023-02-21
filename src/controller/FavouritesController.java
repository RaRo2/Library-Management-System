package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import model.Book;
import model.Library;
import model.Patron;

public class FavouritesController extends Controller<Library>{
    public final Library getLibrary() { return model; }
    private int getId() { return Integer.parseInt(idTf.getText()); }
    private Patron getPatron() { return getLibrary().getPatron(getId()); }
    
    @FXML TextField idTf;
    @FXML Button patBtn;
    @FXML Text statusTxt;
    @FXML ListView<Book> favLv;
    
    @FXML public void initialize() {
        idTf.textProperty().addListener(
            (o, oldPatron, newPatron) -> patBtn.setDisable(getPatron() == null));
    }
    
    @FXML private void handleFavourites() {
        statusTxt.setText(getPatron().getName() + " Selected");
        favLv.setItems(getPatron().favourites());
    }
    
    @FXML private void handleClose() { stage.close(); }    
}
