/*
 * SK1 korak 7 / SK3 korak 12 - Zapamti Älansku kartu (UPDATE + stavke).
 */
package operacije.clanskekarte;

import domen.ClanskaKarta;
import domen.StavkaClanskeKarte;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

public class ZapamtiClanskuKartuSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof ClanskaKarta)) {
            throw new Exception("Sistem ne moÅ¾e da zapamti Älansku kartu.");
        }
        ClanskaKarta ck = (ClanskaKarta) param;
        if (ck.getIdClanskaKarta() <= 0) {
            throw new Exception("Sistem ne moÅ¾e da zapamti Älansku kartu.");
        }
        if (ck.getDatumUclanjenja() == null || ck.getInstruktor() == null || ck.getPolaznik() == null) {
            throw new Exception("Sistem ne moÅ¾e da zapamti Älansku kartu.");
        }
        VrednosnaOgranicenjaClanskeKarte.proveri(ck, broker);
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        ClanskaKarta ck = (ClanskaKarta) param;
        broker.edit(ck);
        String uslov = " WHERE sck.clanskakarta=" + ck.getIdClanskaKarta();
        List<StavkaClanskeKarte> stareStavke = (List<StavkaClanskeKarte>) (List<?>) broker.getAll(new StavkaClanskeKarte(), uslov);
        for (StavkaClanskeKarte s : stareStavke) {
            s.setClanskaKarta(ck);
            broker.delete(s);
        }
        if (ck.getStavke() != null && !ck.getStavke().isEmpty()) {
            int rb = 1;
            for (StavkaClanskeKarte s : ck.getStavke()) {
                s.setClanskaKarta(ck);
                s.setRb(rb++);
                broker.add(s);
            }
        }
    }
}
