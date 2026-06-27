/*
 * Brisanje Älanske kartice (i njenih stavki).
 */
package operacije.clanskekarte;

import domen.ClanskaKarta;
import domen.StavkaClanskeKarte;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;
import operacije.stavke.UcitajStavkeClanskeKarteSO;

public class ObrisiClanskuKartuSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof ClanskaKarta)) {
            throw new Exception("Sistem nije mogao da obriÅ¡e Älansku kartu.");
        }
        ClanskaKarta ck = (ClanskaKarta) param;
        if (ck.getIdClanskaKarta() <= 0) {
            throw new Exception("Neispravan ID Älanske kartice.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        ClanskaKarta ck = (ClanskaKarta) param;
        String uslov = " WHERE sck.clanskakarta=" + ck.getIdClanskaKarta();
        List<StavkaClanskeKarte> stavke = (List<StavkaClanskeKarte>) (List<?>) broker.getAll(new StavkaClanskeKarte(), uslov);
        for (StavkaClanskeKarte s : stavke) {
            s.setClanskaKarta(ck);
            broker.delete(s);
        }
        broker.delete(ck);
    }
}
