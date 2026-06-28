package operacije.polaznici;

import domen.Polaznik;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za ažuriranje postojećeg polaznika u bazi.
 */
public class ZapamtiPolaznikaSO extends ApstraktnaGenerickaOperacija {

    /**
     * Proverava da li je prosleđen ispravan polaznik za izmenu.
     *
     * @param param polaznik za izmenu
     * @throws Exception ako parametar nije ispravan
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Polaznik)) {
            throw new Exception("Sistem ne može da zapamti polaznika.");
        }
        Polaznik p = (Polaznik) param;
        if (p.getIdPolaznik() <= 0) {
            throw new Exception("Sistem ne može da zapamti polaznika.");
        }
        if (p.getIme() == null || p.getIme().trim().isEmpty()
                || p.getPrezime() == null || p.getPrezime().trim().isEmpty()
                || p.getBrojTelefona() == null || p.getBrojTelefona().trim().isEmpty()
                || p.getMesto() == null) {
            throw new Exception("Sistem ne može da zapamti polaznika.");
        }
    }

    /**
     * Ažurira podatke polaznika u bazi podataka.
     *
     * @param param polaznik sa ažuriranim podacima
     * @param kljuc dodatni ključ operacije
     * @throws Exception ako dođe do greške pri radu sa bazom
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.edit((Polaznik) param);
    }
}
