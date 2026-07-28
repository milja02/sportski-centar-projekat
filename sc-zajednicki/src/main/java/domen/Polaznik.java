package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Predstavlja polaznika sportskog centra.
 */
public class Polaznik implements ApstraktniDomenskiObjekat {

    /** Jedinstveni identifikator polaznika u bazi. */
    private int idPolaznik;

    /** Ime polaznika. */
    private String ime;

    /** Prezime polaznika. */
    private String prezime;

    /** Kontakt telefon polaznika. */
    private String brojTelefona;

    /** Mesto prebivališta polaznika. */
    private Mesto mesto;

    /**
     * Konstruktor bez inicijalizacije atributa.
     */
    public Polaznik() {
    }

    /**
     * Konstruktor koji postavlja atribute.
     *
     * @param idPolaznik jedinstveni identifikator polaznika
     * @param ime ime polaznika
     * @param prezime prezime polaznika
     * @param brojTelefona kontakt telefon
     * @param mesto mesto prebivališta
     */
    public Polaznik(int idPolaznik, String ime, String prezime, String brojTelefona, Mesto mesto) {
        if (idPolaznik > 0) {
            setIdPolaznik(idPolaznik);
        }
        setIme(ime);
        setPrezime(prezime);
        setBrojTelefona(brojTelefona);
        setMesto(mesto);
    }

    /**
     * Vraća identifikator polaznika.
     *
     * @return id polaznika
     */
    public int getIdPolaznik() {
        return idPolaznik;
    }

    /**
     * Postavlja identifikator polaznika.
     *
     * @param idPolaznik jedinstveni identifikator polaznika, mora biti veći od nule
     * @throws IllegalArgumentException ako je id manji ili jednak nuli
     */
    public void setIdPolaznik(int idPolaznik) {
        if (idPolaznik <= 0) {
            throw new IllegalArgumentException("Id polaznika mora biti veci od nule.");
        }
        this.idPolaznik = idPolaznik;
    }

    /**
     * Vraća ime polaznika.
     *
     * @return ime polaznika
     */
    public String getIme() {
        return ime;
    }

    /**
     * Postavlja ime polaznika.
     *
     * @param ime ime polaznika, ne sme biti {@code null} niti prazno
     * @throws NullPointerException ako je ime {@code null}
     * @throws IllegalArgumentException ako je ime prazno
     */
    public void setIme(String ime) {
        Objects.requireNonNull(ime, "Ime polaznika je obavezno.");
        if (ime.isBlank()) {
            throw new IllegalArgumentException("Ime polaznika ne sme biti prazno.");
        }
        this.ime = ime;
    }

    /**
     * Vraća prezime polaznika.
     *
     * @return prezime polaznika
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * Postavlja prezime polaznika.
     *
     * @param prezime prezime polaznika, ne sme biti {@code null} niti prazno
     * @throws NullPointerException ako je prezime {@code null}
     * @throws IllegalArgumentException ako je prezime prazno
     */
    public void setPrezime(String prezime) {
        Objects.requireNonNull(prezime, "Prezime polaznika je obavezno.");
        if (prezime.isBlank()) {
            throw new IllegalArgumentException("Prezime polaznika ne sme biti prazno.");
        }
        this.prezime = prezime;
    }

    /**
     * Vraća broj telefona polaznika.
     *
     * @return broj telefona
     */
    public String getBrojTelefona() {
        return brojTelefona;
    }

    /**
     * Postavlja broj telefona polaznika.
     *
     * @param brojTelefona kontakt telefon, ne sme biti {@code null} niti prazan
     * @throws NullPointerException ako je broj telefona {@code null}
     * @throws IllegalArgumentException ako je broj telefona prazan
     */
    public void setBrojTelefona(String brojTelefona) {
        Objects.requireNonNull(brojTelefona, "Broj telefona polaznika je obavezan.");
        if (brojTelefona.isBlank()) {
            throw new IllegalArgumentException("Broj telefona polaznika ne sme biti prazan.");
        }
        this.brojTelefona = brojTelefona;
    }

    /**
     * Vraća mesto prebivališta polaznika.
     *
     * @return mesto polaznika
     */
    public Mesto getMesto() {
        return mesto;
    }

    /**
     * Postavlja mesto prebivališta polaznika i validira njegove atribute.
     *
     * @param mesto mesto prebivališta, ne sme biti {@code null}
     * @throws NullPointerException ako je mesto {@code null}
     */
    public void setMesto(Mesto mesto) {
        Objects.requireNonNull(mesto, "Polaznik mora imati definisano mesto.");
        Objects.requireNonNull(mesto.getNaziv(), "Naziv mesta je obavezan.");
        this.mesto = mesto;
    }

    /**
     * Poredi dva polaznika na osnovu imena, prezimena i broja telefona.
     *
     * @param obj objekat za poređenje
     * @return {@code true} ako su polaznici jednaki, inače {@code false}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Polaznik other = (Polaznik) obj;
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        return Objects.equals(this.brojTelefona, other.brojTelefona);
    }

    /**
     * Vraća String reprezentaciju objekta polaznika.
     *
     * @return ime i prezime polaznika
     */
    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public String nazivTabele() {
        return "polaznik";
    }

    @Override
    public String primarniKljuc() {
        return "polaznik.idPolaznik=" + idPolaznik;
    }

    @Override
    public String koloneZaUbacivanje() {
        return "ime,prezime,brojTelefona,mesto";
    }

    @Override
    public String vrednostiZaUbacivanje() {
        return "'" + ime + "','" + prezime + "','" + brojTelefona + "'," + mesto.getIdMesto();
    }

    @Override
    public String vrednostiZaIzmenu() {
        return "ime='" + ime + "',prezime='" + prezime + "',brojTelefona='" + brojTelefona + "', mesto=" + mesto.getIdMesto();
    }

    @Override
    public String uslovWhere() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Formira listu polaznika na osnovu rezultata upita.
     * Učitava i mesto prebivališta svakog polaznika.
     *
     * @param rs kursor sa rezultatima upita
     * @return lista polaznika
     * @throws Exception ako dođe do greške pri čitanju rezultata
     */
    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int idPolaznik = rs.getInt("idPolaznik");
            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            String brojTelefona = rs.getString("brojTelefona");
            int idMesto = rs.getInt("idMesto");
            String naziv = rs.getString("naziv");
            int postanskiBroj = rs.getInt("postanskiBroj");
            Mesto m = new Mesto(idMesto, naziv, postanskiBroj);
            Polaznik p = new Polaznik(idPolaznik, ime, prezime, brojTelefona, m);
            lista.add(p);
        }
        return lista;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRs(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String join() {
        return "JOIN mesto ON po.mesto = mesto.idMesto";
    }

    @Override
    public String alijasTabele() {
        return "po";
    }
}
