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

class SportTest {

    private Sport sport;

    @BeforeEach
    void setUp() {
        sport = new Sport(1, "Plivanje", 2000);
    }

    @AfterEach
    void tearDown() {
        sport = null;
    }

    @Test
    void konstruktorPostavljaAtribute() {
        assertEquals(1, sport.getIdSport());
        assertEquals("Plivanje", sport.getNaziv());
        assertEquals(2000, sport.getCena());
    }

    @Test
    void setteriPostavljajuAtribute() {
        sport = new Sport();
        sport.setIdSport(2);
        sport.setNaziv("Tenis");
        sport.setCena(1500);

        assertEquals(2, sport.getIdSport());
        assertEquals("Tenis", sport.getNaziv());
        assertEquals(1500, sport.getCena());
    }

    @Test
    void setIdSport_bacaIllegalArgumentException_kadaJeNula() {
        sport = new Sport();
        assertThrows(IllegalArgumentException.class, () -> sport.setIdSport(0));
    }

    @Test
    void setIdSport_bacaIllegalArgumentException_kadaJeNegativan() {
        sport = new Sport();
        assertThrows(IllegalArgumentException.class, () -> sport.setIdSport(-5));
    }

    @Test
    void setNaziv_bacaNullPointerException_kadaJeNull() {
        sport = new Sport();
        assertThrows(NullPointerException.class, () -> sport.setNaziv(null));
    }

    @Test
    void setNaziv_bacaIllegalArgumentException_kadaJePrazan() {
        sport = new Sport();
        assertThrows(IllegalArgumentException.class, () -> sport.setNaziv(""));
    }

    @Test
    void setNaziv_bacaIllegalArgumentException_kadaJeBlank() {
        sport = new Sport();
        assertThrows(IllegalArgumentException.class, () -> sport.setNaziv("  "));
    }

    @Test
    void setCena_bacaIllegalArgumentException_kadaJeNegativna() {
        assertThrows(IllegalArgumentException.class, () -> sport.setCena(-100));
    }

    @ParameterizedTest(name = "{3}")
    @MethodSource("equalsPodaci")
    void equals(Sport levi, Sport desni, boolean ocekivano, String opis) {
        assertEquals(ocekivano, levi.equals(desni));
    }

    static Stream<Arguments> equalsPodaci() {
        return Stream.of(
                Arguments.of(
                        new Sport(1, "Plivanje", 3000),
                        new Sport(2, "Plivanje", 5000),
                        true,
                        "isti naziv, razlicit ID i cena"),
                Arguments.of(
                        new Sport(1, "Plivanje", 3000),
                        new Sport(1, "Tenis", 3000),
                        false,
                        "razlicit naziv"),
                Arguments.of(
                        new Sport(1, "Plivanje", 3000),
                        null,
                        false,
                        "poredjenje sa null"));
    }

    @Test
    void toStringVracaNaziv() {
        assertEquals("Joga", new Sport(1, "Joga", 2000).toString());
    }

    @Test
    void sqlMetodeVracajuOcekivaneVrednosti() {
        Sport sport = new Sport(7, "Kosarka", 4500);

        assertEquals("sport", sport.nazivTabele());
        assertEquals("sport.idSport=7", sport.primarniKljuc());
        assertEquals("naziv,cena", sport.koloneZaUbacivanje());
        assertEquals("'Kosarka',4500", sport.vrednostiZaUbacivanje());
        assertEquals("naziv='Kosarka',cena=4500", sport.vrednostiZaIzmenu());
        assertEquals("sp", sport.alijasTabele());
    }

    @Test
    void vratiListuMapiraResultSet() throws Exception {
        var rs = PomocniResultSet.jedanRed(Map.of(
                "idSport", 4,
                "naziv", "Fudbal",
                "cena", 3500));

        List<ApstraktniDomenskiObjekat> lista = new Sport().vratiListu(rs);

        assertEquals(1, lista.size());
        Sport sport = (Sport) lista.get(0);
        assertEquals(4, sport.getIdSport());
        assertEquals("Fudbal", sport.getNaziv());
        assertEquals(3500, sport.getCena());
    }
}
