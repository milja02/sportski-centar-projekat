package forme;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class DodajClanskuKartuForma extends javax.swing.JFrame {

    private javax.swing.JButton jButtonDodaj;
    private javax.swing.JButton jButtonIzmeni;
    private javax.swing.JButton jButtonDodajStavku;
    private javax.swing.JButton jButtonObrisiStavku;
    private javax.swing.JButton jButtonObrisi;
    private javax.swing.JButton jButtonNazad;
    private javax.swing.JComboBox<Object> jComboBoxPolaznik;
    private javax.swing.JLabel jLabelDatum;
    private javax.swing.JLabel jLabelId;
    private javax.swing.JLabel jLabelInstruktor;
    private javax.swing.JComboBox<Object> jComboBoxInstruktor;
    private javax.swing.JLabel jLabelPolaznik;
    private javax.swing.JLabel jLabelUkupanIznos;
    private javax.swing.JLabel jLabelSport;
    private javax.swing.JLabel jLabelBrojTermina;
    private javax.swing.JLabel jLabelIznosStavke;
    private javax.swing.JLabel jLabelCenaSporta;
    private javax.swing.JScrollPane jScrollPaneStavke;
    private javax.swing.JTable jTableStavke;
    private javax.swing.JTextField jTextFieldId;
    private javax.swing.JTextField jTextFieldDatum;
    private javax.swing.JTextField jTextFieldUkupanIznos;
    private javax.swing.JTextField jTextFieldBrojTermina;
    private javax.swing.JTextField jTextFieldIznosStavke;
    private javax.swing.JComboBox<Object> jComboBoxSport;

    public DodajClanskuKartuForma() {
        initComponents();
    }

    private void initComponents() {
        jLabelId = new JLabel("ID");
        jTextFieldId = new JTextField(10);
        jTextFieldId.setEditable(false);

        jLabelDatum = new JLabel("Datum učlanjenja (dd.MM.yyyy)");
        jTextFieldDatum = new JTextField(12);

        jLabelUkupanIznos = new JLabel("Ukupan iznos (zbir stavki)");
        jTextFieldUkupanIznos = new JTextField(10);
        jTextFieldUkupanIznos.setEditable(false);

        jLabelInstruktor = new JLabel("Instruktor");
        jComboBoxInstruktor = new JComboBox<>();
        jLabelPolaznik = new JLabel("Polaznik");
        jComboBoxPolaznik = new JComboBox<>();

        jTableStavke = new JTable();
        jScrollPaneStavke = new JScrollPane(jTableStavke);

        jLabelSport = new JLabel("Sport");
        jComboBoxSport = new JComboBox<>();
        jLabelCenaSporta = new JLabel("Cena po terminu: -");
        jLabelBrojTermina = new JLabel("Broj termina");
        jTextFieldBrojTermina = new JTextField(5);
        jLabelIznosStavke = new JLabel("Iznos stavke");
        jTextFieldIznosStavke = new JTextField(8);
        jTextFieldIznosStavke.setEditable(false);

        jButtonDodajStavku = new JButton("Dodaj stavku");
        jButtonObrisiStavku = new JButton("Obriši stavku");
        jButtonDodaj = new JButton("Zapamti");
        jButtonIzmeni = new JButton("Zapamti");
        jButtonObrisi = new JButton("Obrisi");
        jButtonNazad = new JButton("Nazad");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Članska karta");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelId)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelDatum)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldDatum, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelUkupanIznos)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldUkupanIznos, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelInstruktor)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxInstruktor, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelPolaznik)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxPolaznik, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPaneStavke, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelSport)
                        .addGap(10, 10, 10)
                        .addComponent(jComboBoxSport, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jLabelCenaSporta))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelBrojTermina)
                        .addGap(5, 5, 5)
                        .addComponent(jTextFieldBrojTermina, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelIznosStavke)
                        .addGap(5, 5, 5)
                        .addComponent(jTextFieldIznosStavke, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonDodajStavku)
                        .addGap(10, 10, 10)
                        .addComponent(jButtonObrisiStavku))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonNazad)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonObrisi)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonIzmeni)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonDodaj)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelId)
                    .addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDatum)
                    .addComponent(jTextFieldDatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUkupanIznos)
                    .addComponent(jTextFieldUkupanIznos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelInstruktor)
                    .addComponent(jComboBoxInstruktor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPolaznik)
                    .addComponent(jComboBoxPolaznik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jScrollPaneStavke, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSport)
                    .addComponent(jComboBoxSport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCenaSporta))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelBrojTermina)
                    .addComponent(jTextFieldBrojTermina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelIznosStavke)
                    .addComponent(jTextFieldIznosStavke, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDodajStavku)
                    .addComponent(jButtonObrisiStavku))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNazad)
                    .addComponent(jButtonObrisi)
                    .addComponent(jButtonIzmeni)
                    .addComponent(jButtonDodaj))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        pack();
    }

    public JTextField getjTextFieldId() { return jTextFieldId; }
    public JTextField getjTextFieldDatum() { return jTextFieldDatum; }
    public JTextField getjTextFieldUkupanIznos() { return jTextFieldUkupanIznos; }
    public JComboBox<Object> getjComboBoxPolaznik() { return jComboBoxPolaznik; }
    public JTable getjTableStavke() { return jTableStavke; }
    public JComboBox<Object> getjComboBoxSport() { return jComboBoxSport; }
    public JTextField getjTextFieldBrojTermina() { return jTextFieldBrojTermina; }
    public JTextField getjTextFieldIznosStavke() { return jTextFieldIznosStavke; }
    public JComboBox<Object> getjComboBoxInstruktor() { return jComboBoxInstruktor; }
    public JLabel getjLabelCenaSporta() { return jLabelCenaSporta; }
    public JButton getjButtonDodaj() { return jButtonDodaj; }
    public JButton getjButtonIzmeni() { return jButtonIzmeni; }
    public JButton getjButtonDodajStavku() { return jButtonDodajStavku; }
    public JButton getjButtonObrisiStavku() { return jButtonObrisiStavku; }
    public JButton getjButtonObrisi() { return jButtonObrisi; }
    public JButton getjButtonNazad() { return jButtonNazad; }

    public void dodajAddActionListener(ActionListener l) { jButtonDodaj.addActionListener(l); }
    public void obrisiAddActionListener(ActionListener l) { jButtonObrisi.addActionListener(l); }

    public void setUnosStavkiVisible(boolean visible) {
        jLabelSport.setVisible(visible);
        jComboBoxSport.setVisible(visible);
        jLabelCenaSporta.setVisible(visible);
        jLabelBrojTermina.setVisible(visible);
        jTextFieldBrojTermina.setVisible(visible);
        jLabelIznosStavke.setVisible(visible);
        jTextFieldIznosStavke.setVisible(visible);
        jButtonDodajStavku.setVisible(visible);
        jButtonObrisiStavku.setVisible(visible);
    }

    public void izmeniAddActionListener(ActionListener l) { jButtonIzmeni.addActionListener(l); }
    public void dodajStavkuAddActionListener(ActionListener l) { jButtonDodajStavku.addActionListener(l); }
    public void obrisiStavkuAddActionListener(ActionListener l) { jButtonObrisiStavku.addActionListener(l); }
    public void nazadAddActionListener(ActionListener l) { jButtonNazad.addActionListener(l); }
}
