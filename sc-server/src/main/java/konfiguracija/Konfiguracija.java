package konfiguracija;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Konfiguracija {
    private static Konfiguracija instance;
    private final Properties konfiguracija = new Properties();
    private final String putanja = "config/dbconfig.properties";

    private Konfiguracija() {
        try (InputStream input = openConfigStream()) {
            if (input == null) {
                throw new IOException("Nije pronadjen fajl dbconfig.properties");
            }
            konfiguracija.load(input);
        } catch (IOException ex) {
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private InputStream openConfigStream() throws IOException {
        try {
            return new FileInputStream(putanja);
        } catch (IOException ex) {
            InputStream resourceStream = Konfiguracija.class.getClassLoader()
                    .getResourceAsStream("dbconfig.properties");
            if (resourceStream != null) {
                return resourceStream;
            }
            throw ex;
        }
    }

    public static Konfiguracija getInstance() {
        if (instance == null) {
            instance = new Konfiguracija();
        }
        return instance;
    }

    public String getProperty(String key) {
        return konfiguracija.getProperty(key, "n/a");
    }

    public void setProperty(String key, String value) {
        konfiguracija.setProperty(key, value);
    }

    public void sacuvajIzmene() {
        try (FileOutputStream output = new FileOutputStream(putanja)) {
            konfiguracija.store(output, null);
        } catch (IOException ex) {
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
