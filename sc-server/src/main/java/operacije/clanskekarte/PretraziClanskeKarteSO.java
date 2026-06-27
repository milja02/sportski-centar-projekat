/*
 * SK2 - PretraÅ¾i Älansku kartu.
 * Pretraga po polazniku i/ili instruktoru (po imenu/prezimenu) u bazi.
 */
package operacije.clanskekarte;

import domen.ClanskaKarta;
import domen.StavkaClanskeKarte;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

public class PretraziClanskeKarteSO extends ApstraktnaGenerickaOperacija {

    private List<ClanskaKarta> clanskeKarte;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof ClanskaKarta)) {
            throw new Exception("Sistem ne moÅ¾e da pretraÅ¾i Älanske karte po zadatim kriterijumima.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        ClanskaKarta kriterijum = (ClanskaKarta) param;
        StringBuilder uslov = new StringBuilder(" WHERE 1=1");

        // polaznik (iz combo-a po ID-ju, inaÄe po imenu/prezimenu)
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

        // instruktor (iz combo-a po ID-ju, inaÄe po imenu/prezimenu)
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

        // sport (preko stavki Älanske karte â€“ iz combo-a po ID-ju)
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

        // Odmah uÄitavamo i stavke za svaku pronaÄ‘enu karticu
        for (ClanskaKarta ck : clanskeKarte) {
            String uslovStavke = " WHERE clanskakarta=" + ck.getIdClanskaKarta();
            List<StavkaClanskeKarte> stavke = (List<StavkaClanskeKarte>) (List<?>) broker.getAll(new StavkaClanskeKarte(), uslovStavke);
            ck.setStavke(stavke);
        }
    }

    public List<ClanskaKarta> getClanskeKarte() {
        return clanskeKarte;
    }
}
