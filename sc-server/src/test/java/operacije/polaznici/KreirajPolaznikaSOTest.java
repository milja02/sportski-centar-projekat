package operacije.polaznici;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domen.Polaznik;
import operacije.integracija.SOTestoviHelper;
import org.junit.jupiter.api.Test;

class KreirajPolaznikaSOTest extends SOTestoviHelper {

    @Test
    void izvrsiKreiraNoviId() throws Exception {
        KreirajPolaznikaSO so = new KreirajPolaznikaSO();
        so.izvrsi(null, null);

        assertNotNull(so.getPolaznik());
        assertTrue(so.getPolaznik().getIdPolaznik() > 0);

        Polaznik sacuvan = nadjiPolaznikaPoId(so.getPolaznik().getIdPolaznik());
        assertNotNull(sacuvan);
        registrujPolaznikaZaBrisanje(sacuvan);
    }
}
