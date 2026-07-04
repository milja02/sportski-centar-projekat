package operacije.clanskekarte;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domen.ClanskaKarta;
import java.util.stream.Stream;
import operacije.pomocni.InjekcijaBrokera;
import operacije.pomocni.PodaciZaTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DodajClanskuKartuSOTest {

    @ParameterizedTest(name = "{1}")
    @MethodSource("neispravneKarte")
    void predusloviOdbijaNeispravanUnos(Object karta, String opis, String deoPoruke) throws Exception {
        DodajClanskuKartuSO so = InjekcijaBrokera.saBrokerom(new DodajClanskuKartuSO(), PodaciZaTest.repoSaSportovima());

        Exception ex = assertThrows(Exception.class, () -> InjekcijaBrokera.pokreniPreduslove(so, karta));
        assertTrue(ex.getMessage().contains(deoPoruke), opis);
    }

    static Stream<Arguments> neispravneKarte() throws Exception {
        ClanskaKarta bezPolaznika = new ClanskaKarta();
        bezPolaznika.setDatumUclanjenja(PodaciZaTest.datum("2024-06-01"));
        bezPolaznika.setInstruktor(PodaciZaTest.instruktor(1, "Mika", "Mikic", "mika", "pass"));

        return Stream.of(
                Arguments.of(null, "null parametar", "kreira"),
                Arguments.of(new ClanskaKarta(), "nema datum", "datum"),
                Arguments.of(bezPolaznika, "nema polaznika", "instruktor i polaznik"));
    }

    @Test
    void predusloviPrihvataIspravnuKartu() throws Exception {
        DodajClanskuKartuSO so = InjekcijaBrokera.saBrokerom(new DodajClanskuKartuSO(), PodaciZaTest.repoSaSportovima());
        assertDoesNotThrow(() -> InjekcijaBrokera.pokreniPreduslove(so, PodaciZaTest.kartaSaStavkom(2, 4000)));
    }
}
