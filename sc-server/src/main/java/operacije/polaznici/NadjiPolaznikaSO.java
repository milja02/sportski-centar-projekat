package operacije.polaznici;

import domen.Polaznik;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za pronalaženje polaznika po identifikatoru.
 */
public class NadjiPolaznikaSO extends ApstraktnaGenerickaOperacija {

    /** Pronađeni polaznik. */
    private Polaznik polaznik;

    /**
     * Proverava da li je prosleđen ispravan identifikator polaznika.
     *
     * @param param polaznik sa id-jem za pretragu
     * @throws Exception ako parametar nije ispravan
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Polaznik)) {
            throw new Exception("Sistem ne može da nađe polaznika.");
        }
        Polaznik p = (Polaznik) param;
        if (p.getIdPolaznik() <= 0) {
            throw new Exception("Sistem ne može da nađe polaznika.");
        }
    }

    /**
     * Učitava polaznika iz baze po id-ju.
     *
     * @param param polaznik sa id-jem za pretragu
     * @param kljuc dodatni ključ operacije
     * @throws Exception ako polaznik ne postoji ili dođe do greške pri radu sa bazom
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Polaznik kriterijum = (Polaznik) param;
        String uslov = " WHERE po.idPolaznik=" + kriterijum.getIdPolaznik();
        List<Polaznik> lista = (List<Polaznik>) (List<?>) broker.getAll(new Polaznik(), uslov);
        if (lista == null || lista.isEmpty()) {
            throw new Exception("Sistem ne može da nađe polaznika.");
        }
        polaznik = lista.get(0);
    }

    /**
     * Vraća pronađenog polaznika.
     *
     * @return polaznik iz baze
     */
    public Polaznik getPolaznik() {
        return polaznik;
    }
}
