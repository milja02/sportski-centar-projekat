package operacije.polaznici;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domen.Polaznik;
import java.util.stream.Stream;
import operacije.integracija.SOTestoviHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class NadjiPolaznikaSOTest extends SOTestoviHelper {

    @ParameterizedTest(name = "{1}")
    @MethodSource("neispravniPolaznici")
    void izvrsiOdbijaNeispravanUnos(Object polaznik, String opis) {
        Exception ex = assertThrows(Exception.class, () -> new NadjiPolaznikaSO().izvrsi(polaznik, null));
        assertTrue(ex.getMessage().contains("polaznika"), opis);
    }

    static Stream<Arguments> neispravniPolaznici() {
        Polaznik bezId = new Polaznik();

        return Stream.of(
                Arguments.of(null, "null parametar"),
                Arguments.of(bezId, "nema ID"));
    }

    @Test
    void izvrsiVracaPolaznikaPoId() throws Exception {
        Polaznik unet = unesiTestPolaznikaZaCiscenje("Sara", "Saric");

        NadjiPolaznikaSO so = new NadjiPolaznikaSO();
        Polaznik kriterijum = new Polaznik();
        kriterijum.setIdPolaznik(unet.getIdPolaznik());
        so.izvrsi(kriterijum, null);

        assertNotNull(so.getPolaznik());
        assertEquals("Sara", so.getPolaznik().getIme());
        assertEquals("Saric", so.getPolaznik().getPrezime());
    }

    @Test
    void izvrsiBacaAkoPolaznikNePostoji() {
        Polaznik kriterijum = new Polaznik();
        kriterijum.setIdPolaznik(9_999_999);

        Exception ex = assertThrows(Exception.class, () -> new NadjiPolaznikaSO().izvrsi(kriterijum, null));
        assertTrue(ex.getMessage().contains("polaznika"));
    }
}
