package forme;

import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import server.Server;

public class ServerskaForma extends javax.swing.JFrame {
    private Server server;

    public ServerskaForma() {
        initComponents();
        jLabelStatus.setText("Zaustavljen");
        jLabelStatus.setForeground(new Color(180, 50, 50));
        jButtonZaustavi.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jButtonPokreni = new JButton();
        jButtonZaustavi = new JButton();
        jLabelBrand = new JLabel();
        jLabelSubtitle = new JLabel();
        jLabel1 = new JLabel();
        jLabelStatus = new JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sportski centar - Server");
        setResizable(false);

        jLabelBrand.setFont(new Font("Arial", Font.BOLD, 22));
        jLabelBrand.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelBrand.setText("Sportski centar");

        jLabelSubtitle.setFont(new Font("Arial", Font.PLAIN, 13));
        jLabelSubtitle.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelSubtitle.setText("Serverska konzola");

        jLabel1.setFont(new Font("Arial", Font.PLAIN, 13));
        jLabel1.setText("Status servera");

        jLabelStatus.setFont(new Font("Arial", Font.BOLD, 16));
        jLabelStatus.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelStatus.setText("Zaustavljen");

        jButtonPokreni.setText("Pokreni server");
        jButtonPokreni.setPreferredSize(new Dimension(160, 36));
        jButtonPokreni.addActionListener(evt -> jButtonPokreniActionPerformed(evt));

        jButtonZaustavi.setText("Zaustavi server");
        jButtonZaustavi.setPreferredSize(new Dimension(160, 36));
        jButtonZaustavi.addActionListener(evt -> jButtonZaustaviActionPerformed(evt));

        jMenu1.setText("Konfiguracija");
        jMenuItem1.setText("Baza");
        jMenuItem1.addActionListener(evt -> jMenuItem1ActionPerformed(evt));
        jMenu1.add(jMenuItem1);
        jMenuItem2.setText("Port");
        jMenuItem2.addActionListener(evt -> jMenuItem2ActionPerformed(evt));
        jMenu1.add(jMenuItem2);
        jMenuBar1.add(jMenu1);
        setJMenuBar(jMenuBar1);

        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBorder(new EmptyBorder(24, 24, 8, 24));
        jLabelBrand.setAlignmentX(CENTER_ALIGNMENT);
        jLabelSubtitle.setAlignmentX(CENTER_ALIGNMENT);
        header.add(jLabelBrand);
        header.add(Box.createVerticalStrut(4));
        header.add(jLabelSubtitle);

        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
        statusPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Status"),
                new EmptyBorder(16, 16, 16, 16)));
        jLabel1.setAlignmentX(CENTER_ALIGNMENT);
        jLabelStatus.setAlignmentX(CENTER_ALIGNMENT);
        statusPanel.add(jLabel1);
        statusPanel.add(Box.createVerticalStrut(8));
        statusPanel.add(jLabelStatus);

        JPanel dugmad = new JPanel(new GridLayout(1, 2, 16, 0));
        dugmad.setBorder(new EmptyBorder(8, 0, 0, 0));
        dugmad.add(jButtonPokreni);
        dugmad.add(jButtonZaustavi);

        JPanel centar = new JPanel(new BorderLayout(0, 16));
        centar.setBorder(new EmptyBorder(8, 32, 28, 32));
        centar.add(statusPanel, BorderLayout.CENTER);
        centar.add(dugmad, BorderLayout.SOUTH);

        JPanel content = new JPanel(new BorderLayout());
        content.add(header, BorderLayout.NORTH);
        content.add(centar, BorderLayout.CENTER);
        setContentPane(content);

        setMinimumSize(new Dimension(480, 320));
        setSize(520, 360);
        setLocationRelativeTo(null);
    }

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {
        FormaKonfiguracijaBaza baza = new FormaKonfiguracijaBaza(this, false);
        baza.setVisible(true);
    }

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {
        FormaKonfiguracijaPort port = new FormaKonfiguracijaPort(this, false);
        port.setVisible(true);
    }

    private void jButtonPokreniActionPerformed(java.awt.event.ActionEvent evt) {
        server = new Server();
        server.start();
        String port = konfiguracija.Konfiguracija.getInstance().getProperty("port");
        if (port == null || port.equals("n/a") || port.isBlank()) {
            port = "9000";
        }
        jLabelStatus.setText("Pokrenut · port " + port);
        jLabelStatus.setForeground(new Color(30, 140, 70));
        jButtonPokreni.setEnabled(false);
        jButtonZaustavi.setEnabled(true);
    }

    private void jButtonZaustaviActionPerformed(java.awt.event.ActionEvent evt) {
        server.zaustaviServer();
        jLabelStatus.setText("Zaustavljen");
        jLabelStatus.setForeground(new Color(180, 50, 50));
        jButtonZaustavi.setEnabled(false);
        jButtonPokreni.setEnabled(true);
    }

    public static void main(String args[]) {
        FlatIntelliJLaf.setup();
        UIManager.put("Button.arc", 8);
        UIManager.put("Component.arc", 8);
        UIManager.put("TextComponent.arc", 8);
        UIManager.put("defaultFont", new javax.swing.plaf.FontUIResource("Arial", Font.PLAIN, 13));

        java.awt.EventQueue.invokeLater(() -> new ServerskaForma().setVisible(true));
    }

    private JButton jButtonPokreni;
    private JButton jButtonZaustavi;
    private JLabel jLabelBrand;
    private JLabel jLabelSubtitle;
    private JLabel jLabel1;
    private JLabel jLabelStatus;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
}
