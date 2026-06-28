package klijent;

import domen.*;
import komunikacija.Operacija;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Odgovor;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.Zahtev;

public class Komunikacija {
    private Socket socket;
    private static Komunikacija instance;
    private Primalac primalac;
    private Posiljalac posiljalac;

    private Komunikacija() {

    }

    public static Komunikacija getInstance() {
        if (instance == null) {
            instance = new Komunikacija();
        }
        return instance;
    }

    public void konekcija() throws Exception {
        if (socket != null && socket.isConnected() && !socket.isClosed()) {
            return;
        }
        try {
            socket = new Socket("localhost", 9000);
            posiljalac = new Posiljalac(socket);
            primalac = new Primalac(socket);
        } catch (IOException ex) {
            socket = null;
            posiljalac = null;
            primalac = null;
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Sistem ne može da se poveže sa serverom. Server nije pokrenut.");
        }
    }

    private Odgovor posaljiZahtev(Zahtev zahtev) throws Exception {
        if (posiljalac == null || primalac == null) {
            throw new Exception("Sistem ne može da se poveže sa serverom. Server nije pokrenut.");
        }
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg == null) {
            throw new Exception("Sistem ne može da se poveže sa serverom. Server nije pokrenut.");
        }
        return odg;
    }

    private void proveriGresku(Odgovor odg) throws Exception {
        if (odg.getOdgovor() instanceof Exception) {
            throw (Exception) odg.getOdgovor();
        }
    }

    public Instruktor prijaviInstruktora(String korisnickoIme, String sifra) throws Exception {
        Instruktor i = new Instruktor();
        i.setSifra(sifra);
        i.setKorisnickoIme(korisnickoIme);
        Odgovor odgovor = posaljiZahtev(new Zahtev(Operacija.PRIJAVI_INSTRUKTORA, i));
        proveriGresku(odgovor);
        return (Instruktor) odgovor.getOdgovor();
    }

    public List<Polaznik> ucitajPolaznike() throws Exception {
        Odgovor odg = posaljiZahtev(new Zahtev(Operacija.UCITAJ_POLAZNIKE, null));
        proveriGresku(odg);
        return (List<Polaznik>) odg.getOdgovor();
    }

    public Polaznik kreirajPolaznika() throws Exception {
        Odgovor odg = posaljiZahtev(new Zahtev(Operacija.KREIRAJ_POLAZNIKA, null));
        proveriGresku(odg);
        return (Polaznik) odg.getOdgovor();
    }

    public List<Polaznik> pretraziPolaznike(Polaznik kriterijum) throws Exception {
        Odgovor odg = posaljiZahtev(new Zahtev(Operacija.PRETRAZI_POLAZNIKE, kriterijum));
        proveriGresku(odg);
        return (List<Polaznik>) odg.getOdgovor();
    }

    public Polaznik nadjiPolaznika(Polaznik p) throws Exception {
        Odgovor odg = posaljiZahtev(new Zahtev(Operacija.NADJI_POLAZNIKA, p));
        proveriGresku(odg);
        return (Polaznik) odg.getOdgovor();
    }

    public void zapamtiPolaznika(Polaznik p) throws Exception {
        Odgovor odg = posaljiZahtev(new Zahtev(Operacija.ZAPAMTI_POLAZNIKA, p));
        proveriGresku(odg);
        koordinator.Koordinator.getInstance().osveziFormu();
    }

    public void obrisiPolaznika(Polaznik p) throws Exception {
        Odgovor odg = posaljiZahtev(new Zahtev(Operacija.OBRISI_POLAZNIKA, p));
        proveriGresku(odg);
    }

    public List<Mesto> ucitajMesta() throws Exception {
        Odgovor odg = posaljiZahtev(new Zahtev(Operacija.UCITAJ_MESTA, null));
        proveriGresku(odg);
        return (List<Mesto>) odg.getOdgovor();
    }

    public List<ClanskaKarta> ucitajClanskeKarte() throws Exception {
        Odgovor odg = posaljiZahtev(new Zahtev(Operacija.UCITAJ_CLANSKE_KARTE, null));
        return (List<ClanskaKarta>) odg.getOdgovor();
    }

    public List<ClanskaKarta> pretraziClanskeKarte(ClanskaKarta kriterijum) throws Exception {
        Odgovor odg = posaljiZahtev(new Zahtev(Operacija.PRETRAZI_CLANSKE_KARTE, kriterijum));
        proveriGresku(odg);
        return (List<ClanskaKarta>) odg.getOdgovor();
    }

    public ClanskaKarta kreirajClanskuKartu() throws Exception {
        Odgovor odg = posaljiZahtev(new Zahtev(Operacija.KREIRAJ_CLANSKU_KARTU, null));
        proveriGresku(odg);
        return (ClanskaKarta) odg.getOdgovor();
    }

    public ClanskaKarta nadjiClanskuKartu(ClanskaKarta ck) throws Exception {
        Odgovor odg = posaljiZahtev(new Zahtev(Operacija.NADJI_CLANSKU_KARTU, ck));
        proveriGresku(odg);
        return (ClanskaKarta) odg.getOdgovor();
    }

    public void zapamtiClanskuKartu(ClanskaKarta ck) throws Exception {
        Odgovor odg = posaljiZahtev(new Zahtev(Operacija.ZAPAMTI_CLANSKU_KARTU, ck));
        proveriGresku(odg);
    }

    public void obrisiClanskuKartu(ClanskaKarta ck) throws Exception {
        Odgovor odg = posaljiZahtev(new Zahtev(Operacija.OBRISI_CLANSKU_KARTU, ck));
        proveriGresku(odg);
    }

    public void ubaciLicencu(InstruktorLicenca il) throws Exception {
        Odgovor odg = posaljiZahtev(new Zahtev(Operacija.UBACI_LICENCU, il));
        proveriGresku(odg);
    }

    public List<Sport> ucitajSportove() throws Exception {
        Odgovor odg = posaljiZahtev(new Zahtev(Operacija.UCITAJ_SPORTOVE, null));
        return (List<Sport>) odg.getOdgovor();
    }

    public List<Licenca> ucitajLicence() throws Exception {
        Odgovor odg = posaljiZahtev(new Zahtev(Operacija.UCITAJ_LICENCE, null));
        return (List<Licenca>) odg.getOdgovor();
    }

    public List<Instruktor> ucitajInstruktore() throws Exception {
        Odgovor odg = posaljiZahtev(new Zahtev(Operacija.UCITAJ_INSTRUKTORE, null));
        return (List<Instruktor>) odg.getOdgovor();
    }
}
