package cfg;

import connect.DbConnect;
import database.Czytelnik;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class CzytelnikController implements Initializable {
    @FXML
    private TableView<Czytelnik> czytelnikTab;
    @FXML
    private TableColumn<Czytelnik, String> czytelnikImie;
    @FXML
    private TableColumn<Czytelnik, String> czytelnikNazwisko;
    @FXML
    private TableColumn<Czytelnik, String> czytelnikMiejscowosc;
    @FXML
    private TableColumn<Czytelnik, String> czytelnikNr;

    private Connection connection;
    private DbConnect dbConnect;
    private ObservableList<Czytelnik> czytelnikList;

    public CzytelnikController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbConnect = new DbConnect();

        czytelnikTableView();
    }

    private void czytelnikTableView() {
        try {
            czytelnikList = FXCollections.observableArrayList();
            String query = "SELECT * FROM czytelnik";
            connection=dbConnect.getConnection();
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()){
                Czytelnik c = new Czytelnik();
                c.setId_Czytelnika(rs.getInt("Id_Czytelnika"));
                c.setImie(rs.getString("Imie"));
                c.setNazwisko(rs.getString("Nazwisko"));
                c.setMiejscowosc(rs.getString("Miejscowosc"));
                c.setNr_Telefonu(rs.getString("Nr_Telefonu"));
                czytelnikList.add(c);
            }

            czytelnikImie.setCellValueFactory(new PropertyValueFactory<>("Imie"));
            czytelnikNazwisko.setCellValueFactory(new PropertyValueFactory<>("Nazwisko"));
            czytelnikMiejscowosc.setCellValueFactory(new PropertyValueFactory<>("Miejscowosc"));
            czytelnikNr.setCellValueFactory(new PropertyValueFactory<>("Nr_Telefonu"));

            czytelnikTab.setItems(czytelnikList);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
