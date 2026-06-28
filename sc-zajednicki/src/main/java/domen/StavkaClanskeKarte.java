package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Predstavlja stavku na članskoj karti.
 */
public class StavkaClanskeKarte implements ApstraktniDomenskiObjekat {

    /** Članska karta kojoj stavka pripada. */
    private ClanskaKarta clanskaKarta;

    /** Redni broj stavke unutar karte. */
    private int rb;

    /** Broj termina za dati sport na stavci. */
    private int brojTermina;

    /** Iznos stavke (broj termina pomnožen cenom sporta). */
    private int iznosStavke;

    /** Sport na koji se stavka odnosi. */
    private Sport sport;

    /**
     * Konstruktor bez inicijalizacije atributa.
     */
    public StavkaClanskeKarte() {
    }

    /**
     * Konstruktor koji postavlja atribute.
     *
     * @param clanskaKarta članska karta kojoj stavka pripada
     * @param rb redni broj stavke
     * @param brojTermina broj termina za sport
     * @param iznosStavke iznos stavke
     * @param sport sport na koji se stavka odnosi
     */
    public StavkaClanskeKarte(ClanskaKarta clanskaKarta, int rb, int brojTermina, int iznosStavke, Sport sport) {
        this.clanskaKarta = clanskaKarta;
        this.rb = rb;
        this.brojTermina = brojTermina;
        this.iznosStavke = iznosStavke;
        this.sport = sport;
    }

    /**
     * Vraća člansku kartu kojoj stavka pripada.
     *
     * @return članska karta
     */
    public ClanskaKarta getClanskaKarta() {
        return clanskaKarta;
    }

    /**
     * Postavlja člansku kartu kojoj stavka pripada.
     *
     * @param clanskaKarta članska karta
     */
    public void setClanskaKarta(ClanskaKarta clanskaKarta) {
        this.clanskaKarta = clanskaKarta;
    }

    /**
     * Vraća redni broj stavke.
     *
     * @return redni broj stavke
     */
    public int getRb() {
        return rb;
    }

    /**
     * Postavlja redni broj stavke.
     *
     * @param rb redni broj stavke
     */
    public void setRb(int rb) {
        this.rb = rb;
    }

    /**
     * Vraća broj termina na stavci.
     *
     * @return broj termina
     */
    public int getBrojTermina() {
        return brojTermina;
    }

    /**
     * Postavlja broj termina na stavci.
     *
     * @param brojTermina broj termina
     */
    public void setBrojTermina(int brojTermina) {
        this.brojTermina = brojTermina;
    }

    /**
     * Vraća iznos stavke.
     *
     * @return iznos stavke
     */
    public int getIznosStavke() {
        return iznosStavke;
    }

    /**
     * Postavlja iznos stavke.
     *
     * @param iznosStavke iznos stavke
     */
    public void setIznosStavke(int iznosStavke) {
        this.iznosStavke = iznosStavke;
    }

    /**
     * Vraća sport na koji se stavka odnosi.
     *
     * @return sport stavke
     */
    public Sport getSport() {
        return sport;
    }

    /**
     * Postavlja sport stavke.
     *
     * @param sport sport stavke
     */
    public void setSport(Sport sport) {
        this.sport = sport;
    }

    @Override
    public String nazivTabele() {
        return "stavkaclanskekarte";
    }

    /**
     * Vraća String reprezentaciju objekta stavke članske karte.
     *
     * @return tekstualni prikaz atributa stavke
     */
    @Override
    public String toString() {
        return "StavkaClanskeKarte{" + "clanskaKarta=" + clanskaKarta + ", rb=" + rb
                + ", brojTermina=" + brojTermina + ", iznosStavke=" + iznosStavke + ", sport=" + sport + '}';
    }

    @Override
    public String primarniKljuc() {
        return "clanskakarta=" + clanskaKarta.getIdClanskaKarta() + " AND rb=" + rb;
    }

    @Override
    public String koloneZaUbacivanje() {
        return "clanskakarta,rb,brojTermina,iznosStavke,sport";
    }

    @Override
    public String vrednostiZaUbacivanje() {
        return clanskaKarta.getIdClanskaKarta() + ","
                + rb + ","
                + brojTermina + ","
                + iznosStavke + ","
                + sport.getIdSport();
    }

    @Override
    public String vrednostiZaIzmenu() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String uslovWhere() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Formira listu stavki članske karte na osnovu rezultata upita.
     * Učitava sport za svaku stavku.
     *
     * @param rs kursor sa rezultatima upita
     * @return lista stavki
     * @throws Exception ako dođe do greške pri čitanju rezultata
     */
    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            StavkaClanskeKarte stavka = new StavkaClanskeKarte();
            stavka.setRb(rs.getInt("rb"));
            stavka.setIznosStavke(rs.getInt("iznosStavke"));
            stavka.setBrojTermina(rs.getInt("brojTermina"));
            int idSport = rs.getInt("idSport");
            String naziv = rs.getString("naziv");
            int cena = rs.getInt("cena");
            Sport sport = new Sport(idSport, naziv, cena);
            stavka.setSport(sport);
            lista.add(stavka);
        }
        return lista;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRs(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String join() {
        return "JOIN sport sp ON sck.sport = sp.idSport";
    }

    @Override
    public String alijasTabele() {
        return "sck";
    }
}
