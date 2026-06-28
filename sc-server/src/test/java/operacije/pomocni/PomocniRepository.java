package operacije.pomocni;

import domen.ApstraktniDomenskiObjekat;
import domen.ClanskaKarta;
import domen.Instruktor;
import domen.InstruktorLicenca;
import domen.Licenca;
import domen.Mesto;
import domen.Polaznik;
import domen.Sport;
import domen.StavkaClanskeKarte;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import repository.db.DBRepository;

public class PomocniRepository implements DBRepository<ApstraktniDomenskiObjekat> {

    private final Map<String, List<ApstraktniDomenskiObjekat>> tabele = new HashMap<>();
    private final AtomicInteger generatorId = new AtomicInteger(100);

    public void dodaj(ApstraktniDomenskiObjekat objekat) {
        if (dohvatiId(objekat) <= 0) {
            postaviId(objekat, generatorId.incrementAndGet());
        }
        tabele.computeIfAbsent(objekat.nazivTabele(), k -> new ArrayList<>()).add(kopirajReferencu(objekat));
    }

    @Override
    public List<ApstraktniDomenskiObjekat> getAll(ApstraktniDomenskiObjekat param, String uslov) {
        List<ApstraktniDomenskiObjekat> svi = new ArrayList<>(
                tabele.getOrDefault(param.nazivTabele(), List.of()));
        if (uslov == null || uslov.isBlank()) {
            return svi;
        }
        List<ApstraktniDomenskiObjekat> filtrirani = new ArrayList<>();
        for (ApstraktniDomenskiObjekat objekat : svi) {
            if (odgovaraUslovu(objekat, uslov)) {
                filtrirani.add(objekat);
            }
        }
        return filtrirani;
    }

    @Override
    public void add(ApstraktniDomenskiObjekat param) {
        dodaj(param);
    }

    @Override
    public int addAndReturnId(ApstraktniDomenskiObjekat param) {
        int id = generatorId.incrementAndGet();
        postaviId(param, id);
        dodaj(param);
        return id;
    }

    @Override
    public void edit(ApstraktniDomenskiObjekat param) {
        List<ApstraktniDomenskiObjekat> lista = tabele.get(param.nazivTabele());
        if (lista == null) {
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            if (istiPoPrimarnomKljucu(lista.get(i), param)) {
                lista.set(i, param);
                return;
            }
        }
        lista.add(param);
    }

    @Override
    public void delete(ApstraktniDomenskiObjekat param) {
        List<ApstraktniDomenskiObjekat> lista = tabele.get(param.nazivTabele());
        if (lista == null) {
            return;
        }
        lista.removeIf(objekat -> istiPoPrimarnomKljucu(objekat, param));
    }

    @Override
    public List<ApstraktniDomenskiObjekat> getAll() {
        return List.of();
    }

    @Override
    public void connect() {
    }

    @Override
    public void commit() {
    }

    @Override
    public void rollback() {
    }

    public boolean postoji(ApstraktniDomenskiObjekat param) {
        return !getAll(param, null).isEmpty();
    }

    private boolean odgovaraUslovu(ApstraktniDomenskiObjekat objekat, String uslov) {
        if (objekat instanceof Polaznik polaznik) {
            Integer id = izvuciInt(uslov, "po.idPolaznik=");
            if (id != null && polaznik.getIdPolaznik() != id) {
                return false;
            }
            String ime = izvuciLike(uslov, "po.ime LIKE '%");
            if (ime != null && !polaznik.getIme().contains(ime)) {
                return false;
            }
            String prezime = izvuciLike(uslov, "po.prezime LIKE '%");
            if (prezime != null && !polaznik.getPrezime().contains(prezime)) {
                return false;
            }
            String telefon = izvuciLike(uslov, "po.brojTelefona LIKE '%");
            if (telefon != null && !polaznik.getBrojTelefona().contains(telefon)) {
                return false;
            }
            Integer idMesto = izvuciInt(uslov, "po.mesto = ");
            if (idMesto != null && (polaznik.getMesto() == null || polaznik.getMesto().getIdMesto() != idMesto)) {
                return false;
            }
            String nazivMesta = izvuciLike(uslov, "mesto.naziv LIKE '%");
            if (nazivMesta != null) {
                return polaznik.getMesto() != null && polaznik.getMesto().getNaziv().contains(nazivMesta);
            }
            return true;
        }
        if (objekat instanceof StavkaClanskeKarte stavka) {
            Integer idKarte = izvuciInt(uslov, "sck.clanskakarta=");
            if (idKarte == null) {
                idKarte = izvuciInt(uslov, "clanskakarta=");
            }
            return idKarte == null || (stavka.getClanskaKarta() != null
                    && stavka.getClanskaKarta().getIdClanskaKarta() == idKarte);
        }
        return true;
    }

    private Integer izvuciInt(String uslov, String prefiks) {
        int index = uslov.indexOf(prefiks);
        if (index < 0) {
            return null;
        }
        int pocetak = index + prefiks.length();
        int kraj = pocetak;
        while (kraj < uslov.length() && Character.isDigit(uslov.charAt(kraj))) {
            kraj++;
        }
        if (kraj == pocetak) {
            return null;
        }
        return Integer.parseInt(uslov.substring(pocetak, kraj));
    }

    private String izvuciLike(String uslov, String prefiks) {
        int index = uslov.indexOf(prefiks);
        if (index < 0) {
            return null;
        }
        int pocetak = index + prefiks.length();
        int kraj = uslov.indexOf("%'", pocetak);
        if (kraj < 0) {
            return null;
        }
        return uslov.substring(pocetak, kraj);
    }

    private boolean istiPoPrimarnomKljucu(ApstraktniDomenskiObjekat levi, ApstraktniDomenskiObjekat desni) {
        return levi.getClass() == desni.getClass()
                && levi.primarniKljuc().equals(desni.primarniKljuc());
    }

    private int dohvatiId(ApstraktniDomenskiObjekat objekat) {
        if (objekat instanceof Polaznik polaznik) {
            return polaznik.getIdPolaznik();
        }
        if (objekat instanceof ClanskaKarta karta) {
            return karta.getIdClanskaKarta();
        }
        if (objekat instanceof Instruktor instruktor) {
            return instruktor.getIdInstruktor();
        }
        if (objekat instanceof Sport sport) {
            return sport.getIdSport();
        }
        if (objekat instanceof Mesto mesto) {
            return mesto.getIdMesto();
        }
        if (objekat instanceof Licenca licenca) {
            return licenca.getIdLicenca();
        }
        return 0;
    }

    private void postaviId(ApstraktniDomenskiObjekat objekat, int id) {
        if (objekat instanceof Polaznik polaznik) {
            polaznik.setIdPolaznik(id);
        } else if (objekat instanceof ClanskaKarta karta) {
            karta.setIdClanskaKarta(id);
        } else if (objekat instanceof Instruktor instruktor) {
            instruktor.setIdInstruktor(id);
        } else if (objekat instanceof Sport sport) {
            sport.setIdSport(id);
        } else if (objekat instanceof Mesto mesto) {
            mesto.setIdMesto(id);
        } else if (objekat instanceof Licenca licenca) {
            licenca.setIdLicenca(id);
        }
    }

    private ApstraktniDomenskiObjekat kopirajReferencu(ApstraktniDomenskiObjekat objekat) {
        return objekat;
    }
}
