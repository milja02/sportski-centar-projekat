package operacije.integracija;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import domen.Mesto;
import domen.Polaznik;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import operacije.mesta.UcitajMestaSO;
import operacije.polaznici.ObrisiPolaznikaSO;
import operacije.polaznici.PretraziPolaznikeSO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import repository.db.DBConnectionFactory;

/**
 * Zajednicka logika za integracione SO testove nad pravom bazom.
 * Zahteva pokrenut MySQL i bazu iz dbconfig.properties.
 */
public abstract class IntegracioniTestOsnova {

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

    protected void registrujPolaznikaZaBrisanje(Polaznik polaznik) {
        polazniciZaBrisanje.add(polaznik.getIdPolaznik());
    }
}
