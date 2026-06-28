package operacije.mesta;

import domen.Mesto;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za učitavanje svih mesta iz baze.
 */
public class UcitajMestaSO extends ApstraktnaGenerickaOperacija {

    /** Lista učitanih mesta. */
    private List<Mesto> mesta;

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    /**
     * Učitava sva mesta iz baze podataka.
     *
     * @param param parametar operacije (nije korišćen)
     * @param kljuc dodatni ključ operacije
     * @throws Exception ako dođe do greške pri radu sa bazom
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        mesta = (List<Mesto>) (List<?>) broker.getAll(new Mesto(), null);
    }

    /**
     * Vraća listu učitanih mesta.
     *
     * @return lista mesta
     */
    public List<Mesto> getMesta() {
        return mesta;
    }
}
