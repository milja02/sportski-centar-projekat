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
                String sifra = lf.getjTextFieldPassword().getText().trim();

                if (korisnickoIme.isEmpty() || sifra.isEmpty()) {
                    JOptionPane.showMessageDialog(lf,
                            "Korisničko ime i šifra nisu ispravni.",
                            "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    Komunikacija.getInstance().konekcija();
                    Instruktor ulogovani = Komunikacija.getInstance().prijaviInstruktora(korisnickoIme, sifra);
                    JOptionPane.showMessageDialog(lf,
                            "Korisničko ime i šifra su ispravni.",
                            "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    Koordinator.getInstance().setUlogovani(ulogovani);
                    try {
                        Koordinator.getInstance().otvoriGlavnuFormu();
                        lf.dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(lf,
                                "Ne može da se otvori glavna forma i meni.",
                                "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    String poruka = ex.getMessage() != null && !ex.getMessage().isEmpty()
                            ? ex.getMessage()
                            : "Korisničko ime i šifra nisu ispravni.";
                    JOptionPane.showMessageDialog(lf, poruka, "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void otvoriFormu() {
        lf.setVisible(true);
    }
}
