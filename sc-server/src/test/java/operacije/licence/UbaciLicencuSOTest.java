package operacije.licence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domen.Instruktor;
import domen.InstruktorLicenca;
import domen.Licenca;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;
import operacije.integracija.SOTestoviHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class UbaciLicencuSOTest extends SOTestoviHelper {

    @ParameterizedTest(name = "{1}")
    @MethodSource("neispravniParametri")
    void izvrsiOdbijaNeispravanUnos(Object dodela, String opis) throws Exception {
        Exception ex = assertThrows(Exception.class, () -> new UbaciLicencuSO().izvrsi(dodela, null));
        assertTrue(ex.getMessage().contains("licencu") || ex.getMessage().contains("isteka"), opis);
    }

    static Stream<Arguments> neispravniParametri() throws Exception {
        Instruktor instruktor = new Instruktor();
        instruktor.setIdInstruktor(1);
        Licenca licenca = new Licenca();
        licenca.setIdLicenca(1);

        InstruktorLicenca bezIzdavanja = new InstruktorLicenca(instruktor, licenca, null, null);

        Instruktor losInstruktor = new Instruktor();
        losInstruktor.setIdInstruktor(0);
        InstruktorLicenca losIdInstruktora = new InstruktorLicenca(
                losInstruktor, licenca, parsirajDatum("2024-01-01"), null);

        InstruktorLicenca losDatum = new InstruktorLicenca(
                instruktor, licenca, parsirajDatum("2024-06-01"), parsirajDatum("2024-01-01"));

        return Stream.of(
                Arguments.of(null, "null parametar"),
                Arguments.of("nije dodela", "pogresan tip"),
                Arguments.of(bezIzdavanja, "nema datum izdavanja"),
                Arguments.of(losIdInstruktora, "neispravan instruktor"),
                Arguments.of(losDatum, "datum isteka pre izdavanja"));
    }

    private static Date parsirajDatum(String vrednost) throws Exception {
        return new SimpleDateFormat("yyyy-MM-dd").parse(vrednost);
    }

    @Test
    void izvrsiUbacujeDodeluLicence() throws Exception {
        Instruktor instruktor = prviInstruktor();
        Licenca licenca = prvaLicenca();

        InstruktorLicenca dodela = new InstruktorLicenca(
                instruktor, licenca, datum("2024-03-01"), datum("2027-03-01"));

        assertDoesNotThrow(() -> new UbaciLicencuSO().izvrsi(dodela, null));
        registrujLicencuZaBrisanje(dodela);
    }
}
