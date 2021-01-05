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

public class WydawnictwoAddDel implements Initializable {

    @FXML
    private TextField wydawnictwoAddNazwa;
    @FXML
    private TextField wydawnictwoAddSiedziba;
    @FXML
    private TextField wydawnictwoAddEmail;

    @FXML
    private ComboBox wydawnictwoSelect;

    private Connection connection;
    ArrayList listDelWydawnictwo = new ArrayList();


    public void wydawnictwoAdd(ActionEvent actionEvent) throws SQLException {
        DbConnect dbConnect = new DbConnect();
        connection = dbConnect.getConnection();
        String query = "INSERT INTO wydawnictwo(Nazwa_Wydawnictwa, Siedziba_Glowna, `E-mail`) VALUES('"
                +wydawnictwoAddNazwa.getText()+"','"
                +wydawnictwoAddSiedziba.getText()+"','"
                +wydawnictwoAddEmail.getText()+"')";
        int ex = connection.createStatement().executeUpdate(query);

        if (ex>0){
            wydawnictwoAddNazwa.clear();
            wydawnictwoAddSiedziba.clear();
            wydawnictwoAddEmail.clear();
            wydawnictwoSelect.getItems().clear();
            listDelWydawnictwo.clear();
            wydawnictwoSelect();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        wydawnictwoSelect();
    }

    private void wydawnictwoSelect() {
        try {
            DbConnect dbConnect = new DbConnect();
            connection = dbConnect.getConnection();
            String query = "SELECT * FROM wydawnictwo";
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()) {
                wydawnictwoSelect.getItems().add(rs.getString("Nazwa_Wydawnictwa"));
                listDelWydawnictwo.add(rs.getInt("Id_Wydawnictwa"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void wydawnictwoDel(ActionEvent actionEvent) throws SQLException{
        int id = (int) listDelWydawnictwo.get(wydawnictwoSelect.getSelectionModel().getSelectedIndex());
        DbConnect dbConnect = new DbConnect();
        connection = dbConnect.getConnection();
        String query = "DELETE FROM wydawnictwo WHERE Id_Wydawnictwa="+id;
        int ex = connection.createStatement().executeUpdate(query);
        if(ex>0){
            listDelWydawnictwo.clear();
            wydawnictwoSelect.getItems().clear();
            wydawnictwoSelect();
        }
    }
}
