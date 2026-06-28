package operacije.sportovi;

import domen.Sport;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

public class UcitajSportoveSO extends ApstraktnaGenerickaOperacija {

    List<Sport> sportovi;

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        sportovi = (List<Sport>) (List<?>) broker.getAll(new Sport(), null);
    }

    public List<Sport> getSportovi() {
        return sportovi;
    }
}
