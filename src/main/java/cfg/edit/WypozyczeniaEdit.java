package cfg.edit;

import connect.DbConnect;
import database.Wypozyczenia;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class WypozyczeniaEdit implements Initializable {

    @FXML
    private ComboBox wypozyczeniaSelectKsiazka;
    @FXML
    private ComboBox wypozyczeniaSelectCzytelnik;
    @FXML
    private DatePicker wypozyczeniaSelectDataOdd;

    @FXML
    private ComboBox selectWypozyczenia;

    private Connection connection;
    private DbConnect dbConnect;
    ArrayList listEditWypozyczenia = new ArrayList();
    ArrayList listSelectKsiazka = new ArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectWypozyczenia();
    }

    private void selectWypozyczenia() {
        try {
            DbConnect dbConnect = new DbConnect();
            connection = dbConnect.getConnection();
            String query = "SELECT *,CONCAT(czytelnik.Imie,' ',czytelnik.Nazwisko,', nazwa ksiazki: ',ksiazka.Tytul,', data oddania: ',wypozyczenia.Data_Oddania)as wypozyczenia " +
                    "FROM wypozyczenia INNER JOIN ksiazka ON wypozyczenia.Id_Ksiazki=ksiazka.Id_Ksiazki" +
                    " INNER JOIN czytelnik ON wypozyczenia.Id_Czytelnika=czytelnik.Id_Czytelnika";
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()) {
                selectWypozyczenia.getItems().add(rs.getString("wypozyczenia"));
                listEditWypozyczenia.add(rs.getInt("Id_Wypozyczenia"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void wypozyczeniaEdit(ActionEvent actionEvent) throws SQLException{
        int idw = (int) listEditWypozyczenia.get(selectWypozyczenia.getSelectionModel().getSelectedIndex());
        int idk = (int) listSelectKsiazka.get(wypozyczeniaSelectKsiazka.getSelectionModel().getSelectedIndex());
        LocalDate dataOdd = wypozyczeniaSelectDataOdd.getValue();
        DbConnect dbConnect = new DbConnect();
        connection = dbConnect.getConnection();
        String query = "UPDATE wypozyczenia SET Id_Ksiazki="+idk+"" +
                ", Data_Oddania='"+dataOdd+"' WHERE Id_Wypozyczenia="+idw;
        int ex = connection.createStatement().executeUpdate(query);

        if(ex>0){
            selectWypozyczenia.getItems().clear();
            wypozyczeniaSelectKsiazka.getItems().clear();
            wypozyczeniaSelectDataOdd.setValue(null);
            selectWypozyczenia();
        }
     }

    public void wypozyczenia(ActionEvent actionEvent) {
        if(selectWypozyczenia.getValue() != null){
            try{
                wypozyczeniaSelectKsiazka.getItems().clear();
                int id = (int) listEditWypozyczenia.get(selectWypozyczenia.getSelectionModel().getSelectedIndex());
                DbConnect dbConnect = new DbConnect();
                connection = dbConnect.getConnection();
                String query = "SELECT * FROM wypozyczenia WHERE Id_Wypozyczenia = "+id;
                ResultSet rs = connection.createStatement().executeQuery(query);
                Wypozyczenia w = new Wypozyczenia();
                while (rs.next()){
                    w.setData_Oddania(rs.getString("Data_Oddania"));
                    w.setId_Ksiazki(rs.getString("Id_Ksiazki"));
                }
                wypozyczeniaSelectDataOdd.setValue(LocalDate.parse(w.getData_Oddania()));

                String query2 = "SELECT * FROM Ksiazka";
                ResultSet rs2 = connection.createStatement().executeQuery(query2);
                while (rs2.next()){
                    wypozyczeniaSelectKsiazka.getItems().addAll(rs2.getString("Tytul"));
                    listSelectKsiazka.add(rs2.getInt("Id_Ksiazki"));
                }
                ResultSet ks = connection.createStatement().executeQuery("SELECT Tytul FROM ksiazka WHERE Id_Ksiazki="+Integer.valueOf(w.getId_Ksiazki()));
                while (ks.next()){
                    wypozyczeniaSelectKsiazka.getSelectionModel().select(ks.getString(1));
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }


}
