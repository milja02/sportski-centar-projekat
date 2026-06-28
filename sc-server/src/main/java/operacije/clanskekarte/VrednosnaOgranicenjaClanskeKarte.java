package operacije.clanskekarte;

import domen.ClanskaKarta;
import domen.Sport;
import domen.StavkaClanskeKarte;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import repository.Repository;

/**
 * Pomoćna klasa za proveru vrednosnih ograničenja članske karte i njenih stavki.
 */
public final class VrednosnaOgranicenjaClanskeKarte {

    private VrednosnaOgranicenjaClanskeKarte() {
    }

    /**
     * Proverava ispravnost stavki i ukupnog iznosa članske karte.
     * Za svaku stavku proverava sport, broj termina i iznos;
     * ukupan iznos mora biti jednak zbiru iznosa stavki.
     *
     * @param ck članska karta za proveru
     * @param broker repozitorijum za učitavanje cena sportova
     * @throws Exception ako bilo koje ograničenje nije ispunjeno
     */
    public static void proveri(ClanskaKarta ck, Repository broker) throws Exception {
        List<StavkaClanskeKarte> stavke = ck.getStavke();
        int zbirStavki = 0;

        if (stavke != null && !stavke.isEmpty()) {
            Map<Integer, Integer> ceneSportova = ucitajCeneSportova(broker);

            for (StavkaClanskeKarte s : stavke) {
                if (s.getSport() == null || s.getSport().getIdSport() <= 0) {
                    throw new Exception("Sistem ne može da zapamti člansku kartu (neispravan sport na stavci).");
                }
                if (s.getBrojTermina() <= 0) {
                    throw new Exception("Sistem ne može da zapamti člansku kartu (broj termina mora biti veći od nule).");
                }
                Integer cena = ceneSportova.get(s.getSport().getIdSport());
                if (cena == null) {
                    throw new Exception("Sistem ne može da zapamti člansku kartu (sport ne postoji u bazi).");
                }
                int ocekivaniIznos = s.getBrojTermina() * cena;
                if (s.getIznosStavke() != ocekivaniIznos) {
                    throw new Exception("Sistem ne može da zapamti člansku kartu (iznos stavke mora biti brojTermina × cena sporta).");
                }
                zbirStavki += s.getIznosStavke();
            }
        }

        if (ck.getUkupanIznos() != zbirStavki) {
            throw new Exception("Sistem ne može da zapamti člansku kartu (ukupan iznos mora biti zbir iznosa stavki).");
        }
    }

    private static Map<Integer, Integer> ucitajCeneSportova(Repository broker) throws Exception {
        List<Sport> sportovi = (List<Sport>) (List<?>) broker.getAll(new Sport(), null);
        Map<Integer, Integer> mapa = new HashMap<>();
        if (sportovi != null) {
            for (Sport sp : sportovi) {
                mapa.put(sp.getIdSport(), sp.getCena());
            }
        }
        return mapa;
    }
}
