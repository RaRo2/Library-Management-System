package controller;

import au.edu.uts.ap.javafx.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Library;

public class AddPatronController extends Controller<Library>{
    public final Library getLibrary() { return model; }
    
    @FXML
    public void initialize() {
    }
    
    @FXML TextField idTf;
    @FXML TextField nameTf;
    @FXML Text feedbackTxt;
    
    private int getID() { return Integer.parseInt(idTf.getText()); }
    private String getName() { return nameTf.getText(); }
    
    @FXML private void handleAddPatron() {
        if (getLibrary().getPatron(getID()) == null) {
            getLibrary().addPatron(getID(), getName());
            feedbackTxt.setText("Patron Added.");
        }
        else
            feedbackTxt.setText("Patron already exists!");
            
       
    }
    @FXML private void handleExit() {
        stage.close();
    }
}
