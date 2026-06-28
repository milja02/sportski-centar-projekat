package operacije.sportovi;

import domen.Sport;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za učitavanje svih sportova iz baze.
 */
public class UcitajSportoveSO extends ApstraktnaGenerickaOperacija {

    /** Lista učitanih sportova. */
    private List<Sport> sportovi;

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    /**
     * Učitava sve sportove iz baze podataka.
     *
     * @param param parametar operacije (nije korišćen)
     * @param kljuc dodatni ključ operacije
     * @throws Exception ako dođe do greške pri radu sa bazom
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        sportovi = (List<Sport>) (List<?>) broker.getAll(new Sport(), null);
    }

    /**
     * Vraća listu učitanih sportova.
     *
     * @return lista sportova
     */
    public List<Sport> getSportovi() {
        return sportovi;
    }
}
