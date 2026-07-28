package operacije.instruktori;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domen.Instruktor;
import java.util.stream.Stream;
import operacije.integracija.SOTestoviHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PrijaviInstruktoraSOTest extends SOTestoviHelper {

    @ParameterizedTest
    @MethodSource("neispravniParametri")
    void izvrsiOdbijaNeispravanUnos(Object instruktor, String opis) {
        Exception ex = assertThrows(Exception.class, () -> new PrijaviInstruktoraSO().izvrsi(instruktor, null));
        assertTrue(ex.getMessage().contains("nisu ispravni"), opis);
    }

    static Stream<Arguments> neispravniParametri() {
        return Stream.of(
                Arguments.of(null, "null parametar"),
                Arguments.of("nije instruktor", "pogresan tip"));
    }

    @Test
    void izvrsiVracaInstruktoraZaIspravneKredencijale() throws Exception {
        Instruktor izBaze = prviInstruktor();

        Instruktor prijava = new Instruktor();
        prijava.setKorisnickoIme(izBaze.getKorisnickoIme());
        prijava.setSifra(izBaze.getSifra());

        PrijaviInstruktoraSO so = new PrijaviInstruktoraSO();
        so.izvrsi(prijava, null);

        assertNotNull(so.getInstruktor());
        assertEquals(izBaze.getIme(), so.getInstruktor().getIme());
        assertEquals(izBaze.getIdInstruktor(), so.getInstruktor().getIdInstruktor());
    }

    @Test
    void izvrsiBacaZaPogresneKredencijale() throws Exception {
        Instruktor izBaze = prviInstruktor();

        Instruktor prijava = new Instruktor();
        prijava.setKorisnickoIme(izBaze.getKorisnickoIme());
        prijava.setSifra("pogresna-sifra");

        Exception ex = assertThrows(Exception.class, () -> new PrijaviInstruktoraSO().izvrsi(prijava, null));
        assertTrue(ex.getMessage().contains("nisu ispravni"));
    }
}
