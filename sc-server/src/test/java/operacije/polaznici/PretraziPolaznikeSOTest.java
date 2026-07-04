package operacije.polaznici;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domen.Polaznik;
import java.util.stream.Stream;
import operacije.integracija.SOTestoviHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PretraziPolaznikeSOTest extends SOTestoviHelper {

    @ParameterizedTest(name = "{1}")
    @MethodSource("neispravniKriterijumi")
    void izvrsiOdbijaNeispravanUnos(Object parametar, String opis) {
        Exception ex = assertThrows(Exception.class, () -> new PretraziPolaznikeSO().izvrsi(parametar, null));
        assertTrue(ex.getMessage().contains("polaznike"), opis);
    }

    static Stream<Arguments> neispravniKriterijumi() {
        return Stream.of(
                Arguments.of(null, "null parametar"),
                Arguments.of("nije polaznik", "pogresan tip"));
    }

    @Test
    void izvrsiFiltriraPoImenu() throws Exception {
        Polaznik polaznik = unesiTestPolaznikaZaCiscenje("PretragaTest", "Jedan");

        PretraziPolaznikeSO so = new PretraziPolaznikeSO();
        Polaznik kriterijum = new Polaznik();
        kriterijum.setIme("PretragaT");
        so.izvrsi(kriterijum, null);

        assertEquals(1, so.getPolaznici().stream()
                .filter(p -> p.getIdPolaznik() == polaznik.getIdPolaznik())
                .count());
        assertEquals("PretragaTest", so.getPolaznici().stream()
                .filter(p -> p.getIdPolaznik() == polaznik.getIdPolaznik())
                .findFirst()
                .orElseThrow()
                .getIme());
    }
}
