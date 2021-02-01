package cfg.edit;

import connect.DbConnect;
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

    public void ksiazkaEdit(ActionEvent actionEvent) throws SQLException{
        int idk = (int) listEditKsiazka.get(ksiazkaSelect.getSelectionModel().getSelectedIndex());
        int ida = (int) listSelectAutor.get(ksiazkaSelectAutor.getSelectionModel().getSelectedIndex());
        int idw = (int) listSelectWydawnictwo.get(ksiazkaSelectWydawnictwo.getSelectionModel().getSelectedIndex());
        DbConnect dbConnect = new DbConnect();
        connection = dbConnect.getConnection();
        String query = "UPDATE ksiazka SET Tytul ='"+ksiazkaEditTytul.getText()+"'," +
                "Rok_Wydania='"+ksiazkaEditRok.getText()+"'," +
                "Id_Autora='"+ida+"'," +
                "Id_Wydawnictwa='"+idw+"' WHERE Id_Ksiazki="+idk;
        int ex = connection.createStatement().executeUpdate(query);

        if(ex>0){
            ksiazkaSelect.getItems().clear();
            ksiazkaEditRok.clear();
            ksiazkaEditTytul.clear();
            ksiazkaSelectAutor.getItems().clear();
            ksiazkaSelectWydawnictwo.getItems().clear();
        }
    }

    public void ksiazka(ActionEvent actionEvent) {
        if (ksiazkaSelect.getValue() != null) {
            try {
                ksiazkaSelectWydawnictwo.getItems().clear();
                ksiazkaSelectAutor.getItems().clear();
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
                while(rs2.next()){
                    ksiazkaSelectAutor.getItems().addAll(rs2.getString("Id_Autora"));
                    listSelectAutor.add(rs2.getInt("Id_Autora"));
                }
                ksiazkaSelectAutor.getSelectionModel().select(k.getId_Autora());

                String query3 = "SELECT * FROM wydawnictwo";
                ResultSet rs3 = connection.createStatement().executeQuery(query3);
                while(rs3.next()) {
                    ksiazkaSelectWydawnictwo.getItems().addAll(rs3.getString("Id_Wydawnictwa"));
                    listSelectWydawnictwo.add(rs3.getInt("Id_Wydawnictwa"));
                }
                ksiazkaSelectWydawnictwo.getSelectionModel().select(k.getId_Wydawnictwa());
            } catch (Exception ex) {

            }
        }
    }
}