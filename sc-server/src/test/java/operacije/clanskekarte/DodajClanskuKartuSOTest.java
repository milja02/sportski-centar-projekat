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
        ClanskaKarta bezDatuma = PodaciZaTest.kartaSaStavkom(2, 4000);
        bezDatuma.setDatumUclanjenja(null);

        ClanskaKarta bezPolaznika = PodaciZaTest.kartaSaStavkom(2, 4000);
        bezPolaznika.setPolaznik(null);

        ClanskaKarta losIznos = PodaciZaTest.kartaSaStavkom(2, 4000);
        losIznos.setUkupanIznos(1);

        return Stream.of(
                Arguments.of(null, "null parametar", "kreira"),
                Arguments.of(new ClanskaKarta(), "nema datum", "datum"),
                Arguments.of(bezDatuma, "eksplicitno nema datum", "datum"),
                Arguments.of(bezPolaznika, "nema polaznika", "instruktor i polaznik"),
                Arguments.of(losIznos, "los iznos stavke", "ukupan iznos"));
    }

    @Test
    void predusloviPrihvataIspravnuKartu() throws Exception {
        DodajClanskuKartuSO so = InjekcijaBrokera.saBrokerom(new DodajClanskuKartuSO(), PodaciZaTest.repoSaSportovima());
        assertDoesNotThrow(() -> InjekcijaBrokera.pokreniPreduslove(so, PodaciZaTest.kartaSaStavkom(2, 4000)));
    }
}
