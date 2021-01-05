package database;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Egzemplarz {
    @Id
    @GeneratedValue
    @Column(name = "Id_Egzemplarza")
    private int Id_Egzemplarza;
    @Column(name = "Id_Ksiazki")
    private String Id_Ksiazki;
    @Column(name = "Ilosc_Ksiazek")
    private String Ilosc_Ksiazek;
    @Column(name = "Tytul")
    private String Tytul;

    public Egzemplarz() {
    }

    public Egzemplarz(int id_Egzemplarza, String id_Ksiazki, String Ilosc_Ksiazek, String tytul) {
        Id_Egzemplarza = id_Egzemplarza;
        Id_Ksiazki = id_Ksiazki;
        this.Ilosc_Ksiazek = Ilosc_Ksiazek;
        Tytul = tytul;
    }

    public int getId_Egzemplarza() {
        return Id_Egzemplarza;
    }

    public void setId_Egzemplarza(int id_Egzemplarza) {
        Id_Egzemplarza = id_Egzemplarza;
    }

    public String getId_Ksiazki() {
        return Id_Ksiazki;
    }

    public void setId_Ksiazki(String id_Ksiazki) {
        Id_Ksiazki = id_Ksiazki;
    }

    public String getIlosc_Ksiazek() {
        return Ilosc_Ksiazek;
    }

    public void setIlosc_Ksiazek(String Ilosc_Ksiazek) {
        this.Ilosc_Ksiazek = Ilosc_Ksiazek;
    }

    public String getTytul() {
        return Tytul;
    }

    public void setTytul(String tytul) {
        Tytul = tytul;
    }

    @Override
    public String toString() {
        return "Egzemplarz{" +
                "Id_Egzemplarza=" + Id_Egzemplarza +
                ", Id_Ksiazki='" + Id_Ksiazki + '\'' +
                ", Ilosc_Ksiazek='" + Ilosc_Ksiazek + '\'' +
                ", Tytul='" + Tytul + '\'' +
                '}';
    }
}
