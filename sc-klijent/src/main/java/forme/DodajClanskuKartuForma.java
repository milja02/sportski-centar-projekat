package forme;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import ui.UiStil;

public class DodajClanskuKartuForma extends javax.swing.JFrame {

    private JButton jButtonUveziJson;
    private JButton jButtonDodaj;
    private JButton jButtonIzmeni;
    private JButton jButtonDodajStavku;
    private JButton jButtonObrisiStavku;
    private JButton jButtonObrisi;
    private JButton jButtonNazad;
    private JComboBox<Object> jComboBoxPolaznik;
    private JLabel jLabelDatum;
    private JLabel jLabelId;
    private JLabel jLabelInstruktor;
    private JComboBox<Object> jComboBoxInstruktor;
    private JLabel jLabelPolaznik;
    private JLabel jLabelUkupanIznos;
    private JLabel jLabelSport;
    private JLabel jLabelBrojTermina;
    private JLabel jLabelIznosStavke;
    private JLabel jLabelCenaSporta;
    private JScrollPane jScrollPaneStavke;
    private JTable jTableStavke;
    private JTextField jTextFieldId;
    private JTextField jTextFieldDatum;
    private JTextField jTextFieldUkupanIznos;
    private JTextField jTextFieldBrojTermina;
    private JTextField jTextFieldIznosStavke;
    private JComboBox<Object> jComboBoxSport;
    private JPanel panelUnosStavke;

    public DodajClanskuKartuForma() {
        initComponents();
    }

    private void initComponents() {
        jLabelId = new JLabel("ID");
        jTextFieldId = new JTextField(10);
        jTextFieldId.setEditable(false);

        jLabelDatum = new JLabel("Datum učlanjenja (dd.MM.yyyy)");
        jTextFieldDatum = new JTextField(12);

        jLabelUkupanIznos = new JLabel("Ukupan iznos");
        jTextFieldUkupanIznos = new JTextField(10);
        jTextFieldUkupanIznos.setEditable(false);

        jLabelInstruktor = new JLabel("Instruktor");
        jComboBoxInstruktor = new JComboBox<>();
        jLabelPolaznik = new JLabel("Polaznik");
        jComboBoxPolaznik = new JComboBox<>();

        jTableStavke = new JTable();
        UiStil.stilTabela(jTableStavke);
        jScrollPaneStavke = new JScrollPane(jTableStavke);
        jScrollPaneStavke.setPreferredSize(new Dimension(520, 140));

        jLabelSport = new JLabel("Sport");
        jComboBoxSport = new JComboBox<>();
        jLabelCenaSporta = new JLabel("Cena po terminu: -");
        jLabelBrojTermina = new JLabel("Broj termina");
        jTextFieldBrojTermina = new JTextField(8);
        jTextFieldBrojTermina.setPreferredSize(new Dimension(90, 30));
        jTextFieldBrojTermina.setMinimumSize(new Dimension(70, 30));
        jTextFieldBrojTermina.setForeground(java.awt.Color.BLACK);
        jTextFieldBrojTermina.setCaretColor(java.awt.Color.BLACK);
        jTextFieldBrojTermina.setOpaque(true);
        jLabelIznosStavke = new JLabel("Iznos stavke");
        jTextFieldIznosStavke = new JTextField(10);
        jTextFieldIznosStavke.setPreferredSize(new Dimension(110, 30));
        jTextFieldIznosStavke.setMinimumSize(new Dimension(90, 30));
        jTextFieldIznosStavke.setEditable(false);

        jButtonDodajStavku = new JButton("Dodaj stavku");
        jButtonObrisiStavku = new JButton("Obriši stavku");
        jButtonDodaj = new JButton("Zapamti");
        jButtonIzmeni = new JButton("Zapamti");
        jButtonObrisi = new JButton("Obriši");
        jButtonNazad = new JButton("Nazad");
        jButtonUveziJson = new JButton("Uvezi preko JSON-a");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sportski centar - Članska karta");

        JPanel podaci = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 6, 6, 6);
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 0; c.gridy = 0; podaci.add(jLabelId, c);
        c.gridx = 1; podaci.add(jTextFieldId, c);
        c.gridx = 0; c.gridy = 1; podaci.add(jLabelDatum, c);
        c.gridx = 1; podaci.add(jTextFieldDatum, c);
        c.gridx = 0; c.gridy = 2; podaci.add(jLabelUkupanIznos, c);
        c.gridx = 1; podaci.add(jTextFieldUkupanIznos, c);
        c.gridx = 0; c.gridy = 3; podaci.add(jLabelInstruktor, c);
        c.gridx = 1; c.fill = GridBagConstraints.HORIZONTAL; podaci.add(jComboBoxInstruktor, c);
        c.gridx = 0; c.gridy = 4; c.fill = GridBagConstraints.NONE; podaci.add(jLabelPolaznik, c);
        c.gridx = 1; c.fill = GridBagConstraints.HORIZONTAL; podaci.add(jComboBoxPolaznik, c);

        panelUnosStavke = new JPanel(new GridBagLayout());
        GridBagConstraints s = new GridBagConstraints();
        s.insets = new Insets(4, 4, 4, 4);
        s.anchor = GridBagConstraints.WEST;
        s.fill = GridBagConstraints.NONE;
        s.gridy = 0;
        s.gridx = 0; panelUnosStavke.add(jLabelSport, s);
        s.gridx = 1; panelUnosStavke.add(jComboBoxSport, s);
        s.gridx = 2; panelUnosStavke.add(jLabelCenaSporta, s);
        s.gridy = 1;
        s.gridx = 0; panelUnosStavke.add(jLabelBrojTermina, s);
        s.gridx = 1; panelUnosStavke.add(jTextFieldBrojTermina, s);
        s.gridx = 2; panelUnosStavke.add(jLabelIznosStavke, s);
        s.gridx = 3; panelUnosStavke.add(jTextFieldIznosStavke, s);
        s.gridx = 4; panelUnosStavke.add(jButtonDodajStavku, s);
        s.gridx = 5; panelUnosStavke.add(jButtonObrisiStavku, s);

        JPanel stavke = new JPanel(new BorderLayout(0, 8));
        stavke.add(jScrollPaneStavke, BorderLayout.CENTER);
        stavke.add(panelUnosStavke, BorderLayout.SOUTH);

        JPanel akcije = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        akcije.add(jButtonNazad);
        akcije.add(jButtonObrisi);
        akcije.add(jButtonIzmeni);
        akcije.add(jButtonDodaj);
        akcije.add(jButtonUveziJson);

        JPanel north = new JPanel(new BorderLayout());
        north.setBorder(new EmptyBorder(16, 16, 8, 16));
        north.add(UiStil.naslov("Članska karta"), BorderLayout.NORTH);

        JPanel center = new JPanel(new BorderLayout(0, 12));
        center.setBorder(new EmptyBorder(0, 16, 8, 16));
        center.add(UiStil.okvir("Podaci o karti", podaci), BorderLayout.NORTH);
        center.add(UiStil.okvir("Stavke", stavke), BorderLayout.CENTER);

        JPanel south = new JPanel(new BorderLayout());
        south.setBorder(new EmptyBorder(0, 16, 16, 16));
        south.add(akcije, BorderLayout.WEST);

        JPanel content = new JPanel(new BorderLayout());
        content.add(north, BorderLayout.NORTH);
        content.add(center, BorderLayout.CENTER);
        content.add(south, BorderLayout.SOUTH);
        setContentPane(content);

        setMinimumSize(new Dimension(720, 560));
        pack();
        setLocationRelativeTo(null);
    }

    public JTextField getjTextFieldId() { return jTextFieldId; }
    public JTextField getjTextFieldDatum() { return jTextFieldDatum; }
    public JTextField getjTextFieldUkupanIznos() { return jTextFieldUkupanIznos; }
    public JComboBox<Object> getjComboBoxPolaznik() { return jComboBoxPolaznik; }
    public JTable getjTableStavke() { return jTableStavke; }
    public JComboBox<Object> getjComboBoxSport() { return jComboBoxSport; }
    public JTextField getjTextFieldBrojTermina() { return jTextFieldBrojTermina; }
    public JTextField getjTextFieldIznosStavke() { return jTextFieldIznosStavke; }
    public JComboBox<Object> getjComboBoxInstruktor() { return jComboBoxInstruktor; }
    public JLabel getjLabelCenaSporta() { return jLabelCenaSporta; }
    public JButton getjButtonDodaj() { return jButtonDodaj; }
    public JButton getjButtonIzmeni() { return jButtonIzmeni; }
    public JButton getjButtonDodajStavku() { return jButtonDodajStavku; }
    public JButton getjButtonObrisiStavku() { return jButtonObrisiStavku; }
    public JButton getjButtonObrisi() { return jButtonObrisi; }
    public JButton getjButtonNazad() { return jButtonNazad; }

    public void dodajAddActionListener(ActionListener l) { jButtonDodaj.addActionListener(l); }
    public void obrisiAddActionListener(ActionListener l) { jButtonObrisi.addActionListener(l); }

    public void setUnosStavkiVisible(boolean visible) {
        panelUnosStavke.setVisible(visible);
        jLabelSport.setVisible(visible);
        jComboBoxSport.setVisible(visible);
        jLabelCenaSporta.setVisible(visible);
        jLabelBrojTermina.setVisible(visible);
        jTextFieldBrojTermina.setVisible(visible);
        jLabelIznosStavke.setVisible(visible);
        jTextFieldIznosStavke.setVisible(visible);
        jButtonDodajStavku.setVisible(visible);
        jButtonObrisiStavku.setVisible(visible);
    }

    public void izmeniAddActionListener(ActionListener l) { jButtonIzmeni.addActionListener(l); }
    public void dodajStavkuAddActionListener(ActionListener l) { jButtonDodajStavku.addActionListener(l); }
    public void obrisiStavkuAddActionListener(ActionListener l) { jButtonObrisiStavku.addActionListener(l); }
    public void nazadAddActionListener(ActionListener l) { jButtonNazad.addActionListener(l); }
    public void uveziJsonAddActionListener(ActionListener l) { jButtonUveziJson.addActionListener(l); }
    public JButton getjButtonUveziJson() { return jButtonUveziJson; }
}
