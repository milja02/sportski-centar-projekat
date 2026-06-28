package operacije.polaznici;

import domen.Polaznik;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za učitavanje svih polaznika iz baze.
 */
public class UcitajPolaznikeSO extends ApstraktnaGenerickaOperacija {

    /** Lista učitanih polaznika. */
    private List<Polaznik> polaznici;

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    /**
     * Učitava sve polaznike iz baze podataka.
     *
     * @param param parametar operacije (nije korišćen)
     * @param kljuc dodatni ključ operacije
     * @throws Exception ako dođe do greške pri radu sa bazom
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        polaznici = broker.getAll(new Polaznik(), null);
    }

    /**
     * Vraća listu učitanih polaznika.
     *
     * @return lista polaznika
     */
    public List<Polaznik> getPolaznici() {
        return polaznici;
    }
}
