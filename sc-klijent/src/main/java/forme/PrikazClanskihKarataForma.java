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

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][] {}, new String[] {"Title 1", "Title 2", "Title 3", "Title 4"}
        ));
        jScrollPane1.setViewportView(jTable1);
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][] {}, new String[] {"Title 1", "Title 2", "Title 3", "Title 4"}
        ));
        jScrollPane2.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pregled clanskih karata");

        jLabelPolaznik.setText("Polaznik");
        jLabelInstruktor.setText("Instruktor");
        jLabelSport.setText("Sport");
        jButtonPretrazi.setText("Pretrazi");
        jButtonResetuj.setText("Resetuj");
        jButtonNazad.setText("Nazad");
        jButtonNadji.setText("Otvori");
        jButtonDodaj.setText("Dodaj");
        jButtonAzuriraj.setText("Izmeni");
        jButtonObrisi.setText("Obrisi");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelPolaznik)
                        .addGap(4, 4, 4)
                        .addComponent(jComboBoxPolaznik, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabelInstruktor)
                        .addGap(4, 4, 4)
                        .addComponent(jComboBoxInstruktor, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabelSport)
                        .addGap(4, 4, 4)
                        .addComponent(jComboBoxSport, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonResetuj)
                        .addGap(8, 8, 8)
                        .addComponent(jButtonPretrazi))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonNazad)
                        .addGap(8, 8, 8)
                        .addComponent(jButtonDodaj)
                        .addGap(8, 8, 8)
                        .addComponent(jButtonAzuriraj)
                        .addGap(8, 8, 8)
                        .addComponent(jButtonNadji)
                        .addGap(8, 8, 8)
                        .addComponent(jButtonObrisi)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPolaznik)
                    .addComponent(jComboBoxPolaznik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelInstruktor)
                    .addComponent(jComboBoxInstruktor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelSport)
                    .addComponent(jComboBoxSport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonResetuj)
                    .addComponent(jButtonPretrazi))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNazad)
                    .addComponent(jButtonDodaj)
                    .addComponent(jButtonAzuriraj)
                    .addComponent(jButtonNadji)
                    .addComponent(jButtonObrisi))
                .addGap(12, 12, 12))
        );

        pack();
        setLocationRelativeTo(null);
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
