package cfg.edit;

import connect.DbConnect;
import database.Wydawnictwo;
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

public class WydawnictwoEdit implements Initializable {

    @FXML
    private TextField wydawnictwoEditNazwa;
    @FXML
    private TextField wydawnictwoEditSiedziba;
    @FXML
    private TextField wydawnictwoEditEmail;

    @FXML
    private ComboBox selectWydawnictwo;

    private Connection connection;
    private DbConnect dbConnect;
    ArrayList listEditWydawnictwo = new ArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectWydawnictwo();
    }

    private void selectWydawnictwo() {
        try {
            DbConnect dbConnect = new DbConnect();
            connection = dbConnect.getConnection();
            String query = "SELECT *,CONCAT('Nazwa wydawnictwa: ',Nazwa_Wydawnictwa,', siedziba: ',Siedziba_Glowna)as siedziba FROM wydawnictwo";
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()) {
                selectWydawnictwo.getItems().add(rs.getString("siedziba"));
                listEditWydawnictwo.add(rs.getInt("Id_Wydawnictwa"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void wydawnictwo(ActionEvent actionEvent) {
        if(selectWydawnictwo.getValue() != null){
            try {
                int id = (int) listEditWydawnictwo.get(selectWydawnictwo.getSelectionModel().getSelectedIndex());
                DbConnect dbConnect = new DbConnect();
                connection = dbConnect.getConnection();
                String query = "SELECT * FROM wydawnictwo WHERE Id_Wydawnictwa = "+id;
                ResultSet rs = connection.createStatement().executeQuery(query);
                Wydawnictwo w = new Wydawnictwo();
                while (rs.next()){
                    w.setNazwa_Wydawnictwa(rs.getString("Nazwa_Wydawnictwa"));
                    w.setSiedziba_Glowna(rs.getString("Siedziba_Glowna"));
                    w.setEmail(rs.getString("E-mail"));
                }

                wydawnictwoEditNazwa.setText(w.getNazwa_Wydawnictwa());
                wydawnictwoEditSiedziba.setText(w.getSiedziba_Glowna());
                wydawnictwoEditEmail.setText(w.getEmail());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void wydawnictwoEdit(ActionEvent actionEvent) throws SQLException {
        int id = (int) listEditWydawnictwo.get(selectWydawnictwo.getSelectionModel().getSelectedIndex());
        DbConnect dbConnect = new DbConnect();
        connection = dbConnect.getConnection();
        String query = "UPDATE wydawnictwo SET Nazwa_Wydawnictwa='"+wydawnictwoEditNazwa.getText()+"'" +
                ", Siedziba_Glowna='"+wydawnictwoEditSiedziba.getText()+"'" +
                ", `E-mail`='"+wydawnictwoEditEmail.getText()+"' WHERE Id_Wydawnictwa="+id;
        int ex = connection.createStatement().executeUpdate(query);

        if (ex>0){
            selectWydawnictwo.getItems().clear();
            wydawnictwoEditEmail.clear();
            wydawnictwoEditSiedziba.clear();
            wydawnictwoEditNazwa.clear();
            selectWydawnictwo();
        }
    }



}
