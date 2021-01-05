package cfg;

import connect.DbConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;


import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class KsiazkaAddDel implements Initializable {

    @FXML
    private TextField ksiazkaAddTytul;
    @FXML
    private TextField ksiazkaAddRok;
    @FXML
    private ComboBox ksiazkaSelectAutor;
    @FXML
    private ComboBox ksiazkaSelectWydawnictwo;

    @FXML
    private ComboBox ksiazkaSelect;

    private Connection connection;
    ArrayList listSelectAutor = new ArrayList();
    ArrayList listSelectWydawnictwo = new ArrayList();
    ArrayList listDelKsiazka = new ArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ksiazkaSelectAutor();
        ksiazkaSelectWydawnictwo();
        ksiazkaSelect();
    }

    private void ksiazkaSelectWydawnictwo() {
        try {
            DbConnect dbConnect = new DbConnect();
            connection = dbConnect.getConnection();
            String query = "SELECT * FROM wydawnictwo";
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()) {
                ksiazkaSelectWydawnictwo.getItems().add(rs.getString("Nazwa_Wydawnictwa"));
                listSelectWydawnictwo.add(rs.getInt("Id_Wydawnictwa"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void ksiazkaSelectAutor() {
        try {
            DbConnect dbConnect = new DbConnect();
            connection = dbConnect.getConnection();
            String query = "SELECT *,CONCAT(Imie,' ',Nazwisko)as ImNa FROM autor";
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()) {
                ksiazkaSelectAutor.getItems().add(rs.getString("ImNa"));
                listSelectAutor.add(rs.getInt("Id_Autora"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void ksiazkaAdd(ActionEvent actionEvent) throws SQLException{
        DbConnect dbConnect = new DbConnect();
        connection = dbConnect.getConnection();
        int idAutor = (int) listSelectAutor.get(ksiazkaSelectAutor.getSelectionModel().getSelectedIndex());
        int idWydawnictwa = (int) listSelectWydawnictwo.get(ksiazkaSelectWydawnictwo.getSelectionModel().getSelectedIndex());
        String query = "INSERT INTO ksiazka(Tytul, Rok_Wydania, Id_Autora, Id_Wydawnictwa) VALUES('"
                +ksiazkaAddTytul.getText()+"','"
                +ksiazkaAddRok.getText()+"','"
                +idAutor+"','"
                +idWydawnictwa+"')";
        int ex = connection.createStatement().executeUpdate(query);

        if (ex>0){
            ksiazkaAddTytul.clear();
            ksiazkaAddRok.clear();
            ksiazkaSelectWydawnictwo.getItems().clear();
            ksiazkaSelectAutor.getItems().clear();
            ksiazkaSelectAutor();
            ksiazkaSelectWydawnictwo();
            ksiazkaSelect.getItems().clear();
            listDelKsiazka.clear();
            ksiazkaSelect();
        }
    }



    private void ksiazkaSelect() {
        try {
            DbConnect dbConnect = new DbConnect();
            connection = dbConnect.getConnection();
            String query = "SELECT * FROM ksiazka";
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()) {
                ksiazkaSelect.getItems().add(rs.getString("Tytul"));
                listDelKsiazka.add(rs.getInt("Id_Ksiazki"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void ksiazkaDel(ActionEvent actionEvent) throws SQLException{
        int id = (int) listDelKsiazka.get(ksiazkaSelect.getSelectionModel().getSelectedIndex());
        DbConnect dbConnect = new DbConnect();
        connection = dbConnect.getConnection();
        String query = "DELETE FROM ksiazka WHERE Id_Ksiazki="+id;
        int ex = connection.createStatement().executeUpdate(query);
        if(ex>0){
            listDelKsiazka.clear();
            ksiazkaSelect.getItems().clear();
            ksiazkaSelect();
        }
    }
}
