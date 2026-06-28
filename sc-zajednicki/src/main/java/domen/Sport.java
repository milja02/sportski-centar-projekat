package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Predstavlja sport koji se nudi u sportskom centru.
 */
public class Sport implements ApstraktniDomenskiObjekat {

    /** Jedinstveni identifikator sporta u bazi. */
    private int idSport;

    /** Naziv sporta. */
    private String naziv;

    /** Cena jednog termina za dati sport. */
    private int cena;

    /**
     * Konstruktor bez inicijalizacije atributa.
     */
    public Sport() {
    }

    /**
     * Konstruktor koji postavlja atribute.
     *
     * @param idSport jedinstveni identifikator sporta
     * @param naziv naziv sporta
     * @param cena cena po terminu
     */
    public Sport(int idSport, String naziv, int cena) {
        this.idSport = idSport;
        this.naziv = naziv;
        this.cena = cena;
    }

    /**
     * Vraća id sporta.
     *
     * @return id sporta
     */
    public int getIdSport() {
        return idSport;
    }

    /**
     * Postavlja id sporta.
     *
     * @param idSport jedinstveni identifikator sporta
     */
    public void setIdSport(int idSport) {
        this.idSport = idSport;
    }

    /**
     * Vraća naziv sporta.
     *
     * @return naziv sporta
     */
    public String getNaziv() {
        return naziv;
    }

    /**
     * Postavlja naziv sporta.
     *
     * @param naziv naziv sporta
     */
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    /**
     * Vraća cenu sporta po terminu.
     *
     * @return cena po terminu
     */
    public int getCena() {
        return cena;
    }

    /**
     * Postavlja cenu sporta po terminu.
     *
     * @param cena cena po terminu
     */
    public void setCena(int cena) {
        this.cena = cena;
    }

    /**
     * Poredi dva sporta na osnovu naziva.
     *
     * @param obj objekat za poređenje
     * @return {@code true} ako su sportovi jednaki, inače {@code false}
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
        final Sport other = (Sport) obj;
        return Objects.equals(this.naziv, other.naziv);
    }

    /**
     * Vraća String reprezentaciju objekta sporta.
     *
     * @return naziv sporta
     */
    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public String nazivTabele() {
        return "sport";
    }

    @Override
    public String primarniKljuc() {
        return "sport.idSport=" + idSport;
    }

    @Override
    public String koloneZaUbacivanje() {
        return "naziv,cena";
    }

    @Override
    public String vrednostiZaUbacivanje() {
        return "'" + naziv + "'," + cena;
    }

    @Override
    public String vrednostiZaIzmenu() {
        return "naziv='" + naziv + "',cena=" + cena;
    }

    @Override
    public String uslovWhere() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Formira listu sportova na osnovu rezultata upita.
     *
     * @param rs kursor sa rezultatima upita
     * @return lista sportova
     * @throws Exception ako dođe do greške pri čitanju rezultata
     */
    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int idSport = rs.getInt("idSport");
            String naziv = rs.getString("naziv");
            int cena = rs.getInt("cena");
            Sport s = new Sport(idSport, naziv, cena);
            lista.add(s);
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
        return "sp";
    }
}
