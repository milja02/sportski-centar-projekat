package operacije.polaznici;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domen.ClanskaKarta;
import domen.Polaznik;
import java.util.stream.Stream;
import operacije.integracija.SOTestoviHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ObrisiPolaznikaSOTest extends SOTestoviHelper {

    @ParameterizedTest
    @MethodSource("neispravniParametri")
    void izvrsiOdbijaNeispravanUnos(Object parametar, String opis) {
        Exception ex = assertThrows(Exception.class, () -> new ObrisiPolaznikaSO().izvrsi(parametar, null));
        assertTrue(ex.getMessage().contains("obriše") || ex.getMessage().contains("obrise"), opis);
    }

    static Stream<Arguments> neispravniParametri() {
        return Stream.of(
                Arguments.of(null, "null parametar"),
                Arguments.of("nije polaznik", "pogresan tip"));
    }

    @Test
    void izvrsiBrisePolaznikaIzBaze() throws Exception {
        Polaznik polaznik = unesiTestPolaznika("Brisanje", "Test");

        new ObrisiPolaznikaSO().izvrsi(polaznik, null);

        Polaznik kriterijum = new Polaznik();
        kriterijum.setIdPolaznik(polaznik.getIdPolaznik());
        assertThrows(Exception.class, () -> new NadjiPolaznikaSO().izvrsi(kriterijum, null));
    }

    @Test
    void izvrsiBacaKadPolaznikImaClanskuKartu() throws Exception {
        ClanskaKarta karta = unesiTestKartuZaCiscenje(2);
        Polaznik polaznik = karta.getPolaznik();

        assertThrows(Exception.class, () -> new ObrisiPolaznikaSO().izvrsi(polaznik, null));
    }
}
