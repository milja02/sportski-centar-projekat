package operacije.clanskekarte;

import domen.ClanskaKarta;
import domen.StavkaClanskeKarte;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za pretragu članjskih karata po zadatim kriterijumima.
 * Pretraga može obuhvatiti polaznika, instruktora i sport.
 */
public class PretraziClanskeKarteSO extends ApstraktnaGenerickaOperacija {

    /** Lista pronađenih članjskih karata. */
    private List<ClanskaKarta> clanskeKarte;

    /**
     * Proverava da li je prosleđen ispravan kriterijum pretrage.
     *
     * @param param kriterijum pretrage u obliku članske karte
     * @throws Exception ako parametar nije ispravan
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof ClanskaKarta)) {
            throw new Exception("Sistem ne može da pretraži članske karte po zadatim kriterijumima.");
        }
    }

    /**
     * Formira SQL uslov na osnovu kriterijuma, učitava pronađene karte
     * i za svaku kartu učitava stavke.
     *
     * @param param kriterijum pretrage
     * @param kljuc dodatni ključ operacije
     * @throws Exception ako dođe do greške pri radu sa bazom
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        ClanskaKarta kriterijum = (ClanskaKarta) param;
        StringBuilder uslov = new StringBuilder(" WHERE 1=1");

        if (kriterijum.getPolaznik() != null) {
            if (kriterijum.getPolaznik().getIdPolaznik() > 0) {
                uslov.append(" AND p.idPolaznik = ").append(kriterijum.getPolaznik().getIdPolaznik());
            } else {
                String imeP = kriterijum.getPolaznik().getIme();
                String prezimeP = kriterijum.getPolaznik().getPrezime();
                if (imeP != null && !imeP.trim().isEmpty()) {
                    uslov.append(" AND p.ime LIKE '%").append(imeP.trim()).append("%'");
                }
                if (prezimeP != null && !prezimeP.trim().isEmpty()) {
                    uslov.append(" AND p.prezime LIKE '%").append(prezimeP.trim()).append("%'");
                }
            }
        }

        if (kriterijum.getInstruktor() != null) {
            if (kriterijum.getInstruktor().getIdInstruktor() > 0) {
                uslov.append(" AND i.idInstruktor = ").append(kriterijum.getInstruktor().getIdInstruktor());
            } else {
                String imeI = kriterijum.getInstruktor().getIme();
                String prezimeI = kriterijum.getInstruktor().getPrezime();
                if (imeI != null && !imeI.trim().isEmpty()) {
                    uslov.append(" AND i.ime LIKE '%").append(imeI.trim()).append("%'");
                }
                if (prezimeI != null && !prezimeI.trim().isEmpty()) {
                    uslov.append(" AND i.prezime LIKE '%").append(prezimeI.trim()).append("%'");
                }
            }
        }

        if (kriterijum.getStavke() != null && !kriterijum.getStavke().isEmpty()) {
            StavkaClanskeKarte st = kriterijum.getStavke().get(0);
            if (st.getSport() != null && st.getSport().getIdSport() > 0) {
                uslov.append(" AND ck.idClanskaKarta IN (SELECT sck.clanskakarta FROM stavkaclanskekarte sck")
                        .append(" WHERE sck.sport = ").append(st.getSport().getIdSport()).append(")");
            } else if (st.getSport() != null) {
                String nazivSporta = st.getSport().getNaziv();
                if (nazivSporta != null && !nazivSporta.trim().isEmpty()) {
                    uslov.append(" AND ck.idClanskaKarta IN (SELECT sck.clanskakarta FROM stavkaclanskekarte sck")
                            .append(" JOIN sport sp ON sck.sport = sp.idSport")
                            .append(" WHERE sp.naziv LIKE '%").append(nazivSporta.trim()).append("%')");
                }
            }
        }

        clanskeKarte = (List<ClanskaKarta>) (List<?>) broker.getAll(new ClanskaKarta(), uslov.toString());

        for (ClanskaKarta ck : clanskeKarte) {
            String uslovStavke = " WHERE clanskakarta=" + ck.getIdClanskaKarta();
            List<StavkaClanskeKarte> stavke = (List<StavkaClanskeKarte>) (List<?>) broker.getAll(new StavkaClanskeKarte(), uslovStavke);
            ck.setStavke(stavke);
        }
    }

    /**
     * Vraća listu pronađenih članjskih karata.
     *
     * @return lista članjskih karata sa stavkama
     */
    public List<ClanskaKarta> getClanskeKarte() {
        return clanskeKarte;
    }
}
