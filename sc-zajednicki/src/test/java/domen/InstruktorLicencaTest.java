package domen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class InstruktorLicencaTest {

    private Instruktor instruktor;
    private Licenca licenca;

    @BeforeEach
    void setUp() {
        instruktor = new Instruktor(1, "Marko", "Markovic", "marko", "123");
        licenca = new Licenca(2, "FITNESS", "Nivo 1");
    }

    @AfterEach
    void tearDown() {
        instruktor = null;
        licenca = null;
    }

    @Test
    void setInstruktor_bacaNullPointerException_kadaJeNull() {
        InstruktorLicenca il = new InstruktorLicenca();
        assertThrows(NullPointerException.class, () -> il.setInstruktor(null));
    }

    @Test
    void setInstruktor_bacaNullPointerException_kadaJeInstruktorNeispravan() {
        InstruktorLicenca il = new InstruktorLicenca();
        assertThrows(NullPointerException.class, () -> il.setInstruktor(new Instruktor()));
    }

    @Test
    void setLicenca_bacaNullPointerException_kadaJeNull() {
        InstruktorLicenca il = new InstruktorLicenca();
        assertThrows(NullPointerException.class, () -> il.setLicenca(null));
    }

    @Test
    void setLicenca_bacaNullPointerException_kadaJeLicencaNeispravna() {
        InstruktorLicenca il = new InstruktorLicenca();
        assertThrows(NullPointerException.class, () -> il.setLicenca(new Licenca()));
    }

    @Test
    void setDatumIzdavanja_bacaNullPointerException_kadaJeNull() {
        InstruktorLicenca il = new InstruktorLicenca();
        assertThrows(NullPointerException.class, () -> il.setDatumIzdavanja(null));
    }

    @Test
    void konstruktorPostavljaAtribute() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date izdavanje = sdf.parse("2024-01-01");
        Date istek = sdf.parse("2027-01-01");
        InstruktorLicenca il = new InstruktorLicenca(instruktor, licenca, izdavanje, istek);

        assertEquals(instruktor, il.getInstruktor());
        assertEquals(licenca, il.getLicenca());
        assertEquals(izdavanje, il.getDatumIzdavanja());
        assertEquals(istek, il.getDatumIsteka());
    }

    @ParameterizedTest(name = "{3}")
    @MethodSource("equalsPodaci")
    void equals(InstruktorLicenca leva, InstruktorLicenca desna, boolean ocekivano, String opis) {
        assertEquals(ocekivano, leva.equals(desna));
    }

    static Stream<Arguments> equalsPodaci() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Instruktor instruktor = new Instruktor(1, "Marko", "Markovic", "marko", "123");
        Licenca licenca = new Licenca(2, "FITNESS", "Nivo 1");
        Date izdavanje = sdf.parse("2023-01-01");
        Date istek = sdf.parse("2026-01-01");

        return Stream.of(
                Arguments.of(
                        new InstruktorLicenca(instruktor, licenca, izdavanje, istek),
                        new InstruktorLicenca(instruktor, licenca, izdavanje, istek),
                        true,
                        "ista sva polja"),
                Arguments.of(
                        new InstruktorLicenca(instruktor, licenca, izdavanje, istek),
                        new InstruktorLicenca(instruktor, licenca, izdavanje, sdf.parse("2027-01-01")),
                        false,
                        "razlicit datum isteka"),
                Arguments.of(
                        new InstruktorLicenca(instruktor, licenca, izdavanje, istek),
                        new InstruktorLicenca(instruktor, new Licenca(3, "PILATES", "Nivo 1"), izdavanje, istek),
                        false,
                        "razlicita licenca"));
    }

    @Test
    void primarniKljucKoristiIdInstruktoraILicence() {
        InstruktorLicenca il = new InstruktorLicenca();
        il.setInstruktor(instruktor);
        il.setLicenca(licenca);

        assertEquals("instruktor=1 AND licenca=2", il.primarniKljuc());
    }

    @Test
    void vrednostiZaUbacivanjeFormatiraDatume() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date izdavanje = sdf.parse("2024-02-10");
        Date istek = sdf.parse("2027-02-10");
        InstruktorLicenca il = new InstruktorLicenca(instruktor, licenca, izdavanje, istek);

        assertEquals("1,2,'2024-02-10','2027-02-10'", il.vrednostiZaUbacivanje());
    }

    @Test
    void vrednostiZaUbacivanjePodrzavaNullDatume() {
        InstruktorLicenca il = new InstruktorLicenca();
        il.setInstruktor(instruktor);
        il.setLicenca(licenca);

        assertEquals("1,2,NULL,NULL", il.vrednostiZaUbacivanje());
    }

    @Test
    void vrednostiZaUbacivanjeBacaAkoNedostajuInstruktorIliLicenca() {
        InstruktorLicenca bezInstruktora = new InstruktorLicenca();
        bezInstruktora.setLicenca(licenca);

        InstruktorLicenca bezLicence = new InstruktorLicenca();
        bezLicence.setInstruktor(instruktor);

        assertThrows(IllegalStateException.class, bezInstruktora::vrednostiZaUbacivanje);
        assertThrows(IllegalStateException.class, bezLicence::vrednostiZaUbacivanje);
    }

    @Test
    void sqlMetodeVracajuOcekivaneVrednosti() {
        InstruktorLicenca il = new InstruktorLicenca();

        assertEquals("instruktorlicenca", il.nazivTabele());
        assertEquals("instruktor,licenca,datumIzdavanja,datumIsteka", il.koloneZaUbacivanje());
        assertEquals("il", il.alijasTabele());
    }
}
