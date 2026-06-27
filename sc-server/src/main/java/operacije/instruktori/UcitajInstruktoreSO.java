/*
 * UÄitavanje liste instruktora (za combo u formi Ubaci licencu).
 */
package operacije.instruktori;

import domen.Instruktor;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

public class UcitajInstruktoreSO extends ApstraktnaGenerickaOperacija {

    List<Instruktor> instruktori;

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        instruktori = (List<Instruktor>) (List<?>) broker.getAll(new Instruktor(), null);
    }

    public List<Instruktor> getInstruktori() {
        return instruktori;
    }
}
