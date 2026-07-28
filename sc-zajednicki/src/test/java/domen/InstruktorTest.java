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

class InstruktorTest {

    private Instruktor instruktor;

    @BeforeEach
    void setUp() {
        instruktor = new Instruktor(1, "Marko", "Markovic", "marko", "123");
    }

    @AfterEach
    void tearDown() {
        instruktor = null;
    }

    @Test
    void konstruktorPostavljaAtribute() {
        assertEquals(1, instruktor.getIdInstruktor());
        assertEquals("Marko", instruktor.getIme());
        assertEquals("Markovic", instruktor.getPrezime());
        assertEquals("marko", instruktor.getKorisnickoIme());
        assertEquals("123", instruktor.getSifra());
    }

    @Test
    void setteriPostavljajuAtribute() {
        instruktor = new Instruktor();
        instruktor.setIdInstruktor(2);
        instruktor.setIme("Ana");
        instruktor.setPrezime("Anic");
        instruktor.setKorisnickoIme("ana");
        instruktor.setSifra("abc");

        assertEquals(2, instruktor.getIdInstruktor());
        assertEquals("Ana", instruktor.getIme());
        assertEquals("Anic", instruktor.getPrezime());
        assertEquals("ana", instruktor.getKorisnickoIme());
        assertEquals("abc", instruktor.getSifra());
    }

    @Test
    void setIdInstruktor_bacaIllegalArgumentException_kadaJeNula() {
        instruktor = new Instruktor();
        assertThrows(IllegalArgumentException.class, () -> instruktor.setIdInstruktor(0));
    }

    @Test
    void setIme_bacaNullPointerException_kadaJeNull() {
        instruktor = new Instruktor();
        assertThrows(NullPointerException.class, () -> instruktor.setIme(null));
    }

    @Test
    void setIme_bacaIllegalArgumentException_kadaJePrazno() {
        instruktor = new Instruktor();
        assertThrows(IllegalArgumentException.class, () -> instruktor.setIme(""));
    }

    @Test
    void setPrezime_bacaNullPointerException_kadaJeNull() {
        instruktor = new Instruktor();
        assertThrows(NullPointerException.class, () -> instruktor.setPrezime(null));
    }

    @Test
    void setKorisnickoIme_bacaNullPointerException_kadaJeNull() {
        instruktor = new Instruktor();
        assertThrows(NullPointerException.class, () -> instruktor.setKorisnickoIme(null));
    }

    @Test
    void setKorisnickoIme_bacaIllegalArgumentException_kadaJePrazno() {
        instruktor = new Instruktor();
        assertThrows(IllegalArgumentException.class, () -> instruktor.setKorisnickoIme(""));
    }

    @Test
    void setSifra_bacaNullPointerException_kadaJeNull() {
        instruktor = new Instruktor();
        assertThrows(NullPointerException.class, () -> instruktor.setSifra(null));
    }

    @Test
    void setSifra_bacaIllegalArgumentException_kadaJePrazna() {
        instruktor = new Instruktor();
        assertThrows(IllegalArgumentException.class, () -> instruktor.setSifra("   "));
    }

    @ParameterizedTest
    @MethodSource("equalsPodaci")
    void equals(Instruktor levi, Instruktor desni, boolean ocekivano, String opis) {
        assertEquals(ocekivano, levi.equals(desni));
    }

    static Stream<Arguments> equalsPodaci() {
        return Stream.of(
                Arguments.of(
                        new Instruktor(1, "Marko", "Markovic", "marko", "123"),
                        new Instruktor(2, "Petar", "Petrovic", "marko", "123"),
                        true,
                        "isto korisnicko ime i sifra, razlicito ime/prezime/ID"),
                Arguments.of(
                        new Instruktor(1, "Marko", "Markovic", "marko", "123"),
                        new Instruktor(1, "Marko", "Markovic", "marko", "456"),
                        false,
                        "razlicita sifra"),
                Arguments.of(
                        new Instruktor(1, "Marko", "Markovic", "marko", "123"),
                        new Instruktor(1, "Marko", "Markovic", "petar", "123"),
                        false,
                        "razlicito korisnicko ime"));
    }

    @Test
    void toStringVracaImeIPrezime() {
        Instruktor instruktor = new Instruktor(1, "Ana", "Anic", "ana", "pass");
        assertEquals("Ana Anic", instruktor.toString());
    }

    @Test
    void sqlMetodeVracajuOcekivaneVrednosti() {
        Instruktor instruktor = new Instruktor(3, "Jovan", "Jovic", "jovan", "tajna");

        assertEquals("instruktor", instruktor.nazivTabele());
        assertEquals("instruktor.idInstruktor=3", instruktor.primarniKljuc());
        assertEquals("ime,prezime,korisnickoIme,sifra", instruktor.koloneZaUbacivanje());
        assertEquals("'Jovan', 'Jovic', 'jovan', 'tajna'", instruktor.vrednostiZaUbacivanje());
        assertEquals("ime='Jovan',prezime='Jovic',korisnickoIme='jovan',sifra='tajna'", instruktor.vrednostiZaIzmenu());
        assertEquals("i", instruktor.alijasTabele());
    }

    @Test
    void vratiListuMapiraResultSet() throws Exception {
        var rs = PomocniResultSet.jedanRed(Map.of(
                "idInstruktor", 6,
                "ime", "Milan",
                "prezime", "Milic",
                "korisnickoIme", "milan",
                "sifra", "abc"));

        List<ApstraktniDomenskiObjekat> lista = new Instruktor().vratiListu(rs);

        assertEquals(1, lista.size());
        Instruktor instruktor = (Instruktor) lista.get(0);
        assertEquals(6, instruktor.getIdInstruktor());
        assertEquals("Milan", instruktor.getIme());
        assertEquals("Milic", instruktor.getPrezime());
        assertEquals("milan", instruktor.getKorisnickoIme());
        assertEquals("abc", instruktor.getSifra());
    }
}
