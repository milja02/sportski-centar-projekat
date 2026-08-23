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
import klijent.Komunikacija;
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
    private UbaciLicencuKontroler ulKontroler;
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

    public void odjaviSe() {
        zatvoriSvePodforme();
        if (glavnaForma != null) {
            glavnaForma.dispose();
            glavnaForma = null;
            glavnaFormaKontroler = null;
        }
        ulogovani = null;
        parametri.clear();
        ppKontroler = null;
        dpKontroler = null;
        pckKontroler = null;
        plKontroler = null;
        ulKontroler = null;
        Komunikacija.getInstance().prekiniKonekciju();
        otvoriLoginFormu();
    }

    private void zatvoriSvePodforme() {
        if (ppKontroler != null) {
            ppKontroler.zatvoriFormu();
        }
        if (dpKontroler != null) {
            dpKontroler.zatvoriFormu();
        }
        if (pckKontroler != null) {
            pckKontroler.zatvoriFormu();
        }
        if (plKontroler != null) {
            plKontroler.zatvoriFormu();
        }
        if (ulKontroler != null) {
            ulKontroler.zatvoriFormu();
        }
    }

    public void otvoriPrikazPolaznikaFormu() {
        zatvoriFormuPolaznika();
        sakrijGlavnuFormu();
        ppKontroler = new PrikazPolaznikaKontroler(new PrikazPolaznikaForma());
        ppKontroler.otvoriFormu();
    }

    public void otvoriPrikazClanskihKarataFormu() {
        if (pckKontroler != null) {
            pckKontroler.zatvoriFormu();
        }
        sakrijGlavnuFormu();
        pckKontroler = new PrikazClanskihKarataKontroler(new PrikazClanskihKarataForma());
        pckKontroler.otvoriFormu();
    }

    public void otvoriPrikazLicenciFormu() {
        if (plKontroler != null) {
            plKontroler.zatvoriFormu();
        }
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
            Polaznik p = Komunikacija.getInstance().kreirajPolaznika();
            dodajParam("noviPolaznik", p);
            JOptionPane.showMessageDialog(null,
                    "Sistem je kreirao polaznika.",
                    "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            otvoriFormuPolaznika(FormaMod.DODAJ);
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
        sakrijGlavnuFormu();
        if (ppKontroler != null) {
            ppKontroler.sakrijFormu();
        }
        otvoriFormuPolaznika(FormaMod.IZMENI);
    }

    public void otvoriPregledPolaznikaFormu() {
        sakrijGlavnuFormu();
        if (ppKontroler != null) {
            ppKontroler.sakrijFormu();
        }
        otvoriFormuPolaznika(FormaMod.PREGLED);
    }

    private void otvoriFormuPolaznika(FormaMod mod) {
        if (dpKontroler != null) {
            dpKontroler.zatvoriFormu();
        }
        sakrijGlavnuFormu();
        dpKontroler = new DodajPolaznikaKontroler(new DodajPolaznikaForma());
        dpKontroler.otvoriFormu(mod);
    }

    private void zatvoriFormuPolaznika() {
        if (dpKontroler != null) {
            dpKontroler.zatvoriFormu();
        }
        if (ppKontroler != null) {
            ppKontroler.zatvoriFormu();
        }
    }

    public void nazadSaFormePolaznika(java.awt.Window forma, FormaMod mod) {
        if (forma != null) {
            forma.dispose();
        }
        dpKontroler = null;
        if (ppKontroler != null) {
            ppKontroler.prikaziFormu();
            return;
        }
        otvoriGlavnuFormu();
    }

    public void osveziFormu() {
        if (ppKontroler != null) {
            ppKontroler.prikaziFormu();
            return;
        }
        otvoriGlavnuFormu();
    }

    public void otvoriDodajClanskuKartuFormu() {
        try {
            ClanskaKarta ck = Komunikacija.getInstance().kreirajClanskuKartu();
            dodajParam("novaClanskaKarta", ck);
            JOptionPane.showMessageDialog(null,
                    "Sistem je kreirao člansku kartu.",
                    "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            if (pckKontroler != null) {
                pckKontroler.sakrijFormu();
            }
            sakrijGlavnuFormu();
            DodajClanskuKartuKontroler dckKontroler = new DodajClanskuKartuKontroler(new DodajClanskuKartuForma());
            dckKontroler.otvoriFormu(FormaMod.DODAJ);
        } catch (Exception ex) {
            String poruka = ex.getMessage() != null ? ex.getMessage() : "Sistem ne može da kreira člansku kartu.";
            JOptionPane.showMessageDialog(null, poruka, "Greška", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public void otvoriIzmeniClanskuKartuFormu() {
        sakrijGlavnuFormu();
        if (pckKontroler != null) {
            pckKontroler.sakrijFormu();
        }
        DodajClanskuKartuKontroler dckKontroler = new DodajClanskuKartuKontroler(new DodajClanskuKartuForma());
        dckKontroler.otvoriFormu(FormaMod.IZMENI);
    }

    public void otvoriPregledClanskeKarteFormu() {
        sakrijGlavnuFormu();
        if (pckKontroler != null) {
            pckKontroler.sakrijFormu();
        }
        DodajClanskuKartuKontroler dckKontroler = new DodajClanskuKartuKontroler(new DodajClanskuKartuForma());
        dckKontroler.otvoriFormu(FormaMod.PREGLED);
    }

    public void nazadSaFormeClanskeKarte(java.awt.Window forma) {
        if (forma != null) {
            forma.dispose();
        }
        if (pckKontroler != null) {
            pckKontroler.prikaziFormu();
            return;
        }
        otvoriGlavnuFormu();
    }

    public void osveziFormuClanskeKarte() {
        if (pckKontroler != null) {
            pckKontroler.prikaziFormu();
            return;
        }
        otvoriGlavnuFormu();
    }

    public void otvoriUbaciLicencuFormu() {
        if (plKontroler != null) {
            plKontroler.sakrijFormu();
        }
        sakrijGlavnuFormu();
        if (ulKontroler != null) {
            ulKontroler.zatvoriFormu();
        }
        ulKontroler = new UbaciLicencuKontroler(new UbaciLicencuForma());
        ulKontroler.otvoriFormu();
    }

    public void nazadSaUbaciLicencuForme(java.awt.Window forma) {
        forma.dispose();
        ulKontroler = null;
        if (plKontroler != null) {
            plKontroler.prikaziFormu();
            return;
        }
        otvoriGlavnuFormu();
    }

    public void osveziFormuLicenci() {
        if (plKontroler != null) {
            plKontroler.osveziFormu();
        }
    }
}
