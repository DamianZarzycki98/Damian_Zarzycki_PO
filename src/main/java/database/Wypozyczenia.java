package database;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Wypozyczenia {
    @Id
    @GeneratedValue
    @Column(name = "Id_Wypozyczenia")
    private int Id_Wypozyczenia;
    @Column(name = "Id_Ksiazki")
    private String Id_Ksiazki;
    @Column(name = "Tytul")
    private String Tytul;
    @Column(name = "Id_Czytelnika")
    private String Id_Czytelnika;
    @Column(name = "CImNa")
    private String CImNa;
    @Column(name = "Data_Wypozyczenia")
    private String Data_Wypozyczenia;
    @Column(name = "Data_Oddania")
    private String Data_Oddania;

    public Wypozyczenia() {
    }

    public Wypozyczenia(int id_Wypozyczenia, String id_Ksiazki, String tytul, String id_Czytelnika, String CImNa, String data_Wypozyczenia, String data_Oddania) {
        Id_Wypozyczenia = id_Wypozyczenia;
        Id_Ksiazki = id_Ksiazki;
        Tytul = tytul;
        Id_Czytelnika = id_Czytelnika;
        this.CImNa = CImNa;
        Data_Wypozyczenia = data_Wypozyczenia;
        Data_Oddania = data_Oddania;
    }

    public int getId_Wypozyczenia() {
        return Id_Wypozyczenia;
    }

    public void setId_Wypozyczenia(int id_Wypozyczenia) {
        Id_Wypozyczenia = id_Wypozyczenia;
    }

    public String getId_Ksiazki() {
        return Id_Ksiazki;
    }

    public void setId_Ksiazki(String id_Ksiazki) {
        Id_Ksiazki = id_Ksiazki;
    }

    public String getTytul() {
        return Tytul;
    }

    public void setTytul(String tytul) {
        Tytul = tytul;
    }

    public String getId_Czytelnika() {
        return Id_Czytelnika;
    }

    public void setId_Czytelnika(String id_Czytelnika) {
        Id_Czytelnika = id_Czytelnika;
    }

    public String getCImNa() {
        return CImNa;
    }

    public void setCImNa(String CImNa) {
        this.CImNa = CImNa;
    }

    public String getData_Wypozyczenia() {
        return Data_Wypozyczenia;
    }

    public void setData_Wypozyczenia(String data_Wypozyczenia) {
        Data_Wypozyczenia = data_Wypozyczenia;
    }

    public String getData_Oddania() {
        return Data_Oddania;
    }

    public void setData_Oddania(String data_Oddania) {
        Data_Oddania = data_Oddania;
    }

    @Override
    public String toString() {
        return "Wypozyczenia{" +
                "Id_Wypozyczenia=" + Id_Wypozyczenia +
                ", Id_Ksiazki='" + Id_Ksiazki + '\'' +
                ", Tytul='" + Tytul + '\'' +
                ", Id_Czytelnika='" + Id_Czytelnika + '\'' +
                ", CImNa='" + CImNa + '\'' +
                ", Data_Wypozyczenia='" + Data_Wypozyczenia + '\'' +
                ", Data_Oddania='" + Data_Oddania + '\'' +
                '}';
    }
}
