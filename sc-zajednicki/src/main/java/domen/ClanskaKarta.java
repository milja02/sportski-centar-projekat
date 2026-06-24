package domen;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ClanskaKarta implements ApstraktniDomenskiObjekat{
    private int idClanskaKarta;
    private Date datumUclanjenja;
    private int ukupanIznos;
    private Instruktor instruktor;
    private Polaznik polaznik;
    private List<StavkaClanskeKarte> stavke = new ArrayList<>();

    public ClanskaKarta() {
    }

    public ClanskaKarta(int idClanskaKarta, Date datumUclanjenja, int ukupanIznos, Instruktor instruktor, Polaznik polaznik) {
        this.idClanskaKarta = idClanskaKarta;
        this.datumUclanjenja = datumUclanjenja;
        this.ukupanIznos = ukupanIznos;
        this.instruktor = instruktor;
        this.polaznik = polaznik;
    }
    
    
    
    public int getIdClanskaKarta() {
        return idClanskaKarta;
    }

    public void setIdClanskaKarta(int idClanskaKarta) {
        this.idClanskaKarta = idClanskaKarta;
    }

    public Date getDatumUclanjenja() {
        return datumUclanjenja;
    }

    public void setDatumUclanjenja(Date datumUclanjenja) {
        this.datumUclanjenja = datumUclanjenja;
    }

    public int getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(int ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public Instruktor getInstruktor() {
        return instruktor;
    }

    public void setInstruktor(Instruktor instruktor) {
        this.instruktor = instruktor;
    }

    public Polaznik getPolaznik() {
        return polaznik;
    }

    public void setPolaznik(Polaznik polaznik) {
        this.polaznik = polaznik;
    }

    @Override
    public String toString() {
        return "ClanskaKarta{" + "datumUclanjenja=" + datumUclanjenja + ", ukupanIznos=" + ukupanIznos + ", instruktor=" + instruktor + ", polaznik=" + polaznik + '}';
    }

    public List<StavkaClanskeKarte> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaClanskeKarte> stavke) {
        this.stavke = stavke;
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
        return "clanskakarta.idClanskaKarta="+idClanskaKarta;
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
        return "datumUclanjenja=" + datumStr + ",ukupanIznos=" + ukupanIznos + ",instruktor=" + instruktor.getIdInstruktor() + ",polaznik=" + polaznik.getIdPolaznik();
    }

    @Override
    public String uslovWhere() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
         List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
            //id
            int idClanskaKarta = rs.getInt("idClanskaKarta");
            java.sql.Date datumSQLp = rs.getDate("datumUclanjenja");
            java.util.Date datumUTILp = new java.util.Date(datumSQLp.getTime());
            int ukupanIznos = rs.getInt("ukupanIznos");
            //instruktor
            int idInstruktor = rs.getInt("i.idInstruktor");
            String ime = rs.getString("i.ime");
            String prezime = rs.getString("i.prezime");
            String korisnickoIme = rs.getString("i.korisnickoIme");
            String sifra = rs.getString("i.sifra");
            
            Instruktor instruktor = new Instruktor(idInstruktor, ime, prezime, korisnickoIme, sifra);
            //polaznik
            int idPolaznik = rs.getInt("p.idPolaznik");
            String imeP = rs.getString("p.ime");
            String prezimeP = rs.getString("p.prezime");
            String brojTelefona = rs.getString("p.brojTelefona");
            //mesto
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
