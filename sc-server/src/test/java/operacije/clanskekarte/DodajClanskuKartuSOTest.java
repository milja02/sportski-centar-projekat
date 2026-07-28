package operacije.clanskekarte;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domen.ClanskaKarta;
import domen.Instruktor;
import domen.Polaznik;
import domen.Sport;
import domen.StavkaClanskeKarte;
import java.util.stream.Stream;
import operacije.integracija.SOTestoviHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class DodajClanskuKartuSOTest extends SOTestoviHelper {

    @ParameterizedTest
    @MethodSource("neispravneKarte")
    void izvrsiOdbijaNeispravanUnos(Object karta, String opis) {
        Exception ex = assertThrows(Exception.class, () -> new DodajClanskuKartuSO().izvrsi(karta, null));
        assertTrue(ex.getMessage().contains("kartu") || ex.getMessage().contains("kart"), opis);
    }

    static Stream<Arguments> neispravneKarte() {
        ClanskaKarta bezDatuma = new ClanskaKarta();

        return Stream.of(
                Arguments.of(null, "null parametar"),
                Arguments.of("nije karta", "pogresan tip"),
                Arguments.of(bezDatuma, "nema datum"));
    }

    @Test
    void izvrsiOdbijaSportKojiNePostojiUBazi() throws Exception {
        Polaznik polaznik = unesiTestPolaznikaZaCiscenje("Sport", "Test");
        Instruktor instruktor = prviInstruktor();
        Sport nepostojeci = new Sport(99_999, "Skijanje", 5000);

        ClanskaKarta karta = napraviValidnuKartu(polaznik, instruktor, nepostojeci, 3);

        Exception ex = assertThrows(Exception.class, () -> new DodajClanskuKartuSO().izvrsi(karta, null));
        assertTrue(ex.getMessage().contains("sport ne postoji"));
    }

    @Test
    void izvrsiDodajeKartuUBazu() throws Exception {
        Polaznik polaznik = unesiTestPolaznikaZaCiscenje("Karta", "Dodaj");
        Sport sport = prviSport();
        int brojTermina = 3;

        ClanskaKarta karta = napraviValidnuKartu(polaznik, prviInstruktor(), sport, brojTermina);
        new DodajClanskuKartuSO().izvrsi(karta, null);
        registrujKartuZaBrisanje(karta);

        ClanskaKarta sacuvana = nadjiClanskuKartuPoId(karta.getIdClanskaKarta());
        assertEquals(brojTermina * sport.getCena(), sacuvana.getUkupanIznos());
        assertEquals(1, sacuvana.getStavke().size());
        assertEquals(brojTermina, sacuvana.getStavke().get(0).getBrojTermina());
    }
}
