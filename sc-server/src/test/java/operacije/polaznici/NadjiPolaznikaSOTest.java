package operacije.polaznici;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domen.Polaznik;
import java.util.stream.Stream;
import operacije.pomocni.InjekcijaBrokera;
import operacije.pomocni.PodaciZaTest;
import operacije.pomocni.PomocniRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class NadjiPolaznikaSOTest {

    @ParameterizedTest(name = "{1}")
    @MethodSource("neispravniPolaznici")
    void predusloviOdbijaNeispravanUnos(Object polaznik, String opis) throws Exception {
        NadjiPolaznikaSO so = InjekcijaBrokera.saBrokerom(new NadjiPolaznikaSO(), PodaciZaTest.prazanRepo());

        Exception ex = assertThrows(Exception.class, () -> InjekcijaBrokera.pokreniPreduslove(so, polaznik));
        assertTrue(ex.getMessage().contains("polaznika"), opis);
    }

    static Stream<Arguments> neispravniPolaznici() {
        return Stream.of(
                Arguments.of(null, "null parametar"),
                Arguments.of(new Polaznik(), "nema ID"),
                Arguments.of(PodaciZaTest.polaznik(0, "Pera", "Peric", "061"), "ID nula"));
    }

    @Test
    void izvrsiVracaPolaznikaPoId() throws Exception {
        PomocniRepository repo = new PomocniRepository();
        repo.dodaj(PodaciZaTest.polaznik(7, "Sara", "Saric", "064"));

        NadjiPolaznikaSO so = InjekcijaBrokera.saBrokerom(new NadjiPolaznikaSO(), repo);
        so.izvrsi(PodaciZaTest.polaznik(7, "", "", ""), null);

        assertNotNull(so.getPolaznik());
        assertEquals("Sara", so.getPolaznik().getIme());
    }

    @Test
    void izvrsiBacaAkoPolaznikNePostoji() throws Exception {
        NadjiPolaznikaSO so = InjekcijaBrokera.saBrokerom(new NadjiPolaznikaSO(), new PomocniRepository());

        Exception ex = assertThrows(Exception.class,
                () -> so.izvrsi(PodaciZaTest.polaznik(99, "X", "Y", "060"), null));
        assertTrue(ex.getMessage().contains("polaznika"));
    }
}
