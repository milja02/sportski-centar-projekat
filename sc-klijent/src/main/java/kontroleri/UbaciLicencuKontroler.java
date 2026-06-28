package kontroleri;

import domen.Instruktor;
import domen.InstruktorLicenca;
import domen.Licenca;
import forme.UbaciLicencuForma;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import klijent.Komunikacija;
import koordinator.Koordinator;
import json.JsonFajlServis;

public class UbaciLicencuKontroler {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd.MM.yyyy");
    private final UbaciLicencuForma forma;

    public UbaciLicencuKontroler(UbaciLicencuForma forma) {
        this.forma = forma;
        pripremiFormu();
        forma.addUbaciActionListener(e -> ubaci());
        forma.uveziJsonAddActionListener(e -> uveziJson());
        forma.addNazadActionListener(e -> Koordinator.getInstance().nazadNaGlavnuFormu(forma));
    }

    private void uveziJson() {
        try {
            File fajl = JsonFajlServis.izaberiFajlZaUcitavanje(forma);
            if (fajl == null) {
                return;
            }
            InstruktorLicenca il = JsonFajlServis.ucitaj(fajl, InstruktorLicenca.class);
            if (il == null) {
                JOptionPane.showMessageDialog(forma,
                        "JSON fajl ne sadrži podatke o licenci.",
                        "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            popuniFormu(il);
            JOptionPane.showMessageDialog(forma,
                    "Podaci su uvezeni iz JSON fajla. Proverite unos i kliknite Ubaci licencu.",
                    "Uspeh", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma,
                    "Sistem ne može da uveze podatke iz JSON fajla.",
                    "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private InstruktorLicenca napraviInstruktorLicencuIzForme(boolean obavezniDatumi) throws ParseException {
        Instruktor instruktor = (Instruktor) forma.getjComboBoxInstruktor().getSelectedItem();
        Licenca licenca = (Licenca) forma.getjComboBoxLicenca().getSelectedItem();
        if (instruktor == null || licenca == null) {
            JOptionPane.showMessageDialog(forma, "Izaberite instruktora i licencu.", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        String datumIzdavanjaStr = forma.getjTextFieldDatumIzdavanja().getText().trim();
        if (obavezniDatumi && datumIzdavanjaStr.isEmpty()) {
            JOptionPane.showMessageDialog(forma, "Unesite datum izdavanja.", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        Date datumIzdavanja = datumIzdavanjaStr.isEmpty() ? null : SDF.parse(datumIzdavanjaStr);
        Date datumIsteka = null;
        String datumIstekaStr = forma.getjTextFieldDatumIsteka().getText().trim();
        if (!datumIstekaStr.isEmpty()) {
            datumIsteka = SDF.parse(datumIstekaStr);
        }
        return new InstruktorLicenca(instruktor, licenca, datumIzdavanja, datumIsteka);
    }

    private void popuniFormu(InstruktorLicenca il) {
        selektujInstruktora(il.getInstruktor());
        selektujLicencu(il.getLicenca());
        if (il.getDatumIzdavanja() != null) {
            forma.getjTextFieldDatumIzdavanja().setText(SDF.format(il.getDatumIzdavanja()));
        } else {
            forma.getjTextFieldDatumIzdavanja().setText("");
        }
        if (il.getDatumIsteka() != null) {
            forma.getjTextFieldDatumIsteka().setText(SDF.format(il.getDatumIsteka()));
        } else {
            forma.getjTextFieldDatumIsteka().setText("");
        }
    }

    private void selektujInstruktora(Instruktor instruktor) {
        if (instruktor == null) {
            return;
        }
        for (int i = 0; i < forma.getjComboBoxInstruktor().getItemCount(); i++) {
            Instruktor uCombo = (Instruktor) forma.getjComboBoxInstruktor().getItemAt(i);
            if (uCombo.getIdInstruktor() == instruktor.getIdInstruktor()) {
                forma.getjComboBoxInstruktor().setSelectedIndex(i);
                return;
            }
        }
    }

    private void selektujLicencu(Licenca licenca) {
        if (licenca == null) {
            return;
        }
        for (int i = 0; i < forma.getjComboBoxLicenca().getItemCount(); i++) {
            Licenca uCombo = (Licenca) forma.getjComboBoxLicenca().getItemAt(i);
            if (uCombo.getIdLicenca() == licenca.getIdLicenca()) {
                forma.getjComboBoxLicenca().setSelectedIndex(i);
                return;
            }
        }
    }

    private void pripremiFormu() {
        try {
            List<Instruktor> instruktori = Komunikacija.getInstance().ucitajInstruktore();
            forma.getjComboBoxInstruktor().removeAllItems();
            if (instruktori != null) {
                for (Instruktor i : instruktori) {
                    forma.getjComboBoxInstruktor().addItem(i);
                }
            }
            List<Licenca> licence = Komunikacija.getInstance().ucitajLicence();
            forma.getjComboBoxLicenca().removeAllItems();
            if (licence != null) {
                for (Licenca l : licence) {
                    forma.getjComboBoxLicenca().addItem(l);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma,
                    "Sistem ne može da učita instruktore i licence.",
                    "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void otvoriFormu() {
        forma.setVisible(true);
    }

    private void ubaci() {
        try {
            InstruktorLicenca il = napraviInstruktorLicencuIzForme(true);
            if (il == null) {
                return;
            }
            if (il.getInstruktor().getIdInstruktor() <= 0 || il.getLicenca().getIdLicenca() <= 0) {
                JOptionPane.showMessageDialog(forma,
                        "Podaci o instruktoru ili licenci nisu učitani ispravno. Ponovo otvorite formu.",
                        "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (il.getDatumIsteka() != null && !il.getDatumIsteka().after(il.getDatumIzdavanja())) {
                JOptionPane.showMessageDialog(forma,
                        "Datum isteka mora biti posle datuma izdavanja.",
                        "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Komunikacija.getInstance().ubaciLicencu(il);
            JOptionPane.showMessageDialog(forma, "Sistem je zapamtio licencu.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            forma.getjTextFieldDatumIzdavanja().setText("");
            forma.getjTextFieldDatumIsteka().setText("");
            Koordinator.getInstance().osveziFormuLicenci();
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(forma,
                    "Datum mora biti u formatu dd.MM.yyyy (npr. 19.05.2026).",
                    "Greška", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            String poruka = ex.getMessage() != null && !ex.getMessage().isEmpty()
                    ? ex.getMessage()
                    : "Sistem ne može da zapamti licencu.";
            JOptionPane.showMessageDialog(forma, poruka, "Greška", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
