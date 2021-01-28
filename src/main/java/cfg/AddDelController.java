package cfg;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class AddDelController {
    @FXML
    private Pane pane;

    public void autorAddDel(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/addDel/autorAddDel.fxml"));
        pane.getChildren().setAll(anchorPane);
    }

    public void czytelnikAddDel(ActionEvent actionEvent) throws IOException{
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/addDel/czytelnikAddDel.fxml"));
        pane.getChildren().setAll(anchorPane);
    }

    public void egzemplarzAddDel(ActionEvent actionEvent) throws IOException{
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/addDel/egzemplarzAddDel.fxml"));
        pane.getChildren().setAll(anchorPane);
    }

    public void ksiazkaAddDel(ActionEvent actionEvent) throws IOException{
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/addDel/ksiazkaAddDel.fxml"));
        pane.getChildren().setAll(anchorPane);
    }

    public void wydawnictwoAddDel(ActionEvent actionEvent) throws IOException{
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/addDel/wydawnictwoAddDel.fxml"));
        pane.getChildren().setAll(anchorPane);
    }

    public void wypozyczeniaAddDel(ActionEvent actionEvent) throws IOException{
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/addDel/wypozyczeniaAddDel.fxml"));
        pane.getChildren().setAll(anchorPane);
    }
}
