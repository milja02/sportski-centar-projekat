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
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneLicence, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonNazad)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonDodaj)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPaneLicence, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNazad)
                    .addComponent(jButtonDodaj))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        pack();
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
