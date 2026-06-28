package kontroleri;

import domen.Mesto;
import domen.Polaznik;
import forme.DodajPolaznikaForma;
import forme.FormaMod;
import java.io.File;
import java.util.List;
import javax.swing.JOptionPane;
import klijent.Komunikacija;
import koordinator.Koordinator;
import json.JsonFajlServis;

public class DodajPolaznikaKontroler {

    private final DodajPolaznikaForma dpf;
    private FormaMod trenutniMod;

    public DodajPolaznikaKontroler(DodajPolaznikaForma dpf) {
        this.dpf = dpf;
        addActionListener();
        dpf.nazadAddActionListener(e -> {
            dpf.dispose();
            if (trenutniMod != FormaMod.PREGLED) {
                Koordinator.getInstance().nazadNaGlavnuFormu(null);
            }
        });
    }

    public void otvoriFormu(FormaMod mod) {
        this.trenutniMod = mod;
        pripremiFormu(mod);
        dpf.setVisible(true);
    }

    private void addActionListener() {
        dpf.dodajAddActionListener(e -> zapamti());
        dpf.izmeniAddActionListener(e -> zapamti());
        dpf.obrisiAddActionListener(e -> obrisi());
        dpf.uveziJsonAddActionListener(e -> uveziJson());
    }

    private void uveziJson() {
        try {
            File fajl = JsonFajlServis.izaberiFajlZaUcitavanje(dpf);
            if (fajl == null) {
                return;
            }
            Polaznik polaznik = JsonFajlServis.ucitaj(fajl, Polaznik.class);
            if (polaznik == null) {
                JOptionPane.showMessageDialog(dpf,
                        "JSON fajl ne sadrži podatke o polazniku.",
                        "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            popuniPodatke(polaznik, false);
            JOptionPane.showMessageDialog(dpf,
                    "Podaci su uvezeni iz JSON fajla. Proverite unos i kliknite Dodaj.",
                    "Uspeh", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(dpf,
                    "Sistem ne može da uveze podatke iz JSON fajla.",
                    "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void zapamti() {
        if (!validirajUnos()) {
            return;
        }
        try {
            Polaznik p = napraviPolaznikaIzForme();
            Komunikacija.getInstance().zapamtiPolaznika(p);
            JOptionPane.showMessageDialog(dpf,
                    "Sistem je zapamtio polaznika.",
                    "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            dpf.dispose();
            Koordinator.getInstance().osveziFormu();
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(dpf,
                    "Sistem ne može da zapamti polaznika.",
                    "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void obrisi() {
        try {
            Polaznik p = (Polaznik) Koordinator.getInstance().vratiParam("polaznik");
            if (p == null) {
                p = napraviPolaznikaIzForme();
            }
            Komunikacija.getInstance().obrisiPolaznika(p);
            JOptionPane.showMessageDialog(dpf,
                    "Sistem je obrisao polaznika.",
                    "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            dpf.dispose();
            Koordinator.getInstance().osveziFormu();
        } catch (Exception exc) {
            String poruka = exc.getMessage() != null && exc.getMessage().contains("nađe")
                    ? "Sistem ne može da nađe polaznika."
                    : "Sistem ne može da obriše polaznika.";
            JOptionPane.showMessageDialog(dpf, poruka, "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validirajUnos() {
        if (dpf.getjTextField1().getText().trim().isEmpty()
                || dpf.getjTextField2().getText().trim().isEmpty()
                || dpf.getjTextField3().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(dpf,
                    "Popunite ime, prezime i broj telefona.",
                    "Greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (dpf.getjComboBoxMesto().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(dpf,
                    "Izaberite mesto.",
                    "Greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private Polaznik napraviPolaznikaIzForme() {
        int id = Integer.parseInt(dpf.getjTextField4().getText().trim());
        String ime = dpf.getjTextField1().getText().trim();
        String prezime = dpf.getjTextField2().getText().trim();
        String brojTelefona = dpf.getjTextField3().getText().trim();
        Mesto m = (Mesto) dpf.getjComboBoxMesto().getSelectedItem();
        return new Polaznik(id, ime, prezime, brojTelefona, m);
    }

    private void selektujMesto(Mesto mesto) {
        if (mesto == null) return;
        for (int i = 0; i < dpf.getjComboBoxMesto().getItemCount(); i++) {
            Mesto uCombo = (Mesto) dpf.getjComboBoxMesto().getItemAt(i);
            if (uCombo.getIdMesto() == mesto.getIdMesto()) {
                dpf.getjComboBoxMesto().setSelectedIndex(i);
                return;
            }
        }
    }

    private void sakrijSvaDugmad() {
        dpf.getjButton1().setVisible(false);
        dpf.getjButtonIzmeni().setVisible(false);
        dpf.getjButtonObrisi().setVisible(false);
    }

    private void postaviRezimPregleda() {
        dpf.setTitle("Pregled polaznika");
        sakrijSvaDugmad();
        dpf.getjButtonObrisi().setVisible(true);
        dpf.getjTextField1().setEditable(false);
        dpf.getjTextField2().setEditable(false);
        dpf.getjTextField3().setEditable(false);
        dpf.getjTextField4().setEditable(false);
        dpf.getjComboBoxMesto().setEnabled(false);
    }

    private void popuniPodatke(Polaznik p) {
        popuniPodatke(p, true);
    }

    private void popuniPodatke(Polaznik p, boolean postaviId) {
        dpf.getjTextField1().setText(p.getIme() != null ? p.getIme() : "");
        dpf.getjTextField2().setText(p.getPrezime() != null ? p.getPrezime() : "");
        dpf.getjTextField3().setText(p.getBrojTelefona() != null ? p.getBrojTelefona() : "");
        if (postaviId && p.getIdPolaznik() > 0) {
            dpf.getjTextField4().setText(String.valueOf(p.getIdPolaznik()));
        }
        dpf.getjTextField4().setVisible(true);
        dpf.getjLabel5().setVisible(true);
        selektujMesto(p.getMesto());
    }

    private void pripremiFormu(FormaMod mod) {
        try {
            List<Mesto> mesta = Komunikacija.getInstance().ucitajMesta();
            dpf.getjComboBoxMesto().removeAllItems();
            if (mesta != null) {
                for (Mesto m : mesta) {
                    dpf.getjComboBoxMesto().addItem(m);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(dpf,
                    "Sistem ne može da učita mesta.",
                    "Greška", JOptionPane.ERROR_MESSAGE);
        }

        dpf.getjTextField1().setEditable(true);
        dpf.getjTextField2().setEditable(true);
        dpf.getjTextField3().setEditable(true);
        dpf.getjComboBoxMesto().setEnabled(true);
        sakrijSvaDugmad();

        switch (mod) {
            case PREGLED:
                postaviRezimPregleda();
                dpf.getjButtonUveziJson().setVisible(false);
                Polaznik zaPregled = (Polaznik) Koordinator.getInstance().vratiParam("polaznik");
                popuniPodatke(zaPregled);
                break;
            case DODAJ:
                dpf.setTitle("Polaznik - unos");
                dpf.getjButton1().setVisible(true);
                dpf.getjButtonUveziJson().setVisible(true);
                dpf.getjTextField4().setVisible(true);
                dpf.getjTextField4().setEditable(false);
                dpf.getjLabel5().setVisible(true);
                Polaznik novi = (Polaznik) Koordinator.getInstance().vratiParam("noviPolaznik");
                if (novi != null) {
                    dpf.getjTextField4().setText(String.valueOf(novi.getIdPolaznik()));
                } else {
                    dpf.getjTextField4().setText("");
                }
                dpf.getjTextField1().setText("");
                dpf.getjTextField2().setText("");
                dpf.getjTextField3().setText("");
                break;
            case IZMENI:
                dpf.setTitle("Polaznik - izmena");
                dpf.getjButtonIzmeni().setVisible(true);
                dpf.getjButtonUveziJson().setVisible(false);
                dpf.getjTextField4().setVisible(true);
                dpf.getjTextField4().setEditable(false);
                Polaznik p = (Polaznik) Koordinator.getInstance().vratiParam("polaznik");
                popuniPodatke(p);
                break;
            default:
                throw new AssertionError();
        }
    }
}
