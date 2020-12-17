package database;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Czytelnik {
    @Id
    @GeneratedValue
    @Column(name = "Id_Czytelnika")
    private int Id_Czytelnika;
    @Column(name = "Imie")
    private String Imie;
    @Column(name = "Nazwisko")
    private String Nazwisko;
    @Column(name = "Miejscowosc")
    private String Miejscowosc;
    @Column(name = "Nr_Telefonu")
    private String Nr_Telefonu;

    public Czytelnik() {
    }

    public Czytelnik(int id_Czytelnika, String imie, String nazwisko, String miejscowosc, String nr_Telefonu) {
        Id_Czytelnika = id_Czytelnika;
        Imie = imie;
        Nazwisko = nazwisko;
        Miejscowosc = miejscowosc;
        Nr_Telefonu = nr_Telefonu;
    }

    public int getId_Czytelnika() {
        return Id_Czytelnika;
    }

    public void setId_Czytelnika(int id_Czytelnika) {
        Id_Czytelnika = id_Czytelnika;
    }

    public String getImie() {
        return Imie;
    }

    public void setImie(String imie) {
        Imie = imie;
    }

    public String getNazwisko() {
        return Nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        Nazwisko = nazwisko;
    }

    public String getMiejscowosc() {
        return Miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        Miejscowosc = miejscowosc;
    }

    public String getNr_Telefonu() {
        return Nr_Telefonu;
    }

    public void setNr_Telefonu(String nr_Telefonu) {
        Nr_Telefonu = nr_Telefonu;
    }

    @Override
    public String toString() {
        return "Czytelnik{" +
                "Id_Czytelnika=" + Id_Czytelnika +
                ", Imie='" + Imie + '\'' +
                ", Nazwisko='" + Nazwisko + '\'' +
                ", Miejscowosc='" + Miejscowosc + '\'' +
                ", Nr_Telefonu='" + Nr_Telefonu + '\'' +
                '}';
    }
}
