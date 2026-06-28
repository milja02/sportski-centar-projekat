package operacije.licence;

import domen.Licenca;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

public class UcitajLicenceSO extends ApstraktnaGenerickaOperacija {

    List<Licenca> licence;

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        licence = (List<Licenca>) (List<?>) broker.getAll(new Licenca(), null);
    }

    public List<Licenca> getLicence() {
        return licence;
    }
}
