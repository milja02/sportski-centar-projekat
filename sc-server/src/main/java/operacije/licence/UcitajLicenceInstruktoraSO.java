package operacije.licence;

import domen.Instruktor;
import domen.Licenca;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za učitavanje licenci dodeljenih određenom instruktoru.
 */
public class UcitajLicenceInstruktoraSO extends ApstraktnaGenerickaOperacija {

    private List<Licenca> licence;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Instruktor)) {
            throw new Exception("Sistem ne može da učita licence instruktora.");
        }
        Instruktor instruktor = (Instruktor) param;
        if (instruktor.getIdInstruktor() <= 0) {
            throw new Exception("Sistem ne može da učita licence instruktora.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Instruktor instruktor = (Instruktor) param;
        String uslov = " JOIN instruktorlicenca il ON li.idLicenca = il.licenca"
                + " WHERE il.instruktor = " + instruktor.getIdInstruktor();
        licence = (List<Licenca>) (List<?>) broker.getAll(new Licenca(), uslov);
    }

    public List<Licenca> getLicence() {
        return licence;
    }
}
