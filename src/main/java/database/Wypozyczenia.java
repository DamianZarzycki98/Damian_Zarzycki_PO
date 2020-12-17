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
    @Column(name = "Id_Egzemplarza")
    private String Id_Egzemplarza;

    public Wypozyczenia() {
    }

    public Wypozyczenia(int id_Wypozyczenia, String id_Ksiazki, String tytul, String id_Czytelnika, String CImNa, String id_Egzemplarza) {
        Id_Wypozyczenia = id_Wypozyczenia;
        Id_Ksiazki = id_Ksiazki;
        Tytul = tytul;
        Id_Czytelnika = id_Czytelnika;
        this.CImNa = CImNa;
        Id_Egzemplarza = id_Egzemplarza;
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

    public String getId_Egzemplarza() {
        return Id_Egzemplarza;
    }

    public void setId_Egzemplarza(String id_Egzemplarza) {
        Id_Egzemplarza = id_Egzemplarza;
    }

    @Override
    public String toString() {
        return "Wypozyczenia{" +
                "Id_Wypozyczenia=" + Id_Wypozyczenia +
                ", Id_Ksiazki='" + Id_Ksiazki + '\'' +
                ", Tytul='" + Tytul + '\'' +
                ", Id_Czytelnika='" + Id_Czytelnika + '\'' +
                ", CImNa='" + CImNa + '\'' +
                ", Id_Egzemplarza='" + Id_Egzemplarza + '\'' +
                '}';
    }
}
