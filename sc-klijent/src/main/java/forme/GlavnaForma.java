package forme;

import javax.swing.JLabel;

public class GlavnaForma extends javax.swing.JFrame {

    public GlavnaForma() {
        initComponents();
    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabelUlogovani = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sportski centar");

        jLabel1.setText("Zdravo, ");

        jLabelUlogovani.setText("-");

        jMenu1.setText("Polaznik");

        jMenuItem1.setText("Dodaj");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Pregled");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Clanska karta");

        jMenuItem3.setText("Dodaj");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem4.setText("Pregled");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Licenca");

        jMenuItem5.setText("Dodaj");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuItem6.setText("Pregled");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addComponent(jLabelUlogovani)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabelUlogovani))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
        setMinimumSize(new java.awt.Dimension(320, 120));
        setLocationRelativeTo(null);
    }

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {
        koordinator.Koordinator.getInstance().otvoriPrikazPolaznikaFormu();
    }

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {
        koordinator.Koordinator.getInstance().otvoriDodajPolaznikaFormu();
    }

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {
        koordinator.Koordinator.getInstance().otvoriPrikazClanskihKarataFormu();
    }

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {
        koordinator.Koordinator.getInstance().otvoriDodajClanskuKartuFormu();
    }

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {
        koordinator.Koordinator.getInstance().otvoriUbaciLicencuFormu();
    }

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {
        koordinator.Koordinator.getInstance().otvoriPrikazLicenciFormu();
    }

    public JLabel getjLabelUlogovani() {
        return jLabelUlogovani;
    }

    public void setjLabelUlogovani(JLabel jLabelUlogovani) {
        this.jLabelUlogovani = jLabelUlogovani;
    }

    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelUlogovani;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
}
