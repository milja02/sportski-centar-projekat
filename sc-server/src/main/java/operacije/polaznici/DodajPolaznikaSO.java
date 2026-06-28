package operacije.polaznici;

import domen.Polaznik;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za unos novog polaznika u bazu.
 */
public class DodajPolaznikaSO extends ApstraktnaGenerickaOperacija {

    /**
     * Proverava da li su prosleđeni ispravni podaci o polazniku.
     *
     * @param param polaznik za unos
     * @throws Exception ako parametar nije ispravan
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Polaznik)) {
            throw new Exception("Sistem nije mogao da kreira polaznika");
        }
        Polaznik p = (Polaznik) param;
        if (p.getIme() == null || p.getIme().isEmpty()) {
            throw new Exception("Sistem nije mogao da kreira polaznika");
        }
        if (p.getPrezime() == null || p.getPrezime().isEmpty()) {
            throw new Exception("Sistem nije mogao da kreira polaznika");
        }
        if (p.getBrojTelefona() == null || p.getBrojTelefona().isEmpty()) {
            throw new Exception("Sistem nije mogao da kreira polaznika");
        }
    }

    /**
     * Ubacuje polaznika u bazu podataka.
     *
     * @param param polaznik za unos
     * @param kljuc dodatni ključ operacije
     * @throws Exception ako dođe do greške pri radu sa bazom
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.add((Polaznik) param);
    }
}
