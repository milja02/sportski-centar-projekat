package operacije.clanskekarte;

import domen.ClanskaKarta;
import domen.StavkaClanskeKarte;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za brisanje članske karte iz baze.
 * Pre brisanja karte uklanja sve njene stavke.
 */
public class ObrisiClanskuKartuSO extends ApstraktnaGenerickaOperacija {

    /**
     * Proverava da li je prosleđena ispravna članska karta za brisanje.
     *
     * @param param članska karta za brisanje
     * @throws Exception ako parametar nije ispravan
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof ClanskaKarta)) {
            throw new Exception("Sistem nije mogao da obriše člansku kartu.");
        }
        ClanskaKarta ck = (ClanskaKarta) param;
        if (ck.getIdClanskaKarta() <= 0) {
            throw new Exception("Neispravan ID članske kartice.");
        }
    }

    /**
     * Briše sve stavke članske karte, zatim briše samu kartu iz baze.
     *
     * @param param članska karta za brisanje
     * @param kljuc dodatni ključ operacije
     * @throws Exception ako dođe do greške pri radu sa bazom
     */
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
