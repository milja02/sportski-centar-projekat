package json;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public final class JsonFajlServis {

    private JsonFajlServis() {
    }

    public static File izaberiFajlZaSnimanje(Component roditelj, String predlozenoIme) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Sačuvaj JSON fajl");
        chooser.setFileFilter(new FileNameExtensionFilter("JSON fajlovi (*.json)", "json"));
        if (predlozenoIme != null && !predlozenoIme.isBlank()) {
            chooser.setSelectedFile(new File(predlozenoIme));
        }
        if (chooser.showSaveDialog(roditelj) != JFileChooser.APPROVE_OPTION) {
            return null;
        }
        File fajl = chooser.getSelectedFile();
        if (!fajl.getName().toLowerCase().endsWith(".json")) {
            fajl = new File(fajl.getAbsolutePath() + ".json");
        }
        return fajl;
    }

    public static File izaberiFajlZaUcitavanje(Component roditelj) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Uvezi JSON fajl");
        chooser.setFileFilter(new FileNameExtensionFilter("JSON fajlovi (*.json)", "json"));
        if (chooser.showOpenDialog(roditelj) != JFileChooser.APPROVE_OPTION) {
            return null;
        }
        return chooser.getSelectedFile();
    }

    public static void snimi(Object objekat, File fajl) throws IOException {
        JsonUtil.upisiUFajl(objekat, fajl);
    }

    public static <T> T ucitaj(File fajl, Class<T> klasa) throws IOException {
        return JsonUtil.citajIzFajla(fajl, klasa);
    }
}
