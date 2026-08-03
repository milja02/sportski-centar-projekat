package main;

import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.Font;
import java.util.Enumeration;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class Main {

    private static final String UI_FONT = "Arial";

    public static void main(String[] args) {
        FlatIntelliJLaf.setup();
        UIManager.put("Button.arc", 8);
        UIManager.put("Component.arc", 8);
        UIManager.put("TextComponent.arc", 8);
        UIManager.put("ScrollBar.thumbArc", 999);
        UIManager.put("ScrollBar.thumbInsets", new java.awt.Insets(2, 2, 2, 2));
        UIManager.put("TitlePane.unifiedBackground", Boolean.TRUE);

        // Arial na Windowsu pouzdano renderuje š/č/ć/ž
        primeniFont(new FontUIResource(UI_FONT, Font.PLAIN, 13));

        koordinator.Koordinator.getInstance().otvoriLoginFormu();
    }

    private static void primeniFont(FontUIResource font) {
        UIManager.put("defaultFont", font);
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, font);
            }
        }
    }
}
