package operacije.clanskekarte;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domen.ClanskaKarta;
import operacije.integracija.SOTestoviHelper;
import org.junit.jupiter.api.Test;

class UcitajClanskeKarteSOTest extends SOTestoviHelper {

    @Test
    void izvrsiUcitavaKarteSaStavkama() throws Exception {
        UcitajClanskeKarteSO so = new UcitajClanskeKarteSO();
        so.izvrsi(null, null);

        assertFalse(so.getClanskeKarte().isEmpty());
    }

    @Test
    void izvrsiUkljucujeNovounetuKartu() throws Exception {
        ClanskaKarta karta = unesiTestKartuZaCiscenje(2);

        UcitajClanskeKarteSO so = new UcitajClanskeKarteSO();
        so.izvrsi(null, null);

        assertTrue(so.getClanskeKarte().stream()
                .anyMatch(k -> k.getIdClanskaKarta() == karta.getIdClanskaKarta()));
        assertTrue(so.getClanskeKarte().stream()
                .filter(k -> k.getIdClanskaKarta() == karta.getIdClanskaKarta())
                .findFirst()
                .orElseThrow()
                .getStavke() != null);
    }
}
