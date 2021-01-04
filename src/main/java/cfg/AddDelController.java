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
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/autorAddDel.fxml"));
        pane.getChildren().setAll(anchorPane);
    }
}
