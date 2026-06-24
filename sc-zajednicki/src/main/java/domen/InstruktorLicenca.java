package domen;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class InstruktorLicenca implements ApstraktniDomenskiObjekat{
    private Instruktor instruktor;
    private Licenca licenca;
    private Date datumIzdavanja;
    private Date datumIsteka;

    public InstruktorLicenca() {
    }

    public InstruktorLicenca(Instruktor instruktor, Licenca licenca, Date datumIzdavanja, Date datumIsteka) {
        this.instruktor = instruktor;
        this.licenca = licenca;
        this.datumIzdavanja = datumIzdavanja;
        this.datumIsteka = datumIsteka;
    }

    public Instruktor getInstruktor() {
        return instruktor;
    }

    public void setInstruktor(Instruktor instruktor) {
        this.instruktor = instruktor;
    }

    public Licenca getLicenca() {
        return licenca;
    }

    public void setLicenca(Licenca licenca) {
        this.licenca = licenca;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public Date getDatumIsteka() {
        return datumIsteka;
    }

    public void setDatumIsteka(Date datumIsteka) {
        this.datumIsteka = datumIsteka;
    }

    @Override
    public String toString() {
        return "InstruktorLicenca{" + "instruktor=" + instruktor + ", licenca=" + licenca + ", datumIzdavanja=" + datumIzdavanja + ", datumIsteka=" + datumIsteka + '}';
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String uslovWhere() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        return "il";
    }
    
    
}
