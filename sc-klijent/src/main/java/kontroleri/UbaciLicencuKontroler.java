package kontroleri;

import domen.Instruktor;
import domen.InstruktorLicenca;
import domen.Licenca;
import forme.UbaciLicencuForma;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import klijent.Komunikacija;
import koordinator.Koordinator;

public class UbaciLicencuKontroler {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd.MM.yyyy");
    private final UbaciLicencuForma forma;

    public UbaciLicencuKontroler(UbaciLicencuForma forma) {
        this.forma = forma;
        pripremiFormu();
        forma.addUbaciActionListener(e -> ubaci());
        forma.addNazadActionListener(e -> Koordinator.getInstance().nazadNaGlavnuFormu(forma));
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
            Instruktor instruktor = (Instruktor) forma.getjComboBoxInstruktor().getSelectedItem();
            Licenca licenca = (Licenca) forma.getjComboBoxLicenca().getSelectedItem();
            if (instruktor == null || licenca == null) {
                JOptionPane.showMessageDialog(forma, "Izaberite instruktora i licencu.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (instruktor.getIdInstruktor() <= 0 || licenca.getIdLicenca() <= 0) {
                JOptionPane.showMessageDialog(forma,
                        "Podaci o instruktoru ili licenci nisu učitani ispravno. Ponovo otvorite formu.",
                        "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String datumIzdavanjaStr = forma.getjTextFieldDatumIzdavanja().getText().trim();
            if (datumIzdavanjaStr.isEmpty()) {
                JOptionPane.showMessageDialog(forma, "Unesite datum izdavanja.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Date datumIzdavanja = SDF.parse(datumIzdavanjaStr);
            Date datumIsteka = null;
            String datumIstekaStr = forma.getjTextFieldDatumIsteka().getText().trim();
            if (!datumIstekaStr.isEmpty()) {
                datumIsteka = SDF.parse(datumIstekaStr);
                if (!datumIsteka.after(datumIzdavanja)) {
                    JOptionPane.showMessageDialog(forma,
                            "Datum isteka mora biti posle datuma izdavanja.",
                            "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            InstruktorLicenca il = new InstruktorLicenca(instruktor, licenca, datumIzdavanja, datumIsteka);
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
