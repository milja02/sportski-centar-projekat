package operacije.clanskekarte;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domen.ClanskaKarta;
import java.util.stream.Stream;
import operacije.integracija.SOTestoviHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class NadjiClanskuKartuSOTest extends SOTestoviHelper {

    @ParameterizedTest
    @MethodSource("neispravneKarte")
    void izvrsiOdbijaNeispravanUnos(Object karta, String opis) {
        Exception ex = assertThrows(Exception.class, () -> new NadjiClanskuKartuSO().izvrsi(karta, null));
        assertTrue(ex.getMessage().contains("kartu") || ex.getMessage().contains("kart"), opis);
    }

    static Stream<Arguments> neispravneKarte() {
        return Stream.of(
                Arguments.of(null, "null parametar"),
                Arguments.of(new ClanskaKarta(), "nema ID"));
    }

    @Test
    void izvrsiVracaKartuPoId() throws Exception {
        ClanskaKarta uneta = unesiTestKartuZaCiscenje(2);

        NadjiClanskuKartuSO so = new NadjiClanskuKartuSO();
        ClanskaKarta kriterijum = new ClanskaKarta();
        kriterijum.setIdClanskaKarta(uneta.getIdClanskaKarta());
        so.izvrsi(kriterijum, null);

        assertNotNull(so.getClanskaKarta());
        assertEquals(uneta.getUkupanIznos(), so.getClanskaKarta().getUkupanIznos());
        assertEquals(1, so.getClanskaKarta().getStavke().size());
    }

    @Test
    void izvrsiBacaAkoKartaNePostoji() {
        ClanskaKarta kriterijum = new ClanskaKarta();
        kriterijum.setIdClanskaKarta(9_999_999);

        Exception ex = assertThrows(Exception.class, () -> new NadjiClanskuKartuSO().izvrsi(kriterijum, null));
        assertTrue(ex.getMessage().contains("kartu") || ex.getMessage().contains("kart"));
    }
}
