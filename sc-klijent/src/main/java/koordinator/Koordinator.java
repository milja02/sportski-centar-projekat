package koordinator;

import domen.ClanskaKarta;
import domen.Instruktor;
import domen.Polaznik;
import forme.DodajClanskuKartuForma;
import forme.DodajPolaznikaForma;
import forme.FormaMod;
import forme.GlavnaForma;
import forme.LoginForma;
import forme.PrikazClanskihKarataForma;
import forme.PrikazLicenciForma;
import forme.PrikazPolaznikaForma;
import forme.UbaciLicencuForma;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import kontroleri.DodajClanskuKartuKontroler;
import kontroleri.DodajPolaznikaKontroler;
import kontroleri.GlavnaFormaKontroler;
import kontroleri.LoginKontroler;
import kontroleri.PrikazClanskihKarataKontroler;
import kontroleri.PrikazLicenciKontroler;
import kontroleri.PrikazPolaznikaKontroler;
import kontroleri.UbaciLicencuKontroler;

public class Koordinator {
    private static Koordinator instance;
    private LoginKontroler loginKontroler;
    private GlavnaFormaKontroler glavnaFormaKontroler;
    private GlavnaForma glavnaForma;
    private Instruktor ulogovani;
    private PrikazPolaznikaKontroler ppKontroler;
    private DodajPolaznikaKontroler dpKontroler;
    private PrikazClanskihKarataKontroler pckKontroler;
    private PrikazLicenciKontroler plKontroler;
    private Map<String, Object> parametri;

    private Koordinator() {
        parametri = new HashMap<>();
    }

    public static Koordinator getInstance() {
        if (instance == null) {
            instance = new Koordinator();
        }
        return instance;
    }

    public void otvoriLoginFormu() {
        loginKontroler = new LoginKontroler(new LoginForma());
        loginKontroler.otvoriFormu();
    }

    public void otvoriGlavnuFormu() {
        if (glavnaForma == null) {
            glavnaForma = new GlavnaForma();
            glavnaFormaKontroler = new GlavnaFormaKontroler(glavnaForma);
        }
        glavnaForma.setVisible(true);
        glavnaFormaKontroler.otvoriFormu();
    }

    private void sakrijGlavnuFormu() {
        if (glavnaForma != null) {
            glavnaForma.setVisible(false);
        }
    }

    public void nazadNaGlavnuFormu(java.awt.Window formaZaZatvaranje) {
        if (formaZaZatvaranje != null) {
            formaZaZatvaranje.dispose();
        }
        otvoriGlavnuFormu();
    }

    public void otvoriPrikazPolaznikaFormu() {
        sakrijGlavnuFormu();
        ppKontroler = new PrikazPolaznikaKontroler(new PrikazPolaznikaForma());
        ppKontroler.otvoriFormu();
    }

    public void otvoriPrikazClanskihKarataFormu() {
        sakrijGlavnuFormu();
        pckKontroler = new PrikazClanskihKarataKontroler(new PrikazClanskihKarataForma());
        pckKontroler.otvoriFormu();
    }

    public void otvoriPrikazLicenciFormu() {
        sakrijGlavnuFormu();
        plKontroler = new PrikazLicenciKontroler(new PrikazLicenciForma());
        plKontroler.otvoriFormu();
    }

    public Instruktor getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Instruktor ulogovani) {
        this.ulogovani = ulogovani;
    }

    public void otvoriDodajPolaznikaFormu() {
        try {
            Polaznik p = klijent.Komunikacija.getInstance().kreirajPolaznika();
            dodajParam("noviPolaznik", p);
            JOptionPane.showMessageDialog(null,
                    "Sistem je kreirao polaznika.",
                    "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            dpKontroler = new DodajPolaznikaKontroler(new DodajPolaznikaForma());
            dpKontroler.otvoriFormu(FormaMod.DODAJ);
        } catch (Exception ex) {
            String poruka = ex.getMessage() != null ? ex.getMessage() : "Sistem ne može da kreira polaznika.";
            JOptionPane.showMessageDialog(null, poruka, "Greška", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public void dodajParam(String s, Object o) {
        parametri.put(s, o);
    }

    public Object vratiParam(String s) {
        return parametri.get(s);
    }

    public void otvoriIzmeniPolaznikaFormu() {
        dpKontroler = new DodajPolaznikaKontroler(new DodajPolaznikaForma());
        dpKontroler.otvoriFormu(FormaMod.IZMENI);
    }

    public void otvoriPregledPolaznikaFormu() {
        DodajPolaznikaKontroler kontroler = new DodajPolaznikaKontroler(new DodajPolaznikaForma());
        kontroler.otvoriFormu(FormaMod.PREGLED);
    }

    public void osveziFormu() {
        if (ppKontroler != null) {
            ppKontroler.osveziFormu();
        }
    }

    public void otvoriDodajClanskuKartuFormu() {
        try {
            ClanskaKarta ck = klijent.Komunikacija.getInstance().kreirajClanskuKartu();
            dodajParam("novaClanskaKarta", ck);
            JOptionPane.showMessageDialog(null,
                    "Sistem je kreirao člansku kartu.",
                    "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            DodajClanskuKartuKontroler dckKontroler = new DodajClanskuKartuKontroler(new DodajClanskuKartuForma());
            dckKontroler.otvoriFormu(FormaMod.DODAJ);
        } catch (Exception ex) {
            String poruka = ex.getMessage() != null ? ex.getMessage() : "Sistem ne može da kreira člansku kartu.";
            JOptionPane.showMessageDialog(null, poruka, "Greška", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public void otvoriIzmeniClanskuKartuFormu() {
        DodajClanskuKartuKontroler dckKontroler = new DodajClanskuKartuKontroler(new DodajClanskuKartuForma());
        dckKontroler.otvoriFormu(FormaMod.IZMENI);
    }

    public void otvoriPregledClanskeKarteFormu() {
        DodajClanskuKartuKontroler dckKontroler = new DodajClanskuKartuKontroler(new DodajClanskuKartuForma());
        dckKontroler.otvoriFormu(FormaMod.PREGLED);
    }

    public void osveziFormuClanskeKarte() {
        if (pckKontroler != null) {
            pckKontroler.osveziFormu();
        }
    }

    public void otvoriUbaciLicencuFormu() {
        sakrijGlavnuFormu();
        UbaciLicencuKontroler ublKontroler = new UbaciLicencuKontroler(new UbaciLicencuForma());
        ublKontroler.otvoriFormu();
    }

    public void osveziFormuLicenci() {
        if (plKontroler != null) {
            plKontroler.osveziFormu();
        }
    }
}
