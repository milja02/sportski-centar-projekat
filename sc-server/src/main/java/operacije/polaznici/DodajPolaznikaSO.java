package operacije.polaznici;

import domen.Polaznik;
import operacije.ApstraktnaGenerickaOperacija;

public class DodajPolaznikaSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param == null || !(param instanceof Polaznik)){
            throw new Exception("Sistem nije mogao da kreira polaznika");
        }
        Polaznik p = (Polaznik) param;
        if(p.getIme()==null || p.getIme().isEmpty()){
            throw new Exception("Sistem nije mogao da kreira polaznika");
        }
        if(p.getPrezime()==null || p.getPrezime().isEmpty()){
            throw new Exception("Sistem nije mogao da kreira polaznika");
        }
        if(p.getBrojTelefona()==null || p.getBrojTelefona().isEmpty()){
            throw new Exception("Sistem nije mogao da kreira polaznika");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.add((Polaznik) param);
    }
    
}
