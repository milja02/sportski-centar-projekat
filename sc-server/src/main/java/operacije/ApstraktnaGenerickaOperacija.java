package operacije;

import repository.Repository;
import repository.db.DBRepository;
import repository.db.impl.DBRepositoryGeneric;

/**
 * Definiše zajednički tok izvršavanja: provera preduslova, transakcija,
 * izvršenje operacije i potvrda ili poništavanje transakcije.
 */
public abstract class ApstraktnaGenerickaOperacija {

    /** Repozitorijum za pristup bazi podataka. */
    protected final Repository broker;

    /**
     * Konstruktor koji koristi podrazumevani repozitorijum.
     */
    public ApstraktnaGenerickaOperacija() {
        this(new DBRepositoryGeneric());
    }

    /**
     * Konstruktor koji prima repozitorijum.
     *
     * @param broker repozitorijum za pristup bazi
     */
    protected ApstraktnaGenerickaOperacija(Repository broker) {
        this.broker = broker;
    }

    /**
     * Izvršava sistemsku operaciju u okviru transakcije.
     * Prvo proverava preduslove, zatim pokreće transakciju,
     * poziva konkretnu operaciju i potvrđuje izmene u bazi.
     * U slučaju greške poništava transakciju i prosleđuje izuzetak.
     *
     * @param objekat parametar operacije
     * @param kljuc dodatni ključ operacije
     * @throws Exception ako preduslovi nisu ispunjeni ili dođe do greške pri radu sa bazom
     */
    public final void izvrsi(Object objekat, String kljuc) throws Exception {
        try {
            preduslovi(objekat);
            zapocniTransakciju();
            izvrsiOperaciju(objekat, kljuc);
            potvrdiTransakciju();
        } catch (Exception e) {
            ponistiTransakciju();
            throw e;
        }
    }

    /**
     * Proverava preduslove pre izvršenja operacije.
     *
     * @param param parametar operacije
     * @throws Exception ako preduslovi nisu ispunjeni
     */
    protected abstract void preduslovi(Object param) throws Exception;

    /**
     * Sadrži konkretnu poslovnu logiku sistemske operacije.
     *
     * @param param parametar operacije
     * @param kljuc dodatni ključ operacije
     * @throws Exception ako dođe do greške pri izvršenju operacije
     */
    protected abstract void izvrsiOperaciju(Object param, String kljuc) throws Exception;

    private void zapocniTransakciju() throws Exception {
        ((DBRepository) broker).connect();
    }

    private void potvrdiTransakciju() throws Exception {
        ((DBRepository) broker).commit();
    }

    private void ponistiTransakciju() throws Exception {
        ((DBRepository) broker).rollback();
    }

    private void ugasiKonekciju() throws Exception {
        ((DBRepository) broker).disconnect();
    }
}
