package operacije.clanskekarte;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domen.ClanskaKarta;
import java.util.stream.Stream;
import operacije.integracija.SOTestoviHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ObrisiClanskuKartuSOTest extends SOTestoviHelper {

    @ParameterizedTest
    @MethodSource("neispravniParametri")
    void izvrsiOdbijaNeispravanUnos(Object parametar, String opis) {
        Exception ex = assertThrows(Exception.class, () -> new ObrisiClanskuKartuSO().izvrsi(parametar, null));
        assertTrue(ex.getMessage().contains("kartu") || ex.getMessage().contains("kart") || ex.getMessage().contains("ID"),
                opis);
    }

    static Stream<Arguments> neispravniParametri() {
        return Stream.of(
                Arguments.of(null, "null parametar"),
                Arguments.of("nije karta", "pogresan tip"),
                Arguments.of(new ClanskaKarta(), "nema ID"));
    }

    @Test
    void izvrsiBriseKartuIzBaze() throws Exception {
        ClanskaKarta karta = unesiTestKartu(2);

        new ObrisiClanskuKartuSO().izvrsi(karta, null);

        ClanskaKarta kriterijum = new ClanskaKarta();
        kriterijum.setIdClanskaKarta(karta.getIdClanskaKarta());
        assertThrows(Exception.class, () -> new NadjiClanskuKartuSO().izvrsi(kriterijum, null));
    }
}
