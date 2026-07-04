package domen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domen.pomocni.PomocniResultSet;

class StavkaClanskeKarteTest {

    private ClanskaKarta karta;
    private Sport sport;
    private StavkaClanskeKarte stavka;

    @BeforeEach
    void setUp() {
        karta = new ClanskaKarta();
        karta.setIdClanskaKarta(12);
        sport = new Sport(3, "Tenis", 4000);
        stavka = new StavkaClanskeKarte(karta, 1, 10, 40000, sport);
    }

    @AfterEach
    void tearDown() {
        stavka = null;
        sport = null;
        karta = null;
    }

    @Test
    void konstruktorPostavljaAtribute() {
        assertSame(karta, stavka.getClanskaKarta());
        assertEquals(1, stavka.getRb());
        assertEquals(10, stavka.getBrojTermina());
        assertEquals(40000, stavka.getIznosStavke());
        assertSame(sport, stavka.getSport());
    }

    @Test
    void setteriPostavljajuAtribute() {
        stavka = new StavkaClanskeKarte();
        ClanskaKarta novaKarta = new ClanskaKarta();
        novaKarta.setIdClanskaKarta(5);
        Sport noviSport = new Sport(2, "Plivanje", 1500);

        stavka.setClanskaKarta(novaKarta);
        stavka.setRb(2);
        stavka.setBrojTermina(4);
        stavka.setSport(noviSport);
        stavka.setIznosStavke(6000);

        assertSame(novaKarta, stavka.getClanskaKarta());
        assertEquals(2, stavka.getRb());
        assertEquals(4, stavka.getBrojTermina());
        assertEquals(6000, stavka.getIznosStavke());
        assertSame(noviSport, stavka.getSport());
    }

    @Test
    void setClanskaKarta_bacaNullPointerException_kadaJeNull() {
        stavka = new StavkaClanskeKarte();
        assertThrows(NullPointerException.class, () -> stavka.setClanskaKarta(null));
    }

    @Test
    void setClanskaKarta_bacaIllegalArgumentException_kadaJeIdNula() {
        stavka = new StavkaClanskeKarte();
        ClanskaKarta kartaBezId = new ClanskaKarta();
        assertThrows(IllegalArgumentException.class, () -> stavka.setClanskaKarta(kartaBezId));
    }

    @Test
    void setSport_bacaNullPointerException_kadaJeNull() {
        stavka = new StavkaClanskeKarte();
        assertThrows(NullPointerException.class, () -> stavka.setSport(null));
    }

    @Test
    void setSport_bacaIllegalArgumentException_kadaJeSportNeispravan() {
        assertThrows(IllegalArgumentException.class, () -> stavka.setSport(new Sport(0, "Plivanje", 2000)));
    }

    @Test
    void setRb_bacaIllegalArgumentException_kadaJeNula() {
        assertThrows(IllegalArgumentException.class, () -> stavka.setRb(0));
    }

    @Test
    void setBrojTermina_bacaIllegalArgumentException_kadaJeNula() {
        assertThrows(IllegalArgumentException.class, () -> stavka.setBrojTermina(0));
    }

    @Test
    void setIznosStavke_bacaIllegalArgumentException_kadaIznosNijeJednakProizvodu() {
        assertThrows(IllegalArgumentException.class, () -> stavka.setIznosStavke(5000));
    }

    @Test
    void izracunajOcekivaniIznos_racunaProizvodBrojaTerminaICene() {
        stavka = new StavkaClanskeKarte();
        stavka.setBrojTermina(3);
        stavka.setSport(new Sport(1, "Plivanje", 2000));

        assertEquals(6000, stavka.izracunajOcekivaniIznos());
    }

    @Test
    void izracunajOcekivaniIznos_bacaNullPointerException_kadaJeSportNull() {
        stavka = new StavkaClanskeKarte();
        assertThrows(NullPointerException.class, stavka::izracunajOcekivaniIznos);
    }

    @Test
    void toStringSadrziRbBrojTerminaIIznos() {
        String tekst = stavka.toString();

        assertTrue(tekst.contains("rb=1"));
        assertTrue(tekst.contains("brojTermina=10"));
        assertTrue(tekst.contains("iznosStavke=40000"));
    }

    @Test
    void sqlMetodeVracajuOcekivaneVrednosti() {
        assertEquals("stavkaclanskekarte", stavka.nazivTabele());
        assertEquals("clanskakarta=12 AND rb=1", stavka.primarniKljuc());
        assertEquals("clanskakarta,rb,brojTermina,iznosStavke,sport", stavka.koloneZaUbacivanje());
        assertEquals("12,1,10,40000,3", stavka.vrednostiZaUbacivanje());
        assertEquals("sck", stavka.alijasTabele());
        assertEquals("JOIN sport sp ON sck.sport = sp.idSport", stavka.join());
    }

    @Test
    void vrednostiZaIzmenuBacaUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, stavka::vrednostiZaIzmenu);
    }

    @Test
    void vratiListuMapiraResultSet() throws Exception {
        var rs = PomocniResultSet.jedanRed(Map.of(
                "rb", 2,
                "iznosStavke", 8000,
                "brojTermina", 4,
                "idSport", 5,
                "naziv", "Plivanje",
                "cena", 2000));

        List<ApstraktniDomenskiObjekat> lista = new StavkaClanskeKarte().vratiListu(rs);

        assertEquals(1, lista.size());
        StavkaClanskeKarte stavka = (StavkaClanskeKarte) lista.get(0);
        assertEquals(2, stavka.getRb());
        assertEquals(8000, stavka.getIznosStavke());
        assertEquals(4, stavka.getBrojTermina());
        assertEquals("Plivanje", stavka.getSport().getNaziv());
        assertEquals(2000, stavka.getSport().getCena());
    }
}
