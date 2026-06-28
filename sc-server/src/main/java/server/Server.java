package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import niti.ObradaZahteva;

public class Server extends Thread {
    private boolean kraj = false;
    private ServerSocket ss;

    @Override
    public void run() {
        try {
            ss = new ServerSocket(9000);
            System.out.println("Server je startovan");
            while(!kraj){
                Socket s = ss.accept();
                System.out.println("Klijent je povezan");
                
                ObradaZahteva oz = new ObradaZahteva(s);
                oz.start();
            }
        } catch (IOException ex) {
            if (!kraj) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void zaustaviServer(){
        kraj = true;
        if (ss != null && !ss.isClosed()) {
            try {
                ss.close();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Server je zaustavljen");
    }
}
