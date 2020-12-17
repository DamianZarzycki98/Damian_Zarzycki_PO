package database;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Autor {
    @Id
    @GeneratedValue
    @Column(name = "Id_Autora")
    private int Id_Autora;
    @Column(name = "Imie")
    private String Imie;
    @Column(name = "Nazwisko")
    private String Nazwisko;
    @Column(name = "Rok_Urodzenia")
    private String Rok_Urodzenia;
    @Column(name = "Narodowosc")
    private String Narodowosc;

    public Autor() {
    }

    public Autor(int id_Autora, String imie, String nazwisko, String rok_Urodzenia, String narodowosc) {
        Id_Autora = id_Autora;
        Imie = imie;
        Nazwisko = nazwisko;
        Rok_Urodzenia = rok_Urodzenia;
        Narodowosc = narodowosc;
    }

    public int getId_Autora() { return Id_Autora; }

    public void setId_Autora(int id_Autora) {
        Id_Autora = id_Autora;
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

    public String getRok_Urodzenia() {
        return Rok_Urodzenia;
    }

    public void setRok_Urodzenia(String rok_Urodzenia) {
        Rok_Urodzenia = rok_Urodzenia;
    }

    public String getNarodowosc() {
        return Narodowosc;
    }

    public void setNarodowosc(String narodowosc) {
        Narodowosc = narodowosc;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "Id_Autora=" + Id_Autora +
                ", Imie='" + Imie + '\'' +
                ", Nazwisko='" + Nazwisko + '\'' +
                ", Rok_Urodzenia='" + Rok_Urodzenia + '\'' +
                ", Narodowosc='" + Narodowosc + '\'' +
                '}';
    }
}
