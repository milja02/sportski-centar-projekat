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

class ZapamtiPolaznikaSOTest extends SOTestoviHelper {

    @ParameterizedTest
    @MethodSource("neispravniPolaznici")
    void izvrsiOdbijaNeispravanUnos(Object polaznik, String opis) {
        Exception ex = assertThrows(Exception.class, () -> new ZapamtiPolaznikaSO().izvrsi(polaznik, null));
        assertTrue(ex.getMessage().contains("zapamti") && ex.getMessage().contains("polaznika"), opis);
    }

    static Stream<Arguments> neispravniPolaznici() {
        Polaznik bezMesta = new Polaznik();
        bezMesta.setIdPolaznik(1);
        bezMesta.setIme("Pera");
        bezMesta.setPrezime("Peric");
        bezMesta.setBrojTelefona("061");

        Polaznik bezId = new Polaznik();
        bezId.setIme("Pera");
        bezId.setPrezime("Peric");
        bezId.setBrojTelefona("061");

        return Stream.of(
                Arguments.of(null, "null parametar"),
                Arguments.of("nije polaznik", "pogresan tip"),
                Arguments.of(bezId, "nema ID"),
                Arguments.of(bezMesta, "nema mesto"));
    }

    @Test
    void izvrsiAzuriraPolaznikaUBazi() throws Exception {
        Polaznik polaznik = unesiTestPolaznikaZaCiscenje("Staro", "Ime");

        polaznik.setIme("Novo");
        polaznik.setPrezime("Prezime");
        new ZapamtiPolaznikaSO().izvrsi(polaznik, null);

        Polaznik sacuvan = nadjiPolaznikaPoId(polaznik.getIdPolaznik());
        assertEquals("Novo", sacuvan.getIme());
        assertEquals("Prezime", sacuvan.getPrezime());
    }
}
