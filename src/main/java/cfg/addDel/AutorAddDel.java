package cfg.addDel;

import cfg.AutoCompleteComboBoxListener;
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


public class AutorAddDel implements Initializable {

    @FXML
    private TextField autorAddImie;
    @FXML
    private TextField autorAddNazwisko;
    @FXML
    private TextField autorAddRok;
    @FXML
    private TextField autorAddNarodowosc;

    @FXML
    private ComboBox autorSelect;

    private Connection connection;
    ArrayList listDelAutor = new ArrayList();


    public void autorAdd(ActionEvent actionEvent) throws SQLException {

        DbConnect dbConnect = new DbConnect();
        connection = dbConnect.getConnection();
        String query = "INSERT INTO autor(Imie, Nazwisko, Rok_Urodzenia, Narodowosc) VALUES('"
                +autorAddImie.getText()+"','"
                +autorAddNazwisko.getText()+"','"
                +autorAddRok.getText()+"','"
                +autorAddNarodowosc.getText()+"')";
        int ex = connection.createStatement().executeUpdate(query);

        if (ex>0){
            autorAddImie.clear();
            autorAddNazwisko.clear();
            autorAddRok.clear();
            autorAddNarodowosc.clear();
            autorSelect.getItems().clear();
            listDelAutor.clear();
            autorSelect();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        autorSelect();
    }

    public void autorSelect() {
        try {
            DbConnect dbConnect = new DbConnect();
            connection = dbConnect.getConnection();
            String query = "SELECT *,CONCAT(Imie,' ',Nazwisko)as ImNa FROM autor";
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()) {
                autorSelect.getItems().add(rs.getString("ImNa"));
                listDelAutor.add(rs.getInt("Id_Autora"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void autorDel(ActionEvent actionEvent) {
        try {
            int id = (int) listDelAutor.get(autorSelect.getSelectionModel().getSelectedIndex());
            DbConnect dbConnect = new DbConnect();
            connection = dbConnect.getConnection();
            String query = "DELETE FROM autor WHERE Id_Autora=" + id;
            int ex = connection.createStatement().executeUpdate(query);
            if (ex > 0) {
                listDelAutor.clear();
                autorSelect.getItems().clear();
                autorSelect();
            }
        }catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd!");
            alert.setHeaderText("Musisz najpierw usnąć książkę danego autora!");
            alert.showAndWait();
        }
    }

}
