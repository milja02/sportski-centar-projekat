package operacije.polaznici;

import domen.Polaznik;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

public class NadjiPolaznikaSO extends ApstraktnaGenerickaOperacija {

    private Polaznik polaznik;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Polaznik)) {
            throw new Exception("Sistem ne može da nađe polaznika.");
        }
        Polaznik p = (Polaznik) param;
        if (p.getIdPolaznik() <= 0) {
            throw new Exception("Sistem ne može da nađe polaznika.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Polaznik kriterijum = (Polaznik) param;
        String uslov = " WHERE po.idPolaznik=" + kriterijum.getIdPolaznik();
        List<Polaznik> lista = (List<Polaznik>) (List<?>) broker.getAll(new Polaznik(), uslov);
        if (lista == null || lista.isEmpty()) {
            throw new Exception("Sistem ne može da nađe polaznika.");
        }
        polaznik = lista.get(0);
    }

    public Polaznik getPolaznik() {
        return polaznik;
    }
}
