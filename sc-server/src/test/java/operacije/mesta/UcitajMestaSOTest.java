package operacije.mesta;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import operacije.integracija.SOTestoviHelper;
import org.junit.jupiter.api.Test;

class UcitajMestaSOTest extends SOTestoviHelper {

    @Test
    void izvrsiUcitavaMestaIzBaze() throws Exception {
        UcitajMestaSO so = new UcitajMestaSO();
        so.izvrsi(null, null);

        assertFalse(so.getMesta().isEmpty());
        assertNotNull(so.getMesta().get(0).getNaziv());
    }
}
