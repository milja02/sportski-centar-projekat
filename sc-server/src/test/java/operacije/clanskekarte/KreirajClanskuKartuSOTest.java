package operacije.clanskekarte;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domen.ClanskaKarta;
import operacije.integracija.SOTestoviHelper;
import org.junit.jupiter.api.Test;

class KreirajClanskuKartuSOTest extends SOTestoviHelper {

    @Test
    void izvrsiKreiraNoviId() throws Exception {
        KreirajClanskuKartuSO so = new KreirajClanskuKartuSO();
        so.izvrsi(null, null);

        assertNotNull(so.getClanskaKarta());
        assertTrue(so.getClanskaKarta().getIdClanskaKarta() > 0);

        ClanskaKarta sacuvana = nadjiClanskuKartuPoId(so.getClanskaKarta().getIdClanskaKarta());
        assertNotNull(sacuvana);
        registrujKartuZaBrisanje(sacuvana);
    }
}
