package forme;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class GlavnaForma extends javax.swing.JFrame {

    public GlavnaForma() {
        initComponents();
    }

    private void initComponents() {
        jLabelPozdrav = new JLabel();
        jLabelUlogovani = new JLabel();
        jButtonLogout = new JButton("Odjavi se");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sportski centar");

        jLabelPozdrav.setFont(new Font("Arial", Font.PLAIN, 16));
        jLabelPozdrav.setText("Zdravo,");

        jLabelUlogovani.setFont(new Font("Arial", Font.BOLD, 16));
        jLabelUlogovani.setText("-");

        jButtonLogout.addActionListener(e -> odjaviSe());

        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
        header.setBorder(new EmptyBorder(20, 24, 8, 24));
        header.add(jLabelPozdrav);
        header.add(Box.createHorizontalStrut(6));
        header.add(jLabelUlogovani);
        header.add(Box.createHorizontalGlue());
        header.add(jButtonLogout);

        JLabel naslov = new JLabel("Po\u010detna");
        naslov.setFont(new Font("Arial", Font.BOLD, 20));
        naslov.setBorder(new EmptyBorder(0, 24, 12, 24));

        JPanel kartice = new JPanel(new GridLayout(1, 3, 16, 0));
        kartice.setBorder(new EmptyBorder(8, 24, 24, 24));
        kartice.add(napraviKarticu(
                "Polaznici",
                "Pregled i unos polaznika",
                "Pregled", e -> otvoriPrikazPolaznika(),
                "Dodaj", e -> otvoriDodajPolaznika()));
        kartice.add(napraviKarticu(
                "\u010clanske karte",
                "Pregled i izrada karata",
                "Pregled", e -> otvoriPrikazClanskihKarata(),
                "Dodaj", e -> otvoriDodajClanskuKartu()));
        kartice.add(napraviKarticu(
                "Licence",
                "Pregled i unos licenci",
                "Pregled", e -> otvoriPrikazLicenci(),
                "Ubaci", e -> otvoriUbaciLicencu()));

        JPanel content = new JPanel(new BorderLayout());
        content.add(header, BorderLayout.NORTH);

        JPanel centar = new JPanel(new BorderLayout());
        centar.add(naslov, BorderLayout.NORTH);
        centar.add(kartice, BorderLayout.CENTER);
        content.add(centar, BorderLayout.CENTER);

        setContentPane(content);
        setMinimumSize(new Dimension(760, 420));
        setSize(820, 480);
        setLocationRelativeTo(null);
    }

    private JPanel napraviKarticu(
            String naslov,
            String opis,
            String tekstPrvog,
            java.awt.event.ActionListener akcijaPrvog,
            String tekstDrugog,
            java.awt.event.ActionListener akcijaDrugog) {

        JPanel kartica = new JPanel(new BorderLayout(0, 12));
        kartica.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createEtchedBorder(),
                        naslov,
                        TitledBorder.LEFT,
                        TitledBorder.TOP,
                        new Font("Arial", Font.BOLD, 14)),
                new EmptyBorder(12, 12, 12, 12)));

        JLabel lblOpis = new JLabel(opis);
        lblOpis.setFont(new Font("Arial", Font.PLAIN, 12));

        JButton btnPrvi = new JButton(tekstPrvog);
        btnPrvi.setPreferredSize(new Dimension(10, 34));
        btnPrvi.addActionListener(akcijaPrvog);

        JButton btnDrugi = new JButton(tekstDrugog);
        btnDrugi.setPreferredSize(new Dimension(10, 34));
        btnDrugi.addActionListener(akcijaDrugog);

        JPanel dugmad = new JPanel();
        dugmad.setLayout(new BoxLayout(dugmad, BoxLayout.Y_AXIS));
        btnPrvi.setAlignmentX(LEFT_ALIGNMENT);
        btnDrugi.setAlignmentX(LEFT_ALIGNMENT);
        btnPrvi.setMaximumSize(new Dimension(Integer.MAX_VALUE, 34));
        btnDrugi.setMaximumSize(new Dimension(Integer.MAX_VALUE, 34));
        dugmad.add(btnPrvi);
        dugmad.add(Box.createVerticalStrut(8));
        dugmad.add(btnDrugi);

        kartica.add(lblOpis, BorderLayout.NORTH);
        kartica.add(dugmad, BorderLayout.SOUTH);
        return kartica;
    }

    private void otvoriPrikazPolaznika() {
        koordinator.Koordinator.getInstance().otvoriPrikazPolaznikaFormu();
    }

    private void otvoriDodajPolaznika() {
        koordinator.Koordinator.getInstance().otvoriDodajPolaznikaFormu();
    }

    private void otvoriPrikazClanskihKarata() {
        koordinator.Koordinator.getInstance().otvoriPrikazClanskihKarataFormu();
    }

    private void otvoriDodajClanskuKartu() {
        koordinator.Koordinator.getInstance().otvoriDodajClanskuKartuFormu();
    }

    private void otvoriUbaciLicencu() {
        koordinator.Koordinator.getInstance().otvoriUbaciLicencuFormu();
    }

    private void otvoriPrikazLicenci() {
        koordinator.Koordinator.getInstance().otvoriPrikazLicenciFormu();
    }

    private void odjaviSe() {
        koordinator.Koordinator.getInstance().odjaviSe();
    }

    public JLabel getjLabelUlogovani() {
        return jLabelUlogovani;
    }

    public void setjLabelUlogovani(JLabel jLabelUlogovani) {
        this.jLabelUlogovani = jLabelUlogovani;
    }

    private JLabel jLabelPozdrav;
    private JLabel jLabelUlogovani;
    private JButton jButtonLogout;
}
