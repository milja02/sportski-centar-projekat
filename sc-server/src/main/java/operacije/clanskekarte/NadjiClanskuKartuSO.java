package operacije.clanskekarte;

import domen.ClanskaKarta;
import domen.StavkaClanskeKarte;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

public class NadjiClanskuKartuSO extends ApstraktnaGenerickaOperacija {

    private ClanskaKarta clanskaKarta;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof ClanskaKarta)) {
            throw new Exception("Sistem ne može da nađe člansku kartu.");
        }
        ClanskaKarta ck = (ClanskaKarta) param;
        if (ck.getIdClanskaKarta() <= 0) {
            throw new Exception("Sistem ne može da nađe člansku kartu.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        ClanskaKarta kriterijum = (ClanskaKarta) param;
        String uslov = " WHERE idClanskaKarta=" + kriterijum.getIdClanskaKarta();
        List<ClanskaKarta> lista = (List<ClanskaKarta>) (List<?>) broker.getAll(new ClanskaKarta(), uslov);
        if (lista == null || lista.isEmpty()) {
            throw new Exception("Sistem ne može da nađe člansku kartu.");
        }
        clanskaKarta = lista.get(0);
        String uslovStavke = " WHERE clanskakarta=" + clanskaKarta.getIdClanskaKarta();
        List<StavkaClanskeKarte> stavke = (List<StavkaClanskeKarte>) (List<?>) broker.getAll(new StavkaClanskeKarte(), uslovStavke);
        clanskaKarta.setStavke(stavke);
    }

    public ClanskaKarta getClanskaKarta() {
        return clanskaKarta;
    }
}
