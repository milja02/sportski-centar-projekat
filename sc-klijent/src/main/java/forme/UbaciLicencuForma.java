package forme;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class UbaciLicencuForma extends javax.swing.JFrame {

    private javax.swing.JLabel jLabelInstruktor;
    private javax.swing.JLabel jLabelLicenca;
    private javax.swing.JLabel jLabelDatumIzdavanja;
    private javax.swing.JLabel jLabelDatumIsteka;
    private javax.swing.JComboBox<Object> jComboBoxInstruktor;
    private javax.swing.JComboBox<Object> jComboBoxLicenca;
    private javax.swing.JTextField jTextFieldDatumIzdavanja;
    private javax.swing.JTextField jTextFieldDatumIsteka;
    private javax.swing.JButton jButtonUbaci;
    private javax.swing.JButton jButtonNazad;

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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ubaci licencu (SK21)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelInstruktor)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxInstruktor, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelLicenca)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxLicenca, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelDatumIzdavanja)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldDatumIzdavanja, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelDatumIsteka)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldDatumIsteka, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonNazad)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonUbaci)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelInstruktor)
                    .addComponent(jComboBoxInstruktor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelLicenca)
                    .addComponent(jComboBoxLicenca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDatumIzdavanja)
                    .addComponent(jTextFieldDatumIzdavanja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDatumIsteka)
                    .addComponent(jTextFieldDatumIsteka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNazad)
                    .addComponent(jButtonUbaci))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        pack();
    }

    public JComboBox<Object> getjComboBoxInstruktor() { return jComboBoxInstruktor; }
    public JComboBox<Object> getjComboBoxLicenca() { return jComboBoxLicenca; }
    public JTextField getjTextFieldDatumIzdavanja() { return jTextFieldDatumIzdavanja; }
    public JTextField getjTextFieldDatumIsteka() { return jTextFieldDatumIsteka; }
    public JButton getjButtonUbaci() { return jButtonUbaci; }

    public void addUbaciActionListener(ActionListener l) { jButtonUbaci.addActionListener(l); }
    public void addNazadActionListener(ActionListener l) { jButtonNazad.addActionListener(l); }
}
