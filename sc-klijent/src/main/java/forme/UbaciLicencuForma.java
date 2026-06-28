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
    private javax.swing.JButton jButtonUveziJson;
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
        jButtonUveziJson = new JButton("Uvezi preko JSON-a");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ubaci licencu");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelInstruktor)
                    .addComponent(jLabelLicenca)
                    .addComponent(jLabelDatumIzdavanja)
                    .addComponent(jLabelDatumIsteka))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxInstruktor, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxLicenca, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldDatumIzdavanja, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldDatumIsteka, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonUveziJson)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonNazad)
                        .addGap(8, 8, 8)
                        .addComponent(jButtonUbaci)))
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelInstruktor)
                    .addComponent(jComboBoxInstruktor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelLicenca)
                    .addComponent(jComboBoxLicenca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDatumIzdavanja)
                    .addComponent(jTextFieldDatumIzdavanja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDatumIsteka)
                    .addComponent(jTextFieldDatumIsteka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(jButtonUveziJson)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNazad)
                    .addComponent(jButtonUbaci))
                .addGap(12, 12, 12))
        );
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
