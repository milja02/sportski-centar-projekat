package operacije.licence;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import operacije.integracija.SOTestoviHelper;
import org.junit.jupiter.api.Test;

class UcitajLicenceSOTest extends SOTestoviHelper {

    @Test
    void izvrsiUcitavaLicenceIzBaze() throws Exception {
        UcitajLicenceSO so = new UcitajLicenceSO();
        so.izvrsi(null, null);

        assertFalse(so.getLicence().isEmpty());
        assertNotNull(so.getLicence().get(0).getTipLicence());
    }
}
