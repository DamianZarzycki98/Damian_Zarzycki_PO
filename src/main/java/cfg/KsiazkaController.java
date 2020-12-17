package cfg;

import connect.DbConnect;
import database.Ksiazka;
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

public class KsiazkaController implements Initializable {
    @FXML
    private TableView<Ksiazka> ksiazkaTab;
    @FXML
    private TableColumn<Ksiazka, String> ksiazkaTytul;
    @FXML
    private TableColumn<Ksiazka, String> ksiazkaRok;
    @FXML
    private TableColumn<Ksiazka, String> ksiazkaAutor;
    @FXML
    private TableColumn<Ksiazka, String> ksiazkaWydawnictwo;

    private Connection connection;
    private DbConnect dbConnect;
    private ObservableList<Ksiazka> ksiazkaList;

    public KsiazkaController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbConnect = new DbConnect();

        ksiazkaTableView();
    }

    private void ksiazkaTableView() {
        try{
            ksiazkaList = FXCollections.observableArrayList();
            String query = "SELECT CONCAT(autor.Imie,' ',autor.Nazwisko) AS aimna, Id_Ksiazki, " +
                    "ksiazka.Id_Autora, ksiazka.Id_Wydawnictwa, Tytul, Rok_Wydania,wydawnictwo.Nazwa_Wydawnictwa " +
                    "FROM ((ksiazka INNER JOIN autor ON ksiazka.Id_Autora=autor.Id_Autora) " +
                    "INNER JOIN wydawnictwo ON ksiazka.Id_Wydawnictwa=wydawnictwo.Id_Wydawnictwa)";
            connection=dbConnect.getConnection();
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()){
                Ksiazka k = new Ksiazka();
                k.setId_Ksiazki(rs.getInt("Id_Ksiazki"));
                k.setTytul(rs.getString("Tytul"));
                k.setRok_Wydania(rs.getString("Rok_Wydania"));
                k.setId_Autora(rs.getString("ksiazka.Id_Autora"));
                k.setId_Wydawnictwa(rs.getString("ksiazka.Id_Wydawnictwa"));
                k.setAImNa(rs.getString("aimna"));
                k.setNazwa_Wydawnictwa(rs.getString("wydawnictwo.Nazwa_Wydawnictwa"));
                ksiazkaList.add(k);
            }

            ksiazkaTytul.setCellValueFactory(new PropertyValueFactory<>("Tytul"));
            ksiazkaRok.setCellValueFactory(new PropertyValueFactory<>("Rok_Wydania"));
            ksiazkaAutor.setCellValueFactory(new PropertyValueFactory<>("AImNa"));
            ksiazkaWydawnictwo.setCellValueFactory(new PropertyValueFactory<>("Nazwa_Wydawnictwa"));

            ksiazkaTab.setItems(ksiazkaList);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
