package domen;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Predstavlja vezu između instruktora i licence (dodelu licence instruktoru).
 */
public class InstruktorLicenca implements ApstraktniDomenskiObjekat {

    /** Instruktor kome je dodeljena licenca. */
    private Instruktor instruktor;

    /** Licenca koja je dodeljena instruktoru. */
    private Licenca licenca;

    /** Datum izdavanja licence. */
    private Date datumIzdavanja;

    /** Datum isteka licence. */
    private Date datumIsteka;

    /**
     * Konstruktor bez inicijalizacije atributa.
     */
    public InstruktorLicenca() {
    }

    /**
     * Konstruktor koji postavlja atribute.
     *
     * @param instruktor instruktor kome se dodeljuje licenca
     * @param licenca licenca koja se dodeljuje
     * @param datumIzdavanja datum izdavanja licence
     * @param datumIsteka datum isteka licence
     */
    public InstruktorLicenca(Instruktor instruktor, Licenca licenca, Date datumIzdavanja, Date datumIsteka) {
        this.instruktor = instruktor;
        this.licenca = licenca;
        this.datumIzdavanja = datumIzdavanja;
        this.datumIsteka = datumIsteka;
    }

    /**
     * Vraća instruktora.
     *
     * @return instruktor
     */
    public Instruktor getInstruktor() {
        return instruktor;
    }

    /**
     * Postavlja instruktora.
     *
     * @param instruktor instruktor kome se dodeljuje licenca
     */
    public void setInstruktor(Instruktor instruktor) {
        this.instruktor = instruktor;
    }

    /**
     * Vraća licencu.
     *
     * @return licenca
     */
    public Licenca getLicenca() {
        return licenca;
    }

    /**
     * Postavlja licencu.
     *
     * @param licenca licenca koja se dodeljuje
     */
    public void setLicenca(Licenca licenca) {
        this.licenca = licenca;
    }

    /**
     * Vraća datum izdavanja licence.
     *
     * @return datum izdavanja
     */
    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    /**
     * Postavlja datum izdavanja licence.
     *
     * @param datumIzdavanja datum izdavanja
     */
    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    /**
     * Vraća datum isteka licence.
     *
     * @return datum isteka
     */
    public Date getDatumIsteka() {
        return datumIsteka;
    }

    /**
     * Postavlja datum isteka licence.
     *
     * @param datumIsteka datum isteka
     */
    public void setDatumIsteka(Date datumIsteka) {
        this.datumIsteka = datumIsteka;
    }

    /**
     * Vraća String reprezentaciju objekta dodele licence.
     *
     * @return tekstualni prikaz atributa objekta
     */
    @Override
    public String toString() {
        return "InstruktorLicenca{" + "instruktor=" + instruktor + ", licenca=" + licenca
                + ", datumIzdavanja=" + datumIzdavanja + ", datumIsteka=" + datumIsteka + '}';
    }

    /**
     * Poredi dve dodele licence na osnovu instruktora, licence i datuma.
     *
     * @param obj objekat za poređenje
     * @return {@code true} ako su objekti jednaki, inače {@code false}
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
        final InstruktorLicenca other = (InstruktorLicenca) obj;
        if (!Objects.equals(this.instruktor, other.instruktor)) {
            return false;
        }
        if (!Objects.equals(this.licenca, other.licenca)) {
            return false;
        }
        if (!Objects.equals(this.datumIzdavanja, other.datumIzdavanja)) {
            return false;
        }
        return Objects.equals(this.datumIsteka, other.datumIsteka);
    }

    @Override
    public String nazivTabele() {
        return "instruktorlicenca";
    }

    @Override
    public String primarniKljuc() {
        return "instruktor=" + instruktor.getIdInstruktor() + " AND licenca=" + licenca.getIdLicenca();
    }

    @Override
    public String koloneZaUbacivanje() {
        return "instruktor,licenca,datumIzdavanja,datumIsteka";
    }

    /**
     * Formira vrednosti za INSERT upit.
     *
     * @return vrednosti kolona u formatu pogodnom za SQL
     * @throws IllegalStateException ako instruktor ili licenca nisu postavljeni
     */
    @Override
    public String vrednostiZaUbacivanje() {
        if (instruktor == null || licenca == null) {
            throw new IllegalStateException("Instruktor i licenca moraju biti postavljeni.");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dIzd = datumIzdavanja != null ? "'" + sdf.format(datumIzdavanja) + "'" : "NULL";
        String dIst = datumIsteka != null ? "'" + sdf.format(datumIsteka) + "'" : "NULL";
        return instruktor.getIdInstruktor() + ","
                + licenca.getIdLicenca() + ","
                + dIzd + ","
                + dIst;
    }

    @Override
    public String vrednostiZaIzmenu() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String uslovWhere() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
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
        return "il";
    }
}
