package operacije.clanskekarte;

import domen.ClanskaKarta;
import domen.StavkaClanskeKarte;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za ažuriranje postojeće članske karte u bazi.
 * Menja podatke karte i zamenjuje njene stavke novim listom.
 */
public class ZapamtiClanskuKartuSO extends ApstraktnaGenerickaOperacija {

    /**
     * Proverava da li je prosleđena ispravna članska karta
     * i da li ispunjava vrednosna ograničenja.
     *
     * @param param članska karta za izmenu
     * @throws Exception ako parametar nije ispravan ili validacija ne uspe
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof ClanskaKarta)) {
            throw new Exception("Sistem ne može da zapamti člansku kartu.");
        }
        ClanskaKarta ck = (ClanskaKarta) param;
        if (ck.getIdClanskaKarta() <= 0) {
            throw new Exception("Sistem ne može da zapamti člansku kartu.");
        }
        if (ck.getDatumUclanjenja() == null || ck.getInstruktor() == null || ck.getPolaznik() == null) {
            throw new Exception("Sistem ne može da zapamti člansku kartu.");
        }
        VrednosnaOgranicenjaClanskeKarte.proveri(ck, broker);
    }

    /**
     * Ažurira člansku kartu u bazi, briše postojeće stavke
     * i ubacuje nove stavke iz prosleđenog objekta.
     *
     * @param param članska karta sa ažuriranim podacima
     * @param kljuc dodatni ključ operacije
     * @throws Exception ako dođe do greške pri radu sa bazom
     */
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
