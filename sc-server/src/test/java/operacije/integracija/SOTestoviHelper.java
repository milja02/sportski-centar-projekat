package operacije.integracija;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import domen.ClanskaKarta;
import domen.Instruktor;
import domen.InstruktorLicenca;
import domen.Licenca;
import domen.Mesto;
import domen.Polaznik;
import domen.Sport;
import domen.StavkaClanskeKarte;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import operacije.clanskekarte.DodajClanskuKartuSO;
import operacije.clanskekarte.NadjiClanskuKartuSO;
import operacije.clanskekarte.ObrisiClanskuKartuSO;
import operacije.instruktori.UcitajInstruktoreSO;
import operacije.licence.UcitajLicenceSO;
import operacije.mesta.UcitajMestaSO;
import operacije.polaznici.DodajPolaznikaSO;
import operacije.polaznici.NadjiPolaznikaSO;
import operacije.polaznici.ObrisiPolaznikaSO;
import operacije.polaznici.PretraziPolaznikeSO;
import operacije.sportovi.UcitajSportoveSO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import repository.db.DBConnectionFactory;
import repository.db.impl.DBRepositoryGeneric;

public abstract class SOTestoviHelper {

    private final List<Integer> karteZaBrisanje = new ArrayList<>();
    private final List<InstruktorLicenca> licenceZaBrisanje = new ArrayList<>();
    private final List<Integer> polazniciZaBrisanje = new ArrayList<>();

    @BeforeAll
    static void pretpostaviDostupnuBazu() throws Exception {
        Connection konekcija = DBConnectionFactory.getInstance().getConnection();
        Assumptions.assumeTrue(konekcija != null && !konekcija.isClosed(),
                "Integracioni testovi zahtevaju pokrenut MySQL (baza sportskicentar)");
    }

    @AfterEach
    void ocistiTestPodatke() throws Exception {
        for (int id : karteZaBrisanje) {
            ClanskaKarta karta = new ClanskaKarta();
            karta.setIdClanskaKarta(id);
            new ObrisiClanskuKartuSO().izvrsi(karta, null);
        }
        karteZaBrisanje.clear();

        for (InstruktorLicenca il : licenceZaBrisanje) {
            obrisiDodeluLicence(il);
        }
        licenceZaBrisanje.clear();

        for (int id : polazniciZaBrisanje) {
            Polaznik polaznik = new Polaznik();
            polaznik.setIdPolaznik(id);
            new ObrisiPolaznikaSO().izvrsi(polaznik, null);
        }
        polazniciZaBrisanje.clear();
    }

    protected Mesto prvoMesto() throws Exception {
        UcitajMestaSO so = new UcitajMestaSO();
        so.izvrsi(null, null);
        assertFalse(so.getMesta().isEmpty(), "Baza mora imati bar jedno mesto");
        return so.getMesta().get(0);
    }

    protected Sport prviSport() throws Exception {
        UcitajSportoveSO so = new UcitajSportoveSO();
        so.izvrsi(null, null);
        assertFalse(so.getSportovi().isEmpty(), "Baza mora imati bar jedan sport");
        return so.getSportovi().get(0);
    }

    protected Instruktor prviInstruktor() throws Exception {
        UcitajInstruktoreSO so = new UcitajInstruktoreSO();
        so.izvrsi(null, null);
        assertFalse(so.getInstruktori().isEmpty(), "Baza mora imati bar jednog instruktora");
        return so.getInstruktori().get(0);
    }

    protected Licenca prvaLicenca() throws Exception {
        UcitajLicenceSO so = new UcitajLicenceSO();
        so.izvrsi(null, null);
        assertFalse(so.getLicence().isEmpty(), "Baza mora imati bar jednu licencu");
        return so.getLicence().get(0);
    }

    protected Date datum(String vrednost) throws Exception {
        return new SimpleDateFormat("yyyy-MM-dd").parse(vrednost);
    }

    protected String jedinstvenTelefon() {
        return "069" + (System.currentTimeMillis() % 10_000_000L);
    }

    protected Polaznik nadjiPolaznikaPoTelefonu(String telefon) throws Exception {
        Polaznik kriterijum = new Polaznik();
        kriterijum.setBrojTelefona(telefon);
        PretraziPolaznikeSO pretraga = new PretraziPolaznikeSO();
        pretraga.izvrsi(kriterijum, null);
        assertEquals(1, pretraga.getPolaznici().size(),
                "Ocekivan je tacno jedan polaznik sa telefonom " + telefon);
        return pretraga.getPolaznici().get(0);
    }

    protected Polaznik nadjiPolaznikaPoId(int id) throws Exception {
        Polaznik kriterijum = new Polaznik();
        kriterijum.setIdPolaznik(id);
        NadjiPolaznikaSO nadji = new NadjiPolaznikaSO();
        nadji.izvrsi(kriterijum, null);
        return nadji.getPolaznik();
    }

    protected ClanskaKarta nadjiClanskuKartuPoId(int id) throws Exception {
        ClanskaKarta kriterijum = new ClanskaKarta();
        kriterijum.setIdClanskaKarta(id);
        NadjiClanskuKartuSO nadji = new NadjiClanskuKartuSO();
        nadji.izvrsi(kriterijum, null);
        return nadji.getClanskaKarta();
    }

    protected Polaznik unesiTestPolaznika(String ime, String prezime) throws Exception {
        Mesto mesto = prvoMesto();
        String telefon = jedinstvenTelefon();

        Polaznik polaznik = new Polaznik();
        polaznik.setIme(ime);
        polaznik.setPrezime(prezime);
        polaznik.setBrojTelefona(telefon);
        polaznik.setMesto(mesto);

        new DodajPolaznikaSO().izvrsi(polaznik, null);
        return nadjiPolaznikaPoTelefonu(telefon);
    }

    protected Polaznik unesiTestPolaznikaZaCiscenje(String ime, String prezime) throws Exception {
        Polaznik sacuvan = unesiTestPolaznika(ime, prezime);
        registrujPolaznikaZaBrisanje(sacuvan);
        return sacuvan;
    }

    protected ClanskaKarta napraviValidnuKartu(Polaznik polaznik, Instruktor instruktor, Sport sport, int brojTermina)
            throws Exception {
        ClanskaKarta karta = new ClanskaKarta();
        karta.setDatumUclanjenja(datum("2024-06-01"));
        karta.setInstruktor(instruktor);
        karta.setPolaznik(polaznik);

        int iznos = brojTermina * sport.getCena();
        StavkaClanskeKarte stavka = new StavkaClanskeKarte(karta, 1, brojTermina, iznos, sport);
        karta.setStavke(new ArrayList<>(List.of(stavka)));
        karta.setUkupanIznos(iznos);
        return karta;
    }

    protected ClanskaKarta unesiTestKartu(int brojTermina) throws Exception {
        Polaznik polaznik = unesiTestPolaznikaZaCiscenje("Karta", "Test");
        ClanskaKarta karta = napraviValidnuKartu(polaznik, prviInstruktor(), prviSport(), brojTermina);
        new DodajClanskuKartuSO().izvrsi(karta, null);
        return nadjiClanskuKartuPoId(karta.getIdClanskaKarta());
    }

    protected ClanskaKarta unesiTestKartuZaCiscenje(int brojTermina) throws Exception {
        ClanskaKarta sacuvana = unesiTestKartu(brojTermina);
        registrujKartuZaBrisanje(sacuvana);
        return sacuvana;
    }

    protected void registrujKartuZaBrisanje(ClanskaKarta karta) {
        karteZaBrisanje.add(karta.getIdClanskaKarta());
    }

    protected void registrujPolaznikaZaBrisanje(Polaznik polaznik) {
        polazniciZaBrisanje.add(polaznik.getIdPolaznik());
    }

    protected void registrujLicencuZaBrisanje(InstruktorLicenca il) {
        licenceZaBrisanje.add(il);
    }

    private void obrisiDodeluLicence(InstruktorLicenca il) throws Exception {
        DBRepositoryGeneric repo = new DBRepositoryGeneric();
        repo.connect();
        try {
            repo.delete(il);
            repo.commit();
        } catch (Exception e) {
            repo.rollback();
            throw e;
        }
    }
}
