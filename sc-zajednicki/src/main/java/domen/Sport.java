package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sport implements ApstraktniDomenskiObjekat{
    private int idSport;
    private String naziv;
    private int cena;

    public Sport() {
    }

    public Sport(int idSport, String naziv, int cena) {
        this.idSport = idSport;
        this.naziv = naziv;
        this.cena = cena;
    }

    
    public int getIdSport() {
        return idSport;
    }

    public void setIdSport(int idSport) {
        this.idSport = idSport;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
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
        final Sport other = (Sport) obj;
        return Objects.equals(this.naziv, other.naziv);
    }

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
        return "sport.idSport="+idSport;
    }

    @Override
    public String koloneZaUbacivanje() {
        return "naziv,cena";
    }

    @Override
    public String vrednostiZaUbacivanje() {
        return "'"+naziv+"',"+cena;
    }

    @Override
    public String vrednostiZaIzmenu() {
        return "naziv='"+naziv+"',cena="+cena;
    }

    @Override
    public String uslovWhere() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
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
