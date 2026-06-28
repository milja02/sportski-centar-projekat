package operacije.licence;

import domen.Licenca;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za učitavanje svih licenci iz baze.
 */
public class UcitajLicenceSO extends ApstraktnaGenerickaOperacija {

    /** Lista učitanih licenci. */
    private List<Licenca> licence;

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    /**
     * Učitava sve licence iz baze podataka.
     *
     * @param param parametar operacije (nije korišćen)
     * @param kljuc dodatni ključ operacije
     * @throws Exception ako dođe do greške pri radu sa bazom
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        licence = (List<Licenca>) (List<?>) broker.getAll(new Licenca(), null);
    }

    /**
     * Vraća listu učitanih licenci.
     *
     * @return lista licenci
     */
    public List<Licenca> getLicence() {
        return licence;
    }
}
