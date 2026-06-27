package server;

import java.util.HashSet;
import java.util.Set;

public class PrijavljeniInstruktori {

    private static PrijavljeniInstruktori instance;
    private final Set<String> aktivni = new HashSet<>();

    private PrijavljeniInstruktori() {
    }

    public static synchronized PrijavljeniInstruktori getInstance() {
        if (instance == null) {
            instance = new PrijavljeniInstruktori();
        }
        return instance;
    }

    public synchronized boolean prijavi(String korisnickoIme) {
        if (korisnickoIme == null || korisnickoIme.isEmpty()) {
            return false;
        }
        if (aktivni.contains(korisnickoIme)) {
            return false;
        }
        aktivni.add(korisnickoIme);
        return true;
    }

    public synchronized void odjavi(String korisnickoIme) {
        if (korisnickoIme != null) {
            aktivni.remove(korisnickoIme);
        }
    }
}
