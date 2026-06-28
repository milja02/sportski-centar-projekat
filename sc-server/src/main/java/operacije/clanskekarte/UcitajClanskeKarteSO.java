package operacije.clanskekarte;

import domen.ClanskaKarta;
import domen.StavkaClanskeKarte;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

public class UcitajClanskeKarteSO extends ApstraktnaGenerickaOperacija{
    List<ClanskaKarta> clanskeKarte;
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        clanskeKarte = broker.getAll(new ClanskaKarta(), null);
        for (ClanskaKarta ck : clanskeKarte) {
            String uslov = " WHERE clanskakarta=" + ck.getIdClanskaKarta();
            List<StavkaClanskeKarte> stavke = (List<StavkaClanskeKarte>) (List<?>) broker.getAll(new StavkaClanskeKarte(), uslov);
            ck.setStavke(stavke);
        }
    }
    
    public List<ClanskaKarta> getClanskeKarte(){
        return clanskeKarte;
    }
    
}
