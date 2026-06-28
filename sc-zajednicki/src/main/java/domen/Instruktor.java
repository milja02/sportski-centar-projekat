package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Instruktor implements ApstraktniDomenskiObjekat{
        private int idInstruktor;
        private String ime;
        private String prezime;
        private String korisnickoIme;
        private String sifra;

    public Instruktor() {
    }

    public Instruktor(int idInstruktor, String ime, String prezime, String korisnickoIme, String sifra) {
        this.idInstruktor = idInstruktor;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
    }

    public int getIdInstruktor() {
        return idInstruktor;
    }

    public void setIdInstruktor(int idInstruktor) {
        this.idInstruktor = idInstruktor;
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

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
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
        final Instruktor other = (Instruktor) obj;
        if (!Objects.equals(this.korisnickoIme, other.korisnickoIme)) {
            return false;
        }
        return Objects.equals(this.sifra, other.sifra);
    }

    @Override
    public String nazivTabele() {
        return "instruktor";
    }

    @Override
    public String primarniKljuc() {
        return "instruktor.idInstruktor="+idInstruktor;
    }

    @Override
    public String koloneZaUbacivanje() {
        return "ime,prezime,korisnickoIme,sifra";
    }

    @Override
    public String vrednostiZaUbacivanje() {
        return "'"+ime+"', '"+prezime+"', '"+korisnickoIme+"', '"+sifra+"'";
    }

    @Override
    public String vrednostiZaIzmenu() {
        return "ime='"+ime+"',prezime='"+prezime+"',korisnickoIme='"+korisnickoIme+"',sifra='"+sifra+"'";
    }

    @Override
    public String uslovWhere() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
            int idInstruktor = rs.getInt("idInstruktor");
            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            String korisnickoIme = rs.getString("korisnickoIme");
            String sifra = rs.getString("sifra");
            
            Instruktor i = new Instruktor(idInstruktor, ime, prezime, korisnickoIme, sifra);
            lista.add(i);
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
        return "i";
    }
    
    
}
