package controller;

import au.edu.uts.ap.javafx.*;
import java.io.IOException;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.*;
import model.Catalogue;
import model.Library;

public class LibraryController extends Controller<Library> {
    
    
    @FXML
    public void initialize() {

    }

    public final Library getLibrary() {
        return model;
    }
    
    @FXML private void handleExploreCatalogue(ActionEvent event) throws Exception {
        ViewLoader.showStage(getLibrary() , "/view/catalogue.fxml", "Catalogue", new Stage()); 
    }
    @FXML private void handlePatronRecord(ActionEvent event) throws Exception {
        ViewLoader.showStage(getLibrary() , "/view/record.fxml", "Patron Record", new Stage()); 
    }
    @FXML private void handleFavourites(ActionEvent event) throws Exception {
        ViewLoader.showStage(getLibrary() , "/view/favourites.fxml", "Favourites", new Stage()); 
    }
    @FXML private void handleAdminMode(ActionEvent event) throws Exception {
        ViewLoader.showStage(getLibrary() , "/view/admin.fxml", "Administration Menu", new Stage()); 
    }
    @FXML private void handleExit() {
        stage.close();
    }

}
