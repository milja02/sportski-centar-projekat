package forme;

import domen.Instruktor;
import domen.Polaznik;
import domen.Sport;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class PrikazClanskihKarataForma extends javax.swing.JFrame {

    public PrikazClanskihKarataForma() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButtonObrisi = new javax.swing.JButton();
        jButtonAzuriraj = new javax.swing.JButton();
        jButtonDodaj = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabelPolaznik = new javax.swing.JLabel();
        jComboBoxPolaznik = new javax.swing.JComboBox<>();
        jLabelInstruktor = new javax.swing.JLabel();
        jComboBoxInstruktor = new javax.swing.JComboBox<>();
        jButtonPretrazi = new javax.swing.JButton();
        jButtonResetuj = new javax.swing.JButton();
        jLabelSport = new javax.swing.JLabel();
        jComboBoxSport = new javax.swing.JComboBox<>();
        jButtonNadji = new javax.swing.JButton();
        jButtonNazad = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jButtonObrisi.setText("Obrisi");

        jButtonAzuriraj.setText("Izmeni");

        jButtonDodaj.setText("Dodaj");

        jLabelPolaznik.setText("Polaznik:");
        jLabelInstruktor.setText("Instruktor:");
        jButtonPretrazi.setText("Pretrazi");
        jButtonResetuj.setText("Resetuj");
        jButtonNazad.setText("Nazad");
        jButtonNadji.setText("Nadji");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelPolaznik)
                        .addGap(5, 5, 5)
                        .addComponent(jComboBoxPolaznik, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabelInstruktor)
                        .addGap(5, 5, 5)
                        .addComponent(jComboBoxInstruktor, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabelSport)
                        .addGap(5, 5, 5)
                        .addComponent(jComboBoxSport, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jButtonPretrazi)
                        .addGap(10, 10, 10)
                        .addComponent(jButtonResetuj))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonNazad)
                        .addGap(10, 10, 10)
                        .addComponent(jButtonDodaj)
                        .addGap(10, 10, 10)
                        .addComponent(jButtonAzuriraj)
                        .addGap(10, 10, 10)
                        .addComponent(jButtonNadji)
                        .addGap(10, 10, 10)
                        .addComponent(jButtonObrisi)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPolaznik)
                    .addComponent(jComboBoxPolaznik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelInstruktor)
                    .addComponent(jComboBoxInstruktor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelSport)
                    .addComponent(jComboBoxSport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPretrazi)
                    .addComponent(jButtonResetuj))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDodaj)
                    .addComponent(jButtonAzuriraj)
                    .addComponent(jButtonNadji)
                    .addComponent(jButtonObrisi)
                    .addComponent(jButtonNazad))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }

    public JTable getjTable1() {
        return jTable1;
    }

    public JTable getjTable2() {
        return jTable2;
    }

    public JButton getjButtonDodaj() { return jButtonDodaj; }
    public JButton getjButtonAzuriraj() { return jButtonAzuriraj; }
    public JButton getjButtonObrisi() { return jButtonObrisi; }

    public JComboBox<Polaznik> getjComboBoxPolaznik() {
        return jComboBoxPolaznik;
    }

    public JComboBox<Instruktor> getjComboBoxInstruktor() {
        return jComboBoxInstruktor;
    }

    public JButton getjButtonPretrazi() { return jButtonPretrazi; }
    public JButton getjButtonResetuj() { return jButtonResetuj; }

    public void addBtnDodajActionListener(ActionListener l) { jButtonDodaj.addActionListener(l); }
    public void addBtnAzurirajActionListener(ActionListener l) { jButtonAzuriraj.addActionListener(l); }
    public void addBtnObrisiActionListener(ActionListener l) { jButtonObrisi.addActionListener(l); }
    public void addBtnPretraziActionListener(ActionListener l) { jButtonPretrazi.addActionListener(l); }
    public void addBtnResetujActionListener(ActionListener l) { jButtonResetuj.addActionListener(l); }
    public void addBtnNadjiActionListener(ActionListener l) { jButtonNadji.addActionListener(l); }
    public void addBtnNazadActionListener(ActionListener l) { jButtonNazad.addActionListener(l); }

    public JComboBox<Sport> getjComboBoxSport() {
        return jComboBoxSport;
    }

    private javax.swing.JButton jButtonAzuriraj;
    private javax.swing.JButton jButtonDodaj;
    private javax.swing.JButton jButtonObrisi;
    private javax.swing.JButton jButtonPretrazi;
    private javax.swing.JButton jButtonResetuj;
    private javax.swing.JButton jButtonNadji;
    private javax.swing.JButton jButtonNazad;
    private javax.swing.JComboBox<Sport> jComboBoxSport;
    private javax.swing.JComboBox<Polaznik> jComboBoxPolaznik;
    private javax.swing.JComboBox<Instruktor> jComboBoxInstruktor;
    private javax.swing.JLabel jLabelSport;
    private javax.swing.JLabel jLabelInstruktor;
    private javax.swing.JLabel jLabelPolaznik;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
}
