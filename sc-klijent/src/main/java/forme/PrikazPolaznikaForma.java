package forme;

import domen.Mesto;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import ui.UiStil;

public class PrikazPolaznikaForma extends javax.swing.JFrame {

    public PrikazPolaznikaForma() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jTablePolaznici = new JTable();
        jButtonObrisi = new JButton("Obri\u0161i");
        jButtonIzmeni = new JButton("Izmeni");
        jLabel1 = new JLabel("Ime");
        jTextFieldIme = new JTextField();
        jLabel2 = new JLabel("Prezime");
        jTextFieldPrezime = new JTextField();
        jLabel3 = new JLabel("Telefon");
        jTextFieldBrojTelefona = new JTextField();
        jButton1 = new JButton("Pretra\u017ei");
        jButtonResetuj = new JButton("Resetuj");
        jLabelMesto = new JLabel("Mesto");
        jComboBoxMesto = new JComboBox<>();
        jButtonNadji = new JButton("Otvori");
        jButtonNazad = new JButton("Nazad");
        jButtonSacuvajJson = new JButton("Sa\u010duvaj JSON");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sportski centar - Pregled polaznika");

        jTablePolaznici.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {}, new String[] {"Title 1", "Title 2", "Title 3", "Title 4"}));
        UiStil.velikoPolje(jTextFieldIme);
        UiStil.velikoPolje(jTextFieldPrezime);
        UiStil.velikoPolje(jTextFieldBrojTelefona);
        UiStil.velikiCombo(jComboBoxMesto);

        jButtonIzmeni.addActionListener(evt -> jButtonIzmeniActionPerformed(evt));

        JPanel filterSadrzaj = new JPanel(new BorderLayout(16, 8));
        filterSadrzaj.setOpaque(false);

        JPanel polja = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 4));
        polja.setOpaque(false);
        polja.add(UiStil.poljeSaLabelom("Ime", jTextFieldIme));
        polja.add(UiStil.poljeSaLabelom("Prezime", jTextFieldPrezime));
        polja.add(UiStil.poljeSaLabelom("Telefon", jTextFieldBrojTelefona));
        polja.add(UiStil.poljeSaLabelom("Mesto", jComboBoxMesto));

        JPanel filterDugmad = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 4));
        filterDugmad.setOpaque(false);
        filterDugmad.add(Box.createVerticalStrut(18));
        JPanel dugmadWrap = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        dugmadWrap.setOpaque(false);
        dugmadWrap.add(jButtonResetuj);
        dugmadWrap.add(jButton1);
        JPanel desno = new JPanel(new BorderLayout());
        desno.setOpaque(false);
        desno.add(Box.createVerticalStrut(18), BorderLayout.NORTH);
        desno.add(dugmadWrap, BorderLayout.CENTER);

        filterSadrzaj.add(polja, BorderLayout.CENTER);
        filterSadrzaj.add(desno, BorderLayout.EAST);

        jScrollPane1 = UiStil.tabelaScroll(jTablePolaznici);

        JPanel north = new JPanel(new BorderLayout(0, 8));
        north.setBorder(new EmptyBorder(16, 20, 0, 20));
        north.add(UiStil.naslov("Pregled polaznika"), BorderLayout.NORTH);
        north.add(UiStil.filterPanel(filterSadrzaj), BorderLayout.CENTER);

        JPanel center = new JPanel(new BorderLayout());
        center.setBorder(new EmptyBorder(12, 20, 0, 20));
        center.add(jScrollPane1, BorderLayout.CENTER);

        JPanel south = UiStil.actionBar(
                jButtonNazad, jButtonObrisi, jButtonNadji, jButtonIzmeni, jButtonSacuvajJson);

        JPanel content = new JPanel(new BorderLayout());
        content.add(north, BorderLayout.NORTH);
        content.add(center, BorderLayout.CENTER);
        content.add(south, BorderLayout.SOUTH);
        setContentPane(content);

        setMinimumSize(new Dimension(960, 580));
        setSize(1020, 640);
        setLocationRelativeTo(null);
    }

    private void jButtonIzmeniActionPerformed(java.awt.event.ActionEvent evt) {
    }

    public JTable getjTablePolaznici() {
        return jTablePolaznici;
    }

    private JButton jButtonSacuvajJson;
    private JButton jButton1;
    private JButton jButtonIzmeni;
    private JButton jButtonObrisi;
    private JButton jButtonResetuj;
    private JButton jButtonNadji;
    private JButton jButtonNazad;
    private JLabel jLabelMesto;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JScrollPane jScrollPane1;
    private JTable jTablePolaznici;
    private JTextField jTextFieldBrojTelefona;
    private JTextField jTextFieldIme;
    private JTextField jTextFieldPrezime;
    private JComboBox<Mesto> jComboBoxMesto;

    public JButton getjButtonObrisi() {
        return jButtonObrisi;
    }

    public void addBtnObrisiActionListener(ActionListener actionListener) {
        jButtonObrisi.addActionListener(actionListener);
    }

    public void addBtnIzmeniActionListener(ActionListener actionListener) {
        jButtonIzmeni.addActionListener(actionListener);
    }

    public void addBtnPretraziActionListener(ActionListener actionListener) {
        jButton1.addActionListener(actionListener);
    }

    public void addBtnResetujActionListener(ActionListener actionListener) {
        jButtonResetuj.addActionListener(actionListener);
    }

    public void addBtnNadjiActionListener(ActionListener actionListener) {
        jButtonNadji.addActionListener(actionListener);
    }

    public JTextField getjTextFieldBrojTelefona() {
        return jTextFieldBrojTelefona;
    }

    public JTextField getjTextFieldIme() {
        return jTextFieldIme;
    }

    public JTextField getjTextFieldPrezime() {
        return jTextFieldPrezime;
    }

    public JComboBox<Mesto> getjComboBoxMesto() {
        return jComboBoxMesto;
    }

    public void addBtnNazadActionListener(ActionListener actionListener) {
        jButtonNazad.addActionListener(actionListener);
    }

    public void addBtnSacuvajJsonActionListener(ActionListener actionListener) {
        jButtonSacuvajJson.addActionListener(actionListener);
    }
}
