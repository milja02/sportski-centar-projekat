package operacije.instruktori;

import domen.Instruktor;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

public class PrijaviInstruktoraSO extends ApstraktnaGenerickaOperacija {

    private Instruktor instruktor;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Instruktor)) {
            throw new Exception("Korisničko ime i šifra nisu ispravni.");
        }
        Instruktor i = (Instruktor) param;
        if (i.getKorisnickoIme() == null || i.getKorisnickoIme().trim().isEmpty()
                || i.getSifra() == null || i.getSifra().trim().isEmpty()) {
            throw new Exception("Korisničko ime i šifra nisu ispravni.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        List<Instruktor> sviInstruktori = (List<Instruktor>) (List<?>) broker.getAll((Instruktor) param, null);
        for (Instruktor i : sviInstruktori) {
            if (i.equals((Instruktor) param)) {
                instruktor = i;
                return;
            }
        }
        throw new Exception("Korisničko ime i šifra nisu ispravni.");
    }

    public Instruktor getInstruktor() {
        return instruktor;
    }
}
