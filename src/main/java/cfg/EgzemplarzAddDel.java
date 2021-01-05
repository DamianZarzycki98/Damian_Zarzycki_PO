package cfg;

import connect.DbConnect;
import database.Egzemplarz;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EgzemplarzAddDel implements Initializable {

    @FXML
    private ComboBox egzemplarzSelectKsiazka;
    @FXML
    private TextField egzemplarzAddIlosc;

    private Connection connection;
    ArrayList listSelectKsiazka = new ArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        egzemplarzSelectKsiazka();
    }

    private void egzemplarzSelectKsiazka() {
        try {
            DbConnect dbConnect = new DbConnect();
            connection = dbConnect.getConnection();
            String query = "SELECT * FROM ksiazka";
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()) {
                egzemplarzSelectKsiazka.getItems().add(rs.getString("Tytul"));
                listSelectKsiazka.add(rs.getInt("Id_Ksiazki"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void egzemplarzAdd(ActionEvent actionEvent) throws SQLException{
        DbConnect dbConnect = new DbConnect();
        connection = dbConnect.getConnection();
        int idKsiazki = (int) listSelectKsiazka.get(egzemplarzSelectKsiazka.getSelectionModel().getSelectedIndex());
        String query = "SELECT * FROM egzemplarz WHERE Id_Ksiazki="+idKsiazki;
        ResultSet rs = connection.createStatement().executeQuery(query);
        int i=0;
        while (rs.next()) i++;
        if(i>0){
            String ilosc = egzemplarzAddIlosc.getText();
            String query2 = "UPDATE egzemplarz SET Ilosc_Ksiazek = Ilosc_Ksiazek +"+ilosc+" Where Id_Ksiazki="+idKsiazki;
            connection.createStatement().executeUpdate(query2);
            listSelectKsiazka.clear();
            egzemplarzAddIlosc.clear();
            egzemplarzSelectKsiazka.getItems().clear();
            egzemplarzSelectKsiazka();
        } else {
            String ilosc = egzemplarzAddIlosc.getText();
            String query2 = "INSERT INTO egzemplarz(Id_Ksiazki,Ilosc_Ksiazek) VALUES('"+idKsiazki+"','"+ilosc+"') ";
            connection.createStatement().executeUpdate(query2);
            listSelectKsiazka.clear();
            egzemplarzAddIlosc.clear();
            egzemplarzSelectKsiazka.getItems().clear();
            egzemplarzSelectKsiazka();
        }
    }


}
