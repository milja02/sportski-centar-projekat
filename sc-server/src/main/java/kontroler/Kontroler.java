package kontroler;

import domen.ClanskaKarta;
import domen.Instruktor;
import domen.InstruktorLicenca;
import domen.Licenca;
import domen.Mesto;
import domen.Polaznik;
import domen.Sport;
import java.util.List;
import operacije.clanskekarte.KreirajClanskuKartuSO;
import operacije.clanskekarte.NadjiClanskuKartuSO;
import operacije.clanskekarte.ObrisiClanskuKartuSO;
import operacije.clanskekarte.PretraziClanskeKarteSO;
import operacije.clanskekarte.UcitajClanskeKarteSO;
import operacije.clanskekarte.ZapamtiClanskuKartuSO;
import operacije.instruktori.PrijaviInstruktoraSO;
import operacije.instruktori.UcitajInstruktoreSO;
import operacije.licence.UbaciLicencuSO;
import operacije.licence.UcitajLicenceSO;
import operacije.mesta.UcitajMestaSO;
import operacije.polaznici.KreirajPolaznikaSO;
import operacije.polaznici.NadjiPolaznikaSO;
import operacije.polaznici.ObrisiPolaznikaSO;
import operacije.polaznici.PretraziPolaznikeSO;
import operacije.polaznici.UcitajPolaznikeSO;
import operacije.polaznici.ZapamtiPolaznikaSO;
import operacije.sportovi.UcitajSportoveSO;

public class Kontroler {
    private static Kontroler instance;
    
    private Kontroler(){
        
    }
    
    public static Kontroler getInstance(){
        if (instance == null){
            instance = new Kontroler();
        }
        return instance;
    }

    public Instruktor prijaviInstruktora(Instruktor i) throws Exception {
        PrijaviInstruktoraSO operacija = new PrijaviInstruktoraSO();
        operacija.izvrsi(i, null);
        return operacija.getInstruktor();
    }

    public List<Polaznik> ucitajPolaznike() throws Exception {
        UcitajPolaznikeSO operacija = new UcitajPolaznikeSO();
        operacija.izvrsi(null, null);
        return operacija.getPolaznici();
    }

    public Polaznik kreirajPolaznika() throws Exception {
        KreirajPolaznikaSO operacija = new KreirajPolaznikaSO();
        operacija.izvrsi(null, null);
        return operacija.getPolaznik();
    }

    public List<Polaznik> pretraziPolaznike(Polaznik kriterijum) throws Exception {
        PretraziPolaznikeSO operacija = new PretraziPolaznikeSO();
        operacija.izvrsi(kriterijum, null);
        return operacija.getPolaznici();
    }

    public Polaznik nadjiPolaznika(Polaznik p) throws Exception {
        NadjiPolaznikaSO operacija = new NadjiPolaznikaSO();
        operacija.izvrsi(p, null);
        return operacija.getPolaznik();
    }

    public void zapamtiPolaznika(Polaznik p) throws Exception {
        ZapamtiPolaznikaSO operacija = new ZapamtiPolaznikaSO();
        operacija.izvrsi(p, null);
    }

    public void obrisiPolaznika(Polaznik p) throws Exception {
        ObrisiPolaznikaSO operacija = new ObrisiPolaznikaSO();
        operacija.izvrsi(p, null);
    }

    public List<Mesto> ucitajMesta() throws Exception {
        UcitajMestaSO operacija = new UcitajMestaSO();
        operacija.izvrsi(null, null);
        return operacija.getMesta();
    }

    public List<ClanskaKarta> ucitajClanskeKarte() throws Exception {
        UcitajClanskeKarteSO operacija = new UcitajClanskeKarteSO();
        operacija.izvrsi(null, null);
        return operacija.getClanskeKarte();
    }
    
    public List<ClanskaKarta> pretraziClanskeKarte(ClanskaKarta kriterijum) throws Exception {
        PretraziClanskeKarteSO operacija = new PretraziClanskeKarteSO();
        operacija.izvrsi(kriterijum, null);
        return operacija.getClanskeKarte();
    }

    public ClanskaKarta kreirajClanskuKartu() throws Exception {
        KreirajClanskuKartuSO operacija = new KreirajClanskuKartuSO();
        operacija.izvrsi(null, null);
        return operacija.getClanskaKarta();
    }

    public ClanskaKarta nadjiClanskuKartu(ClanskaKarta ck) throws Exception {
        NadjiClanskuKartuSO operacija = new NadjiClanskuKartuSO();
        operacija.izvrsi(ck, null);
        return operacija.getClanskaKarta();
    }

    public void zapamtiClanskuKartu(ClanskaKarta ck) throws Exception {
        ZapamtiClanskuKartuSO operacija = new ZapamtiClanskuKartuSO();
        operacija.izvrsi(ck, null);
    }

    public void obrisiClanskuKartu(ClanskaKarta ck) throws Exception {
        ObrisiClanskuKartuSO operacija = new ObrisiClanskuKartuSO();
        operacija.izvrsi(ck, null);
    }

    public void ubaciLicencu(InstruktorLicenca il) throws Exception {
        UbaciLicencuSO operacija = new UbaciLicencuSO();
        operacija.izvrsi(il, null);
    }

    public List<Sport> ucitajSportove() throws Exception {
        UcitajSportoveSO operacija = new UcitajSportoveSO();
        operacija.izvrsi(null, null);
        return operacija.getSportovi();
    }

    public List<Licenca> ucitajLicence() throws Exception {
        UcitajLicenceSO operacija = new UcitajLicenceSO();
        operacija.izvrsi(null, null);
        return operacija.getLicence();
    }

    public List<Instruktor> ucitajInstruktore() throws Exception {
        UcitajInstruktoreSO operacija = new UcitajInstruktoreSO();
        operacija.izvrsi(null, null);
        return operacija.getInstruktori();
    }
}
