package kontroleri;

import domen.Mesto;
import domen.Polaznik;
import forme.PrikazPolaznikaForma;
import java.io.File;
import java.util.List;
import javax.swing.JOptionPane;
import json.JsonFajlServis;
import klijent.Komunikacija;
import koordinator.Koordinator;
import modeli.ModelTabelePolaznika;

public class PrikazPolaznikaKontroler {
    private final PrikazPolaznikaForma ppf;

    public PrikazPolaznikaKontroler(PrikazPolaznikaForma ppf) {
        this.ppf = ppf;
        ppf.getjButtonObrisi().setVisible(false);
        azurirajDugmadZaSelekciju();
        addActionListener();
        addSelectionListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        ppf.setVisible(true);
    }

    public void pripremiFormu() {
        ucitajMestaUCombo();
        ucitajPolaznikeUTabelu();
        azurirajDugmadZaSelekciju();
    }

    private void ucitajMestaUCombo() {
        try {
            List<Mesto> mesta = Komunikacija.getInstance().ucitajMesta();
            ppf.getjComboBoxMesto().removeAllItems();
            ppf.getjComboBoxMesto().addItem(null);
            if (mesta != null) {
                for (Mesto m : mesta) {
                    ppf.getjComboBoxMesto().addItem(m);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(ppf,
                    "Sistem ne može da učita mesta za pretragu.",
                    "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ucitajPolaznikeUTabelu() {
        try {
            List<Polaznik> polaznici = Komunikacija.getInstance().ucitajPolaznike();
            if (polaznici == null) {
                polaznici = java.util.Collections.emptyList();
            }
            ppf.getjTablePolaznici().setModel(new ModelTabelePolaznika(polaznici));
            azurirajDugmadZaSelekciju();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(ppf,
                    "Sistem ne može da učita listu polaznika.",
                    "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void azurirajDugmadZaSelekciju() {
        boolean imaSelekciju = ppf.getjTablePolaznici().getSelectedRow() != -1;
        ppf.getjButtonNadji().setEnabled(imaSelekciju);
        ppf.getjButtonIzmeni().setEnabled(imaSelekciju);
        ppf.getjButtonSacuvajJson().setEnabled(imaSelekciju);
    }

    private void addSelectionListener() {
        ppf.getjTablePolaznici().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                azurirajDugmadZaSelekciju();
            }
        });
    }

    private Polaznik selektovaniPolaznik() {
        int red = ppf.getjTablePolaznici().getSelectedRow();
        if (red == -1) {
            JOptionPane.showMessageDialog(ppf, "Morate da selektujete polaznika.", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        ModelTabelePolaznika mtp = (ModelTabelePolaznika) ppf.getjTablePolaznici().getModel();
        return mtp.getLista().get(red);
    }

    private void addActionListener() {
        ppf.addBtnPretraziActionListener(e -> pretrazi());
        ppf.addBtnResetujActionListener(e -> pripremiFormu());
        ppf.addBtnNazadActionListener(e -> Koordinator.getInstance().nazadNaGlavnuFormu(ppf));
        ppf.addBtnNadjiActionListener(e -> otvori());
        ppf.addBtnIzmeniActionListener(e -> promeni());
        ppf.addBtnSacuvajJsonActionListener(e -> sacuvajJson());
    }

    private void sacuvajJson() {
        Polaznik izTabele = selektovaniPolaznik();
        if (izTabele == null) {
            return;
        }
        try {
            Polaznik polaznik = Komunikacija.getInstance().nadjiPolaznika(izTabele);
            File fajl = JsonFajlServis.izaberiFajlZaSnimanje(ppf,
                    "polaznik_" + polaznik.getIdPolaznik() + ".json");
            if (fajl == null) {
                return;
            }
            JsonFajlServis.snimi(polaznik, fajl);
            JOptionPane.showMessageDialog(ppf,
                    "Podaci o polazniku su sačuvani u JSON fajl.",
                    "Uspeh", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(ppf,
                    "Sistem ne može da sačuva podatke u JSON fajl.",
                    "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void pretrazi() {
        try {
            String ime = ppf.getjTextFieldIme().getText().trim();
            String prezime = ppf.getjTextFieldPrezime().getText().trim();
            String brojTelefona = ppf.getjTextFieldBrojTelefona().getText().trim();
            Mesto izabranoMesto = (Mesto) ppf.getjComboBoxMesto().getSelectedItem();

            Polaznik kriterijum = new Polaznik();
            if (!ime.isEmpty()) {
                kriterijum.setIme(ime);
            }
            if (!prezime.isEmpty()) {
                kriterijum.setPrezime(prezime);
            }
            if (!brojTelefona.isEmpty()) {
                kriterijum.setBrojTelefona(brojTelefona);
            }
            if (izabranoMesto != null) {
                kriterijum.setMesto(izabranoMesto);
            }

            List<Polaznik> rezultat = Komunikacija.getInstance().pretraziPolaznike(kriterijum);
            if (rezultat == null || rezultat.isEmpty()) {
                JOptionPane.showMessageDialog(ppf,
                        "Sistem ne može da nađe polaznike po zadatim kriterijumima.",
                        "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            ppf.getjTablePolaznici().setModel(new ModelTabelePolaznika(rezultat));
            azurirajDugmadZaSelekciju();
            JOptionPane.showMessageDialog(ppf,
                    "Sistem je našao polaznike po zadatim kriterijumima.",
                    "Uspeh", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(ppf,
                    "Sistem ne može da nađe polaznike po zadatim kriterijumima.",
                    "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void otvori() {
        Polaznik izTabele = selektovaniPolaznik();
        if (izTabele == null) return;
        try {
            Polaznik pronadjen = Komunikacija.getInstance().nadjiPolaznika(izTabele);
            JOptionPane.showMessageDialog(ppf,
                    "Sistem je našao polaznika.",
                    "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            Koordinator.getInstance().dodajParam("polaznik", pronadjen);
            Koordinator.getInstance().otvoriPregledPolaznikaFormu();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(ppf,
                    "Sistem ne može da nađe polaznika.",
                    "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void promeni() {
        Polaznik izTabele = selektovaniPolaznik();
        if (izTabele == null) return;
        try {
            Polaznik pronadjen = Komunikacija.getInstance().nadjiPolaznika(izTabele);
            JOptionPane.showMessageDialog(ppf,
                    "Sistem je našao polaznika.",
                    "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            Koordinator.getInstance().dodajParam("polaznik", pronadjen);
            Koordinator.getInstance().otvoriIzmeniPolaznikaFormu();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(ppf,
                    "Sistem ne može da nađe polaznika.",
                    "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void osveziFormu() {
        pripremiFormu();
    }

    public void zatvoriFormu() {
        ppf.dispose();
    }

    public void sakrijFormu() {
        ppf.setVisible(false);
    }

    public void prikaziFormu() {
        ppf.setVisible(true);
        osveziFormu();
    }
}
