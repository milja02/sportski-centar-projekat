package forme;

import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginForma extends javax.swing.JFrame {

    public LoginForma() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabelBrand = new JLabel();
        jLabelSubtitle = new JLabel();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jTextFieldUsername = new JTextField();
        jPasswordField = new JPasswordField();
        jButton1 = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sportski centar - Prijava");
        setResizable(false);

        jLabelBrand.setFont(new Font("Arial", Font.BOLD, 22));
        jLabelBrand.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelBrand.setText("Sportski centar");

        jLabelSubtitle.setFont(new Font("Arial", Font.PLAIN, 13));
        jLabelSubtitle.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelSubtitle.setText("Prijava instruktora");

        jLabel1.setText("Korisni\u010dko ime");
        jLabel2.setText("\u0160ifra");
        jButton1.setText("Uloguj se");
        jButton1.putClientProperty("JButton.buttonType", "default");

        javax.swing.JPanel panel = new javax.swing.JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(28, 36, 28, 36));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(jLabelBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabelSubtitle, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2))
                    .addGap(12, 12, 12)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextFieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(jLabelBrand)
                .addGap(4, 4, 4)
                .addComponent(jLabelSubtitle)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        getContentPane().add(panel);
        pack();
        setMinimumSize(new java.awt.Dimension(420, 280));
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(jButton1);
    }

    public JButton getjButton1() {
        return jButton1;
    }

    public void setjButton1(JButton jButton1) {
        this.jButton1 = jButton1;
    }

    public JPasswordField getjPasswordField() {
        return jPasswordField;
    }

    public void setjPasswordField(JPasswordField jPasswordField) {
        this.jPasswordField = jPasswordField;
    }

    /** @deprecated koristi {@link #getjPasswordField()} */
    public JPasswordField getjTextFieldPassword() {
        return jPasswordField;
    }

    public JTextField getjTextFieldUsername() {
        return jTextFieldUsername;
    }

    public void setjTextFieldUsername(JTextField jTextFieldUsername) {
        this.jTextFieldUsername = jTextFieldUsername;
    }

    public void loginAddActionListener(ActionListener actionListener) {
        jButton1.addActionListener(actionListener);
    }

    private JButton jButton1;
    private JLabel jLabelBrand;
    private JLabel jLabelSubtitle;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JPasswordField jPasswordField;
    private JTextField jTextFieldUsername;
}
