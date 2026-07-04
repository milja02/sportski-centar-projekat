package operacije.clanskekarte;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domen.ClanskaKarta;
import domen.Polaznik;
import domen.StavkaClanskeKarte;
import java.text.SimpleDateFormat;
import java.util.stream.Stream;
import operacije.integracija.SOTestoviHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ZapamtiClanskuKartuSOTest extends SOTestoviHelper {

    @ParameterizedTest(name = "{1}")
    @MethodSource("neispravneKarte")
    void izvrsiOdbijaNeispravanUnos(Object karta, String opis) {
        Exception ex = assertThrows(Exception.class, () -> new ZapamtiClanskuKartuSO().izvrsi(karta, null));
        assertTrue(ex.getMessage().contains("zapamti") && ex.getMessage().contains("kartu"), opis);
    }

    static Stream<Arguments> neispravneKarte() throws Exception {
        ClanskaKarta bezDatuma = new ClanskaKarta();
        bezDatuma.setIdClanskaKarta(1);

        ClanskaKarta bezInstruktora = new ClanskaKarta();
        bezInstruktora.setIdClanskaKarta(1);
        bezInstruktora.setDatumUclanjenja(new SimpleDateFormat("yyyy-MM-dd").parse("2024-06-01"));
        Polaznik polaznik = new Polaznik();
        polaznik.setIdPolaznik(1);
        bezInstruktora.setPolaznik(polaznik);

        return Stream.of(
                Arguments.of(null, "null parametar"),
                Arguments.of(new ClanskaKarta(), "nema ID"),
                Arguments.of(bezDatuma, "nema datum"),
                Arguments.of(bezInstruktora, "nema instruktora"));
    }

    @Test
    void izvrsiMenjaKartuIZamenjujeStavke() throws Exception {
        ClanskaKarta karta = unesiTestKartuZaCiscenje(2);
        int noviBrojTermina = 5;
        int noviIznos = noviBrojTermina * prviSport().getCena();

        karta.getStavke().clear();
        karta.getStavke().add(new StavkaClanskeKarte(
                karta, 1, noviBrojTermina, noviIznos, prviSport()));
        karta.setUkupanIznos(noviIznos);

        new ZapamtiClanskuKartuSO().izvrsi(karta, null);

        ClanskaKarta sacuvana = nadjiClanskuKartuPoId(karta.getIdClanskaKarta());
        assertEquals(noviIznos, sacuvana.getUkupanIznos());
        assertEquals(1, sacuvana.getStavke().size());
        assertEquals(noviBrojTermina, sacuvana.getStavke().get(0).getBrojTermina());
    }
}
