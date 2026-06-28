package kontroleri;

import forme.GlavnaForma;
import domen.Instruktor;

public class GlavnaFormaKontroler {
    private final GlavnaForma gf;

    public GlavnaFormaKontroler(GlavnaForma gf) {
        this.gf = gf;
    }

    public void otvoriFormu() {
        Instruktor ulogovani = koordinator.Koordinator.getInstance().getUlogovani();
        String ime = ulogovani.getIme();
        gf.setVisible(true);
        gf.getjLabelUlogovani().setText(ime);
    }
}
