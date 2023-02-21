package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.Library;
import model.Patron;

public class RemovePatronController extends Controller<Library>{
    public final Library getLibrary() { return model; }
    private Patron getPatron() { return patronLv.getSelectionModel().getSelectedItem(); }
    
    @FXML ListView<Patron> patronLv;
    @FXML Button removeBtn;
    
    @FXML public void initialize() {
        patronLv.setItems(getLibrary().getPatrons());
        patronLv.getSelectionModel().selectedItemProperty().addListener(
             (o, oldPatron, newPatron) -> removeBtn.setDisable(getPatron() == null));
    }
    
    @FXML private void handleRemovePatron() {
        getLibrary().removePatron(getPatron());
    }
    
    @FXML private void handleClose() { stage.close(); }

}
