/*
 * SK2 korak 7 / SK3 korak 7 - NaÄ‘i Älansku kartu po ID-ju (sa stavkama).
 */
package operacije.clanskekarte;

import domen.ClanskaKarta;
import domen.StavkaClanskeKarte;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

public class NadjiClanskuKartuSO extends ApstraktnaGenerickaOperacija {

    private ClanskaKarta clanskaKarta;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof ClanskaKarta)) {
            throw new Exception("Sistem ne moÅ¾e da naÄ‘e Älansku kartu.");
        }
        ClanskaKarta ck = (ClanskaKarta) param;
        if (ck.getIdClanskaKarta() <= 0) {
            throw new Exception("Sistem ne moÅ¾e da naÄ‘e Älansku kartu.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        ClanskaKarta kriterijum = (ClanskaKarta) param;
        String uslov = " WHERE idClanskaKarta=" + kriterijum.getIdClanskaKarta();
        List<ClanskaKarta> lista = (List<ClanskaKarta>) (List<?>) broker.getAll(new ClanskaKarta(), uslov);
        if (lista == null || lista.isEmpty()) {
            throw new Exception("Sistem ne moÅ¾e da naÄ‘e Älansku kartu.");
        }
        clanskaKarta = lista.get(0);
        String uslovStavke = " WHERE clanskakarta=" + clanskaKarta.getIdClanskaKarta();
        List<StavkaClanskeKarte> stavke = (List<StavkaClanskeKarte>) (List<?>) broker.getAll(new StavkaClanskeKarte(), uslovStavke);
        clanskaKarta.setStavke(stavke);
    }

    public ClanskaKarta getClanskaKarta() {
        return clanskaKarta;
    }
}
