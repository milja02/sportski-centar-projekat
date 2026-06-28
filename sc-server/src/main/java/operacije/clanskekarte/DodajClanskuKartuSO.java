package operacije.clanskekarte;

import domen.ClanskaKarta;
import domen.StavkaClanskeKarte;
import operacije.ApstraktnaGenerickaOperacija;

public class DodajClanskuKartuSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof ClanskaKarta)) {
            throw new Exception("Sistem nije mogao da kreira člansku kartu.");
        }
        ClanskaKarta ck = (ClanskaKarta) param;
        if (ck.getDatumUclanjenja() == null) {
            throw new Exception("Sistem nije mogao da kreira člansku kartu - datum je obavezan.");
        }
        if (ck.getInstruktor() == null || ck.getPolaznik() == null) {
            throw new Exception("Sistem nije mogao da kreira člansku kartu - instruktor i polaznik su obavezni.");
        }
        VrednosnaOgranicenjaClanskeKarte.proveri(ck, broker);
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        ClanskaKarta ck = (ClanskaKarta) param;
        int id = broker.addAndReturnId(ck);
        if (id <= 0) {
            throw new Exception("Sistem nije mogao da kreira člansku kartu - nije moguće dobiti ID.");
        }
        ck.setIdClanskaKarta(id);
        if (ck.getStavke() != null && !ck.getStavke().isEmpty()) {
            int rb = 1;
            for (StavkaClanskeKarte s : ck.getStavke()) {
                s.setClanskaKarta(ck);
                s.setRb(rb++);
                broker.add(s);
            }
        }
    }
}
