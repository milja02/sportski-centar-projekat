package operacije.polaznici;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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

class DodajPolaznikaSOTest {

    @ParameterizedTest(name = "{1}")
    @MethodSource("neispravniParametri")
    void predusloviOdbijaNeispravanUnos(Object polaznik, String opis) throws Exception {
        DodajPolaznikaSO so = InjekcijaBrokera.saBrokerom(new DodajPolaznikaSO(), PodaciZaTest.prazanRepo());

        Exception ex = assertThrows(Exception.class, () -> InjekcijaBrokera.pokreniPreduslove(so, polaznik));
        assertTrue(ex.getMessage().contains("kreira") && ex.getMessage().contains("polaznika"), opis);
    }

    static Stream<Arguments> neispravniParametri() {
        return Stream.of(
                Arguments.of(null, "null parametar"),
                Arguments.of("nije polaznik", "pogresan tip"));
    }

    @Test
    void predusloviPrihvataIspravnogPolaznika() throws Exception {
        DodajPolaznikaSO so = InjekcijaBrokera.saBrokerom(new DodajPolaznikaSO(), PodaciZaTest.prazanRepo());
        assertDoesNotThrow(() -> InjekcijaBrokera.pokreniPreduslove(so, PodaciZaTest.polaznik(0, "Luka", "Lukic", "065")));
    }

    @Test
    void izvrsiDodajePolaznikaUBazu() throws Exception {
        PomocniRepository repo = new PomocniRepository();
        DodajPolaznikaSO so = InjekcijaBrokera.saBrokerom(new DodajPolaznikaSO(), repo);
        Polaznik polaznik = PodaciZaTest.polaznik(0, "Jovana", "Jovic", "066");
        polaznik.setMesto(PodaciZaTest.beograd());

        so.izvrsi(polaznik, null);

        assertTrue(repo.getAll(new Polaznik(), null).size() == 1);
    }
}
