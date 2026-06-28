package operacije.polaznici;

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

class PretraziPolaznikeSOTest {

    @ParameterizedTest(name = "{1}")
    @MethodSource("neispravniKriterijumi")
    void predusloviOdbijaNeispravanUnos(Object parametar, String opis) throws Exception {
        PretraziPolaznikeSO so = InjekcijaBrokera.saBrokerom(new PretraziPolaznikeSO(), PodaciZaTest.prazanRepo());

        Exception ex = assertThrows(Exception.class, () -> InjekcijaBrokera.pokreniPreduslove(so, parametar));
        assertTrue(ex.getMessage().contains("polaznike"), opis);
    }

    static Stream<Arguments> neispravniKriterijumi() {
        return Stream.of(
                Arguments.of(null, "null parametar"),
                Arguments.of("nije polaznik", "pogresan tip"));
    }

    @Test
    void izvrsiFiltriraPoImenu() throws Exception {
        PomocniRepository repo = new PomocniRepository();
        PodaciZaTest.popuniPolaznike(repo);

        PretraziPolaznikeSO so = InjekcijaBrokera.saBrokerom(new PretraziPolaznikeSO(), repo);
        Polaznik kriterijum = new Polaznik();
        kriterijum.setIme("Per");
        so.izvrsi(kriterijum, null);

        assertEquals(1, so.getPolaznici().size());
        assertEquals("Pera", so.getPolaznici().get(0).getIme());
    }
}
