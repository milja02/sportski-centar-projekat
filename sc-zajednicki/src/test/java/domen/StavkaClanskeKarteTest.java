package domen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domen.pomocni.PomocniResultSet;

class StavkaClanskeKarteTest {

    private ClanskaKarta karta;
    private Sport sport;

    @BeforeEach
    void pripremi() {
        karta = new ClanskaKarta();
        karta.setIdClanskaKarta(12);
        sport = new Sport(3, "Tenis", 4000);
    }

    @Test
    void sqlMetodeVracajuOcekivaneVrednosti() {
        StavkaClanskeKarte stavka = new StavkaClanskeKarte(karta, 1, 10, 40000, sport);

        assertEquals("stavkaclanskekarte", stavka.nazivTabele());
        assertEquals("clanskakarta=12 AND rb=1", stavka.primarniKljuc());
        assertEquals("clanskakarta,rb,brojTermina,iznosStavke,sport", stavka.koloneZaUbacivanje());
        assertEquals("12,1,10,40000,3", stavka.vrednostiZaUbacivanje());
        assertEquals("sck", stavka.alijasTabele());
        assertEquals("JOIN sport sp ON sck.sport = sp.idSport", stavka.join());
    }

    @Test
    void vrednostiZaIzmenuBacaUnsupportedOperationException() {
        StavkaClanskeKarte stavka = new StavkaClanskeKarte(karta, 1, 5, 20000, sport);
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
