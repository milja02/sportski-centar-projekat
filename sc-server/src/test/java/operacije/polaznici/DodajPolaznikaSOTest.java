package operacije.polaznici;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domen.Mesto;
import domen.Polaznik;
import java.util.stream.Stream;
import operacije.integracija.SOTestoviHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DodajPolaznikaSOTest extends SOTestoviHelper {

    @ParameterizedTest
    @MethodSource("neispravniParametri")
    void izvrsiOdbijaNeispravanUnos(Object polaznik, String opis) {
        DodajPolaznikaSO so = new DodajPolaznikaSO();

        Exception ex = assertThrows(Exception.class, () -> so.izvrsi(polaznik, null));
        assertTrue(ex.getMessage().contains("kreira") && ex.getMessage().contains("polaznika"), opis);
    }

    static Stream<Arguments> neispravniParametri() {
        Polaznik bezMesta = new Polaznik();
        bezMesta.setIme("Jovana");
        bezMesta.setPrezime("Jovic");
        bezMesta.setBrojTelefona("069000111");

        Polaznik bezImena = new Polaznik();
        bezImena.setPrezime("Jovic");
        bezImena.setBrojTelefona("069000111");
        bezImena.setMesto(new Mesto(1, "Beograd", 11000));

        Polaznik bezPrezimena = new Polaznik();
        bezPrezimena.setIme("Jovana");
        bezPrezimena.setBrojTelefona("069000111");
        bezPrezimena.setMesto(new Mesto(1, "Beograd", 11000));

        Polaznik bezTelefona = new Polaznik();
        bezTelefona.setIme("Jovana");
        bezTelefona.setPrezime("Jovic");
        bezTelefona.setMesto(new Mesto(1, "Beograd", 11000));

        return Stream.of(
                Arguments.of(null, "null parametar"),
                Arguments.of("nije polaznik", "pogresan tip"),
                Arguments.of(bezMesta, "nema mesto"),
                Arguments.of(bezImena, "nema ime"),
                Arguments.of(bezPrezimena, "nema prezime"),
                Arguments.of(bezTelefona, "nema telefon"));
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
