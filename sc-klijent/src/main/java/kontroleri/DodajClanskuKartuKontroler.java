package kontroleri;

import domen.ClanskaKarta;
import domen.Instruktor;
import domen.Polaznik;
import domen.Sport;
import domen.StavkaClanskeKarte;
import forme.DodajClanskuKartuForma;
import forme.FormaMod;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import klijent.Komunikacija;
import koordinator.Koordinator;
import modeli.ModelTabeleStavkaClanskeKarte;

public class DodajClanskuKartuKontroler {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd.MM.yyyy");
    private final DodajClanskuKartuForma forma;
    private FormaMod trenutniMod;

    public DodajClanskuKartuKontroler(DodajClanskuKartuForma forma) {
        this.forma = forma;
        addActionListeners();
        addAutoKalkulacijaListeneri();
    }

    public void otvoriFormu(FormaMod mod) {
        this.trenutniMod = mod;
        pripremiFormu(mod);
        forma.setVisible(true);
    }

    private void addActionListeners() {
        forma.dodajAddActionListener(e -> zapamti());
        forma.izmeniAddActionListener(e -> zapamti());
        forma.obrisiAddActionListener(e -> obrisiKartu());
        forma.dodajStavkuAddActionListener(e -> dodajStavku());
        forma.obrisiStavkuAddActionListener(e -> obrisiStavku());
        forma.nazadAddActionListener(e -> {
            forma.dispose();
            if (trenutniMod != FormaMod.PREGLED) {
                Koordinator.getInstance().nazadNaGlavnuFormu(null);
            }
        });
    }

    private void addAutoKalkulacijaListeneri() {
        forma.getjComboBoxSport().addActionListener(e -> {
            prikaziCenuSporta();
            izracunajIznosStavke();
        });
        forma.getjTextFieldBrojTermina().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { izracunajIznosStavke(); }
            @Override
            public void removeUpdate(DocumentEvent e) { izracunajIznosStavke(); }
            @Override
            public void changedUpdate(DocumentEvent e) { izracunajIznosStavke(); }
        });
    }

    private void prikaziCenuSporta() {
        Sport sport = (Sport) forma.getjComboBoxSport().getSelectedItem();
        if (sport != null) {
            forma.getjLabelCenaSporta().setText("Cena po terminu: " + sport.getCena());
        } else {
            forma.getjLabelCenaSporta().setText("Cena po terminu: -");
        }
    }

    private void izracunajIznosStavke() {
        Sport sport = (Sport) forma.getjComboBoxSport().getSelectedItem();
        if (sport == null) {
            forma.getjTextFieldIznosStavke().setText("");
            return;
        }
        try {
            int brojTermina = Integer.parseInt(forma.getjTextFieldBrojTermina().getText().trim());
            if (brojTermina > 0) {
                forma.getjTextFieldIznosStavke().setText(String.valueOf(brojTermina * sport.getCena()));
            } else {
                forma.getjTextFieldIznosStavke().setText("");
            }
        } catch (NumberFormatException e) {
            forma.getjTextFieldIznosStavke().setText("");
        }
    }

    private void osveziUkupanIznos() {
        ModelTabeleStavkaClanskeKarte model = (ModelTabeleStavkaClanskeKarte) forma.getjTableStavke().getModel();
        int zbir = 0;
        for (StavkaClanskeKarte s : model.getLista()) {
            zbir += s.getIznosStavke();
        }
        forma.getjTextFieldUkupanIznos().setText(String.valueOf(zbir));
    }

    private void ucitajInstruktoreUCombo() {
        try {
            List<Instruktor> instruktori = Komunikacija.getInstance().ucitajInstruktore();
            forma.getjComboBoxInstruktor().removeAllItems();
            if (instruktori != null) {
                for (Instruktor i : instruktori) {
                    forma.getjComboBoxInstruktor().addItem(i);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma,
                    "Sistem ne može da učita instruktore.",
                    "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void selektujInstruktora(Instruktor instruktor) {
        if (instruktor == null) return;
        for (int i = 0; i < forma.getjComboBoxInstruktor().getItemCount(); i++) {
            Instruktor uCombo = (Instruktor) forma.getjComboBoxInstruktor().getItemAt(i);
            if (uCombo.getIdInstruktor() == instruktor.getIdInstruktor()) {
                forma.getjComboBoxInstruktor().setSelectedIndex(i);
                return;
            }
        }
    }

    private void selektujPolaznika(Polaznik polaznik) {
        if (polaznik == null) return;
        for (int i = 0; i < forma.getjComboBoxPolaznik().getItemCount(); i++) {
            Polaznik uCombo = (Polaznik) forma.getjComboBoxPolaznik().getItemAt(i);
            if (uCombo.getIdPolaznik() == polaznik.getIdPolaznik()) {
                forma.getjComboBoxPolaznik().setSelectedIndex(i);
                return;
            }
        }
    }

    private void sakrijSvaDugmad() {
        forma.getjButtonDodaj().setVisible(false);
        forma.getjButtonIzmeni().setVisible(false);
        forma.getjButtonObrisi().setVisible(false);
    }

    private void postaviRezimUnosa(boolean omogucen) {
        forma.getjTextFieldDatum().setEditable(omogucen);
        forma.getjComboBoxPolaznik().setEnabled(omogucen);
        forma.getjComboBoxInstruktor().setEnabled(omogucen);
        forma.getjComboBoxSport().setEnabled(omogucen);
        forma.getjTextFieldBrojTermina().setEditable(omogucen);
        forma.setUnosStavkiVisible(omogucen);
    }

    private void postaviRezimPregleda() {
        forma.setTitle("Pregled članske karte");
        sakrijSvaDugmad();
        forma.getjButtonObrisi().setVisible(true);
        postaviRezimUnosa(false);
        ClanskaKarta ck = (ClanskaKarta) Koordinator.getInstance().vratiParam("clanskaKarta");
        popuniPodatkeKarte(ck);
    }

    private void popuniPodatkeKarte(ClanskaKarta ck) {
        forma.getjTextFieldId().setText(String.valueOf(ck.getIdClanskaKarta()));
        if (ck.getDatumUclanjenja() != null) {
            forma.getjTextFieldDatum().setText(SDF.format(ck.getDatumUclanjenja()));
        }
        selektujPolaznika(ck.getPolaznik());
        selektujInstruktora(ck.getInstruktor());
        List<StavkaClanskeKarte> stavke = ck.getStavke() != null ? new ArrayList<>(ck.getStavke()) : new ArrayList<>();
        ModelTabeleStavkaClanskeKarte model = new ModelTabeleStavkaClanskeKarte(stavke);
        model.dodeliRb();
        forma.getjTableStavke().setModel(model);
        osveziUkupanIznos();
    }

    private void pripremiFormu(FormaMod mod) {
        try {
            List<Polaznik> polaznici = Komunikacija.getInstance().ucitajPolaznike();
            forma.getjComboBoxPolaznik().removeAllItems();
            if (polaznici != null) {
                for (Polaznik p : polaznici) {
                    forma.getjComboBoxPolaznik().addItem(p);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma,
                    "Sistem ne može da učita polaznike.",
                    "Greška", JOptionPane.ERROR_MESSAGE);
        }
        try {
            List<Sport> sportovi = Komunikacija.getInstance().ucitajSportove();
            forma.getjComboBoxSport().removeAllItems();
            if (sportovi != null) {
                for (Sport s : sportovi) {
                    forma.getjComboBoxSport().addItem(s);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma,
                    "Sistem ne može da učita sportove.",
                    "Greška", JOptionPane.ERROR_MESSAGE);
        }
        ucitajInstruktoreUCombo();

        forma.getjTableStavke().setModel(new ModelTabeleStavkaClanskeKarte(new ArrayList<>()));
        forma.getjTextFieldUkupanIznos().setEditable(false);
        forma.getjTextFieldIznosStavke().setEditable(false);
        sakrijSvaDugmad();
        prikaziCenuSporta();

        if (mod == FormaMod.PREGLED) {
            postaviRezimPregleda();
            return;
        }

        postaviRezimUnosa(true);

        if (mod == FormaMod.DODAJ) {
            forma.setTitle("Članska karta - unos");
            forma.getjButtonDodaj().setVisible(true);
            ClanskaKarta nova = (ClanskaKarta) Koordinator.getInstance().vratiParam("novaClanskaKarta");
            if (nova != null) {
                forma.getjTextFieldId().setText(String.valueOf(nova.getIdClanskaKarta()));
            } else {
                forma.getjTextFieldId().setText("");
            }
            forma.getjTextFieldDatum().setText(SDF.format(new Date()));
            forma.getjTextFieldUkupanIznos().setText("0");
            Instruktor ulogovani = Koordinator.getInstance().getUlogovani();
            selektujInstruktora(ulogovani);
        } else {
            forma.setTitle("Članska karta - izmena");
            forma.getjButtonIzmeni().setVisible(true);
            ClanskaKarta ck = (ClanskaKarta) Koordinator.getInstance().vratiParam("clanskaKarta");
            popuniPodatkeKarte(ck);
        }
    }

    private void obrisiKartu() {
        try {
            ClanskaKarta ck = (ClanskaKarta) Koordinator.getInstance().vratiParam("clanskaKarta");
            if (ck == null) {
                ck = napraviClanskuKartuIzForme();
            }
            if (ck == null) return;
            int id = Integer.parseInt(forma.getjTextFieldId().getText().trim());
            ck.setIdClanskaKarta(id);
            Komunikacija.getInstance().obrisiClanskuKartu(ck);
            JOptionPane.showMessageDialog(forma,
                    "Sistem je obrisao člansku kartu.",
                    "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            forma.dispose();
            Koordinator.getInstance().osveziFormuClanskeKarte();
        } catch (Exception ex) {
            String poruka = ex.getMessage() != null ? ex.getMessage() : "Sistem ne može da obriše člansku kartu.";
            JOptionPane.showMessageDialog(forma, poruka, "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void zapamti() {
        try {
            ClanskaKarta ck = napraviClanskuKartuIzForme();
            if (ck == null) return;
            int id = Integer.parseInt(forma.getjTextFieldId().getText().trim());
            ck.setIdClanskaKarta(id);
            Komunikacija.getInstance().zapamtiClanskuKartu(ck);
            JOptionPane.showMessageDialog(forma, "Sistem je zapamtio člansku kartu.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            forma.dispose();
            Koordinator.getInstance().osveziFormuClanskeKarte();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne može da zapamti člansku kartu.", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private ClanskaKarta napraviClanskuKartuIzForme() {
        String datumStr = forma.getjTextFieldDatum().getText().trim();
        if (datumStr.isEmpty()) {
            JOptionPane.showMessageDialog(forma, "Unesite datum učlanjenja.", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        Date datum;
        try {
            datum = SDF.parse(datumStr);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(forma,
                    "Datum mora biti u formatu dd.MM.yyyy (npr. 19.05.2026).",
                    "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        Polaznik polaznik = (Polaznik) forma.getjComboBoxPolaznik().getSelectedItem();
        if (polaznik == null) {
            JOptionPane.showMessageDialog(forma, "Izaberite polaznika.", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        Instruktor instruktor = (Instruktor) forma.getjComboBoxInstruktor().getSelectedItem();
        if (instruktor == null) {
            JOptionPane.showMessageDialog(forma, "Izaberite instruktora.", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        ModelTabeleStavkaClanskeKarte model = (ModelTabeleStavkaClanskeKarte) forma.getjTableStavke().getModel();
        int ukupanIznos = 0;
        for (StavkaClanskeKarte s : model.getLista()) {
            ukupanIznos += s.getIznosStavke();
        }
        ClanskaKarta ck = new ClanskaKarta(-1, datum, ukupanIznos, instruktor, polaznik);
        ck.setStavke(new ArrayList<>(model.getLista()));
        return ck;
    }

    private void dodajStavku() {
        try {
            Sport sport = (Sport) forma.getjComboBoxSport().getSelectedItem();
            if (sport == null) {
                JOptionPane.showMessageDialog(forma, "Izaberite sport.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String brojStr = forma.getjTextFieldBrojTermina().getText().trim();
            if (brojStr.isEmpty()) {
                JOptionPane.showMessageDialog(forma, "Unesite broj termina.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int brojTermina = Integer.parseInt(brojStr);
            if (brojTermina <= 0) {
                JOptionPane.showMessageDialog(forma, "Broj termina mora biti veći od nule.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int iznosStavke = brojTermina * sport.getCena();

            StavkaClanskeKarte s = new StavkaClanskeKarte();
            s.setSport(sport);
            s.setBrojTermina(brojTermina);
            s.setIznosStavke(iznosStavke);

            ModelTabeleStavkaClanskeKarte model = (ModelTabeleStavkaClanskeKarte) forma.getjTableStavke().getModel();
            model.getLista().add(s);
            model.dodeliRb();
            osveziUkupanIznos();

            forma.getjTextFieldBrojTermina().setText("");
            forma.getjTextFieldIznosStavke().setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(forma, "Broj termina mora biti ceo broj.", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void obrisiStavku() {
        int red = forma.getjTableStavke().getSelectedRow();
        if (red == -1) {
            JOptionPane.showMessageDialog(forma, "Selektujte stavku za brisanje.", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }
        ModelTabeleStavkaClanskeKarte model = (ModelTabeleStavkaClanskeKarte) forma.getjTableStavke().getModel();
        model.getLista().remove(red);
        model.dodeliRb();
        osveziUkupanIznos();
    }
}
