package cfg.view;

import connect.DbConnect;
import database.Wydawnictwo;
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

public class WydawnictwoController implements Initializable {
    @FXML
    private TableView<Wydawnictwo> wydawnictwoTab;
    @FXML
    private TableColumn<Wydawnictwo, String> wydawnictwoNazwa;
    @FXML
    private TableColumn<Wydawnictwo, String> wydawnictwoSiedziba;
    @FXML
    private TableColumn<Wydawnictwo, String> wydawnictwoEmail;

    private Connection connection;
    private DbConnect dbConnect;
    private ObservableList<Wydawnictwo> wydawnictwoList;

    public WydawnictwoController() {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbConnect = new DbConnect();

        wydawnictwoTableView();
    }

    private void wydawnictwoTableView() {
        try {
            wydawnictwoList = FXCollections.observableArrayList();
            String query = "SELECT * FROM wydawnictwo";
            connection=dbConnect.getConnection();
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()){
                Wydawnictwo wyd = new Wydawnictwo();
                wyd.setId_Wydawnictwa(rs.getInt("Id_Wydawnictwa"));
                wyd.setNazwa_Wydawnictwa(rs.getString("Nazwa_Wydawnictwa"));
                wyd.setSiedziba_Glowna(rs.getString("Siedziba_Glowna"));
                wyd.setEmail(rs.getString("E-mail"));
                wydawnictwoList.add(wyd);
            }

            wydawnictwoNazwa.setCellValueFactory(new PropertyValueFactory<>("Nazwa_Wydawnictwa"));
            wydawnictwoSiedziba.setCellValueFactory(new PropertyValueFactory<>("Siedziba_Glowna"));
            wydawnictwoEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));

            wydawnictwoTab.setItems(wydawnictwoList);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
