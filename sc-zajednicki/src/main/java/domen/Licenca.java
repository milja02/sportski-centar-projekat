package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Predstavlja licencu koju instruktor može da poseduje.
 */
public class Licenca implements ApstraktniDomenskiObjekat {

    /** Jedinstveni identifikator licence u bazi. */
    private int idLicenca;

    /** Tip licence. */
    private String tipLicence;

    /** Nivo kvalifikacije licence. */
    private String nivoKvalifikacije;

    /**
     * Konstruktor bez inicijalizacije atributa.
     */
    public Licenca() {
    }

    /**
     * Konstruktor koji postavlja atribute.
     *
     * @param idLicenca jedinstveni identifikator licence
     * @param tipLicence tip licence
     * @param nivoKvalifikacije nivo kvalifikacije
     */
    public Licenca(int idLicenca, String tipLicence, String nivoKvalifikacije) {
        this.idLicenca = idLicenca;
        this.tipLicence = tipLicence;
        this.nivoKvalifikacije = nivoKvalifikacije;
    }

    /**
     * Vraća identifikator licence.
     *
     * @return id licence
     */
    public int getIdLicenca() {
        return idLicenca;
    }

    /**
     * Postavlja identifikator licence.
     *
     * @param idLicenca jedinstveni identifikator licence
     */
    public void setIdLicenca(int idLicenca) {
        this.idLicenca = idLicenca;
    }

    /**
     * Vraća tip licence.
     *
     * @return tip licence
     */
    public String getTipLicence() {
        return tipLicence;
    }

    /**
     * Postavlja tip licence.
     *
     * @param tipLicence tip licence
     */
    public void setTipLicence(String tipLicence) {
        this.tipLicence = tipLicence;
    }

    /**
     * Vraća nivo kvalifikacije licence.
     *
     * @return nivo kvalifikacije
     */
    public String getNivoKvalifikacije() {
        return nivoKvalifikacije;
    }

    /**
     * Postavlja nivo kvalifikacije licence.
     *
     * @param nivoKvalifikacije nivo kvalifikacije
     */
    public void setNivoKvalifikacije(String nivoKvalifikacije) {
        this.nivoKvalifikacije = nivoKvalifikacije;
    }

    /**
     * Vraća String reprezentaciju objekta licence.
     *
     * @return tip i nivo licence u čitljivom obliku
     */
    @Override
    public String toString() {
        if (tipLicence == null && nivoKvalifikacije == null) {
            return "";
        }
        String tip = tipLicence != null ? tipLicence : "";
        String nivo = nivoKvalifikacije != null ? nivoKvalifikacije : "";
        if (tip.isEmpty()) {
            return nivo;
        }
        if (nivo.isEmpty()) {
            return tip;
        }
        return tip + " - " + nivo;
    }

    /**
     * Poredi dve licence na osnovu tipa i nivoa kvalifikacije.
     *
     * @param obj objekat za poređenje
     * @return {@code true} ako su licence jednake, inače {@code false}
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
        final Licenca other = (Licenca) obj;
        if (!Objects.equals(this.tipLicence, other.tipLicence)) {
            return false;
        }
        return Objects.equals(this.nivoKvalifikacije, other.nivoKvalifikacije);
    }

    @Override
    public String nazivTabele() {
        return "licenca";
    }

    @Override
    public String primarniKljuc() {
        return "licenca.idLicenca=" + idLicenca;
    }

    @Override
    public String koloneZaUbacivanje() {
        return "tipLicence,nivoKvalifikacije";
    }

    @Override
    public String vrednostiZaUbacivanje() {
        return "'" + tipLicence + "','" + nivoKvalifikacije + "'";
    }

    @Override
    public String vrednostiZaIzmenu() {
        return "tipLicence='" + tipLicence + "',nivoKvalifikacije='" + nivoKvalifikacije + "'";
    }

    @Override
    public String uslovWhere() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Formira listu licenci na osnovu rezultata upita.
     *
     * @param rs kursor sa rezultatima upita
     * @return lista licenci
     * @throws Exception ako dođe do greške pri čitanju rezultata
     */
    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int idLicenca = rs.getInt("idLicenca");
            String tipLicence = rs.getString("tipLicence");
            String nivoKvalifikacije = rs.getString("nivoKvalifikacije");
            Licenca l = new Licenca(idLicenca, tipLicence, nivoKvalifikacije);
            lista.add(l);
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
        return "li";
    }
}
