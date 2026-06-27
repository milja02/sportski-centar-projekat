/*
 * SK4 - Kreiraj polaznika.
 * INSERT praznog reda (placeholder vrednosti) i vraÄ‡anje generisanog ID-ja.
 */
package operacije.polaznici;

import domen.ApstraktniDomenskiObjekat;
import domen.Mesto;
import domen.Polaznik;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;
import repository.db.impl.DBRepositoryGeneric;

public class KreirajPolaznikaSO extends ApstraktnaGenerickaOperacija {

    private Polaznik polaznik;

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        List<ApstraktniDomenskiObjekat> mesta = broker.getAll(new Mesto(), null);
        if (mesta == null || mesta.isEmpty()) {
            throw new Exception("Sistem ne moÅ¾e da kreira polaznika.");
        }
        Mesto mesto = (Mesto) mesta.get(0);

        Polaznik novi = new Polaznik();
        novi.setIme("");
        novi.setPrezime("");
        novi.setBrojTelefona("");
        novi.setMesto(mesto);

        int id = ((DBRepositoryGeneric) broker).addAndReturnId(novi);
        if (id <= 0) {
            throw new Exception("Sistem ne moÅ¾e da kreira polaznika.");
        }

        polaznik = new Polaznik();
        polaznik.setIdPolaznik(id);
    }

    public Polaznik getPolaznik() {
        return polaznik;
    }
}
