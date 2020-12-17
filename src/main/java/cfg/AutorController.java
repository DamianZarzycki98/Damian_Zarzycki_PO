package cfg;

import connect.DbConnect;
import database.Autor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class AutorController implements Initializable {

    @FXML
    private TableView<Autor> autorTab;
    @FXML
    private TableColumn<Autor, String> autorImie;
    @FXML
    private TableColumn<Autor, String> autorNazwisko;
    @FXML
    private TableColumn<Autor, String> autorRok;
    @FXML
    private TableColumn<Autor, String> autorNarodowosc;

    private Connection connection;
    private DbConnect dbConnect;
    private ObservableList<Autor> autorList;

    public AutorController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbConnect = new DbConnect();

        autorTableView();
    }

    private void autorTableView(){
        try {
            autorList = FXCollections.observableArrayList();
            String query = "SELECT * FROM autor";
            connection=dbConnect.getConnection();
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()){
                Autor a = new Autor();
                a.setId_Autora(rs.getInt("Id_Autora"));
                a.setImie(rs.getString("Imie"));
                a.setNazwisko(rs.getString("Nazwisko"));
                a.setRok_Urodzenia(rs.getString("Rok_Urodzenia"));
                a.setNarodowosc(rs.getString("Narodowosc"));
                autorList.add(a);
            }

            autorImie.setCellValueFactory(new PropertyValueFactory<>("Imie"));
            autorNazwisko.setCellValueFactory(new PropertyValueFactory<>("Nazwisko"));
            autorRok.setCellValueFactory(new PropertyValueFactory<>("Rok_Urodzenia"));
            autorNarodowosc.setCellValueFactory(new PropertyValueFactory<>("Narodowosc"));

            autorTab.setItems(autorList);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
