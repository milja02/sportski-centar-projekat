package forme;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import ui.UiStil;

public class PrikazLicenciForma extends javax.swing.JFrame {

    private JScrollPane jScrollPaneLicence;
    private JTable jTableLicence;
    private JButton jButtonDodaj;
    private JButton jButtonNazad;

    public PrikazLicenciForma() {
        initComponents();
    }

    private void initComponents() {
        jTableLicence = new JTable();
        jButtonDodaj = new JButton("Dodaj");
        jButtonNazad = new JButton("Nazad");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sportski centar - Pregled licenci");

        jTableLicence.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {},
                new String[] {"ID", "Tip licence", "Nivo kvalifikacije"}));
        jScrollPaneLicence = UiStil.tabelaScroll(jTableLicence);

        JPanel north = new JPanel(new BorderLayout());
        north.setBorder(new EmptyBorder(16, 20, 8, 20));
        north.add(UiStil.naslov("Pregled licenci"), BorderLayout.NORTH);

        JPanel center = new JPanel(new BorderLayout());
        center.setBorder(new EmptyBorder(4, 20, 0, 20));
        center.add(jScrollPaneLicence, BorderLayout.CENTER);

        JPanel south = UiStil.actionBar(jButtonNazad, jButtonDodaj);

        JPanel content = new JPanel(new BorderLayout());
        content.add(north, BorderLayout.NORTH);
        content.add(center, BorderLayout.CENTER);
        content.add(south, BorderLayout.SOUTH);
        setContentPane(content);

        setMinimumSize(new Dimension(720, 480));
        setSize(800, 560);
        setLocationRelativeTo(null);
    }

    public JTable getjTableLicence() {
        return jTableLicence;
    }

    public void addDodajActionListener(ActionListener l) {
        jButtonDodaj.addActionListener(l);
    }

    public void addNazadActionListener(ActionListener l) {
        jButtonNazad.addActionListener(l);
    }
}
