package operacije.polaznici;

import static org.junit.jupiter.api.Assertions.assertTrue;

import operacije.integracija.SOTestoviHelper;
import org.junit.jupiter.api.Test;

class UcitajPolaznikeSOTest extends SOTestoviHelper {

    @Test
    void izvrsiUcitavaPolaznikeIzBaze() throws Exception {
        var polaznik = unesiTestPolaznikaZaCiscenje("Ucitaj", "Test");

        UcitajPolaznikeSO so = new UcitajPolaznikeSO();
        so.izvrsi(null, null);

        assertTrue(so.getPolaznici().stream()
                .anyMatch(p -> p.getIdPolaznik() == polaznik.getIdPolaznik()));
    }

    @Test
    void izvrsiUkljucujeNovounetogPolaznika() throws Exception {
        var polaznik = unesiTestPolaznikaZaCiscenje("Ucitaj", "Novi");

        UcitajPolaznikeSO so = new UcitajPolaznikeSO();
        so.izvrsi(null, null);

        assertTrue(so.getPolaznici().stream()
                .anyMatch(p -> p.getIdPolaznik() == polaznik.getIdPolaznik()));
    }
}
