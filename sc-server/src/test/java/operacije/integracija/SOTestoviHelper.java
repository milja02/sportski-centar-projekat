package operacije.integracija;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import domen.Mesto;
import domen.Polaznik;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import operacije.mesta.UcitajMestaSO;
import operacije.polaznici.DodajPolaznikaSO;
import operacije.polaznici.NadjiPolaznikaSO;
import operacije.polaznici.ObrisiPolaznikaSO;
import operacije.polaznici.PretraziPolaznikeSO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import repository.db.DBConnectionFactory;

public abstract class SOTestoviHelper {

    private final List<Integer> polazniciZaBrisanje = new ArrayList<>();

    @BeforeAll
    static void pretpostaviDostupnuBazu() throws Exception {
        Connection konekcija = DBConnectionFactory.getInstance().getConnection();
        Assumptions.assumeTrue(konekcija != null && !konekcija.isClosed(),
                "Integracioni testovi zahtevaju pokrenut MySQL (baza sportskicentar)");
    }

    @AfterEach
    void ocistiTestPodatke() throws Exception {
        for (int id : polazniciZaBrisanje) {
            Polaznik polaznik = new Polaznik();
            polaznik.setIdPolaznik(id);
            new ObrisiPolaznikaSO().izvrsi(polaznik, null);
        }
        polazniciZaBrisanje.clear();
    }

    protected Mesto prvoMesto() throws Exception {
        UcitajMestaSO so = new UcitajMestaSO();
        so.izvrsi(null, null);
        assertFalse(so.getMesta().isEmpty(), "Baza mora imati bar jedno mesto");
        return so.getMesta().get(0);
    }

    protected String jedinstvenTelefon() {
        return "069" + (System.currentTimeMillis() % 10_000_000L);
    }

    protected Polaznik nadjiPolaznikaPoTelefonu(String telefon) throws Exception {
        Polaznik kriterijum = new Polaznik();
        kriterijum.setBrojTelefona(telefon);
        PretraziPolaznikeSO pretraga = new PretraziPolaznikeSO();
        pretraga.izvrsi(kriterijum, null);
        assertEquals(1, pretraga.getPolaznici().size(),
                "Ocekivan je tacno jedan polaznik sa telefonom " + telefon);
        return pretraga.getPolaznici().get(0);
    }

    protected Polaznik nadjiPolaznikaPoId(int id) throws Exception {
        Polaznik kriterijum = new Polaznik();
        kriterijum.setIdPolaznik(id);
        NadjiPolaznikaSO nadji = new NadjiPolaznikaSO();
        nadji.izvrsi(kriterijum, null);
        return nadji.getPolaznik();
    }

    protected Polaznik unesiTestPolaznika(String ime, String prezime) throws Exception {
        Mesto mesto = prvoMesto();
        String telefon = jedinstvenTelefon();

        Polaznik polaznik = new Polaznik();
        polaznik.setIme(ime);
        polaznik.setPrezime(prezime);
        polaznik.setBrojTelefona(telefon);
        polaznik.setMesto(mesto);

        new DodajPolaznikaSO().izvrsi(polaznik, null);
        return nadjiPolaznikaPoTelefonu(telefon);
    }

    protected Polaznik unesiTestPolaznikaZaCiscenje(String ime, String prezime) throws Exception {
        Polaznik sacuvan = unesiTestPolaznika(ime, prezime);
        registrujPolaznikaZaBrisanje(sacuvan);
        return sacuvan;
    }

    protected void registrujPolaznikaZaBrisanje(Polaznik polaznik) {
        polazniciZaBrisanje.add(polaznik.getIdPolaznik());
    }
}
