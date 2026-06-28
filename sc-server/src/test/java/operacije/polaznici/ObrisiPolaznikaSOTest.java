package operacije.polaznici;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

class ObrisiPolaznikaSOTest {

    @ParameterizedTest(name = "{1}")
    @MethodSource("neispravniPolaznici")
    void predusloviOdbijaNeispravanUnos(Object parametar, String opis) throws Exception {
        ObrisiPolaznikaSO so = InjekcijaBrokera.saBrokerom(new ObrisiPolaznikaSO(), PodaciZaTest.prazanRepo());

        Exception ex = assertThrows(Exception.class, () -> InjekcijaBrokera.pokreniPreduslove(so, parametar));
        assertTrue(ex.getMessage().contains("obriše") && ex.getMessage().contains("polaznika"), opis);
    }

    static Stream<Arguments> neispravniPolaznici() {
        return Stream.of(
                Arguments.of(null, "null parametar"),
                Arguments.of("nije polaznik", "pogresan tip"));
    }

    @Test
    void predusloviPrihvataIspravnogPolaznika() throws Exception {
        ObrisiPolaznikaSO so = InjekcijaBrokera.saBrokerom(new ObrisiPolaznikaSO(), PodaciZaTest.prazanRepo());
        assertDoesNotThrow(() -> InjekcijaBrokera.pokreniPreduslove(so, PodaciZaTest.polaznik(1, "Pera", "Peric", "061")));
    }

    @Test
    void izvrsiUklanjaPolaznikaIzBaze() throws Exception {
        PomocniRepository repo = new PomocniRepository();
        Polaznik polaznik = PodaciZaTest.polaznik(1, "Pera", "Peric", "061");
        repo.dodaj(polaznik);

        ObrisiPolaznikaSO so = InjekcijaBrokera.saBrokerom(new ObrisiPolaznikaSO(), repo);
        so.izvrsi(polaznik, null);

        assertEquals(0, repo.getAll(new Polaznik(), null).size());
    }
}
