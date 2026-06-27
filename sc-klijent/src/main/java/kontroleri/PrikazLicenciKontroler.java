package kontroleri;

import domen.Licenca;
import forme.PrikazLicenciForma;
import java.util.List;
import javax.swing.JOptionPane;
import klijent.Komunikacija;
import koordinator.Koordinator;
import modeli.ModelTabeleLicence;

public class PrikazLicenciKontroler {

    private final PrikazLicenciForma forma;

    public PrikazLicenciKontroler(PrikazLicenciForma forma) {
        this.forma = forma;
        forma.addNazadActionListener(e -> Koordinator.getInstance().nazadNaGlavnuFormu(forma));
        forma.addDodajActionListener(e -> Koordinator.getInstance().otvoriUbaciLicencuFormu());
    }

    public void otvoriFormu() {
        pripremiFormu();
        forma.setVisible(true);
    }

    public void pripremiFormu() {
        try {
            List<Licenca> licence = Komunikacija.getInstance().ucitajLicence();
            forma.getjTableLicence().setModel(new ModelTabeleLicence(licence));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma,
                    "Sistem ne može da učita licence.",
                    "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void osveziFormu() {
        pripremiFormu();
    }
}
