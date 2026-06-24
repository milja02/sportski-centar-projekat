package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Mesto implements ApstraktniDomenskiObjekat{
    private int idMesto;
    private String naziv;
    private int postanskiBroj;

    public Mesto() {
    }

    public Mesto(int idMesto, String naziv, int postanskiBroj) {
        this.idMesto = idMesto;
        this.naziv = naziv;
        this.postanskiBroj = postanskiBroj;
    }

    
    
    public int getIdMesto() {
        return idMesto;
    }

    public void setIdMesto(int idMesto) {
        this.idMesto = idMesto;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getPostanskiBroj() {
        return postanskiBroj;
    }

    public void setPostanskiBroj(int postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }

    @Override
    public String toString() {
        return  naziv;
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
        return "mesto.idMesto="+idMesto;
    }

    @Override
    public String koloneZaUbacivanje() {
        return "naziv,postanskiBroj";
    }

    @Override
    public String vrednostiZaUbacivanje() {
        return "'"+naziv+"',"+postanskiBroj;
    }

    @Override
    public String vrednostiZaIzmenu() {
        return "naziv='"+naziv+"',"+postanskiBroj;
    }

    @Override
    public String uslovWhere() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
