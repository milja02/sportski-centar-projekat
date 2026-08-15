package kontroleri;

import forme.LoginForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import domen.Instruktor;
import javax.swing.JOptionPane;
import klijent.Komunikacija;
import koordinator.Koordinator;

public class LoginKontroler {
    private final LoginForma lf;

    public LoginKontroler(LoginForma lf) {
        this.lf = lf;
        addActionListeners();
    }

    private void addActionListeners() {
        lf.loginAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prijava(e);
            }

            private void prijava(ActionEvent e) {
                String korisnickoIme = lf.getjTextFieldUsername().getText().trim();
                String sifra = new String(lf.getjPasswordField().getPassword()).trim();

                if (korisnickoIme.isEmpty() || sifra.isEmpty()) {
                    JOptionPane.showMessageDialog(lf,
                            "Sistem ne može da prijavi instruktora. Korisničko ime i šifra nisu ispravni.",
                            "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    Komunikacija.getInstance().konekcija();
                    Instruktor ulogovani = Komunikacija.getInstance().prijaviInstruktora(korisnickoIme, sifra);
                    JOptionPane.showMessageDialog(lf,
                            "Uspešna prijava. Dobrodošli.",
                            "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    Koordinator.getInstance().setUlogovani(ulogovani);
                    try {
                        Koordinator.getInstance().otvoriGlavnuFormu();
                        lf.dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(lf,
                                "Sistem ne može da otvori glavnu formu.",
                                "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    String poruka = ex.getMessage() != null && !ex.getMessage().isEmpty()
                            ? ex.getMessage()
                            : "Sistem ne može da prijavi instruktora. Korisničko ime i šifra nisu ispravni.";
                    JOptionPane.showMessageDialog(lf, poruka, "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void otvoriFormu() {
        lf.getjTextFieldUsername().requestFocusInWindow();
        lf.setVisible(true);
    }
}
