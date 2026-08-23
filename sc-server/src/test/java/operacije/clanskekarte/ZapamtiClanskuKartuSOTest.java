package operacije.clanskekarte;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domen.ClanskaKarta;
import domen.Mesto;
import domen.Polaznik;
import domen.Sport;
import java.text.SimpleDateFormat;
import java.util.stream.Stream;
import operacije.integracija.SOTestoviHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ZapamtiClanskuKartuSOTest extends SOTestoviHelper {

    @ParameterizedTest
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
        Mesto mesto = new Mesto(1, "Beograd", 11000);
        Polaznik polaznik = new Polaznik(1, "Test", "Test", "0611111111", mesto);
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
        Sport sport = prviSport();
        int noviIznos = noviBrojTermina * sport.getCena();

        karta.getStavke().clear();
        karta.getStavke().add(napraviStavku(noviBrojTermina, noviIznos, sport));
        karta.setUkupanIznos(noviIznos);

        new ZapamtiClanskuKartuSO().izvrsi(karta, null);

        ClanskaKarta sacuvana = nadjiClanskuKartuPoId(karta.getIdClanskaKarta());
        assertEquals(noviIznos, sacuvana.getUkupanIznos());
        assertEquals(1, sacuvana.getStavke().size());
        assertEquals(noviBrojTermina, sacuvana.getStavke().get(0).getBrojTermina());
    }

    @Test
    void izvrsiOdbijaKadJeBrojTerminaNeispravan() throws Exception {
        ClanskaKarta karta = unesiTestKartuZaCiscenje(2);
        postaviPolje(karta.getStavke().get(0), "brojTermina", -1);
        postaviPolje(karta.getStavke().get(0), "iznosStavke", -1);
        postaviPolje(karta, "ukupanIznos", -1);

        Exception ex = assertThrows(Exception.class, () -> new ZapamtiClanskuKartuSO().izvrsi(karta, null));
        assertTrue(ex.getMessage().contains("broj termina"));
    }

    @Test
    void izvrsiOdbijaKadUkupanIznosNijeZbirStavki() throws Exception {
        ClanskaKarta karta = unesiTestKartuZaCiscenje(2);
        postaviPolje(karta, "ukupanIznos", karta.getUkupanIznos() + 50);

        Exception ex = assertThrows(Exception.class, () -> new ZapamtiClanskuKartuSO().izvrsi(karta, null));
        assertTrue(ex.getMessage().contains("ukupan iznos"));
    }
}
