package cfg;

import connect.DbConnect;
import database.Wypozyczenia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class WypozyczeniaController implements Initializable {
    @FXML
    private TableView<Wypozyczenia> wypozyczeniaTab;
    @FXML
    private TableColumn<Wypozyczenia, String> wypozyczeniaKsiazka;
    @FXML
    private TableColumn<Wypozyczenia, String> wypozyczeniaCzytelnik;

    private Connection connection;
    private DbConnect dbConnect;
    private ObservableList<Wypozyczenia> wypozyczeniaList;

    public WypozyczeniaController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbConnect = new DbConnect();

        wypozyczeniaTableView();
    }

    private void wypozyczeniaTableView() {
        try {
            wypozyczeniaList = FXCollections.observableArrayList();
            String query = "SELECT ";
            connection=dbConnect.getConnection();
            ResultSet rs = connection.createStatement().executeQuery(query);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
