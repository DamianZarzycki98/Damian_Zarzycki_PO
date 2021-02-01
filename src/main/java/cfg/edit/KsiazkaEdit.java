package cfg.edit;

import connect.DbConnect;
import database.Autor;
import database.Ksiazka;
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

public class KsiazkaEdit implements Initializable {

    @FXML
    private TextField ksiazkaEditTytul;
    @FXML
    private TextField ksiazkaEditRok;
    @FXML
    private ComboBox ksiazkaSelectAutor;
    @FXML
    private ComboBox ksiazkaSelectWydawnictwo;
    @FXML
    private ComboBox ksiazkaSelect;

    private Connection connection;
    private DbConnect dbConnect;
    ArrayList listEditKsiazka = new ArrayList();
    ArrayList listSelectAutor = new ArrayList();
    ArrayList listSelectWydawnictwo = new ArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //ksiazkaSelectAutor();
        //ksiazkaSelectWydawnictwo();
        selectKsiazka();
    }

    private void selectKsiazka() {
        try {
            DbConnect dbConnect = new DbConnect();
            connection = dbConnect.getConnection();
            String query = "SELECT *,CONCAT(Tytul,', ',Rok_Wydania)as ksiazka FROM ksiazka";
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()) {
                ksiazkaSelect.getItems().add(rs.getString("Ksiazka"));
                listEditKsiazka.add(rs.getInt("Id_Ksiazki"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void ksiazkaEdit(ActionEvent actionEvent) {

    }

//    public void ksiazka(ActionEvent actionEvent) {
//    }
//
//    private void ksiazkaSelectAutor(){
//
//    }
//
//    private void ksiazkaSelectWydawnictwo() {
//    }

    public void ksiazka(ActionEvent actionEvent) {
        if (ksiazkaSelect.getValue() != null) {
            try {
                int id = (int) listEditKsiazka.get(ksiazkaSelect.getSelectionModel().getSelectedIndex());
                DbConnect dbConnect = new DbConnect();
                connection = dbConnect.getConnection();
                String query = "SELECT * FROM ksiazka WHERE Id_Ksiazki = " + id;
                ResultSet rs = connection.createStatement().executeQuery(query);
                Ksiazka k = new Ksiazka();
                while (rs.next()) {
                    k.setTytul(rs.getString("Tytul"));
                    k.setRok_Wydania(rs.getString("Rok_Wydania"));
                    k.setId_Autora(rs.getString("Id_Autora"));
                    k.setId_Wydawnictwa(rs.getString("Id_Wydawnictwa"));
                }
                ksiazkaEditTytul.setText(k.getTytul());
                ksiazkaEditRok.setText(k.getRok_Wydania());
                String query2 = "SELECT * FROM autor";
                ResultSet rs2 = connection.createStatement().executeQuery(query2);
                ksiazkaSelectAutor.getItems().addAll(rs2.getString("Imie"));
                String query3 = "SELECT * FROM wydawnictwo";
                ResultSet rs3 = connection.createStatement().executeQuery(query3);
                ksiazkaSelectWydawnictwo.getItems().addAll(rs3.getString("Nazwa_Wydawnictwa"));
            } catch (Exception ex) {

            }
        }
    }
}