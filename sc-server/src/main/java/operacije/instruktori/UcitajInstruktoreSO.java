package operacije.instruktori;

import domen.Instruktor;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za učitavanje svih instruktora iz baze.
 */
public class UcitajInstruktoreSO extends ApstraktnaGenerickaOperacija {

    /** Lista učitanih instruktora. */
    private List<Instruktor> instruktori;

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    /**
     * Učitava sve instruktore iz baze podataka.
     *
     * @param param parametar operacije (nije korišćen)
     * @param kljuc dodatni ključ operacije
     * @throws Exception ako dođe do greške pri radu sa bazom
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        instruktori = (List<Instruktor>) (List<?>) broker.getAll(new Instruktor(), null);
    }

    /**
     * Vraća listu učitanih instruktora.
     *
     * @return lista instruktora
     */
    public List<Instruktor> getInstruktori() {
        return instruktori;
    }
}
