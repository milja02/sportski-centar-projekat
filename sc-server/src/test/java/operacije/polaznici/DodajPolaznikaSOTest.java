package operacije.polaznici;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domen.Mesto;
import domen.Polaznik;
import java.util.stream.Stream;
import operacije.integracija.IntegracioniTestOsnova;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DodajPolaznikaSOTest extends IntegracioniTestOsnova {

    @ParameterizedTest(name = "{1}")
    @MethodSource("neispravniParametri")
    void izvrsiOdbijaNeispravanUnos(Object polaznik, String opis) {
        DodajPolaznikaSO so = new DodajPolaznikaSO();

        Exception ex = assertThrows(Exception.class, () -> so.izvrsi(polaznik, null));
        assertTrue(ex.getMessage().contains("kreira") && ex.getMessage().contains("polaznika"), opis);
    }

    static Stream<Arguments> neispravniParametri() {
        return Stream.of(
                Arguments.of(null, "null parametar"),
                Arguments.of("nije polaznik", "pogresan tip"));
    }

    @Test
    void izvrsiDodajePolaznikaUBazu() throws Exception {
        Mesto mesto = prvoMesto();
        String telefon = jedinstvenTelefon();

        Polaznik polaznik = new Polaznik();
        polaznik.setIme("Jovana");
        polaznik.setPrezime("Jovic");
        polaznik.setBrojTelefona(telefon);
        polaznik.setMesto(mesto);

        new DodajPolaznikaSO().izvrsi(polaznik, null);

        Polaznik sacuvan = nadjiPolaznikaPoTelefonu(telefon);
        registrujPolaznikaZaBrisanje(sacuvan);

        assertEquals("Jovana", sacuvan.getIme());
        assertEquals("Jovic", sacuvan.getPrezime());
        assertEquals(telefon, sacuvan.getBrojTelefona());
        assertEquals(mesto.getNaziv(), sacuvan.getMesto().getNaziv());
    }
}
