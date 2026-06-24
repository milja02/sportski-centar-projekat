package komunikacija;

import java.io.ObjectInputStream;
import java.net.Socket;

public class Primalac {
    private Socket socket;

    public Primalac(Socket socket) {
        this.socket = socket;
    }

    public Object primi() {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            return ois.readObject();
        } catch (Exception ex) {
            System.out.println("Klijent je zatvorio formu");
        }
        return null;
    }
}
