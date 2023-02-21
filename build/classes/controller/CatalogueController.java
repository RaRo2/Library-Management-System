package controller;

import au.edu.uts.ap.javafx.*;
import java.io.IOException;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.*;
import model.Catalogue;
import au.edu.uts.ap.javafx.ViewLoader;
import model.Library;


public class CatalogueController extends Controller<Library>{
    public final Library getLibrary() { return model; }
    
    @FXML public void initialize() {}
    
    @FXML private void handleAllBooks() throws Exception {
        ViewLoader.showStage(getLibrary().getCatalogue(), "/view/showAllBooks.fxml", "Complete Catalogue", new Stage());
    }
    @FXML private void handleAvailableBooks() throws Exception {
        ViewLoader.showStage(getLibrary().getCatalogue(), "/view/showAvailableBooks.fxml", "Available Books", new Stage());
    }
    @FXML private void handleShowBooksByGenre() throws Exception {
        ViewLoader.showStage(getLibrary().getCatalogue(),"/view/showBooksByGenre.fxml", "Browse by Genre", new Stage());
    }
    @FXML private void handleShowBooksByAuthor() throws Exception {
        ViewLoader.showStage(getLibrary().getCatalogue(),"/view/showBooksByAuthor.fxml", "Browse by Author", new Stage());
    }
    @FXML private void handleBorrow() throws Exception {
        ViewLoader.showStage(getLibrary().getCatalogue(),"/view/borrow.fxml", "Borrow a Book", new Stage());
    }
    @FXML private void handleReturn() throws Exception {
        ViewLoader.showStage(getLibrary().getCatalogue(),"/view/return.fxml", "Return a Book", new Stage());
    }
    @FXML private void handleHold() throws Exception {
        ViewLoader.showStage(getLibrary().getCatalogue(),"/view/placeHold.fxml", "Place a Hold", new Stage());
    }
    @FXML private void handleExit() { stage.close(); }
}
