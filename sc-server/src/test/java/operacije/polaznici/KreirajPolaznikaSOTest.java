package operacije.polaznici;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domen.Polaznik;
import operacije.pomocni.InjekcijaBrokera;
import operacije.pomocni.PodaciZaTest;
import operacije.pomocni.PomocniRepository;
import org.junit.jupiter.api.Test;

class KreirajPolaznikaSOTest {

    @Test
    void izvrsiKreiraNoviId() throws Exception {
        PomocniRepository repo = new PomocniRepository();
        PodaciZaTest.popuniMesta(repo);

        KreirajPolaznikaSO so = InjekcijaBrokera.saBrokerom(new KreirajPolaznikaSO(), repo);
        so.izvrsi(null, null);

        assertNotNull(so.getPolaznik());
        assertTrue(so.getPolaznik().getIdPolaznik() > 0);
        assertEquals(1, repo.getAll(new Polaznik(), null).size());
    }

    @Test
    void izvrsiBacaAkoNemaMesta() throws Exception {
        KreirajPolaznikaSO so = InjekcijaBrokera.saBrokerom(new KreirajPolaznikaSO(), new PomocniRepository());

        Exception ex = assertThrows(Exception.class, () -> so.izvrsi(null, null));
        assertTrue(ex.getMessage().contains("kreira") && ex.getMessage().contains("polaznika"));
    }
}
