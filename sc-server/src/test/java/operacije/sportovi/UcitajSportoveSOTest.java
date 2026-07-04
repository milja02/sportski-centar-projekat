package operacije.sportovi;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import operacije.integracija.SOTestoviHelper;
import org.junit.jupiter.api.Test;

class UcitajSportoveSOTest extends SOTestoviHelper {

    @Test
    void izvrsiUcitavaSportoveIzBaze() throws Exception {
        UcitajSportoveSO so = new UcitajSportoveSO();
        so.izvrsi(null, null);

        assertFalse(so.getSportovi().isEmpty());
        assertTrue(so.getSportovi().get(0).getCena() > 0);
    }
}
