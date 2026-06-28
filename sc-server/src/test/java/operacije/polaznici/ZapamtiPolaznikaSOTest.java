package operacije.polaznici;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domen.Mesto;
import domen.Polaznik;
import java.util.stream.Stream;
import operacije.pomocni.InjekcijaBrokera;
import operacije.pomocni.PodaciZaTest;
import operacije.pomocni.PomocniRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ZapamtiPolaznikaSOTest {

    @ParameterizedTest(name = "{1}")
    @MethodSource("neispravniPolaznici")
    void predusloviOdbijaNeispravanUnos(Object polaznik, String opis) throws Exception {
        ZapamtiPolaznikaSO so = InjekcijaBrokera.saBrokerom(new ZapamtiPolaznikaSO(), PodaciZaTest.prazanRepo());

        Exception ex = assertThrows(Exception.class, () -> InjekcijaBrokera.pokreniPreduslove(so, polaznik));
        assertTrue(ex.getMessage().contains("zapamti") && ex.getMessage().contains("polaznika"), opis);
    }

    static Stream<Arguments> neispravniPolaznici() {
        Mesto mesto = PodaciZaTest.beograd();
        return Stream.of(
                Arguments.of(null, "null parametar"),
                Arguments.of("nije polaznik", "pogresan tip"),
                Arguments.of(new Polaznik(), "nema ID"),
                Arguments.of(new Polaznik(0, "Pera", "Peric", "061", mesto), "ID nula"),
                Arguments.of(new Polaznik(1, "", "Peric", "061", mesto), "prazno ime"),
                Arguments.of(new Polaznik(1, "Pera", "", "061", mesto), "prazno prezime"),
                Arguments.of(new Polaznik(1, "Pera", "Peric", "", mesto), "prazan telefon"),
                Arguments.of(new Polaznik(1, "Pera", "Peric", "061", null), "nema mesto"));
    }

    @Test
    void predusloviPrihvataIspravnogPolaznika() throws Exception {
        ZapamtiPolaznikaSO so = InjekcijaBrokera.saBrokerom(new ZapamtiPolaznikaSO(), PodaciZaTest.prazanRepo());
        assertDoesNotThrow(() -> InjekcijaBrokera.pokreniPreduslove(so, PodaciZaTest.polaznik(5, "Ana", "Anic", "063")));
    }

    @Test
    void izvrsiAzuriraPolaznikaUBazi() throws Exception {
        PomocniRepository repo = new PomocniRepository();
        Polaznik polaznik = PodaciZaTest.polaznik(1, "Staro", "Ime", "060");
        repo.dodaj(polaznik);

        ZapamtiPolaznikaSO so = InjekcijaBrokera.saBrokerom(new ZapamtiPolaznikaSO(), repo);
        polaznik.setIme("Novo");
        polaznik.setPrezime("Prezime");
        so.izvrsi(polaznik, null);

        Polaznik sacuvan = (Polaznik) repo.getAll(new Polaznik(), null).get(0);
        assertEquals("Novo", sacuvan.getIme());
        assertEquals("Prezime", sacuvan.getPrezime());
    }
}
