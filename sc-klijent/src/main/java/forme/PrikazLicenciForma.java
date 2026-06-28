package forme;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PrikazLicenciForma extends javax.swing.JFrame {

    private JScrollPane jScrollPaneLicence;
    private JTable jTableLicence;
    private JButton jButtonDodaj;
    private JButton jButtonNazad;

    public PrikazLicenciForma() {
        initComponents();
    }

    private void initComponents() {
        jScrollPaneLicence = new JScrollPane();
        jTableLicence = new JTable();
        jButtonDodaj = new JButton("Dodaj");
        jButtonNazad = new JButton("Nazad");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pregled licenci");

        jTableLicence.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {},
                new String[] {"ID", "Tip licence", "Nivo kvalifikacije"}
        ));
        jScrollPaneLicence.setViewportView(jTableLicence);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneLicence, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonNazad)
                        .addGap(8, 8, 8)
                        .addComponent(jButtonDodaj)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jScrollPaneLicence, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNazad)
                    .addComponent(jButtonDodaj))
                .addGap(12, 12, 12))
        );
        pack();
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
