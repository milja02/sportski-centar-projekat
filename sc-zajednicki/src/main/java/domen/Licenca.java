package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Licenca implements ApstraktniDomenskiObjekat{
    private int idLicenca;
    private String tipLicence;
    private String nivoKvalifikacije;

    public Licenca() {
    }

    public Licenca(int idLicenca, String tipLicence, String nivoKvalifikacije) {
        this.idLicenca = idLicenca;
        this.tipLicence = tipLicence;
        this.nivoKvalifikacije = nivoKvalifikacije;
    }

    
    
    public int getIdLicenca() {
        return idLicenca;
    }

    public void setIdLicenca(int idLicenca) {
        this.idLicenca = idLicenca;
    }

    public String getTipLicence() {
        return tipLicence;
    }

    public void setTipLicence(String tipLicence) {
        this.tipLicence = tipLicence;
    }

    public String getNivoKvalifikacije() {
        return nivoKvalifikacije;
    }

    public void setNivoKvalifikacije(String nivoKvalifikacije) {
        this.nivoKvalifikacije = nivoKvalifikacije;
    }

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
        return "licenca.idLicenca="+idLicenca;
    }

    @Override
    public String koloneZaUbacivanje() {
        return "tipLicence,nivoKvalifikacije";
    }

    @Override
    public String vrednostiZaUbacivanje() {
        return "'"+tipLicence+"','"+nivoKvalifikacije+"'";
    }

    @Override
    public String vrednostiZaIzmenu() {
        return "tipLicence='"+tipLicence+"',nivoKvalifikacije='"+nivoKvalifikacije+"'";
    }

    @Override
    public String uslovWhere() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
