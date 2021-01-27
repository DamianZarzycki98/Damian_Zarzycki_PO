package cfg;

import connect.DbConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class WypozyczeniaAddDel implements Initializable {

    @FXML
    private ComboBox wypozyczeniaSelectKsiazka;
    @FXML
    private ComboBox wypozyczeniaSelectCzytelnik;
    @FXML
    private DatePicker wypozyczeniaSelectDataOdd;

    @FXML
    private ComboBox wypozyczeniaSelect;

    private Connection connection;
    ArrayList listSelectKsiazka = new ArrayList();
    ArrayList listSelectCzytelnik = new ArrayList();
    ArrayList listSelectWypozyczenia = new ArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        wypozyczeniaSelectKsiazka();
        wypozyczeniaSelectCzytelnik();
        wypozyczeniaSelect();

    }



    private void wypozyczeniaSelectKsiazka() {
        try {
            DbConnect dbConnect = new DbConnect();
            connection = dbConnect.getConnection();
            String query = "SELECT * FROM ksiazka";
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()) {
                wypozyczeniaSelectKsiazka.getItems().add(rs.getString("Tytul"));
                listSelectKsiazka.add(rs.getInt("Id_Ksiazki"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void wypozyczeniaSelectCzytelnik() {
        try {
            DbConnect dbConnect = new DbConnect();
            connection = dbConnect.getConnection();
            String query = "SELECT *,CONCAT(Imie,' ',Nazwisko,', numer telefonu: ',Nr_Telefonu,', miejscowość: ',Miejscowosc)as ImNa FROM czytelnik";
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()) {
                wypozyczeniaSelectCzytelnik.getItems().add(rs.getString("ImNa"));
                listSelectCzytelnik.add(rs.getInt("Id_Czytelnika"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void wypozyczeniaAdd(ActionEvent actionEvent) throws SQLException {
        DbConnect dbConnect = new DbConnect();
        connection = dbConnect.getConnection();
        int idKsiazki = (int) listSelectKsiazka.get(wypozyczeniaSelectKsiazka.getSelectionModel().getSelectedIndex());
        int idCzytelnika = (int) listSelectCzytelnik.get(wypozyczeniaSelectCzytelnik.getSelectionModel().getSelectedIndex());
        LocalDate data = LocalDate.now();
        LocalDate dataOdd = wypozyczeniaSelectDataOdd.getValue();
        if (data.isBefore(dataOdd) || data.isEqual(dataOdd)) {
            String query = "INSERT INTO wypozyczenia(Id_Ksiazki, Id_Czytelnika, Data_Wypozyczenia, Data_Oddania) VALUES('"
                    + idKsiazki + "','"
                    + idCzytelnika + "','"
                    + data + "','"
                    + wypozyczeniaSelectDataOdd.getValue() + "')";
            int ex = connection.createStatement().executeUpdate(query);

            if (ex>0) {
                wypozyczeniaSelectKsiazka.getItems().clear();
                wypozyczeniaSelectCzytelnik.getItems().clear();
                wypozyczeniaSelectDataOdd.setValue(null);
                listSelectWypozyczenia.clear();
                wypozyczeniaSelect.getItems().clear();
                wypozyczeniaSelect();
                wypozyczeniaSelectKsiazka();
                wypozyczeniaSelectCzytelnik();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd!");
            alert.setHeaderText("Ustaw poprawnie datę oddania (nie może być wcześniejsza niż aktualna)");
            alert.showAndWait();
        }
    }

    private void wypozyczeniaSelect() {
        try {
            DbConnect dbConnect = new DbConnect();
            connection = dbConnect.getConnection();
            String query = "SELECT *,CONCAT(ksiazka.tytul,', ',czytelnik.Imie,' ',czytelnik.Nazwisko)as NaImNa " +
                    "FROM wypozyczenia INNER JOIN ksiazka ON wypozyczenia.Id_Ksiazki=ksiazka.Id_Ksiazki " +
                    "INNER JOIN czytelnik ON wypozyczenia.Id_Czytelnika=czytelnik.Id_Czytelnika";
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()) {
                wypozyczeniaSelect.getItems().add(rs.getString("NaImNa"));
                listSelectWypozyczenia.add(rs.getInt("Id_Wypozyczenia"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void wypozyczeniaDel(ActionEvent actionEvent) throws SQLException{
        int id = (int) listSelectWypozyczenia.get(wypozyczeniaSelect.getSelectionModel().getSelectedIndex());
        DbConnect dbConnect = new DbConnect();
        connection = dbConnect.getConnection();
        String query = "DELETE FROM wypozyczenia WHERE Id_Wypozyczenia="+id;
        int ex = connection.createStatement().executeUpdate(query);
        if(ex>0){
            listSelectWypozyczenia.clear();
            wypozyczeniaSelect.getItems().clear();
            wypozyczeniaSelect();
        }
    }
}
