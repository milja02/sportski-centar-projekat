package niti;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Odgovor;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.Zahtev;
import domen.ClanskaKarta;
import domen.Instruktor;
import domen.InstruktorLicenca;
import domen.Licenca;
import domen.Mesto;
import domen.Polaznik;
import domen.Sport;
import kontroler.Kontroler;
import server.PrijavljeniInstruktori;

public class ObradaZahteva extends Thread {
    Socket socket;
    Posiljalac posiljalac;
    Primalac primalac;
    private boolean kraj = false;
    private String prijavljenKorisnik;
    public ObradaZahteva() {
    }

    public ObradaZahteva(Socket socket) {
        this.socket = socket;
        posiljalac = new Posiljalac(socket);
        primalac = new Primalac(socket);
    }

    
    
    @Override
    public void run() {
        try {
        while(!kraj){
            try {
                Zahtev zahtev = (Zahtev) primalac.primi();
                Odgovor odgovor = new Odgovor();
                if (zahtev == null){
                    kraj = true;
                    break;
                }
                switch (zahtev.getOperacija()) {
                    case PRIJAVI_INSTRUKTORA:
                        try {
                            Instruktor i = (Instruktor) zahtev.getParams();
                            i = Kontroler.getInstance().prijaviInstruktora(i);
                            if (!PrijavljeniInstruktori.getInstance().prijavi(i.getKorisnickoIme())) {
                                throw new Exception("Instruktor je već prijavljen sa druge sesije.");
                            }
                            prijavljenKorisnik = i.getKorisnickoIme();
                            odgovor.setOdgovor(i);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                    case UCITAJ_POLAZNIKE:
                        List<Polaznik> polaznici = Kontroler.getInstance().ucitajPolaznike();
                        odgovor.setOdgovor(polaznici);
                        break;
                    case KREIRAJ_POLAZNIKA:
                        try {
                            Polaznik noviPolaznik = Kontroler.getInstance().kreirajPolaznika();
                            odgovor.setOdgovor(noviPolaznik);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                    case PRETRAZI_POLAZNIKE:
                        try {
                            Polaznik kriterijum = (Polaznik) zahtev.getParams();
                            List<Polaznik> filtrirani = Kontroler.getInstance().pretraziPolaznike(kriterijum);
                            odgovor.setOdgovor(filtrirani);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                    case NADJI_POLAZNIKA:
                        try {
                            Polaznik pNadji = (Polaznik) zahtev.getParams();
                            Polaznik pronadjen = Kontroler.getInstance().nadjiPolaznika(pNadji);
                            odgovor.setOdgovor(pronadjen);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                    case ZAPAMTI_POLAZNIKA:
                        try {
                            Polaznik pZapamti = (Polaznik) zahtev.getParams();
                            Kontroler.getInstance().zapamtiPolaznika(pZapamti);
                            odgovor.setOdgovor(null);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                    case OBRISI_POLAZNIKA:
                        try {
                            Polaznik p = (Polaznik) zahtev.getParams();
                            Kontroler.getInstance().obrisiPolaznika(p);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case UCITAJ_MESTA:
                        List<Mesto> mesta = Kontroler.getInstance().ucitajMesta();
                        odgovor.setOdgovor(mesta);
                        break;
                    case UCITAJ_CLANSKE_KARTE:
                        List<ClanskaKarta> clanskeKarte = Kontroler.getInstance().ucitajClanskeKarte();
                        odgovor.setOdgovor(clanskeKarte);
                        break;
                    case PRETRAZI_CLANSKE_KARTE:
                        try {
                            ClanskaKarta kriterijumCK = (ClanskaKarta) zahtev.getParams();
                            List<ClanskaKarta> filtriraneKarte = Kontroler.getInstance().pretraziClanskeKarte(kriterijumCK);
                            odgovor.setOdgovor(filtriraneKarte);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                    case KREIRAJ_CLANSKU_KARTU:
                        try {
                            ClanskaKarta novaKarta = Kontroler.getInstance().kreirajClanskuKartu();
                            odgovor.setOdgovor(novaKarta);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                    case NADJI_CLANSKU_KARTU:
                        try {
                            ClanskaKarta ckNadji = (ClanskaKarta) zahtev.getParams();
                            ClanskaKarta pronadjena = Kontroler.getInstance().nadjiClanskuKartu(ckNadji);
                            odgovor.setOdgovor(pronadjena);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                    case ZAPAMTI_CLANSKU_KARTU:
                        try {
                            ClanskaKarta ckZapamti = (ClanskaKarta) zahtev.getParams();
                            Kontroler.getInstance().zapamtiClanskuKartu(ckZapamti);
                            odgovor.setOdgovor(null);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                    case OBRISI_CLANSKU_KARTU:
                        try {
                            ClanskaKarta ckDel = (ClanskaKarta) zahtev.getParams();
                            Kontroler.getInstance().obrisiClanskuKartu(ckDel);
                            odgovor.setOdgovor(null);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                    case UBACI_LICENCU:
                        try {
                            InstruktorLicenca il = (InstruktorLicenca) zahtev.getParams();
                            Kontroler.getInstance().ubaciLicencu(il);
                            odgovor.setOdgovor(null);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                    case UCITAJ_SPORTOVE:
                        List<Sport> sportovi = Kontroler.getInstance().ucitajSportove();
                        odgovor.setOdgovor(sportovi);
                        break;
                    case UCITAJ_LICENCE:
                        List<Licenca> licence = Kontroler.getInstance().ucitajLicence();
                        odgovor.setOdgovor(licence);
                        break;
                    case UCITAJ_INSTRUKTORE:
                        List<Instruktor> instruktori = Kontroler.getInstance().ucitajInstruktore();
                        odgovor.setOdgovor(instruktori);
                        break;
                    default:
                        System.out.println("Operacija ne postoji");
                }
                posiljalac.posalji(odgovor);
            } catch (Exception ex) {
                kraj = true;
            }
        }
        } finally {
            PrijavljeniInstruktori.getInstance().odjavi(prijavljenKorisnik);
        }
    }
    
    public void prekiniNit(){
        PrijavljeniInstruktori.getInstance().odjavi(prijavljenKorisnik);
        kraj = true;
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ObradaZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        interrupt();
    }
}
