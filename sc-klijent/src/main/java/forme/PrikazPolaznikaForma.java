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
        jButtonSacuvajJson = new javax.swing.JButton();

        jTablePolaznici.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][] {}, new String[] {"Title 1", "Title 2", "Title 3", "Title 4"}
        ));
        jScrollPane1.setViewportView(jTablePolaznici);

        jButtonIzmeni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIzmeniActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pregled polaznika");

        jLabel1.setText("Ime");
        jLabel2.setText("Prezime");
        jLabel3.setText("Telefon");
        jLabelMesto.setText("Mesto");
        jButton1.setText("Pretrazi");
        jButtonResetuj.setText("Resetuj");
        jButtonNadji.setText("Otvori");
        jButtonNazad.setText("Nazad");
        jButtonObrisi.setText("Obrisi");
        jButtonIzmeni.setText("Izmeni");
        jButtonSacuvajJson.setText("Sačuvaj JSON");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(4, 4, 4)
                        .addComponent(jTextFieldIme, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel2)
                        .addGap(4, 4, 4)
                        .addComponent(jTextFieldPrezime, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel3)
                        .addGap(4, 4, 4)
                        .addComponent(jTextFieldBrojTelefona, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabelMesto)
                        .addGap(4, 4, 4)
                        .addComponent(jComboBoxMesto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonResetuj)
                        .addGap(8, 8, 8)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonNazad)
                        .addGap(8, 8, 8)
                        .addComponent(jButtonObrisi)
                        .addGap(8, 8, 8)
                        .addComponent(jButtonNadji)
                        .addGap(8, 8, 8)
                        .addComponent(jButtonIzmeni)
                        .addGap(8, 8, 8)
                        .addComponent(jButtonSacuvajJson)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldIme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldPrezime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldBrojTelefona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMesto)
                    .addComponent(jComboBoxMesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonResetuj)
                    .addComponent(jButton1))
                .addGap(8, 8, 8)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNazad)
                    .addComponent(jButtonObrisi)
                    .addComponent(jButtonNadji)
                    .addComponent(jButtonIzmeni)
                    .addComponent(jButtonSacuvajJson))
                .addGap(12, 12, 12))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void jButtonIzmeniActionPerformed(java.awt.event.ActionEvent evt) {
    }

    public JTable getjTablePolaznici() {
        return jTablePolaznici;
    }

    private javax.swing.JButton jButtonSacuvajJson;
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

    public void addBtnSacuvajJsonActionListener(ActionListener actionListener) {
        jButtonSacuvajJson.addActionListener(actionListener);
    }
}
