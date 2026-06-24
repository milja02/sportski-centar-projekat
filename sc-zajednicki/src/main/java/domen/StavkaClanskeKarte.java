package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StavkaClanskeKarte implements ApstraktniDomenskiObjekat{
    private ClanskaKarta clanskaKarta;
    private int rb;
    private int brojTermina;
    private int iznosStavke;
    private Sport sport;

    public StavkaClanskeKarte() {
    }

    public StavkaClanskeKarte(ClanskaKarta clanskaKarta, int rb, int brojTermina, int iznosStavke, Sport sport) {
        this.clanskaKarta = clanskaKarta;
        this.rb = rb;
        this.brojTermina = brojTermina;
        this.iznosStavke = iznosStavke;
        this.sport = sport;
    }

    public ClanskaKarta getClanskaKarta() {
        return clanskaKarta;
    }

    public void setClanskaKarta(ClanskaKarta clanskaKarta) {
        this.clanskaKarta = clanskaKarta;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public int getBrojTermina() {
        return brojTermina;
    }

    public void setBrojTermina(int brojTermina) {
        this.brojTermina = brojTermina;
    }

    public int getIznosStavke() {
        return iznosStavke;
    }

    public void setIznosStavke(int iznosStavke) {
        this.iznosStavke = iznosStavke;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    @Override
    public String nazivTabele() {
        return "stavkaclanskekarte";
    }

    @Override
    public String toString() {
        return "StavkaClanskeKarte{" + "clanskaKarta=" + clanskaKarta + ", rb=" + rb + ", brojTermina=" + brojTermina + ", iznosStavke=" + iznosStavke + ", sport=" + sport + '}';
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String uslovWhere() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
