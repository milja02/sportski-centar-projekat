package domen;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import domen.pomocni.PomocniResultSet;

class LicencaTest {

    @ParameterizedTest(name = "{3}")
    @MethodSource("equalsPodaci")
    void equals(Licenca leva, Licenca desna, boolean ocekivano, String opis) {
        assertEquals(ocekivano, leva.equals(desna));
    }

    static Stream<Arguments> equalsPodaci() {
        return Stream.of(
                Arguments.of(
                        new Licenca(1, "FITNESS", "Nivo 1"),
                        new Licenca(2, "FITNESS", "Nivo 1"),
                        true,
                        "isti tip i nivo, razlicit ID"),
                Arguments.of(
                        new Licenca(1, "FITNESS", "Nivo 1"),
                        new Licenca(1, "PILATES", "Nivo 1"),
                        false,
                        "razlicit tip licence"),
                Arguments.of(
                        new Licenca(1, "FITNESS", "Nivo 1"),
                        new Licenca(1, "FITNESS", "Nivo 2"),
                        false,
                        "razlicit nivo kvalifikacije"));
    }

    @Test
    void toStringKombinujeTipINivo() {
        assertEquals("FITNESS - Nivo 2", new Licenca(1, "FITNESS", "Nivo 2").toString());
        assertEquals("Nivo 3", new Licenca(1, null, "Nivo 3").toString());
        assertEquals("YOGA", new Licenca(1, "YOGA", null).toString());
        assertEquals("", new Licenca(1, null, null).toString());
    }

    @Test
    void sqlMetodeVracajuOcekivaneVrednosti() {
        Licenca licenca = new Licenca(9, "CROSSFIT", "Nivo 1");

        assertEquals("licenca", licenca.nazivTabele());
        assertEquals("licenca.idLicenca=9", licenca.primarniKljuc());
        assertEquals("tipLicence,nivoKvalifikacije", licenca.koloneZaUbacivanje());
        assertEquals("'CROSSFIT','Nivo 1'", licenca.vrednostiZaUbacivanje());
        assertEquals("tipLicence='CROSSFIT',nivoKvalifikacije='Nivo 1'", licenca.vrednostiZaIzmenu());
        assertEquals("li", licenca.alijasTabele());
    }

    @Test
    void vratiListuMapiraResultSet() throws Exception {
        var rs = PomocniResultSet.jedanRed(Map.of(
                "idLicenca", 2,
                "tipLicence", "PILATES",
                "nivoKvalifikacije", "Nivo 2"));

        List<ApstraktniDomenskiObjekat> lista = new Licenca().vratiListu(rs);

        assertEquals(1, lista.size());
        Licenca licenca = (Licenca) lista.get(0);
        assertEquals(2, licenca.getIdLicenca());
        assertEquals("PILATES", licenca.getTipLicence());
        assertEquals("Nivo 2", licenca.getNivoKvalifikacije());
    }
}
