package cfg;

import connect.DbConnect;
import database.Wypozyczenia;
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
            String query = "SELECT *, CONCAT(czytelnik.Imie,' ',czytelnik.Nazwisko) AS cimna FROM wypozyczenia INNER JOIN egzemplarz ON wypozyczenia.Id_Egzemplarza=egzemplarz.Id_Egzemplarza INNER JOIN czytelnik ON wypozyczenia.Id_Czytelnika=czytelnik.Id_Czytelnika INNER JOIN ksiazka ON egzemplarz.Id_Ksiazki=ksiazka.Id_Ksiazki";
            connection=dbConnect.getConnection();
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()){
                Wypozyczenia wyp = new Wypozyczenia();
                wyp.setId_Wypozyczenia(rs.getInt("Id_Wypozyczenia"));
                wyp.setId_Ksiazki(rs.getString("Id_Ksiazki"));
                wyp.setTytul(rs.getString("ksiazka.Tytul"));
                wyp.setId_Egzemplarza(rs.getString("Id_Egzemplarza"));
                wyp.setId_Czytelnika(rs.getString("Id_Czytelnika"));
                wyp.setCImNa(rs.getString("cimna"));
                wypozyczeniaList.add(wyp);

                System.out.println(wypozyczeniaList);
            }

            wypozyczeniaKsiazka.setCellValueFactory(new PropertyValueFactory<>("Tytul"));
            wypozyczeniaCzytelnik.setCellValueFactory(new PropertyValueFactory<>("CImNa"));

            wypozyczeniaTab.setItems(wypozyczeniaList);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
