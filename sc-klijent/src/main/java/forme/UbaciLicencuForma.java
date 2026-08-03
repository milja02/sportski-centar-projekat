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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import ui.UiStil;

public class UbaciLicencuForma extends javax.swing.JFrame {

    private JLabel jLabelInstruktor;
    private JLabel jLabelLicenca;
    private JLabel jLabelDatumIzdavanja;
    private JLabel jLabelDatumIsteka;
    private JComboBox<Object> jComboBoxInstruktor;
    private JComboBox<Object> jComboBoxLicenca;
    private JTextField jTextFieldDatumIzdavanja;
    private JTextField jTextFieldDatumIsteka;
    private JButton jButtonUveziJson;
    private JButton jButtonUbaci;
    private JButton jButtonNazad;

    public UbaciLicencuForma() {
        initComponents();
    }

    private void initComponents() {
        jLabelInstruktor = new JLabel("Instruktor");
        jLabelLicenca = new JLabel("Licenca");
        jLabelDatumIzdavanja = new JLabel("Datum izdavanja (dd.MM.yyyy)");
        jLabelDatumIsteka = new JLabel("Datum isteka (dd.MM.yyyy)");
        jComboBoxInstruktor = new JComboBox<>();
        jComboBoxLicenca = new JComboBox<>();
        jTextFieldDatumIzdavanja = new JTextField(12);
        jTextFieldDatumIsteka = new JTextField(12);
        jButtonUbaci = new JButton("Ubaci licencu");
        jButtonNazad = new JButton("Nazad");
        jButtonUveziJson = new JButton("Uvezi preko JSON-a");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sportski centar - Ubaci licencu");

        JPanel forma = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 6, 6, 6);
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 0; c.gridy = 0; forma.add(jLabelInstruktor, c);
        c.gridx = 1; c.fill = GridBagConstraints.HORIZONTAL; forma.add(jComboBoxInstruktor, c);
        c.gridx = 0; c.gridy = 1; c.fill = GridBagConstraints.NONE; forma.add(jLabelLicenca, c);
        c.gridx = 1; c.fill = GridBagConstraints.HORIZONTAL; forma.add(jComboBoxLicenca, c);
        c.gridx = 0; c.gridy = 2; c.fill = GridBagConstraints.NONE; forma.add(jLabelDatumIzdavanja, c);
        c.gridx = 1; forma.add(jTextFieldDatumIzdavanja, c);
        c.gridx = 0; c.gridy = 3; forma.add(jLabelDatumIsteka, c);
        c.gridx = 1; forma.add(jTextFieldDatumIsteka, c);

        JPanel akcije = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        akcije.add(jButtonNazad);
        akcije.add(jButtonUbaci);
        akcije.add(jButtonUveziJson);

        JPanel north = new JPanel(new BorderLayout());
        north.setBorder(new EmptyBorder(16, 16, 8, 16));
        north.add(UiStil.naslov("Ubaci licencu"), BorderLayout.NORTH);

        JPanel center = new JPanel(new BorderLayout());
        center.setBorder(new EmptyBorder(0, 16, 8, 16));
        center.add(UiStil.okvir("Podaci", forma), BorderLayout.CENTER);

        JPanel south = new JPanel(new BorderLayout());
        south.setBorder(new EmptyBorder(0, 16, 16, 16));
        south.add(akcije, BorderLayout.WEST);

        JPanel content = new JPanel(new BorderLayout());
        content.add(north, BorderLayout.NORTH);
        content.add(center, BorderLayout.CENTER);
        content.add(south, BorderLayout.SOUTH);
        setContentPane(content);

        setMinimumSize(new Dimension(480, 340));
        pack();
        setLocationRelativeTo(null);
    }

    public JComboBox<Object> getjComboBoxInstruktor() { return jComboBoxInstruktor; }
    public JComboBox<Object> getjComboBoxLicenca() { return jComboBoxLicenca; }
    public JTextField getjTextFieldDatumIzdavanja() { return jTextFieldDatumIzdavanja; }
    public JTextField getjTextFieldDatumIsteka() { return jTextFieldDatumIsteka; }
    public JButton getjButtonUbaci() { return jButtonUbaci; }

    public void addUbaciActionListener(ActionListener l) { jButtonUbaci.addActionListener(l); }
    public void addNazadActionListener(ActionListener l) { jButtonNazad.addActionListener(l); }
    public void uveziJsonAddActionListener(ActionListener l) { jButtonUveziJson.addActionListener(l); }
}
