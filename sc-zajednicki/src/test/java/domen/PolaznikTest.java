package domen;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import domen.pomocni.PomocniResultSet;

class PolaznikTest {

    @ParameterizedTest(name = "{3}")
    @MethodSource("equalsPodaci")
    void equals(Polaznik levi, Polaznik desni, boolean ocekivano, String opis) {
        assertEquals(ocekivano, levi.equals(desni));
    }

    static Stream<Arguments> equalsPodaci() {
        Mesto beograd = new Mesto(1, "Beograd", 11000);
        Mesto noviSad = new Mesto(2, "Novi Sad", 21000);

        return Stream.of(
                Arguments.of(
                        new Polaznik(1, "Nikola", "Nikolic", "061111", beograd),
                        new Polaznik(2, "Nikola", "Nikolic", "061111", noviSad),
                        true,
                        "isto ime, prezime i telefon, razlicit ID i mesto"),
                Arguments.of(
                        new Polaznik(1, "Nikola", "Nikolic", "061111", beograd),
                        new Polaznik(1, "Nikola", "Nikolic", "062222", beograd),
                        false,
                        "razlicit broj telefona"),
                Arguments.of(
                        new Polaznik(1, "Nikola", "Nikolic", "061111", beograd),
                        new Polaznik(1, "Nikola", "Jokic", "061111", beograd),
                        false,
                        "razlicito prezime"));
    }

    @Test
    void toStringVracaImeIPrezime() {
        Polaznik polaznik = new Polaznik(1, "Sara", "Saric", "060000", new Mesto(1, "Beograd", 11000));
        assertEquals("Sara Saric", polaznik.toString());
    }

    @Test
    void sqlMetodeVracajuOcekivaneVrednosti() {
        Mesto mesto = new Mesto(4, "Zrenjanin", 23000);
        Polaznik polaznik = new Polaznik(10, "Luka", "Lukic", "063333", mesto);

        assertEquals("polaznik", polaznik.nazivTabele());
        assertEquals("polaznik.idPolaznik=10", polaznik.primarniKljuc());
        assertEquals("ime,prezime,brojTelefona,mesto", polaznik.koloneZaUbacivanje());
        assertEquals("'Luka','Lukic','063333',4", polaznik.vrednostiZaUbacivanje());
        assertEquals("ime='Luka',prezime='Lukic',brojTelefona='063333', mesto=4", polaznik.vrednostiZaIzmenu());
        assertEquals("po", polaznik.alijasTabele());
        assertEquals("JOIN mesto ON po.mesto = mesto.idMesto", polaznik.join());
    }

    @Test
    void vratiListuMapiraResultSet() throws Exception {
        var rs = PomocniResultSet.jedanRed(Map.of(
                "idPolaznik", 8,
                "ime", "Ivana",
                "prezime", "Ivanovic",
                "brojTelefona", "064444",
                "idMesto", 2,
                "naziv", "Novi Sad",
                "postanskiBroj", 21000));

        List<ApstraktniDomenskiObjekat> lista = new Polaznik().vratiListu(rs);

        assertEquals(1, lista.size());
        Polaznik polaznik = (Polaznik) lista.get(0);
        assertEquals(8, polaznik.getIdPolaznik());
        assertEquals("Ivana", polaznik.getIme());
        assertEquals("Ivanovic", polaznik.getPrezime());
        assertEquals("064444", polaznik.getBrojTelefona());
        assertEquals("Novi Sad", polaznik.getMesto().getNaziv());
        assertEquals(21000, polaznik.getMesto().getPostanskiBroj());
    }
}
