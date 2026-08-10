-- Šema baze za Sportski centar
-- Učitava se automatski pri prvom pokretanju MySQL kontejnera (docker-compose).

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS stavkaclanskekarte;
DROP TABLE IF EXISTS clanskakarta;
DROP TABLE IF EXISTS instruktorlicenca;
DROP TABLE IF EXISTS polaznik;
DROP TABLE IF EXISTS licenca;
DROP TABLE IF EXISTS instruktor;
DROP TABLE IF EXISTS sport;
DROP TABLE IF EXISTS mesto;

SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE mesto (
    idMesto INT NOT NULL AUTO_INCREMENT,
    naziv VARCHAR(100) NOT NULL,
    postanskiBroj INT NOT NULL,
    PRIMARY KEY (idMesto)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE sport (
    idSport INT NOT NULL AUTO_INCREMENT,
    naziv VARCHAR(100) NOT NULL,
    cena INT NOT NULL,
    PRIMARY KEY (idSport)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE instruktor (
    idInstruktor INT NOT NULL AUTO_INCREMENT,
    ime VARCHAR(100) NOT NULL,
    prezime VARCHAR(100) NOT NULL,
    korisnickoIme VARCHAR(100) NOT NULL,
    sifra VARCHAR(100) NOT NULL,
    PRIMARY KEY (idInstruktor),
    UNIQUE KEY uk_instruktor_korisnickoIme (korisnickoIme)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE licenca (
    idLicenca INT NOT NULL AUTO_INCREMENT,
    tipLicence VARCHAR(100) NOT NULL,
    nivoKvalifikacije VARCHAR(100) NOT NULL,
    PRIMARY KEY (idLicenca)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE polaznik (
    idPolaznik INT NOT NULL AUTO_INCREMENT,
    ime VARCHAR(100) NOT NULL,
    prezime VARCHAR(100) NOT NULL,
    brojTelefona VARCHAR(30) NOT NULL,
    mesto INT NOT NULL,
    PRIMARY KEY (idPolaznik),
    CONSTRAINT fk_polaznik_mesto FOREIGN KEY (mesto) REFERENCES mesto (idMesto)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE clanskakarta (
    idClanskaKarta INT NOT NULL AUTO_INCREMENT,
    datumUclanjenja DATE NULL,
    ukupanIznos INT NOT NULL DEFAULT 0,
    instruktor INT NOT NULL,
    polaznik INT NOT NULL,
    PRIMARY KEY (idClanskaKarta),
    CONSTRAINT fk_ck_instruktor FOREIGN KEY (instruktor) REFERENCES instruktor (idInstruktor),
    CONSTRAINT fk_ck_polaznik FOREIGN KEY (polaznik) REFERENCES polaznik (idPolaznik)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE stavkaclanskekarte (
    clanskakarta INT NOT NULL,
    rb INT NOT NULL,
    brojTermina INT NOT NULL,
    iznosStavke INT NOT NULL,
    sport INT NOT NULL,
    PRIMARY KEY (clanskakarta, rb),
    CONSTRAINT fk_stavka_karta FOREIGN KEY (clanskakarta) REFERENCES clanskakarta (idClanskaKarta) ON DELETE CASCADE,
    CONSTRAINT fk_stavka_sport FOREIGN KEY (sport) REFERENCES sport (idSport)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE instruktorlicenca (
    instruktor INT NOT NULL,
    licenca INT NOT NULL,
    datumIzdavanja DATE NULL,
    datumIsteka DATE NULL,
    PRIMARY KEY (instruktor, licenca),
    CONSTRAINT fk_il_instruktor FOREIGN KEY (instruktor) REFERENCES instruktor (idInstruktor),
    CONSTRAINT fk_il_licenca FOREIGN KEY (licenca) REFERENCES licenca (idLicenca)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
