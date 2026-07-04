package domen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import domen.pomocni.PomocniResultSet;

class MestoTest {

    private Mesto mesto;

    @BeforeEach
    void setUp() {
        mesto = new Mesto(1, "Beograd", 11000);
    }

    @AfterEach
    void tearDown() {
        mesto = null;
    }

    @Test
    void konstruktorPostavljaAtribute() {
        assertEquals(1, mesto.getIdMesto());
        assertEquals("Beograd", mesto.getNaziv());
        assertEquals(11000, mesto.getPostanskiBroj());
    }

    @Test
    void setteriPostavljajuAtribute() {
        mesto = new Mesto();
        mesto.setIdMesto(2);
        mesto.setNaziv("Novi Sad");
        mesto.setPostanskiBroj(21000);

        assertEquals(2, mesto.getIdMesto());
        assertEquals("Novi Sad", mesto.getNaziv());
        assertEquals(21000, mesto.getPostanskiBroj());
    }

    @Test
    void setIdMesto_bacaIllegalArgumentException_kadaJeNula() {
        mesto = new Mesto();
        assertThrows(IllegalArgumentException.class, () -> mesto.setIdMesto(0));
    }

    @Test
    void setIdMesto_bacaIllegalArgumentException_kadaJeNegativan() {
        mesto = new Mesto();
        assertThrows(IllegalArgumentException.class, () -> mesto.setIdMesto(-1));
    }

    @Test
    void setNaziv_bacaNullPointerException_kadaJeNull() {
        mesto = new Mesto();
        assertThrows(NullPointerException.class, () -> mesto.setNaziv(null));
    }

    @Test
    void setNaziv_bacaIllegalArgumentException_kadaJePrazan() {
        mesto = new Mesto();
        assertThrows(IllegalArgumentException.class, () -> mesto.setNaziv(""));
    }

    @Test
    void setNaziv_bacaIllegalArgumentException_kadaJeBlank() {
        mesto = new Mesto();
        assertThrows(IllegalArgumentException.class, () -> mesto.setNaziv("   "));
    }

    @ParameterizedTest(name = "{3}")
    @MethodSource("equalsPodaci")
    void equals(Mesto levo, Mesto desno, boolean ocekivano, String opis) {
        assertEquals(ocekivano, levo.equals(desno));
    }

    static Stream<Arguments> equalsPodaci() {
        return Stream.of(
                Arguments.of(
                        new Mesto(1, "Beograd", 11000),
                        new Mesto(2, "Beograd", 11000),
                        true,
                        "isti naziv i postanski broj, razlicit ID"),
                Arguments.of(
                        new Mesto(1, "Beograd", 11000),
                        new Mesto(1, "Novi Sad", 21000),
                        false,
                        "razlicit naziv i postanski broj"),
                Arguments.of(
                        new Mesto(1, "Beograd", 11000),
                        new Mesto(1, "Beograd", 21000),
                        false,
                        "isti naziv, razlicit postanski broj"));
    }

    @Test
    void toStringVracaNaziv() {
        Mesto mesto = new Mesto(1, "Nis", 18000);
        assertEquals("Nis", mesto.toString());
    }

    @Test
    void sqlMetodeVracajuOcekivaneVrednosti() {
        Mesto mesto = new Mesto(5, "Subotica", 24000);

        assertEquals("mesto", mesto.nazivTabele());
        assertEquals("mesto.idMesto=5", mesto.primarniKljuc());
        assertEquals("naziv,postanskiBroj", mesto.koloneZaUbacivanje());
        assertEquals("'Subotica',24000", mesto.vrednostiZaUbacivanje());
        assertEquals("naziv='Subotica',24000", mesto.vrednostiZaIzmenu());
        assertEquals("me", mesto.alijasTabele());
        assertEquals("", mesto.join());
    }

    @Test
    void vratiListuMapiraResultSet() throws Exception {
        var rs = PomocniResultSet.jedanRed(Map.of(
                "idMesto", 3,
                "naziv", "Kragujevac",
                "postanskiBroj", 34000));

        List<ApstraktniDomenskiObjekat> lista = new Mesto().vratiListu(rs);

        assertEquals(1, lista.size());
        Mesto mesto = (Mesto) lista.get(0);
        assertEquals(3, mesto.getIdMesto());
        assertEquals("Kragujevac", mesto.getNaziv());
        assertEquals(34000, mesto.getPostanskiBroj());
    }
}
