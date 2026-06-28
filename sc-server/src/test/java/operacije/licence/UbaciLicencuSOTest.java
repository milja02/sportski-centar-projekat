package operacije.licence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domen.Instruktor;
import domen.InstruktorLicenca;
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
        InstruktorLicenca bezIzdavanja = PodaciZaTest.instruktorLicenca(null, null);
        bezIzdavanja.setDatumIzdavanja(null);

        InstruktorLicenca losInstruktor = PodaciZaTest.instruktorLicenca(
                PodaciZaTest.datum("2024-01-01"), PodaciZaTest.datum("2026-01-01"));
        losInstruktor.setInstruktor(new Instruktor());

        InstruktorLicenca losDatum = PodaciZaTest.instruktorLicenca(
                PodaciZaTest.datum("2024-06-01"), PodaciZaTest.datum("2024-01-01"));

        InstruktorLicenca bezLicence = PodaciZaTest.instruktorLicenca(
                PodaciZaTest.datum("2024-01-01"), null);
        bezLicence.setLicenca(null);

        return Stream.of(
                Arguments.of(null, "null parametar", "zapamti licencu"),
                Arguments.of("nije licenca", "pogresan tip", "zapamti licencu"),
                Arguments.of(bezLicence, "nema licencu", "zapamti licencu"),
                Arguments.of(bezIzdavanja, "nema datum izdavanja", "zapamti licencu"),
                Arguments.of(losInstruktor, "los instruktor", "neispravan instruktor"),
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
