package operacije.polaznici;

import domen.Polaznik;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

public class UcitajPolaznikeSO extends ApstraktnaGenerickaOperacija{
    List<Polaznik> polaznici;
    @Override
    protected void preduslovi(Object param) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        polaznici = broker.getAll(new Polaznik(), null);
    }

    public List<Polaznik> getPolaznici() {
        return polaznici;
    }
    
}
