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
    @MethodSource("neispravniInstruktori")
    void predusloviOdbijaNeispravanUnos(Object instruktor, String opis) throws Exception {
        PrijaviInstruktoraSO so = InjekcijaBrokera.saBrokerom(new PrijaviInstruktoraSO(), PodaciZaTest.prazanRepo());

        Exception ex = assertThrows(Exception.class, () -> InjekcijaBrokera.pokreniPreduslove(so, instruktor));
        assertTrue(ex.getMessage().contains("nisu ispravni"), opis);
    }

    static Stream<Arguments> neispravniInstruktori() {
        Instruktor bezSifre = PodaciZaTest.instruktor(1, "Marko", "Markovic", "marko", "123");
        bezSifre.setSifra("");
        Instruktor bezKorisnickog = PodaciZaTest.instruktor(1, "Marko", "Markovic", "marko", "123");
        bezKorisnickog.setKorisnickoIme("");

        return Stream.of(
                Arguments.of(null, "null parametar"),
                Arguments.of("nije instruktor", "pogresan tip"),
                Arguments.of(bezKorisnickog, "prazno korisnicko ime"),
                Arguments.of(bezSifre, "prazna sifra"));
    }

    @Test
    void izvrsiVracaInstruktoraZaIspravneKredencijale() throws Exception {
        PrijaviInstruktoraSO so = InjekcijaBrokera.saBrokerom(new PrijaviInstruktoraSO(), PodaciZaTest.repoSaInstruktorom());
        Instruktor prijava = PodaciZaTest.instruktor(0, "", "", "marko", "123");

        so.izvrsi(prijava, null);

        assertNotNull(so.getInstruktor());
        assertEquals("Marko", so.getInstruktor().getIme());
    }

    @Test
    void izvrsiBacaZaPogresneKredencijale() throws Exception {
        PrijaviInstruktoraSO so = InjekcijaBrokera.saBrokerom(new PrijaviInstruktoraSO(), PodaciZaTest.repoSaInstruktorom());
        Instruktor prijava = PodaciZaTest.instruktor(0, "", "", "marko", "pogresna");

        Exception ex = assertThrows(Exception.class, () -> so.izvrsi(prijava, null));
        assertTrue(ex.getMessage().contains("nisu ispravni"));
    }
}
