package database;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Ksiazka {
    @Id
    @GeneratedValue
    @Column(name = "Id_Ksiazki")
    private int Id_Ksiazki;
    @Column(name = "Tytul")
    private String Tytul;
    @Column(name = "Rok_Wydania")
    private String Rok_Wydania;
    @Column(name = "Id_Autora")
    private String Id_Autora;
    @Column(name = "Id_Wydawnictwa")
    private String Id_Wydawnictwa;

    public Ksiazka() {
    }

    public Ksiazka(int id_Ksiazki, String tytul, String rok_Wydania, String id_Autora, String id_Wydawnictwa) {
        Id_Ksiazki = id_Ksiazki;
        Tytul = tytul;
        Rok_Wydania = rok_Wydania;
        Id_Autora = id_Autora;
        Id_Wydawnictwa = id_Wydawnictwa;
    }

    public int getId_Ksiazki() {
        return Id_Ksiazki;
    }

    public void setId_Ksiazki(int id_Ksiazki) {
        Id_Ksiazki = id_Ksiazki;
    }

    public String getTytul() {
        return Tytul;
    }

    public void setTytul(String tytul) {
        Tytul = tytul;
    }

    public String getRok_Wydania() {
        return Rok_Wydania;
    }

    public void setRok_Wydania(String rok_Wydania) {
        Rok_Wydania = rok_Wydania;
    }

    public String getId_Autora() {
        return Id_Autora;
    }

    public void setId_Autora(String id_Autora) {
        Id_Autora = id_Autora;
    }

    public String getId_Wydawnictwa() {
        return Id_Wydawnictwa;
    }

    public void setId_Wydawnictwa(String id_Wydawnictwa) {
        Id_Wydawnictwa = id_Wydawnictwa;
    }

    @Override
    public String toString() {
        return "Ksiazka{" +
                "Id_Ksiazki=" + Id_Ksiazki +
                ", Tytul='" + Tytul + '\'' +
                ", Rok_Wydania='" + Rok_Wydania + '\'' +
                ", Id_Autora='" + Id_Autora + '\'' +
                ", Id_Wydawnictwa='" + Id_Wydawnictwa + '\'' +
                '}';
    }
}
