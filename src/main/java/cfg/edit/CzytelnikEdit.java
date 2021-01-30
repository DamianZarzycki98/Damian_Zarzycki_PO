package cfg.edit;

import connect.DbConnect;
import database.Autor;
import database.Czytelnik;
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

public class CzytelnikEdit implements Initializable {

    @FXML
    private TextField czytelnikEditImie;
    @FXML
    private TextField czytelnikEditNazwisko;
    @FXML
    private TextField czytelnikEditMiejscowosc;
    @FXML
    private TextField czytelnikEditNr;

    @FXML
    private ComboBox selectCzytelnik;

    private Connection connection;
    private DbConnect dbConnect;
    ArrayList listEditCzytelnik = new ArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectCzytelnik();
    }

    private void selectCzytelnik() {
        try {
            DbConnect dbConnect = new DbConnect();
            connection = dbConnect.getConnection();
            String query = "SELECT *,CONCAT(Imie,' ',Nazwisko,', miejscowosc: ',Miejscowosc,', nr telefonu: ',Nr_Telefonu)as ImNa FROM czytelnik";
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()) {
                selectCzytelnik.getItems().add(rs.getString("ImNa"));
                listEditCzytelnik.add(rs.getInt("Id_Czytelnika"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void czytelnikEdit(ActionEvent actionEvent) throws SQLException {
        int id = (int) listEditCzytelnik.get(selectCzytelnik.getSelectionModel().getSelectedIndex());
        DbConnect dbConnect = new DbConnect();
        connection = dbConnect.getConnection();
        String query = "UPDATE czytelnik SET Imie='"+czytelnikEditImie.getText()+"'" +
                ", Nazwisko='"+czytelnikEditNazwisko.getText()+"'" +
                ", Miejscowosc='"+czytelnikEditMiejscowosc.getText()+"'" +
                ", Nr_Telefonu='"+czytelnikEditNr.getText()+"' WHERE Id_Czytelnika="+id;
        int ex = connection.createStatement().executeUpdate(query);

        if(ex>0){
            selectCzytelnik.getItems().clear();
            czytelnikEditImie.clear();
            czytelnikEditNazwisko.clear();
            czytelnikEditMiejscowosc.clear();
            czytelnikEditNr.clear();
            selectCzytelnik();
        }
    }

    public void czytelnik(ActionEvent actionEvent) {
        if(selectCzytelnik.getValue() != null) {
            try {
                int id = (int) listEditCzytelnik.get(selectCzytelnik.getSelectionModel().getSelectedIndex());
                DbConnect dbConnect = new DbConnect();
                connection = dbConnect.getConnection();
                String query = "SELECT * FROM czytelnik WHERE Id_Czytelnika = "+id;
                ResultSet rs = connection.createStatement().executeQuery(query);
                Czytelnik c = new Czytelnik();
                while (rs.next()) {
                    c.setImie(rs.getString("Imie"));
                    c.setNazwisko(rs.getString("Nazwisko"));
                    c.setMiejscowosc(rs.getString("Miejscowosc"));
                    c.setNr_Telefonu(rs.getString("Nr_Telefonu"));
                }
                czytelnikEditImie.setText(c.getImie());
                czytelnikEditNazwisko.setText(c.getNazwisko());
                czytelnikEditMiejscowosc.setText(c.getMiejscowosc());
                czytelnikEditNr.setText(c.getNr_Telefonu());
            } catch (Exception ex) {

            }
        }
    }


}
