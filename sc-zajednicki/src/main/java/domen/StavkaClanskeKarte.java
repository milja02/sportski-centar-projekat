package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Predstavlja stavku na članskoj karti.
 */
public class StavkaClanskeKarte implements ApstraktniDomenskiObjekat {

    /** Članska karta kojoj stavka pripada. */
    private transient ClanskaKarta clanskaKarta;

    /** Redni broj stavke unutar karte. */
    private int rb;

    /** Broj termina za dati sport na stavci. */
    private int brojTermina;

    /** Iznos stavke (broj termina pomnožen cenom sporta). */
    private int iznosStavke;

    /** Sport na koji se stavka odnosi. */
    private Sport sport;

    /** Oznaka da je iznos stavke eksplicitno postavljen i treba validirati. */
    private boolean iznosPostavljen;

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
        setClanskaKarta(clanskaKarta);
        setRb(rb);
        setBrojTermina(brojTermina);
        setSport(sport);
        setIznosStavke(iznosStavke);
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
     * @param clanskaKarta članska karta sa validnim id-jem
     * @throws NullPointerException ako je karta {@code null}
     * @throws IllegalArgumentException ako id karte nije veći od nule
     */
    public void setClanskaKarta(ClanskaKarta clanskaKarta) {
        Objects.requireNonNull(clanskaKarta, "Stavka mora pripadati clanskoj karti.");
        if (clanskaKarta.getIdClanskaKarta() <= 0) {
            throw new IllegalArgumentException("Id clanske karte na stavci mora biti veci od nule.");
        }
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
     * @param rb redni broj, mora biti veći od nule
     * @throws IllegalArgumentException ako je redni broj manji ili jednak nuli
     */
    public void setRb(int rb) {
        if (rb <= 0) {
            throw new IllegalArgumentException("Redni broj stavke mora biti veci od nule.");
        }
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
     * @param brojTermina broj termina, mora biti veći ili jednak 1
     * @throws IllegalArgumentException ako je broj termina manji od 1 ili iznos ne odgovara
     */
    public void setBrojTermina(int brojTermina) {
        if (brojTermina < 1) {
            throw new IllegalArgumentException("Broj termina mora biti veci ili jednak 1.");
        }
        this.brojTermina = brojTermina;
        if (iznosPostavljen) {
            proveriIznosStavke();
        }
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
     * Postavlja iznos stavke i proverava da li odgovara broju termina i ceni sporta.
     *
     * @param iznosStavke iznos stavke
     * @throws IllegalArgumentException ako iznos ne odgovara očekivanom iznosu
     */
    public void setIznosStavke(int iznosStavke) {
        this.iznosStavke = iznosStavke;
        if (sport != null && brojTermina >= 1) {
            proveriIznosStavke();
            iznosPostavljen = true;
        }
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
     * Postavlja sport stavke i validira njegove atribute.
     *
     * @param sport sport stavke, ne sme biti {@code null}
     * @throws NullPointerException ako je sport {@code null}
     * @throws IllegalArgumentException ako iznos ne odgovara novom sportu
     */
    public void setSport(Sport sport) {
        Objects.requireNonNull(sport, "Stavka mora imati definisan sport.");
        if (sport.getIdSport() <= 0) {
            throw new IllegalArgumentException("Id sporta mora biti veci od nule.");
        }
        Objects.requireNonNull(sport.getNaziv(), "Naziv sporta je obavezan.");
        this.sport = sport;
        if (iznosPostavljen) {
            proveriIznosStavke();
        }
    }

    /**
     * Računa očekivani iznos stavke kao proizvod broja termina i cene sporta.
     *
     * @return očekivani iznos stavke
     * @throws NullPointerException ako sport nije definisan
     */
    public int izracunajOcekivaniIznos() {
        Objects.requireNonNull(sport, "Sport mora biti definisan da bi se izracunao iznos stavke.");
        return brojTermina * sport.getCena();
    }

    /**
     * Proverava da li je postavljeni iznos stavke jednak očekivanom iznosu.
     *
     * @throws IllegalArgumentException ako iznos ne odgovara proizvodu broja termina i cene sporta
     */
    private void proveriIznosStavke() {
        int ocekivaniIznos = izracunajOcekivaniIznos();
        if (iznosStavke != ocekivaniIznos) {
            throw new IllegalArgumentException(
                    "Iznos stavke mora biti jednak proizvodu broja termina i cene sporta");
        }
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
        return "StavkaClanskeKarte[rb=" + rb + ", brojTermina=" + brojTermina
                + ", iznosStavke=" + iznosStavke + ", sport=" + sport + "]";
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
            int idSport = rs.getInt("idSport");
            String naziv = rs.getString("naziv");
            int cena = rs.getInt("cena");
            Sport sport = new Sport(idSport, naziv, cena);

            StavkaClanskeKarte stavka = new StavkaClanskeKarte();
            stavka.setRb(rs.getInt("rb"));
            stavka.setSport(sport);
            stavka.setBrojTermina(rs.getInt("brojTermina"));
            stavka.setIznosStavke(rs.getInt("iznosStavke"));
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
