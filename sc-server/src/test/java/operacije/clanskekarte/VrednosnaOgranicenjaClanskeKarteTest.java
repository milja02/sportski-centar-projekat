package operacije.clanskekarte;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domen.ClanskaKarta;
import domen.Sport;
import domen.StavkaClanskeKarte;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import operacije.pomocni.PodaciZaTest;
import operacije.pomocni.PomocniRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class VrednosnaOgranicenjaClanskeKarteTest {

    @Test
    void proveriPrihvataIspravnuKartu() throws Exception {
        PomocniRepository repo = PodaciZaTest.repoSaSportovima();
        ClanskaKarta karta = PodaciZaTest.kartaSaStavkom(5, 4000);

        assertDoesNotThrow(() -> VrednosnaOgranicenjaClanskeKarte.proveri(karta, repo));
    }

    @Test
    void proveriPrihvataPraznuListuStavki() throws Exception {
        ClanskaKarta karta = new ClanskaKarta();
        karta.setUkupanIznos(0);
        karta.setStavke(new ArrayList<>());

        assertDoesNotThrow(() -> VrednosnaOgranicenjaClanskeKarte.proveri(karta, PodaciZaTest.prazanRepo()));
    }

    @ParameterizedTest(name = "{1}")
    @MethodSource("neispravneKarte")
    void proveriOdbijaNeispravnuKartu(ClanskaKarta karta, String opis, String deoPoruke) throws Exception {
        PomocniRepository repo = PodaciZaTest.repoSaSportovima();

        Exception ex = assertThrows(Exception.class, () -> VrednosnaOgranicenjaClanskeKarte.proveri(karta, repo));
        assertTrue(ex.getMessage().contains(deoPoruke), opis);
    }

    static Stream<Arguments> neispravneKarte() throws Exception {
        ClanskaKarta losIznosStavke = PodaciZaTest.kartaSaStavkom(5, 4000);
        losIznosStavke.getStavke().get(0).setIznosStavke(1000);

        ClanskaKarta losBrojTermina = PodaciZaTest.kartaSaStavkom(0, 4000);

        ClanskaKarta losUkupanIznos = PodaciZaTest.kartaSaStavkom(5, 4000);
        losUkupanIznos.setUkupanIznos(1);

        ClanskaKarta nepostojeciSport = PodaciZaTest.kartaSaStavkom(5, 4000);
        nepostojeciSport.getStavke().get(0).setSport(new Sport(99, "Skijanje", 5000));
        nepostojeciSport.getStavke().get(0).setIznosStavke(5 * 5000);
        nepostojeciSport.setUkupanIznos(5 * 5000);

        ClanskaKarta bezSporta = PodaciZaTest.kartaSaStavkom(5, 4000);
        bezSporta.getStavke().get(0).setSport(null);

        return Stream.of(
                Arguments.of(losIznosStavke, "pogresan iznos stavke", "iznos stavke"),
                Arguments.of(losBrojTermina, "broj termina nula", "broj termina"),
                Arguments.of(losUkupanIznos, "pogresan ukupan iznos", "ukupan iznos"),
                Arguments.of(nepostojeciSport, "sport ne postoji", "sport ne postoji"),
                Arguments.of(bezSporta, "nedostaje sport", "neispravan sport"));
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
