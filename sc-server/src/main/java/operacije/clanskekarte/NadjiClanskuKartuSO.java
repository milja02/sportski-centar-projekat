package operacije.clanskekarte;

import domen.ClanskaKarta;
import domen.StavkaClanskeKarte;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za pronalaženje članske karte po identifikatoru.
 * Učitava kartu zajedno sa svim njenim stavkama.
 */
public class NadjiClanskuKartuSO extends ApstraktnaGenerickaOperacija {

    /** Pronađena članska karta. */
    private ClanskaKarta clanskaKarta;

    /**
     * Proverava da li je prosleđen ispravan identifikator članske karte.
     *
     * @param param članska karta sa id-jem za pretragu
     * @throws Exception ako parametar nije ispravan
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof ClanskaKarta)) {
            throw new Exception("Sistem ne može da nađe člansku kartu.");
        }
        ClanskaKarta ck = (ClanskaKarta) param;
        if (ck.getIdClanskaKarta() <= 0) {
            throw new Exception("Sistem ne može da nađe člansku kartu.");
        }
    }

    /**
     * Učitava člansku kartu iz baze po id-ju i postavlja njene stavke.
     *
     * @param param članska karta sa id-jem za pretragu
     * @param kljuc dodatni ključ operacije
     * @throws Exception ako karta ne postoji ili dođe do greške pri radu sa bazom
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        ClanskaKarta kriterijum = (ClanskaKarta) param;
        String uslov = " WHERE idClanskaKarta=" + kriterijum.getIdClanskaKarta();
        List<ClanskaKarta> lista = (List<ClanskaKarta>) (List<?>) broker.getAll(new ClanskaKarta(), uslov);
        if (lista == null || lista.isEmpty()) {
            throw new Exception("Sistem ne može da nađe člansku kartu.");
        }
        clanskaKarta = lista.get(0);
        String uslovStavke = " WHERE clanskakarta=" + clanskaKarta.getIdClanskaKarta();
        List<StavkaClanskeKarte> stavke = (List<StavkaClanskeKarte>) (List<?>) broker.getAll(new StavkaClanskeKarte(), uslovStavke);
        clanskaKarta.setStavke(stavke);
    }

    /**
     * Vraća pronađenu člansku kartu.
     *
     * @return članska karta sa stavkama
     */
    public ClanskaKarta getClanskaKarta() {
        return clanskaKarta;
    }
}
