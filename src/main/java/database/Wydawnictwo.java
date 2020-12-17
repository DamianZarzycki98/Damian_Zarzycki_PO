package database;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Wydawnictwo {
    @Id
    @GeneratedValue
    @Column(name = "Id_Wydawnictwa")
    private int Id_Wydawnictwa;
    @Column(name = "Nazwa_Wydawnictwa")
    private String Nazwa_Wydawnictwa;
    @Column(name = "Siedziba_Glowna")
    private String Siedziba_Glowna;
    @Column(name = "E-mail")
    private String Email;

    public Wydawnictwo() {
    }

    public Wydawnictwo(int id_Wydawnictwa, String nazwa_Wydawnictwa, String siedziba_Glowna, String email) {
        Id_Wydawnictwa = id_Wydawnictwa;
        Nazwa_Wydawnictwa = nazwa_Wydawnictwa;
        Siedziba_Glowna = siedziba_Glowna;
        Email = email;
    }

    public int getId_Wydawnictwa() {
        return Id_Wydawnictwa;
    }

    public void setId_Wydawnictwa(int id_Wydawnictwa) {
        Id_Wydawnictwa = id_Wydawnictwa;
    }

    public String getNazwa_Wydawnictwa() {
        return Nazwa_Wydawnictwa;
    }

    public void setNazwa_Wydawnictwa(String nazwa_Wydawnictwa) {
        Nazwa_Wydawnictwa = nazwa_Wydawnictwa;
    }

    public String getSiedziba_Glowna() {
        return Siedziba_Glowna;
    }

    public void setSiedziba_Glowna(String siedziba_Glowna) {
        Siedziba_Glowna = siedziba_Glowna;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    @Override
    public String toString() {
        return "Wydawnictwo{" +
                "Id_Wydawnictwa=" + Id_Wydawnictwa +
                ", Nazwa_Wydawnictwa='" + Nazwa_Wydawnictwa + '\'' +
                ", Siedziba_Glowana='" + Siedziba_Glowna + '\'' +
                ", Email='" + Email + '\'' +
                '}';
    }
}
