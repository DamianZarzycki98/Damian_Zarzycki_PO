package cfg.edit;

import connect.DbConnect;
import database.Autor;
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

public class AutorEdit implements Initializable {

    @FXML
    private TextField autorEditImie;
    @FXML
    private TextField autorEditNazwisko;
    @FXML
    private TextField autorEditRok;
    @FXML
    private TextField autorEditNarodowosc;

    @FXML
    private ComboBox selectAutor;

    private Connection connection;
    private DbConnect dbConnect;
    ArrayList listEditAutor = new ArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectAutor();

    }



    public void selectAutor() {
            try {
                DbConnect dbConnect = new DbConnect();
                connection = dbConnect.getConnection();
                String query = "SELECT *,CONCAT(Imie,' ',Nazwisko)as ImNa FROM autor";
                ResultSet rs = connection.createStatement().executeQuery(query);
                while (rs.next()) {
                    selectAutor.getItems().add(rs.getString("ImNa"));
                    listEditAutor.add(rs.getInt("Id_Autora"));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

    }

    public void Autor(ActionEvent actionEvent) {
        if(selectAutor.getValue() != null) {
            try {
                int id = (int) listEditAutor.get(selectAutor.getSelectionModel().getSelectedIndex());
                DbConnect dbConnect = new DbConnect();
                connection = dbConnect.getConnection();
                String query = "SELECT * FROM autor WHERE Id_Autora = "+id;
                ResultSet rs = connection.createStatement().executeQuery(query);
                Autor a = new Autor();
                while (rs.next()) {
                    a.setImie(rs.getString("Imie"));
                    a.setNazwisko(rs.getString("Nazwisko"));
                    a.setRok_Urodzenia(rs.getString("Rok_Urodzenia"));
                    a.setNarodowosc(rs.getString("Narodowosc"));
                }
                autorEditImie.setText(a.getImie());
                autorEditNazwisko.setText(a.getNazwisko());
                autorEditRok.setText(a.getRok_Urodzenia());
                autorEditNarodowosc.setText(a.getNarodowosc());
            } catch (Exception ex) {

            }
        }
    }

    public void autorEdit(ActionEvent actionEvent) throws SQLException {
        int id = (int) listEditAutor.get(selectAutor.getSelectionModel().getSelectedIndex());
        DbConnect dbConnect = new DbConnect();
        connection = dbConnect.getConnection();
        String query = "UPDATE autor SET Imie='"+autorEditImie.getText()+"'" +
                ", Nazwisko='"+autorEditNazwisko.getText()+"'" +
                ", Narodowosc='"+autorEditNarodowosc.getText()+"'" +
                ", Rok_Urodzenia='"+autorEditRok.getText()+"' WHERE Id_Autora="+id;
        int ex = connection.createStatement().executeUpdate(query);

        if(ex>0){
            selectAutor.getItems().clear();
            autorEditImie.clear();
            autorEditNazwisko.clear();
            autorEditNarodowosc.clear();
            autorEditRok.clear();
            selectAutor();
        }
    }
}
