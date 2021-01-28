package cfg.addDel;

import connect.DbConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CzytelnikAddDel implements Initializable {

    @FXML
    private TextField czytelnikAddImie;
    @FXML
    private TextField czytelnikAddNazwisko;
    @FXML
    private TextField czytelnikAddMiejscowosc;
    @FXML
    private TextField czytelnikAddNr;

    @FXML
    private ComboBox czytelnikSelect;


    private Connection connection;
    ArrayList listDelCzytelnik = new ArrayList();

    public void czytelnikAdd(ActionEvent actionEvent) throws SQLException {
        DbConnect dbConnect = new DbConnect();
        connection = dbConnect.getConnection();
        String query = "INSERT INTO czytelnik(Imie, Nazwisko, Miejscowosc, Nr_Telefonu) VALUES ('"
                +czytelnikAddImie.getText()+"','"
                +czytelnikAddNazwisko.getText()+"','"
                +czytelnikAddMiejscowosc.getText()+"','"
                +czytelnikAddNr.getText()+"')";
        int ex = connection.createStatement().executeUpdate(query);

        if (ex>0) {
            czytelnikAddImie.clear();
            czytelnikAddNazwisko.clear();
            czytelnikAddMiejscowosc.clear();
            czytelnikAddNr.clear();
            czytelnikSelect();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        czytelnikSelect();
    }

    private void czytelnikSelect() {
        try {
            DbConnect dbConnect = new DbConnect();
            connection = dbConnect.getConnection();
            String query = "SELECT *, CONCAT(Imie,' ',Nazwisko,', numer telefonu: ',Nr_Telefonu,', miejscowość: ',Miejscowosc)as ImNa from czytelnik";
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()){
                czytelnikSelect.getItems().add(rs.getString("ImNa"));
                listDelCzytelnik.add(rs.getInt("Id_Czytelnika"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void czytelnikDel(ActionEvent actionEvent) {
        try {
            int id = (int) listDelCzytelnik.get(czytelnikSelect.getSelectionModel().getSelectedIndex());
            DbConnect dbConnect = new DbConnect();
            connection = dbConnect.getConnection();
            String query = "DELETE FROM czytelnik WHERE Id_Czytelnika=" + id;
            int ex = connection.createStatement().executeUpdate(query);
            if (ex > 0) {
                listDelCzytelnik.clear();
                czytelnikSelect.getItems().clear();
                czytelnikSelect();
            }
        }catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd!");
            alert.setHeaderText("Musisz najpierw usnąć wypożyczenie z danym czytelnikiem!");
            alert.showAndWait();
        }
    }


}
