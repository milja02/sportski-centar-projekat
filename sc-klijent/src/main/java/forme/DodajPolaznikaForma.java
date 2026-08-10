package forme;

import domen.Mesto;
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

public class DodajPolaznikaForma extends javax.swing.JFrame {

    public DodajPolaznikaForma() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new JLabel("Ime");
        jTextField1 = new JTextField(18);
        jLabel2 = new JLabel("Prezime");
        jTextField2 = new JTextField(18);
        jTextField3 = new JTextField(18);
        jLabel3 = new JLabel("Telefon");
        jComboBox1 = new JComboBox<>();
        jLabel4 = new JLabel("Mesto");
        jButton1 = new JButton("Dodaj");
        jButtonIzmeni = new JButton("Izmeni");
        jButtonObrisi = new JButton("Obriši");
        jButtonNazad = new JButton("Nazad");
        jTextField4 = new JTextField(8);
        jLabel5 = new JLabel("ID");
        jButtonUveziJson = new JButton("Uvezi preko JSON-a");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sportski centar - Polaznik");
        jTextField4.setEditable(false);

        JPanel forma = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 6, 6, 6);
        c.anchor = GridBagConstraints.WEST;

        c.gridx = 0; c.gridy = 0; forma.add(jLabel5, c);
        c.gridx = 1; forma.add(jTextField4, c);
        c.gridx = 0; c.gridy = 1; forma.add(jLabel1, c);
        c.gridx = 1; forma.add(jTextField1, c);
        c.gridx = 0; c.gridy = 2; forma.add(jLabel2, c);
        c.gridx = 1; forma.add(jTextField2, c);
        c.gridx = 0; c.gridy = 3; forma.add(jLabel3, c);
        c.gridx = 1; forma.add(jTextField3, c);
        c.gridx = 0; c.gridy = 4; forma.add(jLabel4, c);
        c.gridx = 1; c.fill = GridBagConstraints.HORIZONTAL; forma.add(jComboBox1, c);

        JPanel akcije = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        akcije.add(jButtonNazad);
        akcije.add(jButtonObrisi);
        akcije.add(jButtonIzmeni);
        akcije.add(jButton1);
        akcije.add(jButtonUveziJson);

        JPanel north = new JPanel(new BorderLayout());
        north.setBorder(new EmptyBorder(16, 16, 8, 16));
        north.add(UiStil.naslov("Polaznik"), BorderLayout.NORTH);

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

        setMinimumSize(new Dimension(480, 360));
        pack();
        setLocationRelativeTo(null);
    }

    private JButton jButtonUveziJson;
    private JButton jButton1;
    private JButton jButtonIzmeni;
    private JButton jButtonObrisi;
    private JButton jButtonNazad;
    private JComboBox<String> jComboBox1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JTextField jTextField1;
    private JTextField jTextField2;
    private JTextField jTextField3;
    private JTextField jTextField4;

    public void dodajAddActionListener(ActionListener actionListener) {
        jButton1.addActionListener(actionListener);
    }

    public void izmeniAddActionListener(ActionListener actionListener) {
        jButtonIzmeni.addActionListener(actionListener);
    }

    public void obrisiAddActionListener(ActionListener actionListener) {
        jButtonObrisi.addActionListener(actionListener);
    }

    public void nazadAddActionListener(ActionListener actionListener) {
        jButtonNazad.addActionListener(actionListener);
    }

    public void uveziJsonAddActionListener(ActionListener actionListener) {
        jButtonUveziJson.addActionListener(actionListener);
    }

    public JButton getjButtonUveziJson() {
        return jButtonUveziJson;
    }

    public JTextField getjTextField1() {
        return jTextField1;
    }

    public JTextField getjTextField2() {
        return jTextField2;
    }

    public JTextField getjTextField3() {
        return jTextField3;
    }

    public JTextField getjTextField4() {
        return jTextField4;
    }

    public JButton getjButton1() {
        return jButton1;
    }

    public JButton getjButtonIzmeni() {
        return jButtonIzmeni;
    }

    public JButton getjButtonObrisi() {
        return jButtonObrisi;
    }

    public JLabel getjLabel5() {
        return jLabel5;
    }

    @SuppressWarnings("unchecked")
    public JComboBox<Mesto> getjComboBoxMesto() {
        return (JComboBox<Mesto>) (JComboBox<?>) jComboBox1;
    }
}
