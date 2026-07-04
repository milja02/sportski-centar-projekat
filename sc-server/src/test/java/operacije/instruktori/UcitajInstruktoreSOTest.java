package operacije.instruktori;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import operacije.integracija.SOTestoviHelper;
import org.junit.jupiter.api.Test;

class UcitajInstruktoreSOTest extends SOTestoviHelper {

    @Test
    void izvrsiUcitavaInstruktoreIzBaze() throws Exception {
        UcitajInstruktoreSO so = new UcitajInstruktoreSO();
        so.izvrsi(null, null);

        assertFalse(so.getInstruktori().isEmpty());
        assertNotNull(so.getInstruktori().get(0).getKorisnickoIme());
    }
}
