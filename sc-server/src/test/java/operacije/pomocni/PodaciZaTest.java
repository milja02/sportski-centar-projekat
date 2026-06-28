package operacije.pomocni;

import domen.ClanskaKarta;
import domen.Instruktor;
import domen.InstruktorLicenca;
import domen.Licenca;
import domen.Mesto;
import domen.Polaznik;
import domen.Sport;
import domen.StavkaClanskeKarte;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import repository.Repository;

public final class PodaciZaTest {

    private PodaciZaTest() {
    }

    public static Mesto beograd() {
        return new Mesto(1, "Beograd", 11000);
    }

    public static Polaznik polaznik(int id, String ime, String prezime, String telefon) {
        return new Polaznik(id, ime, prezime, telefon, beograd());
    }

    public static Instruktor instruktor(int id, String ime, String prezime, String korisnicko, String sifra) {
        return new Instruktor(id, ime, prezime, korisnicko, sifra);
    }

    public static Sport sport(int id, String naziv, int cena) {
        return new Sport(id, naziv, cena);
    }

    public static Date datum(String vrednost) throws Exception {
        return new SimpleDateFormat("yyyy-MM-dd").parse(vrednost);
    }

    public static ClanskaKarta kartaSaStavkom(int brojTermina, int cenaSporta) throws Exception {
        ClanskaKarta karta = new ClanskaKarta();
        karta.setIdClanskaKarta(1);
        karta.setDatumUclanjenja(datum("2024-06-01"));
        karta.setInstruktor(instruktor(1, "Mika", "Mikic", "mika", "pass"));
        karta.setPolaznik(polaznik(1, "Pera", "Peric", "061"));
        Sport tenis = sport(1, "Tenis", cenaSporta);
        StavkaClanskeKarte stavka = new StavkaClanskeKarte(karta, 1, brojTermina, brojTermina * cenaSporta, tenis);
        karta.setStavke(new ArrayList<>(List.of(stavka)));
        karta.setUkupanIznos(brojTermina * cenaSporta);
        return karta;
    }

    public static InstruktorLicenca instruktorLicenca(Date izdavanje, Date istek) {
        return new InstruktorLicenca(
                instruktor(1, "Marko", "Markovic", "marko", "123"),
                new Licenca(2, "FITNESS", "Nivo 1"),
                izdavanje,
                istek);
    }

    public static PomocniRepository repoSaSportovima() {
        PomocniRepository repo = new PomocniRepository();
        repo.dodaj(sport(1, "Tenis", 4000));
        repo.dodaj(sport(2, "Plivanje", 3000));
        return repo;
    }

    public static PomocniRepository repoSaInstruktorom() {
        PomocniRepository repo = new PomocniRepository();
        repo.dodaj(instruktor(1, "Marko", "Markovic", "marko", "123"));
        return repo;
    }

    public static void popuniMesta(PomocniRepository repo) {
        repo.dodaj(beograd());
    }

    public static void popuniPolaznike(PomocniRepository repo) {
        repo.dodaj(polaznik(1, "Pera", "Peric", "061"));
        repo.dodaj(polaznik(2, "Mika", "Mikic", "062"));
    }

    public static Repository prazanRepo() {
        return new PomocniRepository();
    }
}
