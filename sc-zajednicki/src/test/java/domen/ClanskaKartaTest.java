package domen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

class ClanskaKartaTest {

    private ClanskaKarta clanskaKarta;

    @BeforeEach
    void setUp() {
        clanskaKarta = new ClanskaKarta(
                1, datum("2024-03-15"), 4000, noviInstruktor(), noviPolaznik());
        clanskaKarta.setStavke(new ArrayList<>(List.of(
                new StavkaClanskeKarte(clanskaKarta, 1, 2, 4000, new Sport(1, "Plivanje", 2000)))));
    }

    @AfterEach
    void tearDown() {
        clanskaKarta = null;
    }

    private static java.util.Date datum(String vrednost) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(vrednost);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Polaznik noviPolaznik() {
        return new Polaznik(1, "Pera", "Peric", "061", new Mesto(1, "Beograd", 11000));
    }

    private static Instruktor noviInstruktor() {
        return new Instruktor(2, "Mika", "Mikic", "mika", "pass");
    }

    @Test
    void konstruktorPostavljaAtribute() {
        java.util.Date datum = datum("2024-03-15");
        ClanskaKarta karta = new ClanskaKarta(1, datum, 4000, noviInstruktor(), noviPolaznik());

        assertEquals(1, karta.getIdClanskaKarta());
        assertEquals(datum, karta.getDatumUclanjenja());
        assertEquals(4000, karta.getUkupanIznos());
        assertEquals(noviInstruktor(), karta.getInstruktor());
        assertEquals(noviPolaznik(), karta.getPolaznik());
    }

    @Test
    void setteriPostavljajuAtribute() {
        java.util.Date datum = datum("2024-04-01");
        ClanskaKarta karta = new ClanskaKarta();
        List<StavkaClanskeKarte> stavke = new ArrayList<>();

        karta.setIdClanskaKarta(2);
        karta.setDatumUclanjenja(datum);
        karta.setInstruktor(noviInstruktor());
        karta.setPolaznik(noviPolaznik());
        karta.setStavke(stavke);
        karta.setUkupanIznos(1);

        assertEquals(2, karta.getIdClanskaKarta());
        assertEquals(datum, karta.getDatumUclanjenja());
        assertEquals(1, karta.getUkupanIznos());
        assertEquals(stavke, karta.getStavke());
    }

    @Test
    void setIdClanskaKarta_bacaIllegalArgumentException_kadaJeNula() {
        assertThrows(IllegalArgumentException.class, () -> clanskaKarta.setIdClanskaKarta(0));
    }

    @Test
    void setDatumUclanjenja_bacaNullPointerException_kadaJeNull() {
        assertThrows(NullPointerException.class, () -> clanskaKarta.setDatumUclanjenja(null));
    }

    @Test
    void setInstruktor_bacaNullPointerException_kadaJeNull() {
        assertThrows(NullPointerException.class, () -> clanskaKarta.setInstruktor(null));
    }

    @Test
    void setInstruktor_bacaNullPointerException_kadaJeInstruktorNeispravan() {
        assertThrows(NullPointerException.class, () -> clanskaKarta.setInstruktor(new Instruktor()));
    }

    @Test
    void setPolaznik_bacaNullPointerException_kadaJeNull() {
        assertThrows(NullPointerException.class, () -> clanskaKarta.setPolaznik(null));
    }

    @Test
    void setPolaznik_bacaNullPointerException_kadaJePolaznikNeispravan() {
        assertThrows(NullPointerException.class, () -> clanskaKarta.setPolaznik(new Polaznik()));
    }

    @Test
    void setStavke_bacaNullPointerException_kadaJeListaNull() {
        assertThrows(NullPointerException.class, () -> clanskaKarta.setStavke(null));
    }

    @Test
    void setUkupanIznos_bacaIllegalArgumentException_kadaJeNula() {
        assertThrows(IllegalArgumentException.class, () -> clanskaKarta.setUkupanIznos(0));
    }

    @Test
    void setUkupanIznos_bacaIllegalArgumentException_kadaNijeZbirStavki() {
        assertThrows(IllegalArgumentException.class, () -> clanskaKarta.setUkupanIznos(9999));
    }

    @Test
    void izracunajZbirStavki_racunaZbirIznosa() {
        assertEquals(4000, clanskaKarta.izracunajZbirStavki());
    }

    @ParameterizedTest
    @MethodSource("equalsPodaci")
    void equals(ClanskaKarta leva, ClanskaKarta desna, boolean ocekivano, String opis) {
        assertEquals(ocekivano, leva.equals(desna));
    }

    static Stream<Arguments> equalsPodaci() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date datum = sdf.parse("2024-03-15");
        Polaznik polaznik = noviPolaznik();
        Instruktor instruktor = noviInstruktor();

        return Stream.of(
                Arguments.of(
                        new ClanskaKarta(1, datum, 5000, instruktor, polaznik),
                        new ClanskaKarta(2, datum, 8000, instruktor, polaznik),
                        true,
                        "isti datum, instruktor i polaznik, razlicit ID i iznos"),
                Arguments.of(
                        new ClanskaKarta(1, datum, 5000, instruktor, polaznik),
                        new ClanskaKarta(1, sdf.parse("2024-03-16"), 5000, instruktor, polaznik),
                        false,
                        "razlicit datum uclanjenja"),
                Arguments.of(
                        new ClanskaKarta(1, datum, 5000, instruktor, polaznik),
                        new ClanskaKarta(1, datum, 5000, new Instruktor(3, "A", "B", "x", "y"), polaznik),
                        false,
                        "razlicit instruktor"));
    }

    @Test
    void vrednostiZaUbacivanjeFormatiraDatum() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date datum = sdf.parse("2024-06-01");
        ClanskaKarta karta = new ClanskaKarta(5, datum, 12000, noviInstruktor(), noviPolaznik());

        assertEquals("'2024-06-01',12000,2,1", karta.vrednostiZaUbacivanje());
    }

    @Test
    void vrednostiZaUbacivanjePodrzavaNullDatum() {
        ClanskaKarta karta = new ClanskaKarta();
        karta.setIdClanskaKarta(5);
        karta.setInstruktor(noviInstruktor());
        karta.setPolaznik(noviPolaznik());
        karta.setUkupanIznos(1);

        assertEquals("NULL,1,2,1", karta.vrednostiZaUbacivanje());
    }

    @Test
    void vrednostiZaIzmenuFormatiraDatum() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date datum = sdf.parse("2024-07-20");
        ClanskaKarta karta = new ClanskaKarta(3, datum, 9000, noviInstruktor(), noviPolaznik());

        assertEquals("datumUclanjenja='2024-07-20',ukupanIznos=9000,instruktor=2,polaznik=1", karta.vrednostiZaIzmenu());
    }

    @Test
    void sqlMetodeVracajuOcekivaneVrednosti() {
        ClanskaKarta karta = new ClanskaKarta();
        karta.setIdClanskaKarta(11);

        assertEquals("clanskakarta", karta.nazivTabele());
        assertEquals("clanskakarta.idClanskaKarta=11", karta.primarniKljuc());
        assertEquals("datumUclanjenja,ukupanIznos,instruktor,polaznik", karta.koloneZaUbacivanje());
        assertEquals("ck", karta.alijasTabele());
        assertEquals(
                "JOIN instruktor i ON ck.instruktor = i.idInstruktor "
                        + "JOIN polaznik p ON ck.polaznik = p.idPolaznik "
                        + "JOIN mesto m ON p.mesto = m.idMesto",
                karta.join());
    }

    @Test
    void vratiListuMapiraResultSet() throws Exception {
        var rs = PomocniResultSet.jedanRed(Map.ofEntries(
                Map.entry("idClanskaKarta", 7),
                Map.entry("datumUclanjenja", Date.valueOf("2024-05-10")),
                Map.entry("ukupanIznos", 15000),
                Map.entry("i.idInstruktor", 2),
                Map.entry("i.ime", "Mika"),
                Map.entry("i.prezime", "Mikic"),
                Map.entry("i.korisnickoIme", "mika"),
                Map.entry("i.sifra", "pass"),
                Map.entry("p.idPolaznik", 1),
                Map.entry("p.ime", "Pera"),
                Map.entry("p.prezime", "Peric"),
                Map.entry("p.brojTelefona", "061"),
                Map.entry("m.idMesto", 1),
                Map.entry("m.naziv", "Beograd"),
                Map.entry("m.postanskiBroj", 11000)));

        List<ApstraktniDomenskiObjekat> lista = new ClanskaKarta().vratiListu(rs);

        assertEquals(1, lista.size());
        ClanskaKarta karta = (ClanskaKarta) lista.get(0);
        assertEquals(7, karta.getIdClanskaKarta());
        assertEquals(15000, karta.getUkupanIznos());
        assertEquals("Mika", karta.getInstruktor().getIme());
        assertEquals("Pera", karta.getPolaznik().getIme());
        assertEquals("Beograd", karta.getPolaznik().getMesto().getNaziv());
    }

    @Test
    void uslovWhereBacaUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> new ClanskaKarta().uslovWhere());
    }
}
