package forme;

import domen.Instruktor;
import domen.Polaznik;
import domen.Sport;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import ui.UiStil;

public class PrikazClanskihKarataForma extends javax.swing.JFrame {

    public PrikazClanskihKarataForma() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jTable1 = new JTable();
        jButtonObrisi = new JButton("Obri\u0161i");
        jButtonAzuriraj = new JButton("Izmeni");
        jButtonDodaj = new JButton("Dodaj");
        jTable2 = new JTable();
        jLabelPolaznik = new JLabel("Polaznik");
        jComboBoxPolaznik = new JComboBox<>();
        jLabelInstruktor = new JLabel("Instruktor");
        jComboBoxInstruktor = new JComboBox<>();
        jButtonPretrazi = new JButton("Pretra\u017ei");
        jButtonResetuj = new JButton("Resetuj");
        jLabelSport = new JLabel("Sport");
        jComboBoxSport = new JComboBox<>();
        jButtonNadji = new JButton("Otvori");
        jButtonNazad = new JButton("Nazad");
        jButtonSacuvajJson = new JButton("Sa\u010duvaj JSON");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sportski centar - Pregled \u010dlanskih karata");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {}, new String[] {"Title 1", "Title 2", "Title 3", "Title 4"}));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {}, new String[] {"Title 1", "Title 2", "Title 3", "Title 4"}));

        UiStil.velikiCombo(jComboBoxPolaznik);
        UiStil.velikiCombo(jComboBoxInstruktor);
        UiStil.velikiCombo(jComboBoxSport);

        jScrollPane1 = UiStil.tabelaScroll(jTable1);
        jScrollPane2 = UiStil.tabelaScroll(jTable2);

        JPanel filterSadrzaj = new JPanel(new BorderLayout(16, 8));
        filterSadrzaj.setOpaque(false);

        JPanel polja = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 4));
        polja.setOpaque(false);
        polja.add(UiStil.poljeSaLabelom("Polaznik", jComboBoxPolaznik));
        polja.add(UiStil.poljeSaLabelom("Instruktor", jComboBoxInstruktor));
        polja.add(UiStil.poljeSaLabelom("Sport", jComboBoxSport));

        JPanel dugmadWrap = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        dugmadWrap.setOpaque(false);
        dugmadWrap.add(jButtonResetuj);
        dugmadWrap.add(jButtonPretrazi);
        JPanel desno = new JPanel(new BorderLayout());
        desno.setOpaque(false);
        desno.add(Box.createVerticalStrut(18), BorderLayout.NORTH);
        desno.add(dugmadWrap, BorderLayout.CENTER);

        filterSadrzaj.add(polja, BorderLayout.CENTER);
        filterSadrzaj.add(desno, BorderLayout.EAST);

        JPanel levo = new JPanel(new BorderLayout());
        levo.setBorder(new EmptyBorder(0, 0, 0, 4));
        levo.add(UiStil.sekcijaLabel("\u010clanske karte"), BorderLayout.NORTH);
        levo.add(jScrollPane1, BorderLayout.CENTER);

        JPanel desnoTabela = new JPanel(new BorderLayout());
        desnoTabela.setBorder(new EmptyBorder(0, 4, 0, 0));
        desnoTabela.add(UiStil.sekcijaLabel("Stavke"), BorderLayout.NORTH);
        desnoTabela.add(jScrollPane2, BorderLayout.CENTER);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, levo, desnoTabela);
        split.setResizeWeight(0.65);
        split.setDividerLocation(640);
        split.setBorder(null);
        split.setContinuousLayout(true);

        JPanel north = new JPanel(new BorderLayout(0, 8));
        north.setBorder(new EmptyBorder(16, 20, 0, 20));
        north.add(UiStil.naslov("Pregled \u010dlanskih karata"), BorderLayout.NORTH);
        north.add(UiStil.filterPanel(filterSadrzaj), BorderLayout.CENTER);

        JPanel center = new JPanel(new BorderLayout());
        center.setBorder(new EmptyBorder(12, 20, 0, 20));
        center.add(split, BorderLayout.CENTER);

        JPanel south = UiStil.actionBar(
                jButtonNazad, jButtonDodaj, jButtonAzuriraj, jButtonNadji, jButtonObrisi, jButtonSacuvajJson);

        JPanel content = new JPanel(new BorderLayout());
        content.add(north, BorderLayout.NORTH);
        content.add(center, BorderLayout.CENTER);
        content.add(south, BorderLayout.SOUTH);
        setContentPane(content);

        setMinimumSize(new Dimension(1000, 600));
        setSize(1100, 680);
        setLocationRelativeTo(null);
    }

    public JTable getjTable1() {
        return jTable1;
    }

    public JTable getjTable2() {
        return jTable2;
    }

    public JButton getjButtonDodaj() { return jButtonDodaj; }
    public JButton getjButtonAzuriraj() { return jButtonAzuriraj; }
    public JButton getjButtonObrisi() { return jButtonObrisi; }

    public JComboBox<Polaznik> getjComboBoxPolaznik() {
        return jComboBoxPolaznik;
    }

    public JComboBox<Instruktor> getjComboBoxInstruktor() {
        return jComboBoxInstruktor;
    }

    public JButton getjButtonPretrazi() { return jButtonPretrazi; }
    public JButton getjButtonResetuj() { return jButtonResetuj; }

    public void addBtnDodajActionListener(ActionListener l) { jButtonDodaj.addActionListener(l); }
    public void addBtnAzurirajActionListener(ActionListener l) { jButtonAzuriraj.addActionListener(l); }
    public void addBtnObrisiActionListener(ActionListener l) { jButtonObrisi.addActionListener(l); }
    public void addBtnPretraziActionListener(ActionListener l) { jButtonPretrazi.addActionListener(l); }
    public void addBtnResetujActionListener(ActionListener l) { jButtonResetuj.addActionListener(l); }
    public void addBtnNadjiActionListener(ActionListener l) { jButtonNadji.addActionListener(l); }
    public void addBtnNazadActionListener(ActionListener l) { jButtonNazad.addActionListener(l); }
    public void addBtnSacuvajJsonActionListener(ActionListener l) { jButtonSacuvajJson.addActionListener(l); }

    public JComboBox<Sport> getjComboBoxSport() {
        return jComboBoxSport;
    }

    private JButton jButtonSacuvajJson;
    private JButton jButtonAzuriraj;
    private JButton jButtonDodaj;
    private JButton jButtonObrisi;
    private JButton jButtonPretrazi;
    private JButton jButtonResetuj;
    private JButton jButtonNadji;
    private JButton jButtonNazad;
    private JComboBox<Sport> jComboBoxSport;
    private JComboBox<Polaznik> jComboBoxPolaznik;
    private JComboBox<Instruktor> jComboBoxInstruktor;
    private JLabel jLabelSport;
    private JLabel jLabelInstruktor;
    private JLabel jLabelPolaznik;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JTable jTable1;
    private JTable jTable2;
}
