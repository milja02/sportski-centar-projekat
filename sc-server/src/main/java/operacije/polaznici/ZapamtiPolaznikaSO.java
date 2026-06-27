/*
 * SK4 korak 7 / SK6 korak 12 - Zapamti polaznika (UPDATE).
 */
package operacije.polaznici;

import domen.Polaznik;
import operacije.ApstraktnaGenerickaOperacija;

public class ZapamtiPolaznikaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Polaznik)) {
            throw new Exception("Sistem ne moÅ¾e da zapamti polaznika.");
        }
        Polaznik p = (Polaznik) param;
        if (p.getIdPolaznik() <= 0) {
            throw new Exception("Sistem ne moÅ¾e da zapamti polaznika.");
        }
        if (p.getIme() == null || p.getIme().trim().isEmpty()
                || p.getPrezime() == null || p.getPrezime().trim().isEmpty()
                || p.getBrojTelefona() == null || p.getBrojTelefona().trim().isEmpty()
                || p.getMesto() == null) {
            throw new Exception("Sistem ne moÅ¾e da zapamti polaznika.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.edit((Polaznik) param);
    }
}
