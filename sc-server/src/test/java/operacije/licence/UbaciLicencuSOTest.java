package operacije.licence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domen.Instruktor;
import domen.InstruktorLicenca;
import domen.Licenca;
import java.text.SimpleDateFormat;
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
        Instruktor instruktor = new Instruktor(1, "Test", "Test", "testuser", "pass");
        Licenca licenca = new Licenca(1, "TIP", "Nivo");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        InstruktorLicenca bezIzdavanja = new InstruktorLicenca();
        bezIzdavanja.setInstruktor(instruktor);
        bezIzdavanja.setLicenca(licenca);

        Instruktor losInstruktor = new Instruktor();
        losInstruktor.setIme("Marko");
        losInstruktor.setPrezime("Markovic");
        losInstruktor.setKorisnickoIme("marko");
        losInstruktor.setSifra("123");
        InstruktorLicenca losIdInstruktora = new InstruktorLicenca();
        losIdInstruktora.setInstruktor(losInstruktor);
        losIdInstruktora.setLicenca(licenca);
        losIdInstruktora.setDatumIzdavanja(format.parse("2024-01-01"));

        InstruktorLicenca losDatum = new InstruktorLicenca();
        losDatum.setInstruktor(instruktor);
        losDatum.setLicenca(licenca);
        losDatum.setDatumIzdavanja(format.parse("2024-06-01"));
        losDatum.setDatumIsteka(format.parse("2024-01-01"));

        return Stream.of(
                Arguments.of(null, "null parametar"),
                Arguments.of("nije dodela", "pogresan tip"),
                Arguments.of(bezIzdavanja, "nema datum izdavanja"),
                Arguments.of(losIdInstruktora, "neispravan instruktor"),
                Arguments.of(losDatum, "datum isteka pre izdavanja"));
    }

    @Test
    void izvrsiUbacujeDodeluLicence() throws Exception {
        Instruktor instruktor = prviInstruktor();
        Licenca licenca = prvaLicenca();

        InstruktorLicenca dodela = new InstruktorLicenca();
        dodela.setInstruktor(instruktor);
        dodela.setLicenca(licenca);
        dodela.setDatumIzdavanja(datum("2024-03-01"));
        dodela.setDatumIsteka(datum("2027-03-01"));

        assertDoesNotThrow(() -> new UbaciLicencuSO().izvrsi(dodela, null));
        registrujLicencuZaBrisanje(dodela);
    }
}
