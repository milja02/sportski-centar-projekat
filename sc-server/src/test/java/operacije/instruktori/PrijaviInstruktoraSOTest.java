package operacije.instruktori;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domen.Instruktor;
import java.util.stream.Stream;
import operacije.pomocni.InjekcijaBrokera;
import operacije.pomocni.PodaciZaTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PrijaviInstruktoraSOTest {

    @ParameterizedTest(name = "{1}")
    @MethodSource("neispravniParametri")
    void predusloviOdbijaNeispravanUnos(Object instruktor, String opis) throws Exception {
        PrijaviInstruktoraSO so = InjekcijaBrokera.saBrokerom(new PrijaviInstruktoraSO(), PodaciZaTest.prazanRepo());

        Exception ex = assertThrows(Exception.class, () -> InjekcijaBrokera.pokreniPreduslove(so, instruktor));
        assertTrue(ex.getMessage().contains("nisu ispravni"), opis);
    }

    static Stream<Arguments> neispravniParametri() {
        return Stream.of(
                Arguments.of(null, "null parametar"),
                Arguments.of("nije instruktor", "pogresan tip"));
    }

    @Test
    void izvrsiVracaInstruktoraZaIspravneKredencijale() throws Exception {
        PrijaviInstruktoraSO so = InjekcijaBrokera.saBrokerom(new PrijaviInstruktoraSO(), PodaciZaTest.repoSaInstruktorom());
        Instruktor prijava = new Instruktor();
        prijava.setKorisnickoIme("marko");
        prijava.setSifra("123");

        so.izvrsi(prijava, null);

        assertNotNull(so.getInstruktor());
        assertEquals("Marko", so.getInstruktor().getIme());
    }

    @Test
    void izvrsiBacaZaPogresneKredencijale() throws Exception {
        PrijaviInstruktoraSO so = InjekcijaBrokera.saBrokerom(new PrijaviInstruktoraSO(), PodaciZaTest.repoSaInstruktorom());
        Instruktor prijava = new Instruktor();
        prijava.setKorisnickoIme("marko");
        prijava.setSifra("pogresna");

        Exception ex = assertThrows(Exception.class, () -> so.izvrsi(prijava, null));
        assertTrue(ex.getMessage().contains("nisu ispravni"));
    }
}
