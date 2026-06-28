package operacije.clanskekarte;

import domen.ClanskaKarta;
import domen.StavkaClanskeKarte;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za učitavanje svih članjskih karata iz baze.
 * Za svaku kartu učitava i njene stavke.
 */
public class UcitajClanskeKarteSO extends ApstraktnaGenerickaOperacija {

    /** Lista učitanih članjskih karata. */
    private List<ClanskaKarta> clanskeKarte;

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    /**
     * Učitava sve članske karte i za svaku kartu učitava pripadajuće stavke.
     *
     * @param param parametar operacije (nije korišćen)
     * @param kljuc dodatni ključ operacije
     * @throws Exception ako dođe do greške pri radu sa bazom
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        clanskeKarte = broker.getAll(new ClanskaKarta(), null);
        for (ClanskaKarta ck : clanskeKarte) {
            String uslov = " WHERE clanskakarta=" + ck.getIdClanskaKarta();
            List<StavkaClanskeKarte> stavke = (List<StavkaClanskeKarte>) (List<?>) broker.getAll(new StavkaClanskeKarte(), uslov);
            ck.setStavke(stavke);
        }
    }

    /**
     * Vraća listu učitanih članjskih karata.
     *
     * @return lista članjskih karata sa stavkama
     */
    public List<ClanskaKarta> getClanskeKarte() {
        return clanskeKarte;
    }
}
