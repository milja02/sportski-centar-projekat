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

class PretraziClanskeKarteSOTest extends SOTestoviHelper {

    @ParameterizedTest
    @MethodSource("neispravniKriterijumi")
    void izvrsiOdbijaNeispravanUnos(Object parametar, String opis) {
        Exception ex = assertThrows(Exception.class, () -> new PretraziClanskeKarteSO().izvrsi(parametar, null));
        assertTrue(ex.getMessage().contains("karte"), opis);
    }

    static Stream<Arguments> neispravniKriterijumi() {
        return Stream.of(
                Arguments.of(null, "null parametar"),
                Arguments.of("nije karta", "pogresan tip"));
    }

    @Test
    void izvrsiFiltriraPoPolazniku() throws Exception {
        ClanskaKarta karta = unesiTestKartuZaCiscenje(2);

        ClanskaKarta kriterijum = new ClanskaKarta();
        kriterijum.setPolaznik(karta.getPolaznik());

        PretraziClanskeKarteSO so = new PretraziClanskeKarteSO();
        so.izvrsi(kriterijum, null);

        assertTrue(so.getClanskeKarte().stream()
                .anyMatch(k -> k.getIdClanskaKarta() == karta.getIdClanskaKarta()));
    }
}
