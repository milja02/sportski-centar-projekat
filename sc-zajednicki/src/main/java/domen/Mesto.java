package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Predstavlja mesto u sistemu sportskog centra.
 */
public class Mesto implements ApstraktniDomenskiObjekat {

    /** Jedinstveni identifikator mesta u bazi. */
    private int idMesto;

    /** Naziv mesta. */
    private String naziv;

    /** Poštanski broj mesta. */
    private int postanskiBroj;

    /**
     * Konstruktor bez inicijalizacije atributa.
     */
    public Mesto() {
    }

    /**
     * Konstruktor koji postavlja atribute.
     *
     * @param idMesto jedinstveni identifikator mesta
     * @param naziv naziv mesta
     * @param postanskiBroj poštanski broj mesta
     */
    public Mesto(int idMesto, String naziv, int postanskiBroj) {
        if (idMesto > 0) {
            setIdMesto(idMesto);
        }
        setNaziv(naziv);
        setPostanskiBroj(postanskiBroj);
    }

    /**
     * Vraća identifikator mesta.
     *
     * @return id mesta
     */
    public int getIdMesto() {
        return idMesto;
    }

    /**
     * Postavlja identifikator mesta.
     *
     * @param idMesto jedinstveni identifikator mesta, mora biti veći od nule
     * @throws IllegalArgumentException ako je id manji ili jednak nuli
     */
    public void setIdMesto(int idMesto) {
        if (idMesto <= 0) {
            throw new IllegalArgumentException("Id mesta mora biti veci od nule.");
        }
        this.idMesto = idMesto;
    }

    /**
     * Vraća naziv mesta.
     *
     * @return naziv mesta
     */
    public String getNaziv() {
        return naziv;
    }

    /**
     * Postavlja naziv mesta.
     *
     * @param naziv naziv mesta, ne sme biti {@code null} niti prazan
     * @throws NullPointerException ako je naziv {@code null}
     * @throws IllegalArgumentException ako je naziv prazan
     */
    public void setNaziv(String naziv) {
        Objects.requireNonNull(naziv, "Naziv mesta je obavezan.");
        if (naziv.isBlank()) {
            throw new IllegalArgumentException("Naziv mesta ne sme biti prazan.");
        }
        this.naziv = naziv;
    }

    /**
     * Vraća poštanski broj mesta.
     *
     * @return poštanski broj
     */
    public int getPostanskiBroj() {
        return postanskiBroj;
    }

    /**
     * Postavlja poštanski broj mesta.
     *
     * @param postanskiBroj poštanski broj mesta
     */
    public void setPostanskiBroj(int postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }

    /**
     * Vraća String reprezentaciju objekta mesta.
     *
     * @return naziv mesta
     */
    @Override
    public String toString() {
        return naziv;
    }

    /**
     * Poredi dva mesta na osnovu naziva i poštanskog broja.
     *
     * @param obj objekat za poređenje
     * @return {@code true} ako su mesta jednaka, inače {@code false}
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
        final Mesto other = (Mesto) obj;
        if (this.postanskiBroj != other.postanskiBroj) {
            return false;
        }
        return Objects.equals(this.naziv, other.naziv);
    }

    @Override
    public String nazivTabele() {
        return "mesto";
    }

    @Override
    public String primarniKljuc() {
        return "mesto.idMesto=" + idMesto;
    }

    @Override
    public String koloneZaUbacivanje() {
        return "naziv,postanskiBroj";
    }

    @Override
    public String vrednostiZaUbacivanje() {
        return "'" + naziv + "'," + postanskiBroj;
    }

    @Override
    public String vrednostiZaIzmenu() {
        return "naziv='" + naziv + "'," + postanskiBroj;
    }

    @Override
    public String uslovWhere() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Formira listu mesta na osnovu rezultata upita.
     *
     * @param rs kursor sa rezultatima upita
     * @return lista mesta
     * @throws Exception ako dođe do greške pri čitanju rezultata
     */
    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int idMesto = rs.getInt("idMesto");
            String naziv = rs.getString("naziv");
            int postanskiBroj = rs.getInt("postanskiBroj");
            Mesto m = new Mesto(idMesto, naziv, postanskiBroj);
            lista.add(m);
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
        return "me";
    }
}
