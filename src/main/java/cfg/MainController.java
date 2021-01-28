package cfg;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    private Pane wyswietl;

    public void autorTable(javafx.event.ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/view/autor.fxml"));
        wyswietl.getChildren().setAll(anchorPane);
    }

    public void czytTable(javafx.event.ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/view/czytelnik.fxml"));
        wyswietl.getChildren().setAll(anchorPane);
    }

    public void egzTable(javafx.event.ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/view/egzemplarz.fxml"));
        wyswietl.getChildren().setAll(anchorPane);
    }

    public void ksTable(javafx.event.ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/view/ksiazka.fxml"));
        wyswietl.getChildren().setAll(anchorPane);
    }

    public void wydaTable(javafx.event.ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/view/wydawnictwo.fxml"));
        wyswietl.getChildren().setAll(anchorPane);
    }

    public void wypoTable(javafx.event.ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/view/wypozyczenia.fxml"));
        wyswietl.getChildren().setAll(anchorPane);
    }


    public void adddel(ActionEvent actionEvent) throws IOException{
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/FXML/mainAddDel.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.setTitle("Dodawanie i usuwanie danych z bazy danych");
        stage.show();
    }

    public void edit(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/FXML/mainEdit.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.setTitle("Edycja danych z bazy danych");
        stage.show();
    }
}
