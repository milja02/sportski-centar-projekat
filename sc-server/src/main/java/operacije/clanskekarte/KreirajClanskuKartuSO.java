/*
 * SK1 - Kreiraj Älansku kartu.
 * INSERT praznog reda (placeholder vrednosti) i vraÄ‡anje generisanog ID-ja.
 */
package operacije.clanskekarte;

import domen.ApstraktniDomenskiObjekat;
import domen.ClanskaKarta;
import domen.Instruktor;
import domen.Polaznik;
import java.util.Date;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;
import repository.db.impl.DBRepositoryGeneric;

public class KreirajClanskuKartuSO extends ApstraktnaGenerickaOperacija {

    private ClanskaKarta clanskaKarta;

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        List<ApstraktniDomenskiObjekat> polaznici = broker.getAll(new Polaznik(), null);
        List<ApstraktniDomenskiObjekat> instruktori = broker.getAll(new Instruktor(), null);
        if (polaznici == null || polaznici.isEmpty()
                || instruktori == null || instruktori.isEmpty()) {
            throw new Exception("Sistem ne moÅ¾e da kreira Älansku kartu.");
        }

        ClanskaKarta nova = new ClanskaKarta();
        nova.setDatumUclanjenja(new Date());
        nova.setUkupanIznos(0);
        nova.setPolaznik((Polaznik) polaznici.get(0));
        nova.setInstruktor((Instruktor) instruktori.get(0));

        int id = ((DBRepositoryGeneric) broker).addAndReturnId(nova);
        if (id <= 0) {
            throw new Exception("Sistem ne moÅ¾e da kreira Älansku kartu.");
        }

        clanskaKarta = new ClanskaKarta();
        clanskaKarta.setIdClanskaKarta(id);
    }

    public ClanskaKarta getClanskaKarta() {
        return clanskaKarta;
    }
}
