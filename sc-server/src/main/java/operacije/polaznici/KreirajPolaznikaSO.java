package operacije.polaznici;

import domen.ApstraktniDomenskiObjekat;
import domen.Mesto;
import domen.Polaznik;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za kreiranje praznog zapisa polaznika u bazi.
 * Ubacuje polaznika sa podrazumevanim vrednostima i vraća generisani identifikator.
 */
public class KreirajPolaznikaSO extends ApstraktnaGenerickaOperacija {

    /** Novokreirani polaznik sa generisanim id-jem. */
    private Polaznik polaznik;

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    /**
     * Ubacuje prazan zapis polaznika u bazu koristeći prvo mesto iz baze,
     * zatim čuva generisani id.
     *
     * @param param parametar operacije (nije korišćen)
     * @param kljuc dodatni ključ operacije
     * @throws Exception ako u bazi nema mesta ili insert ne uspe
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        List<ApstraktniDomenskiObjekat> mesta = broker.getAll(new Mesto(), null);
        if (mesta == null || mesta.isEmpty()) {
            throw new Exception("Sistem ne može da kreira polaznika.");
        }
        Mesto mesto = (Mesto) mesta.get(0);

        Polaznik novi = new Polaznik();
        novi.setIme("");
        novi.setPrezime("");
        novi.setBrojTelefona("");
        novi.setMesto(mesto);

        int id = broker.addAndReturnId(novi);
        if (id <= 0) {
            throw new Exception("Sistem ne može da kreira polaznika.");
        }

        polaznik = new Polaznik();
        polaznik.setIdPolaznik(id);
    }

    /**
     * Vraća novokreiranog polaznika sa generisanim id-jem.
     *
     * @return polaznik sa id-jem
     */
    public Polaznik getPolaznik() {
        return polaznik;
    }
}
