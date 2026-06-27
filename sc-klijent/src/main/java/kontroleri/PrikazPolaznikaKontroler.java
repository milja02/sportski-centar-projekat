package kontroleri;

import domen.Mesto;
import domen.Polaznik;
import forme.PrikazPolaznikaForma;
import java.util.List;
import javax.swing.JOptionPane;
import klijent.Komunikacija;
import koordinator.Koordinator;
import modeli.ModelTabelePolaznika;

public class PrikazPolaznikaKontroler {
    private final PrikazPolaznikaForma ppf;

    public PrikazPolaznikaKontroler(PrikazPolaznikaForma ppf) {
        this.ppf = ppf;
        ppf.getjButtonObrisi().setVisible(false);
        addActionListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        ppf.setVisible(true);
    }

    public void pripremiFormu() {
        ucitajMestaUCombo();
        ucitajPolaznikeUTabelu();
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
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(ppf,
                    "Sistem ne može da učita listu polaznika.",
                    "Greška", JOptionPane.ERROR_MESSAGE);
        }
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
    }

    private void pretrazi() {
        try {
            String ime = ppf.getjTextFieldIme().getText().trim();
            String prezime = ppf.getjTextFieldPrezime().getText().trim();
            String brojTelefona = ppf.getjTextFieldBrojTelefona().getText().trim();
            Mesto izabranoMesto = (Mesto) ppf.getjComboBoxMesto().getSelectedItem();

            Polaznik kriterijum = new Polaznik();
            kriterijum.setIme(ime.isEmpty() ? null : ime);
            kriterijum.setPrezime(prezime.isEmpty() ? null : prezime);
            kriterijum.setBrojTelefona(brojTelefona.isEmpty() ? null : brojTelefona);
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
}
