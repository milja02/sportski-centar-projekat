package operacije.licence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domen.Instruktor;
import domen.InstruktorLicenca;
import domen.Licenca;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import operacije.integracija.SOTestoviHelper;
import operacije.instruktori.UcitajInstruktoreSO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class UcitajLicenceInstruktoraSOTest extends SOTestoviHelper {

    @ParameterizedTest
    @MethodSource("neispravniParametri")
    void izvrsiOdbijaNeispravanUnos(Object instruktor, String opis) {
        Exception ex = assertThrows(Exception.class,
                () -> new UcitajLicenceInstruktoraSO().izvrsi(instruktor, null));
        assertTrue(ex.getMessage().contains("licence instruktora"), opis);
    }

    static Stream<Arguments> neispravniParametri() {
        Instruktor bezId = new Instruktor();

        return Stream.of(
                Arguments.of(null, "null parametar"),
                Arguments.of("nije instruktor", "pogresan tip"),
                Arguments.of(bezId, "nema ID"));
    }

    @Test
    void izvrsiUcitavaLicenceDodeljeneInstruktoru() throws Exception {
        Instruktor instruktor = prviInstruktor();
        Licenca licenca = licencaKojaNijeDodeljena(instruktor);
        dodeliLicencu(instruktor, licenca, "2024-03-01", "2027-03-01");

        UcitajLicenceInstruktoraSO so = new UcitajLicenceInstruktoraSO();
        so.izvrsi(instruktor, null);

        assertFalse(so.getLicence().isEmpty());
        assertTrue(so.getLicence().stream()
                .anyMatch(l -> l.getIdLicenca() == licenca.getIdLicenca()));
    }

    @Test
    void izvrsiNeUkljucujeLicenceDrugogInstruktora() throws Exception {
        UcitajInstruktoreSO ucitaj = new UcitajInstruktoreSO();
        ucitaj.izvrsi(null, null);
        assertTrue(ucitaj.getInstruktori().size() >= 2, "Potrebna su bar dva instruktora u bazi");

        Instruktor prvi = ucitaj.getInstruktori().get(0);
        Instruktor drugi = ucitaj.getInstruktori().get(1);
        Licenca licenca = licencaKojaNijeDodeljena(prvi);
        dodeliLicencu(prvi, licenca, "2024-04-01", "2027-04-01");

        UcitajLicenceInstruktoraSO so = new UcitajLicenceInstruktoraSO();
        so.izvrsi(drugi, null);

        assertTrue(so.getLicence().stream()
                .noneMatch(l -> l.getIdLicenca() == licenca.getIdLicenca()));
    }

    @Test
    void izvrsiVracaPraznuListuZaInstruktoraBezDodela() throws Exception {
        Instruktor bezDodela = new Instruktor();
        bezDodela.setIdInstruktor(9_999_999);

        UcitajLicenceInstruktoraSO so = new UcitajLicenceInstruktoraSO();
        so.izvrsi(bezDodela, null);

        assertTrue(so.getLicence().isEmpty());
    }

    @Test
    void izvrsiUkljucujeNovododeljenuLicencu() throws Exception {
        Instruktor instruktor = prviInstruktor();

        UcitajLicenceInstruktoraSO so = new UcitajLicenceInstruktoraSO();
        so.izvrsi(instruktor, null);
        int pre = so.getLicence().size();

        Licenca prva = licencaKojaNijeDodeljena(instruktor);
        dodeliLicencu(instruktor, prva, "2024-05-01", "2027-05-01");

        so.izvrsi(instruktor, null);
        assertEquals(pre + 1, so.getLicence().size());

        Licenca druga = licencaKojaNijeDodeljena(instruktor);
        dodeliLicencu(instruktor, druga, "2024-06-01", "2027-06-01");

        so.izvrsi(instruktor, null);
        assertEquals(pre + 2, so.getLicence().size());
    }

    @Test
    void izvrsiRazlikujeLicencePoInstruktoru() throws Exception {
        UcitajInstruktoreSO ucitaj = new UcitajInstruktoreSO();
        ucitaj.izvrsi(null, null);
        assertTrue(ucitaj.getInstruktori().size() >= 2, "Potrebna su bar dva instruktora u bazi");

        Instruktor prvi = ucitaj.getInstruktori().get(0);
        Instruktor drugi = ucitaj.getInstruktori().get(1);

        Licenca licencaPrvog = licencaKojaNijeDodeljena(prvi);
        dodeliLicencu(prvi, licencaPrvog, "2024-07-01", "2027-07-01");

        UcitajLicenceSO sve = new UcitajLicenceSO();
        sve.izvrsi(null, null);
        Licenca licencaDrugog = sve.getLicence().stream()
                .filter(l -> l.getIdLicenca() != licencaPrvog.getIdLicenca())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Potrebne su bar dve razlicite licence"));
        dodeliLicencu(drugi, licencaDrugog, "2024-08-01", "2027-08-01");

        UcitajLicenceInstruktoraSO soPrvi = new UcitajLicenceInstruktoraSO();
        soPrvi.izvrsi(prvi, null);
        assertTrue(soPrvi.getLicence().stream()
                .anyMatch(l -> l.getIdLicenca() == licencaPrvog.getIdLicenca()));

        UcitajLicenceInstruktoraSO soDrugi = new UcitajLicenceInstruktoraSO();
        soDrugi.izvrsi(drugi, null);
        assertTrue(soDrugi.getLicence().stream()
                .anyMatch(l -> l.getIdLicenca() == licencaDrugog.getIdLicenca()));
    }

    private Licenca licencaKojaNijeDodeljena(Instruktor instruktor) throws Exception {
        UcitajLicenceSO sve = new UcitajLicenceSO();
        sve.izvrsi(null, null);

        UcitajLicenceInstruktoraSO trenutne = new UcitajLicenceInstruktoraSO();
        trenutne.izvrsi(instruktor, null);
        Set<Integer> dodeljene = trenutne.getLicence().stream()
                .map(Licenca::getIdLicenca)
                .collect(Collectors.toSet());

        return sve.getLicence().stream()
                .filter(l -> !dodeljene.contains(l.getIdLicenca()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Nema slobodne licence za test"));
    }

    private void dodeliLicencu(Instruktor instruktor, Licenca licenca,
            String datumIzdavanja, String datumIsteka) throws Exception {
        InstruktorLicenca dodela = new InstruktorLicenca();
        dodela.setInstruktor(instruktor);
        dodela.setLicenca(licenca);
        dodela.setDatumIzdavanja(datum(datumIzdavanja));
        dodela.setDatumIsteka(datum(datumIsteka));
        new UbaciLicencuSO().izvrsi(dodela, null);
        registrujLicencuZaBrisanje(dodela);
    }
}
