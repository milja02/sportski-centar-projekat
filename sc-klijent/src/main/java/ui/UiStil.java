package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public final class UiStil {

    public static final Dimension FIELD_SIZE = new Dimension(160, 30);
    public static final Dimension COMBO_SIZE = new Dimension(180, 30);

    private UiStil() {
    }

    private static Font font(int style, float size) {
        Font base = UIManager.getFont("Label.font");
        if (base == null) {
            base = new Font("Arial", Font.PLAIN, 13);
        }
        return base.deriveFont(style, size);
    }

    public static JLabel naslov(String tekst) {
        JLabel label = new JLabel(tekst);
        label.setFont(font(Font.BOLD, 20f));
        label.setBorder(new EmptyBorder(0, 0, 4, 0));
        return label;
    }

    public static JLabel sekcijaLabel(String tekst) {
        JLabel label = new JLabel(tekst);
        label.setFont(font(Font.BOLD, 13f));
        label.setBorder(new EmptyBorder(0, 0, 6, 0));
        return label;
    }

    public static void stilTabela(JTable tabela) {
        tabela.setRowHeight(28);
        tabela.setShowHorizontalLines(true);
        tabela.setShowVerticalLines(false);
        tabela.setFillsViewportHeight(true);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.getTableHeader().setFont(font(Font.BOLD, 12f));
        tabela.setFont(font(Font.PLAIN, 13f));
    }

    public static void velikoPolje(JTextField field) {
        field.setColumns(14);
        field.setPreferredSize(FIELD_SIZE);
        field.setMinimumSize(new Dimension(120, 30));
    }

    public static void velikiCombo(JComboBox<?> combo) {
        combo.setPreferredSize(COMBO_SIZE);
        combo.setMinimumSize(new Dimension(140, 30));
    }

    public static JPanel filterPanel(JComponent sadrzaj) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 247, 250));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 224, 230)),
                new EmptyBorder(12, 16, 12, 16)));
        sadrzaj.setOpaque(false);
        panel.add(sadrzaj, BorderLayout.CENTER);
        return panel;
    }

    public static JPanel actionBar(JButton... dugmad) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(220, 224, 230)),
                new EmptyBorder(4, 8, 4, 8)));
        for (JButton dugme : dugmad) {
            panel.add(dugme);
        }
        return panel;
    }

    public static JPanel poljeSaLabelom(String tekstLabele, JComponent polje) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        JLabel label = new JLabel(tekstLabele);
        label.setFont(font(Font.PLAIN, 12f));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        polje.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(label);
        panel.add(Box.createVerticalStrut(4));
        panel.add(polje);
        return panel;
    }

    public static JScrollPane tabelaScroll(JTable tabela) {
        stilTabela(tabela);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(220, 224, 230)));
        return scroll;
    }

    public static JPanel okvir(String naslov, JComponent sadrzaj) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createEtchedBorder(),
                        naslov,
                        javax.swing.border.TitledBorder.LEFT,
                        javax.swing.border.TitledBorder.TOP,
                        font(Font.BOLD, 13f)),
                new EmptyBorder(8, 8, 8, 8)));
        panel.add(sadrzaj, BorderLayout.CENTER);
        return panel;
    }
}
