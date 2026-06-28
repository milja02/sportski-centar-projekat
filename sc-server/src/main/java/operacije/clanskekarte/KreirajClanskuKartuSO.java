package operacije.clanskekarte;

import domen.ApstraktniDomenskiObjekat;
import domen.ClanskaKarta;
import domen.Instruktor;
import domen.Polaznik;
import java.util.Date;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za kreiranje praznog zapisa članske karte u bazi.
 * Ubacuje kartu sa podrazumevanim vrednostima i vraća generisani identifikator.
 */
public class KreirajClanskuKartuSO extends ApstraktnaGenerickaOperacija {

    /** Novokreirana članska karta sa generisanim id-jem. */
    private ClanskaKarta clanskaKarta;

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    /**
     * Ubacuje prazan zapis članske karte u bazu koristeći prvog polaznika
     * i prvog instruktora iz baze, zatim čuva generisani id.
     *
     * @param param parametar operacije (nije korišćen)
     * @param kljuc dodatni ključ operacije
     * @throws Exception ako u bazi nema polaznika ili instruktora, ili insert ne uspe
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        List<ApstraktniDomenskiObjekat> polaznici = broker.getAll(new Polaznik(), null);
        List<ApstraktniDomenskiObjekat> instruktori = broker.getAll(new Instruktor(), null);
        if (polaznici == null || polaznici.isEmpty()
                || instruktori == null || instruktori.isEmpty()) {
            throw new Exception("Sistem ne može da kreira člansku kartu.");
        }

        ClanskaKarta nova = new ClanskaKarta();
        nova.setDatumUclanjenja(new Date());
        nova.setUkupanIznos(0);
        nova.setPolaznik((Polaznik) polaznici.get(0));
        nova.setInstruktor((Instruktor) instruktori.get(0));

        int id = broker.addAndReturnId(nova);
        if (id <= 0) {
            throw new Exception("Sistem ne može da kreira člansku kartu.");
        }

        clanskaKarta = new ClanskaKarta();
        clanskaKarta.setIdClanskaKarta(id);
    }

    /**
     * Vraća novokreiranu člansku kartu sa generisanim id-jem.
     *
     * @return članska karta sa id-jem
     */
    public ClanskaKarta getClanskaKarta() {
        return clanskaKarta;
    }
}
