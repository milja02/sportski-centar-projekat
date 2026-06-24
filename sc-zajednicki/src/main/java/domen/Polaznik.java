package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Polaznik implements ApstraktniDomenskiObjekat{
    private int idPolaznik;
    private String ime;
    private String prezime;
    private String brojTelefona;
    private Mesto mesto;

    public Polaznik() {
    }

    public Polaznik(int idPolaznik, String ime, String prezime, String brojTelefona, Mesto mesto) {
        this.idPolaznik = idPolaznik;
        this.ime = ime;
        this.prezime = prezime;
        this.brojTelefona = brojTelefona;
        this.mesto = mesto;
    }

    public int getIdPolaznik() {
        return idPolaznik;
    }

    public void setIdPolaznik(int idPolaznik) {
        this.idPolaznik = idPolaznik;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
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
        final Polaznik other = (Polaznik) obj;
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        return Objects.equals(this.brojTelefona, other.brojTelefona);
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public String nazivTabele() {
        return "polaznik";
    }

    @Override
    public String primarniKljuc() {
        return "polaznik.idPolaznik="+idPolaznik;
    }

    @Override
    public String koloneZaUbacivanje() {
        return "ime,prezime,brojTelefona,mesto";
    }

    @Override
    public String vrednostiZaUbacivanje() {
        return "'"+ime+"','"+prezime+"','"+brojTelefona+"',"+mesto.getIdMesto();
    }

    @Override
    public String vrednostiZaIzmenu() {
        return "ime='"+ime+"',prezime='"+prezime+"',brojTelefona='"+brojTelefona+"', mesto="+mesto.getIdMesto();
    }

    @Override
    public String uslovWhere() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
            int idPolaznik = rs.getInt("idPolaznik");
            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            String brojTelefona = rs.getString("brojTelefona");
            int idMesto = rs.getInt("idMesto");
            String naziv = rs.getString("naziv");
            int postanskiBroj = rs.getInt("postanskiBroj");
            Mesto m = new Mesto(idMesto, naziv, postanskiBroj);
            Polaznik p = new Polaznik(idPolaznik, ime, prezime, brojTelefona, m);
            lista.add(p);
        }
        return lista;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRs(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String join() {
        return "JOIN mesto ON po.mesto = mesto.idMesto";
    }

    @Override
    public String alijasTabele() {
        return "po";
    }
    
    
}
