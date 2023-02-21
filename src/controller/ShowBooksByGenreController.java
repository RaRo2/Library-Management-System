package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Book;
import model.Catalogue;
import model.Genre;

public class ShowBooksByGenreController extends Controller<Catalogue>{
    public final Catalogue getCatalogue() { return model; }
    private Genre getGenre() { return genreLv.getSelectionModel().getSelectedItem(); }
    
    @FXML private ListView<Genre> genreLv;
    @FXML private ListView<Book> booksLv;
    
    @FXML public void initialize() {
        genreLv.setItems(getCatalogue().getGenres());
    }
    @FXML private void handleDisplay() throws Exception{
        Genre genre = getGenre();
        booksLv.setItems(getCatalogue().getBooksInGenre(genre));
    }
    @FXML private void handleClose() { stage.close(); }
}
