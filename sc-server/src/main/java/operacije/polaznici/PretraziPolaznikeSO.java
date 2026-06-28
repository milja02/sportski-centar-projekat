package operacije.polaznici;

import domen.Polaznik;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za pretragu polaznika po zadatim kriterijumima.
 * Pretraga može obuhvatiti ime, prezime, broj telefona i mesto.
 */
public class PretraziPolaznikeSO extends ApstraktnaGenerickaOperacija {

    /** Lista pronađenih polaznika. */
    private List<Polaznik> polaznici;

    /**
     * Proverava da li je prosleđen ispravan kriterijum pretrage.
     *
     * @param param kriterijum pretrage u obliku polaznika
     * @throws Exception ako parametar nije ispravan
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Polaznik)) {
            throw new Exception("Sistem ne može da pretraži polaznike po zadatim kriterijumima.");
        }
    }

    /**
     * Formira SQL uslov na osnovu kriterijuma i učitava pronađene polaznike.
     *
     * @param param kriterijum pretrage
     * @param kljuc dodatni ključ operacije
     * @throws Exception ako dođe do greške pri radu sa bazom
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Polaznik kriterijum = (Polaznik) param;
        StringBuilder uslov = new StringBuilder(" WHERE 1=1");

        if (kriterijum.getIme() != null && !kriterijum.getIme().trim().isEmpty()) {
            uslov.append(" AND po.ime LIKE '%").append(kriterijum.getIme().trim()).append("%'");
        }
        if (kriterijum.getPrezime() != null && !kriterijum.getPrezime().trim().isEmpty()) {
            uslov.append(" AND po.prezime LIKE '%").append(kriterijum.getPrezime().trim()).append("%'");
        }
        if (kriterijum.getBrojTelefona() != null && !kriterijum.getBrojTelefona().trim().isEmpty()) {
            uslov.append(" AND po.brojTelefona LIKE '%").append(kriterijum.getBrojTelefona().trim()).append("%'");
        }
        if (kriterijum.getMesto() != null) {
            if (kriterijum.getMesto().getIdMesto() > 0) {
                uslov.append(" AND po.mesto = ").append(kriterijum.getMesto().getIdMesto());
            } else if (kriterijum.getMesto().getNaziv() != null
                    && !kriterijum.getMesto().getNaziv().trim().isEmpty()) {
                uslov.append(" AND mesto.naziv LIKE '%").append(kriterijum.getMesto().getNaziv().trim()).append("%'");
            }
        }

        polaznici = (List<Polaznik>) (List<?>) broker.getAll(new Polaznik(), uslov.toString());
    }

    /**
     * Vraća listu pronađenih polaznika.
     *
     * @return lista polaznika
     */
    public List<Polaznik> getPolaznici() {
        return polaznici;
    }
}
