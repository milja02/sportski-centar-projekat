package domen;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Predstavlja člansku kartu polaznika sportskog centra.
 */
public class ClanskaKarta implements ApstraktniDomenskiObjekat {

    /** Jedinstveni identifikator članske karte u bazi. */
    private int idClanskaKarta;

    /** Datum kada je polaznik učlanjen. */
    private Date datumUclanjenja;

    /** Ukupan iznos članske karte (zbir svih stavki). */
    private int ukupanIznos;

    /** Instruktor koji je izdao člansku kartu. */
    private Instruktor instruktor;

    /** Polaznik kojem karta pripada. */
    private Polaznik polaznik;

    /** Lista stavki na članskoj karti. */
    private List<StavkaClanskeKarte> stavke = new ArrayList<>();

    /**
     * Konstruktor bez inicijalizacije atributa.
     */
    public ClanskaKarta() {
    }

    /**
     * Konstruktor koji postavlja atribute.
     *
     * @param idClanskaKarta jedinstveni identifikator karte
     * @param datumUclanjenja datum učlanjenja
     * @param ukupanIznos ukupan iznos karte
     * @param instruktor instruktor koji je izdao kartu
     * @param polaznik polaznik kojem karta pripada
     */
    public ClanskaKarta(int idClanskaKarta, Date datumUclanjenja, int ukupanIznos,
            Instruktor instruktor, Polaznik polaznik) {
        this.idClanskaKarta = idClanskaKarta;
        this.datumUclanjenja = datumUclanjenja;
        this.ukupanIznos = ukupanIznos;
        this.instruktor = instruktor;
        this.polaznik = polaznik;
    }

    /**
     * Vraća identifikator članske karte.
     *
     * @return id članske karte
     */
    public int getIdClanskaKarta() {
        return idClanskaKarta;
    }

    /**
     * Postavlja identifikator članske karte.
     *
     * @param idClanskaKarta jedinstveni identifikator karte
     */
    public void setIdClanskaKarta(int idClanskaKarta) {
        this.idClanskaKarta = idClanskaKarta;
    }

    /**
     * Vraća datum učlanjenja.
     *
     * @return datum učlanjenja
     */
    public Date getDatumUclanjenja() {
        return datumUclanjenja;
    }

    /**
     * Postavlja datum učlanjenja.
     *
     * @param datumUclanjenja datum učlanjenja
     */
    public void setDatumUclanjenja(Date datumUclanjenja) {
        this.datumUclanjenja = datumUclanjenja;
    }

    /**
     * Vraća ukupan iznos članske karte.
     *
     * @return ukupan iznos
     */
    public int getUkupanIznos() {
        return ukupanIznos;
    }

    /**
     * Postavlja ukupan iznos članske karte.
     *
     * @param ukupanIznos ukupan iznos
     */
    public void setUkupanIznos(int ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    /**
     * Vraća instruktora koji je izdao kartu.
     *
     * @return instruktor
     */
    public Instruktor getInstruktor() {
        return instruktor;
    }

    /**
     * Postavlja instruktora koji je izdao kartu.
     *
     * @param instruktor instruktor
     */
    public void setInstruktor(Instruktor instruktor) {
        this.instruktor = instruktor;
    }

    /**
     * Vraća polaznika kojem karta pripada.
     *
     * @return polaznik
     */
    public Polaznik getPolaznik() {
        return polaznik;
    }

    /**
     * Postavlja polaznika kojem karta pripada.
     *
     * @param polaznik polaznik
     */
    public void setPolaznik(Polaznik polaznik) {
        this.polaznik = polaznik;
    }

    /**
     * Vraća listu stavki članske karte.
     *
     * @return lista stavki
     */
    public List<StavkaClanskeKarte> getStavke() {
        return stavke;
    }

    /**
     * Postavlja listu stavki članske karte.
     *
     * @param stavke lista stavki
     */
    public void setStavke(List<StavkaClanskeKarte> stavke) {
        this.stavke = stavke;
    }

    /**
     * Vraća String reprezentaciju objekta članske karte.
     *
     * @return tekstualni prikaz atributa karte
     */
    @Override
    public String toString() {
        return "ClanskaKarta{" + "datumUclanjenja=" + datumUclanjenja + ", ukupanIznos=" + ukupanIznos
                + ", instruktor=" + instruktor + ", polaznik=" + polaznik + '}';
    }

    /**
     * Poredi dve članske karte na osnovu datuma učlanjenja, instruktora i polaznika.
     *
     * @param obj objekat za poređenje
     * @return {@code true} ako su karte jednake, inače {@code false}
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
        final ClanskaKarta other = (ClanskaKarta) obj;
        if (!Objects.equals(this.datumUclanjenja, other.datumUclanjenja)) {
            return false;
        }
        if (!Objects.equals(this.instruktor, other.instruktor)) {
            return false;
        }
        return Objects.equals(this.polaznik, other.polaznik);
    }

    @Override
    public String nazivTabele() {
        return "clanskakarta";
    }

    @Override
    public String primarniKljuc() {
        return "clanskakarta.idClanskaKarta=" + idClanskaKarta;
    }

    @Override
    public String koloneZaUbacivanje() {
        return "datumUclanjenja,ukupanIznos,instruktor,polaznik";
    }

    @Override
    public String vrednostiZaUbacivanje() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String datumStr = datumUclanjenja != null ? "'" + sdf.format(datumUclanjenja) + "'" : "NULL";
        return datumStr + "," + ukupanIznos + "," + instruktor.getIdInstruktor() + "," + polaznik.getIdPolaznik();
    }

    @Override
    public String vrednostiZaIzmenu() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String datumStr = datumUclanjenja != null ? "'" + sdf.format(datumUclanjenja) + "'" : "NULL";
        return "datumUclanjenja=" + datumStr + ",ukupanIznos=" + ukupanIznos
                + ",instruktor=" + instruktor.getIdInstruktor() + ",polaznik=" + polaznik.getIdPolaznik();
    }

    @Override
    public String uslovWhere() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Formira listu članjskih karata na osnovu rezultata upita.
     * Učitava instruktora, polaznika i mesto polaznika za svaku kartu.
     *
     * @param rs kursor sa rezultatima upita
     * @return lista članjskih karata
     * @throws Exception ako dođe do greške pri čitanju rezultata
     */
    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int idClanskaKarta = rs.getInt("idClanskaKarta");
            java.sql.Date datumSQLp = rs.getDate("datumUclanjenja");
            java.util.Date datumUTILp = new java.util.Date(datumSQLp.getTime());
            int ukupanIznos = rs.getInt("ukupanIznos");
            int idInstruktor = rs.getInt("i.idInstruktor");
            String ime = rs.getString("i.ime");
            String prezime = rs.getString("i.prezime");
            String korisnickoIme = rs.getString("i.korisnickoIme");
            String sifra = rs.getString("i.sifra");
            Instruktor instruktor = new Instruktor(idInstruktor, ime, prezime, korisnickoIme, sifra);
            int idPolaznik = rs.getInt("p.idPolaznik");
            String imeP = rs.getString("p.ime");
            String prezimeP = rs.getString("p.prezime");
            String brojTelefona = rs.getString("p.brojTelefona");
            int idMesto = rs.getInt("m.idMesto");
            String naziv = rs.getString("m.naziv");
            int postanskiBroj = rs.getInt("m.postanskiBroj");
            Mesto m = new Mesto(idMesto, naziv, postanskiBroj);
            Polaznik polaznik = new Polaznik(idPolaznik, imeP, prezimeP, brojTelefona, m);
            ClanskaKarta ck = new ClanskaKarta(idClanskaKarta, datumUTILp, ukupanIznos, instruktor, polaznik);
            lista.add(ck);
        }
        return lista;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRs(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String join() {
        return "JOIN instruktor i ON ck.instruktor = i.idInstruktor "
                + "JOIN polaznik p ON ck.polaznik = p.idPolaznik "
                + "JOIN mesto m ON p.mesto = m.idMesto";
    }

    @Override
    public String alijasTabele() {
        return "ck";
    }
}
