package operacije.clanskekarte;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domen.ClanskaKarta;
import domen.Sport;
import domen.StavkaClanskeKarte;
import java.util.List;
import operacije.pomocni.PodaciZaTest;
import operacije.pomocni.PomocniRepository;
import org.junit.jupiter.api.Test;

class VrednosnaOgranicenjaClanskeKarteTest {

    @Test
    void proveriPrihvataIspravnuKartu() throws Exception {
        PomocniRepository repo = PodaciZaTest.repoSaSportovima();
        ClanskaKarta karta = PodaciZaTest.kartaSaStavkom(5, 4000);

        assertDoesNotThrow(() -> VrednosnaOgranicenjaClanskeKarte.proveri(karta, repo));
    }

    @Test
    void proveriOdbijaSportKojiNePostojiUBazi() throws Exception {
        PomocniRepository repo = PodaciZaTest.repoSaSportovima();
        ClanskaKarta karta = new ClanskaKarta();
        karta.setIdClanskaKarta(1);
        karta.setDatumUclanjenja(PodaciZaTest.datum("2024-06-01"));
        karta.setInstruktor(PodaciZaTest.instruktor(1, "Mika", "Mikic", "mika", "pass"));
        karta.setPolaznik(PodaciZaTest.polaznik(1, "Pera", "Peric", "061"));
        Sport skijanje = new Sport(99, "Skijanje", 5000);
        StavkaClanskeKarte stavka = new StavkaClanskeKarte(karta, 1, 5, 25000, skijanje);
        karta.setStavke(List.of(stavka));
        karta.setUkupanIznos(25000);

        Exception ex = assertThrows(Exception.class, () -> VrednosnaOgranicenjaClanskeKarte.proveri(karta, repo));
        assertTrue(ex.getMessage().contains("sport ne postoji"));
    }

    @Test
    void proveriSabiraViseStavki() throws Exception {
        PomocniRepository repo = PodaciZaTest.repoSaSportovima();
        ClanskaKarta karta = PodaciZaTest.kartaSaStavkom(2, 4000);
        StavkaClanskeKarte druga = new StavkaClanskeKarte(
                karta, 2, 3, 9000, PodaciZaTest.sport(2, "Plivanje", 3000));
        karta.getStavke().add(druga);
        karta.setUkupanIznos(8000 + 9000);

        assertDoesNotThrow(() -> VrednosnaOgranicenjaClanskeKarte.proveri(karta, repo));
        assertEquals(17000, karta.getUkupanIznos());
    }
}
