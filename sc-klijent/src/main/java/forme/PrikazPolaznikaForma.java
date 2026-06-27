package forme;

import domen.Mesto;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

public class PrikazPolaznikaForma extends javax.swing.JFrame {

    public PrikazPolaznikaForma() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePolaznici = new javax.swing.JTable();
        jButtonObrisi = new javax.swing.JButton();
        jButtonIzmeni = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldIme = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldPrezime = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldBrojTelefona = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButtonResetuj = new javax.swing.JButton();
        jLabelMesto = new javax.swing.JLabel();
        jComboBoxMesto = new javax.swing.JComboBox<>();
        jButtonNadji = new javax.swing.JButton();
        jButtonNazad = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTablePolaznici.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTablePolaznici);

        jButtonObrisi.setText("Obrisi");

        jButtonIzmeni.setText("Izmeni");
        jButtonIzmeni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIzmeniActionPerformed(evt);
            }
        });

        jLabel1.setText("Ime");

        jLabel2.setText("Prezime");

        jLabel3.setText("Broj telefona");

        jButton1.setText("Pretrazi");

        jButtonResetuj.setText("Resetuj pretragu");
        jLabelMesto.setText("Mesto");
        jButtonNadji.setText("Otvori");
        jButtonNazad.setText("Nazad");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(92, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonResetuj)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButtonObrisi)
                            .addGap(18, 18, 18)
                            .addComponent(jButtonIzmeni))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextFieldIme, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(29, 29, 29)
                            .addComponent(jLabel2)
                            .addGap(18, 18, 18)
                            .addComponent(jTextFieldPrezime, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel3)
                            .addGap(18, 18, 18)
                            .addComponent(jTextFieldBrojTelefona, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabelMesto)
                            .addGap(18, 18, 18)
                            .addComponent(jComboBoxMesto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(76, 76, 76))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldIme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldPrezime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldBrojTelefona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMesto)
                    .addComponent(jComboBoxMesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButtonResetuj))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNazad)
                    .addComponent(jButtonObrisi)
                    .addComponent(jButtonNadji)
                    .addComponent(jButtonIzmeni))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        pack();
    }

    private void jButtonIzmeniActionPerformed(java.awt.event.ActionEvent evt) {
    }

    public JTable getjTablePolaznici() {
        return jTablePolaznici;
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonIzmeni;
    private javax.swing.JButton jButtonObrisi;
    private javax.swing.JButton jButtonResetuj;
    private javax.swing.JButton jButtonNadji;
    private javax.swing.JButton jButtonNazad;
    private javax.swing.JLabel jLabelMesto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablePolaznici;
    private javax.swing.JTextField jTextFieldBrojTelefona;
    private javax.swing.JTextField jTextFieldIme;
    private javax.swing.JTextField jTextFieldPrezime;
    private javax.swing.JComboBox<Mesto> jComboBoxMesto;

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
}
