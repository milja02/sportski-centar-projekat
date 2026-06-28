package kontroleri;

import domen.ClanskaKarta;
import domen.Instruktor;
import domen.Polaznik;
import domen.Sport;
import domen.StavkaClanskeKarte;
import forme.PrikazClanskihKarataForma;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import klijent.Komunikacija;
import koordinator.Koordinator;
import json.JsonFajlServis;
import modeli.ModelTabeleClanskeKarte;
import modeli.ModelTabeleStavkaClanskeKarte;

public class PrikazClanskihKarataKontroler {
    private final PrikazClanskihKarataForma pckf;

    public PrikazClanskihKarataKontroler(PrikazClanskihKarataForma pckf) {
        this.pckf = pckf;
        pckf.getjButtonObrisi().setVisible(false);
        addActionListener();
        addMouseListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        pckf.setVisible(true);
    }

    public void pripremiFormu() {
        try {
            List<Polaznik> polaznici = Komunikacija.getInstance().ucitajPolaznike();
            pckf.getjComboBoxPolaznik().removeAllItems();
            pckf.getjComboBoxPolaznik().addItem(null);
            if (polaznici != null) {
                for (Polaznik p : polaznici) {
                    pckf.getjComboBoxPolaznik().addItem(p);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(pckf,
                    "Sistem ne može da učita polaznike.",
                    "Greška", JOptionPane.ERROR_MESSAGE);
        }

        try {
            List<Instruktor> instruktori = Komunikacija.getInstance().ucitajInstruktore();
            pckf.getjComboBoxInstruktor().removeAllItems();
            pckf.getjComboBoxInstruktor().addItem(null);
            if (instruktori != null) {
                for (Instruktor i : instruktori) {
                    pckf.getjComboBoxInstruktor().addItem(i);
                }
            }

            List<Sport> sportovi = Komunikacija.getInstance().ucitajSportove();
            pckf.getjComboBoxSport().removeAllItems();
            pckf.getjComboBoxSport().addItem(null);
            if (sportovi != null) {
                for (Sport s : sportovi) {
                    pckf.getjComboBoxSport().addItem(s);
                }
            }

            List<ClanskaKarta> clanskeKarte = Komunikacija.getInstance().ucitajClanskeKarte();
            pckf.getjTable1().setModel(new ModelTabeleClanskeKarte(clanskeKarte));
            pckf.getjTable2().setModel(new ModelTabeleStavkaClanskeKarte(new ArrayList<>()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(pckf,
                    "Sistem ne može da učita podatke za članske karte.",
                    "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private ClanskaKarta selektovanaKarta() {
        int red = pckf.getjTable1().getSelectedRow();
        if (red == -1) {
            JOptionPane.showMessageDialog(pckf, "Selektujte člansku kartu.", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        ModelTabeleClanskeKarte mtck = (ModelTabeleClanskeKarte) pckf.getjTable1().getModel();
        return mtck.getLista().get(red);
    }

    private void addActionListener() {
        pckf.addBtnNazadActionListener(e -> Koordinator.getInstance().nazadNaGlavnuFormu(pckf));
        pckf.addBtnDodajActionListener(e -> Koordinator.getInstance().otvoriDodajClanskuKartuFormu());
        pckf.addBtnPretraziActionListener(e -> pretrazi());
        pckf.addBtnResetujActionListener(e -> pripremiFormu());
        pckf.addBtnNadjiActionListener(e -> nadji());
        pckf.addBtnAzurirajActionListener(e -> promeni());
        pckf.addBtnSacuvajJsonActionListener(e -> sacuvajJson());
    }

    private void sacuvajJson() {
        ClanskaKarta izTabele = selektovanaKarta();
        if (izTabele == null) {
            return;
        }
        try {
            ClanskaKarta karta = Komunikacija.getInstance().nadjiClanskuKartu(izTabele);
            File fajl = JsonFajlServis.izaberiFajlZaSnimanje(pckf,
                    "clanska_karta_" + karta.getIdClanskaKarta() + ".json");
            if (fajl == null) {
                return;
            }
            JsonFajlServis.snimi(karta, fajl);
            JOptionPane.showMessageDialog(pckf,
                    "Podaci o članskoj karti su sačuvani u JSON fajl.",
                    "Uspeh", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(pckf,
                    "Sistem ne može da sačuva podatke u JSON fajl.",
                    "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void pretrazi() {
        try {
            ClanskaKarta kriterijum = napraviKriterijum();
            List<ClanskaKarta> rezultat = Komunikacija.getInstance().pretraziClanskeKarte(kriterijum);
            if (rezultat == null || rezultat.isEmpty()) {
                JOptionPane.showMessageDialog(pckf,
                        "Sistem ne može da nađe članske karte po zadatim kriterijumima.",
                        "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            pckf.getjTable1().setModel(new ModelTabeleClanskeKarte(rezultat));
            pckf.getjTable2().setModel(new ModelTabeleStavkaClanskeKarte(new ArrayList<>()));
            JOptionPane.showMessageDialog(pckf,
                    "Sistem je našao članske karte po zadatim kriterijumima.",
                    "Uspeh", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(pckf,
                    "Sistem ne može da nađe članske karte po zadatim kriterijumima.",
                    "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private ClanskaKarta napraviKriterijum() {
        ClanskaKarta kriterijum = new ClanskaKarta();

        Polaznik polaznik = (Polaznik) pckf.getjComboBoxPolaznik().getSelectedItem();
        if (polaznik != null) {
            kriterijum.setPolaznik(polaznik);
        }

        Instruktor instruktor = (Instruktor) pckf.getjComboBoxInstruktor().getSelectedItem();
        if (instruktor != null) {
            kriterijum.setInstruktor(instruktor);
        }

        Sport sport = (Sport) pckf.getjComboBoxSport().getSelectedItem();
        if (sport != null) {
            StavkaClanskeKarte st = new StavkaClanskeKarte();
            st.setSport(sport);
            kriterijum.setStavke(Collections.singletonList(st));
        }
        return kriterijum;
    }

    private void nadji() {
        ClanskaKarta izTabele = selektovanaKarta();
        if (izTabele == null) return;
        try {
            ClanskaKarta pronadjena = Komunikacija.getInstance().nadjiClanskuKartu(izTabele);
            JOptionPane.showMessageDialog(pckf,
                    "Sistem je našao člansku kartu.",
                    "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            Koordinator.getInstance().dodajParam("clanskaKarta", pronadjena);
            Koordinator.getInstance().otvoriPregledClanskeKarteFormu();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(pckf,
                    "Sistem ne može da nađe člansku kartu.",
                    "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void promeni() {
        ClanskaKarta izTabele = selektovanaKarta();
        if (izTabele == null) return;
        try {
            ClanskaKarta pronadjena = Komunikacija.getInstance().nadjiClanskuKartu(izTabele);
            JOptionPane.showMessageDialog(pckf,
                    "Sistem je našao člansku kartu.",
                    "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            Koordinator.getInstance().dodajParam("clanskaKarta", pronadjena);
            Koordinator.getInstance().otvoriIzmeniClanskuKartuFormu();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(pckf,
                    "Sistem ne može da nađe člansku kartu.",
                    "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void prikaziStavke(ClanskaKarta ck) {
        List<StavkaClanskeKarte> stavke = ck.getStavke() != null ? new ArrayList<>(ck.getStavke()) : new ArrayList<>();
        ModelTabeleStavkaClanskeKarte model = new ModelTabeleStavkaClanskeKarte(stavke);
        model.dodeliRb();
        pckf.getjTable2().setModel(model);
    }

    public void osveziFormu() {
        pripremiFormu();
    }

    private void addMouseListener() {
        pckf.getjTable1().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ClanskaKarta ck = selektovanaKarta();
                if (ck != null) {
                    ucitajIPrikaziStavke(ck);
                }
            }
        });
    }

    private void ucitajIPrikaziStavke(ClanskaKarta ck) {
        try {
            if (ck.getStavke() == null || ck.getStavke().isEmpty()) {
                ClanskaKarta puna = Komunikacija.getInstance().nadjiClanskuKartu(ck);
                ck.setStavke(puna.getStavke());
            }
            prikaziStavke(ck);
        } catch (Exception ex) {
            pckf.getjTable2().setModel(new ModelTabeleStavkaClanskeKarte(new ArrayList<>()));
        }
    }
}
