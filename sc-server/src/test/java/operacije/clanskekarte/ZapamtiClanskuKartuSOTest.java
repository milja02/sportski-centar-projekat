package operacije.clanskekarte;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domen.ClanskaKarta;
import domen.StavkaClanskeKarte;
import java.util.stream.Stream;
import operacije.pomocni.InjekcijaBrokera;
import operacije.pomocni.PodaciZaTest;
import operacije.pomocni.PomocniRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ZapamtiClanskuKartuSOTest {

    @ParameterizedTest(name = "{1}")
    @MethodSource("neispravneKarte")
    void predusloviOdbijaNeispravanUnos(Object karta, String opis) throws Exception {
        ZapamtiClanskuKartuSO so = InjekcijaBrokera.saBrokerom(new ZapamtiClanskuKartuSO(), PodaciZaTest.repoSaSportovima());

        Exception ex = assertThrows(Exception.class, () -> InjekcijaBrokera.pokreniPreduslove(so, karta));
        assertTrue(ex.getMessage().contains("zapamti") && ex.getMessage().contains("kartu"), opis);
    }

    static Stream<Arguments> neispravneKarte() throws Exception {
        ClanskaKarta bezDatuma = new ClanskaKarta();
        bezDatuma.setIdClanskaKarta(1);
        bezDatuma.setInstruktor(PodaciZaTest.instruktor(1, "Mika", "Mikic", "mika", "pass"));
        bezDatuma.setPolaznik(PodaciZaTest.polaznik(1, "Pera", "Peric", "061"));

        ClanskaKarta bezInstruktora = new ClanskaKarta();
        bezInstruktora.setIdClanskaKarta(1);
        bezInstruktora.setDatumUclanjenja(PodaciZaTest.datum("2024-06-01"));
        bezInstruktora.setPolaznik(PodaciZaTest.polaznik(1, "Pera", "Peric", "061"));

        return Stream.of(
                Arguments.of(null, "null parametar"),
                Arguments.of(new ClanskaKarta(), "nema ID"),
                Arguments.of(bezDatuma, "nema datum"),
                Arguments.of(bezInstruktora, "nema instruktora"));
    }

    @Test
    void predusloviPrihvataIspravnuKartu() throws Exception {
        ZapamtiClanskuKartuSO so = InjekcijaBrokera.saBrokerom(new ZapamtiClanskuKartuSO(), PodaciZaTest.repoSaSportovima());
        assertDoesNotThrow(() -> InjekcijaBrokera.pokreniPreduslove(so, PodaciZaTest.kartaSaStavkom(3, 4000)));
    }

    @Test
    void izvrsiMenjaKartuIZamenjujeStavke() throws Exception {
        PomocniRepository repo = PodaciZaTest.repoSaSportovima();
        ClanskaKarta karta = PodaciZaTest.kartaSaStavkom(2, 4000);
        repo.dodaj(karta);
        repo.dodaj(karta.getStavke().get(0));

        karta.getStavke().clear();
        karta.getStavke().add(
                new StavkaClanskeKarte(karta, 1, 3, 12000, PodaciZaTest.sport(1, "Tenis", 4000)));
        karta.setUkupanIznos(12000);

        ZapamtiClanskuKartuSO so = InjekcijaBrokera.saBrokerom(new ZapamtiClanskuKartuSO(), repo);
        so.izvrsi(karta, null);

        assertEquals(12000, ((ClanskaKarta) repo.getAll(new ClanskaKarta(), null).get(0)).getUkupanIznos());
        assertEquals(1, repo.getAll(new StavkaClanskeKarte(), " WHERE sck.clanskakarta=1").size());
    }
}
