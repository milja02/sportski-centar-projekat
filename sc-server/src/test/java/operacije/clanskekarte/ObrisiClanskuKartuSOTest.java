package operacije.clanskekarte;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domen.ClanskaKarta;
import domen.StavkaClanskeKarte;
import java.util.stream.Stream;
import operacije.pomocni.InjekcijaBrokera;
import operacije.pomocni.PodaciZaTest;
import operacije.pomocni.PomocniRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ObrisiClanskuKartuSOTest {

    @ParameterizedTest(name = "{1}")
    @MethodSource("neispravneKarte")
    void predusloviOdbijaNeispravanUnos(Object parametar, String opis) throws Exception {
        ObrisiClanskuKartuSO so = InjekcijaBrokera.saBrokerom(new ObrisiClanskuKartuSO(), PodaciZaTest.prazanRepo());

        Exception ex = assertThrows(Exception.class, () -> InjekcijaBrokera.pokreniPreduslove(so, parametar));
        assertTrue(ex.getMessage().contains("obriše") || ex.getMessage().contains("Neispravan"), opis);
    }

    static Stream<Arguments> neispravneKarte() {
        return Stream.of(
                Arguments.of(null, "null parametar"),
                Arguments.of("nije karta", "pogresan tip"),
                Arguments.of(new ClanskaKarta(), "nema ID"));
    }

    @Test
    void predusloviPrihvataIspravanId() throws Exception {
        ObrisiClanskuKartuSO so = InjekcijaBrokera.saBrokerom(new ObrisiClanskuKartuSO(), PodaciZaTest.prazanRepo());
        assertDoesNotThrow(() -> InjekcijaBrokera.pokreniPreduslove(so, PodaciZaTest.kartaSaStavkom(1, 4000)));
    }

    @Test
    void izvrsiBriseKartuIStavke() throws Exception {
        PomocniRepository repo = new PomocniRepository();
        ClanskaKarta karta = PodaciZaTest.kartaSaStavkom(2, 4000);
        repo.dodaj(karta);
        repo.dodaj(karta.getStavke().get(0));

        ObrisiClanskuKartuSO so = InjekcijaBrokera.saBrokerom(new ObrisiClanskuKartuSO(), repo);
        so.izvrsi(karta, null);

        assertEquals(0, repo.getAll(new ClanskaKarta(), null).size());
        assertEquals(0, repo.getAll(new StavkaClanskeKarte(), " WHERE sck.clanskakarta=1").size());
    }
}
