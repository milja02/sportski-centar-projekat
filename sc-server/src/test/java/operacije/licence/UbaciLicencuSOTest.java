package operacije.licence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domen.InstruktorLicenca;
import domen.Licenca;
import java.util.stream.Stream;
import operacije.pomocni.InjekcijaBrokera;
import operacije.pomocni.PodaciZaTest;
import operacije.pomocni.PomocniRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class UbaciLicencuSOTest {

    @ParameterizedTest(name = "{1}")
    @MethodSource("neispravneLicence")
    void predusloviOdbijaNeispravanUnos(Object licenca, String opis, String deoPoruke) throws Exception {
        UbaciLicencuSO so = InjekcijaBrokera.saBrokerom(new UbaciLicencuSO(), PodaciZaTest.prazanRepo());

        Exception ex = assertThrows(Exception.class, () -> InjekcijaBrokera.pokreniPreduslove(so, licenca));
        assertTrue(ex.getMessage().contains(deoPoruke), opis);
    }

    static Stream<Arguments> neispravneLicence() throws Exception {
        InstruktorLicenca bezIzdavanja = new InstruktorLicenca();
        bezIzdavanja.setInstruktor(PodaciZaTest.instruktor(1, "Marko", "Markovic", "marko", "123"));
        bezIzdavanja.setLicenca(new Licenca(2, "FITNESS", "Nivo 1"));

        InstruktorLicenca losInstruktor = new InstruktorLicenca();
        losInstruktor.setInstruktor(PodaciZaTest.instruktor(0, "Marko", "Markovic", "marko", "123"));
        losInstruktor.setLicenca(new Licenca(2, "FITNESS", "Nivo 1"));
        losInstruktor.setDatumIzdavanja(PodaciZaTest.datum("2024-01-01"));

        InstruktorLicenca losDatum = new InstruktorLicenca();
        losDatum.setInstruktor(PodaciZaTest.instruktor(1, "Marko", "Markovic", "marko", "123"));
        losDatum.setLicenca(new Licenca(2, "FITNESS", "Nivo 1"));
        losDatum.setDatumIzdavanja(PodaciZaTest.datum("2024-06-01"));
        losDatum.setDatumIsteka(PodaciZaTest.datum("2024-01-01"));

        InstruktorLicenca bezLicence = new InstruktorLicenca();
        bezLicence.setInstruktor(PodaciZaTest.instruktor(1, "Marko", "Markovic", "marko", "123"));
        bezLicence.setDatumIzdavanja(PodaciZaTest.datum("2024-01-01"));

        return Stream.of(
                Arguments.of(null, "null parametar", "zapamti licencu"),
                Arguments.of("nije licenca", "pogresan tip", "zapamti licencu"),
                Arguments.of(bezLicence, "nema licencu", "zapamti licencu"),
                Arguments.of(bezIzdavanja, "nema datum izdavanja", "zapamti licencu"),
                Arguments.of(losInstruktor, "instruktor bez ID", "neispravan instruktor"),
                Arguments.of(losDatum, "datum isteka pre izdavanja", "Datum isteka"));
    }

    @Test
    void predusloviPrihvataIspravnuLicencu() throws Exception {
        UbaciLicencuSO so = InjekcijaBrokera.saBrokerom(new UbaciLicencuSO(), PodaciZaTest.prazanRepo());
        InstruktorLicenca licenca = PodaciZaTest.instruktorLicenca(
                PodaciZaTest.datum("2024-01-01"), PodaciZaTest.datum("2027-01-01"));

        assertDoesNotThrow(() -> InjekcijaBrokera.pokreniPreduslove(so, licenca));
    }

    @Test
    void izvrsiDodajeLicencuUBazu() throws Exception {
        PomocniRepository repo = new PomocniRepository();
        InstruktorLicenca licenca = PodaciZaTest.instruktorLicenca(
                PodaciZaTest.datum("2024-03-01"), null);

        UbaciLicencuSO so = InjekcijaBrokera.saBrokerom(new UbaciLicencuSO(), repo);
        so.izvrsi(licenca, null);

        assertEquals(1, repo.getAll(new InstruktorLicenca(), null).size());
    }
}
