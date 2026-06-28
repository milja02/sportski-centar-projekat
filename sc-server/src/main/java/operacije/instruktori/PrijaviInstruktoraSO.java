package operacije.instruktori;

import domen.Instruktor;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za prijavu instruktora u sistem.
 * Proverava korisničko ime i šifru u odnosu na podatke iz baze.
 */
public class PrijaviInstruktoraSO extends ApstraktnaGenerickaOperacija {

    /** Prijavljeni instruktor. */
    private Instruktor instruktor;

    /**
     * Proverava da li su prosleđeni korisničko ime i šifra.
     *
     * @param param instruktor sa kredencijalima za prijavu
     * @throws Exception ako parametar nije ispravan
     */
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

    /**
     * Pronalazi instruktora u bazi čije korisničko ime i šifra
     * odgovaraju prosleđenim vrednostima.
     *
     * @param param instruktor sa kredencijalima za prijavu
     * @param kljuc dodatni ključ operacije
     * @throws Exception ako kredencijali nisu ispravni
     */
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

    /**
     * Vraća prijavljenog instruktora.
     *
     * @return instruktor iz baze
     */
    public Instruktor getInstruktor() {
        return instruktor;
    }
}
