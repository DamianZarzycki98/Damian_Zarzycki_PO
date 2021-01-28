package cfg;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class EditController {

    @FXML
    private Pane pane;

    public void autorEdit(ActionEvent actionEvent) throws IOException{
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/edit/autorEdit.fxml"));
        pane.getChildren().setAll(anchorPane);
    }

    public void czytelnikEdit(ActionEvent actionEvent) throws IOException{
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/edit/czytelnikEdit.fxml"));
        pane.getChildren().setAll(anchorPane);
    }

    public void egzemplarzEdit(ActionEvent actionEvent) throws IOException{
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/edit/egzemplarzEdit.fxml"));
        pane.getChildren().setAll(anchorPane);
    }

    public void ksiazkaEdit(ActionEvent actionEvent) throws IOException{
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/edit/ksiazkaEdit.fxml"));
        pane.getChildren().setAll(anchorPane);
    }

    public void wydawnictwoEdit(ActionEvent actionEvent) throws IOException{
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/edit/wydawnictwoEdit.fxml"));
        pane.getChildren().setAll(anchorPane);
    }

    public void wypozyczeniaEdit(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/edit/wypozyczeniaEdit.fxml"));
        pane.getChildren().setAll(anchorPane);
    }
}
