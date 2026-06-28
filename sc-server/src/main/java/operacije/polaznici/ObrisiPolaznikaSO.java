package operacije.polaznici;

import domen.Polaznik;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za brisanje polaznika iz baze.
 */
public class ObrisiPolaznikaSO extends ApstraktnaGenerickaOperacija {

    /**
     * Proverava da li je prosleđen ispravan polaznik za brisanje.
     *
     * @param param polaznik za brisanje
     * @throws Exception ako parametar nije ispravan
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Polaznik)) {
            throw new Exception("Sistem nije mogao da obriše polaznika.");
        }
    }

    /**
     * Briše polaznika iz baze podataka.
     *
     * @param param polaznik za brisanje
     * @param kljuc dodatni ključ operacije
     * @throws Exception ako dođe do greške pri radu sa bazom
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.delete((Polaznik) param);
    }
}
