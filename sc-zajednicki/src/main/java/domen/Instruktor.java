package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Predstavlja instruktora sportskog centra.
 */
public class Instruktor implements ApstraktniDomenskiObjekat {

    /** Jedinstveni identifikator instruktora u bazi. */
    private int idInstruktor;

    /** Ime instruktora. */
    private String ime;

    /** Prezime instruktora. */
    private String prezime;

    /** Korisničko ime za prijavu u sistem. */
    private String korisnickoIme;

    /** Šifra za prijavu u sistem. */
    private String sifra;

    /**
     * Konstruktor bez inicijalizacije atributa.
     */
    public Instruktor() {
    }

    /**
     * Konstruktor koji postavlja atribute.
     *
     * @param idInstruktor jedinstveni identifikator instruktora
     * @param ime ime instruktora
     * @param prezime prezime instruktora
     * @param korisnickoIme korisničko ime za prijavu
     * @param sifra šifra za prijavu
     */
    public Instruktor(int idInstruktor, String ime, String prezime, String korisnickoIme, String sifra) {
        if (idInstruktor > 0) {
            setIdInstruktor(idInstruktor);
        }
        setIme(ime);
        setPrezime(prezime);
        setKorisnickoIme(korisnickoIme);
        setSifra(sifra);
    }

    /**
     * Vraća identifikator instruktora.
     *
     * @return id instruktora
     */
    public int getIdInstruktor() {
        return idInstruktor;
    }

    /**
     * Postavlja identifikator instruktora.
     *
     * @param idInstruktor jedinstveni identifikator instruktora, mora biti veći od nule
     * @throws IllegalArgumentException ako je id manji ili jednak nuli
     */
    public void setIdInstruktor(int idInstruktor) {
        if (idInstruktor <= 0) {
            throw new IllegalArgumentException("Id instruktora mora biti veci od nule.");
        }
        this.idInstruktor = idInstruktor;
    }

    /**
     * Vraća ime instruktora.
     *
     * @return ime instruktora
     */
    public String getIme() {
        return ime;
    }

    /**
     * Postavlja ime instruktora.
     *
     * @param ime ime instruktora, ne sme biti {@code null} niti prazno
     * @throws NullPointerException ako je ime {@code null}
     * @throws IllegalArgumentException ako je ime prazno
     */
    public void setIme(String ime) {
        Objects.requireNonNull(ime, "Ime instruktora je obavezno.");
        if (ime.isBlank()) {
            throw new IllegalArgumentException("Ime instruktora ne sme biti prazno.");
        }
        this.ime = ime;
    }

    /**
     * Vraća prezime instruktora.
     *
     * @return prezime instruktora
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * Postavlja prezime instruktora.
     *
     * @param prezime prezime instruktora, ne sme biti {@code null} niti prazno
     * @throws NullPointerException ako je prezime {@code null}
     * @throws IllegalArgumentException ako je prezime prazno
     */
    public void setPrezime(String prezime) {
        Objects.requireNonNull(prezime, "Prezime instruktora je obavezno.");
        if (prezime.isBlank()) {
            throw new IllegalArgumentException("Prezime instruktora ne sme biti prazno.");
        }
        this.prezime = prezime;
    }

    /**
     * Vraća korisničko ime instruktora.
     *
     * @return korisničko ime
     */
    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    /**
     * Postavlja korisničko ime instruktora.
     *
     * @param korisnickoIme korisničko ime za prijavu, ne sme biti {@code null} niti prazno
     * @throws NullPointerException ako je korisničko ime {@code null}
     * @throws IllegalArgumentException ako je korisničko ime prazno
     */
    public void setKorisnickoIme(String korisnickoIme) {
        Objects.requireNonNull(korisnickoIme, "Korisnicko ime instruktora je obavezno.");
        if (korisnickoIme.isBlank()) {
            throw new IllegalArgumentException("Korisnicko ime instruktora ne sme biti prazno.");
        }
        this.korisnickoIme = korisnickoIme;
    }

    /**
     * Vraća šifru instruktora.
     *
     * @return šifra za prijavu
     */
    public String getSifra() {
        return sifra;
    }

    /**
     * Postavlja šifru instruktora.
     *
     * @param sifra šifra za prijavu, ne sme biti {@code null} niti prazna
     * @throws NullPointerException ako je šifra {@code null}
     * @throws IllegalArgumentException ako je šifra prazna
     */
    public void setSifra(String sifra) {
        Objects.requireNonNull(sifra, "Sifra instruktora je obavezna.");
        if (sifra.isBlank()) {
            throw new IllegalArgumentException("Sifra instruktora ne sme biti prazna.");
        }
        this.sifra = sifra;
    }

    /**
     * Vraća String reprezentaciju objekta instruktora.
     *
     * @return ime i prezime instruktora
     */
    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    /**
     * Poredi dva instruktora na osnovu korisničkog imena i šifre.
     *
     * @param obj objekat za poređenje
     * @return {@code true} ako su instruktori jednaki, inače {@code false}
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
        final Instruktor other = (Instruktor) obj;
        if (!Objects.equals(this.korisnickoIme, other.korisnickoIme)) {
            return false;
        }
        return Objects.equals(this.sifra, other.sifra);
    }

    @Override
    public String nazivTabele() {
        return "instruktor";
    }

    @Override
    public String primarniKljuc() {
        return "instruktor.idInstruktor=" + idInstruktor;
    }

    @Override
    public String koloneZaUbacivanje() {
        return "ime,prezime,korisnickoIme,sifra";
    }

    @Override
    public String vrednostiZaUbacivanje() {
        return "'" + ime + "', '" + prezime + "', '" + korisnickoIme + "', '" + sifra + "'";
    }

    @Override
    public String vrednostiZaIzmenu() {
        return "ime='" + ime + "',prezime='" + prezime + "',korisnickoIme='" + korisnickoIme + "',sifra='" + sifra + "'";
    }

    @Override
    public String uslovWhere() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Formira listu instruktora na osnovu rezultata upita.
     *
     * @param rs kursor sa rezultatima upita
     * @return lista instruktora
     * @throws Exception ako dođe do greške pri čitanju rezultata
     */
    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int idInstruktor = rs.getInt("idInstruktor");
            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            String korisnickoIme = rs.getString("korisnickoIme");
            String sifra = rs.getString("sifra");
            Instruktor i = new Instruktor(idInstruktor, ime, prezime, korisnickoIme, sifra);
            lista.add(i);
        }
        return lista;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRs(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public String alijasTabele() {
        return "i";
    }
}
