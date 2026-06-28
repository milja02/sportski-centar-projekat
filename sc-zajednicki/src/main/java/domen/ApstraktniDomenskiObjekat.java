package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

public interface ApstraktniDomenskiObjekat extends Serializable{
    public String nazivTabele();
    public String primarniKljuc();
    public String koloneZaUbacivanje();
    public String vrednostiZaUbacivanje();
    public String vrednostiZaIzmenu();
    public String alijasTabele();
    public String join();
    public String uslovWhere();
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception;
    public ApstraktniDomenskiObjekat vratiObjekatIzRs(ResultSet rs) throws Exception;
}
