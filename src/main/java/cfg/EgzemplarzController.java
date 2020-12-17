package cfg;

import connect.DbConnect;
import database.Egzemplarz;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EgzemplarzController implements Initializable {

    @FXML
    private TableView<Egzemplarz> egzemplarzTab;
    @FXML
    private TableColumn<Egzemplarz, String> egzemplarzTytyl;
    @FXML
    private TableColumn<Egzemplarz, String> egzemplarzISBN;

    private Connection connection;
    private DbConnect dbConnect;
    private ObservableList<Egzemplarz> egzemplarzList;

    public EgzemplarzController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbConnect = new DbConnect();

        egzemplarzTableView();
    }

    private void egzemplarzTableView() {
        try{
            egzemplarzList = FXCollections.observableArrayList();
            String query = "SELECT * FROM egzemplarz INNER JOIN ksiazka ON egzemplarz.Id_Ksiazki=ksiazka.Id_Ksiazki";
            connection=dbConnect.getConnection();
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()){
                Egzemplarz e = new Egzemplarz();
                e.setId_Egzemplarza(rs.getInt("Id_Egzemplarza"));
                e.setId_Ksiazki(rs.getString("Id_Ksiazki"));
                e.setISBN(rs.getString("ISBN"));
                e.setTytul(rs.getString("Tytul"));
                egzemplarzList.add(e);
            }

            egzemplarzTytyl.setCellValueFactory(new PropertyValueFactory<>("Tytul"));
            egzemplarzISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));

            egzemplarzTab.setItems(egzemplarzList);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
