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
    @Column(name = "Nazwa")
    private String Nazwa;
    @Column(name = "Id_Czytelnika")
    private String Id_Czytelnika;
    @Column(name = "CImNa")
    private String CImNa;

    public Wypozyczenia() {
    }

    public Wypozyczenia(int id_Wypozyczenia, String id_Ksiazki, String nazwa, String id_Czytelnika, String CImNa) {
        Id_Wypozyczenia = id_Wypozyczenia;
        Id_Ksiazki = id_Ksiazki;
        Nazwa = nazwa;
        Id_Czytelnika = id_Czytelnika;
        this.CImNa = CImNa;
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

    public String getNazwa() {
        return Nazwa;
    }

    public void setNazwa(String nazwa) {
        Nazwa = nazwa;
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

    @Override
    public String toString() {
        return "Wypozyczenia{" +
                "Id_Wypozyczenia=" + Id_Wypozyczenia +
                ", Id_Ksiazki='" + Id_Ksiazki + '\'' +
                ", Nazwa='" + Nazwa + '\'' +
                ", Id_Czytelnika='" + Id_Czytelnika + '\'' +
                ", CImNa='" + CImNa + '\'' +
                '}';
    }
}
