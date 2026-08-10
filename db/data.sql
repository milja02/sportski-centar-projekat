-- Početni podaci za lokalni rad i integracione testove.

INSERT INTO mesto (naziv, postanskiBroj) VALUES
('Beograd', 11000),
('Novi Sad', 21000),
('Niš', 18000);

INSERT INTO sport (naziv, cena) VALUES
('Fudbal', 3000),
('Košarka', 2800),
('Tenis', 4500),
('Plivanje', 3500);

INSERT INTO instruktor (ime, prezime, korisnickoIme, sifra) VALUES
('Marko', 'Marković', 'marko', 'marko123'),
('Jelena', 'Jovanović', 'jelena', 'jelena123');

INSERT INTO licenca (tipLicence, nivoKvalifikacije) VALUES
('UEFA', 'B'),
('FIBA', 'Level 1'),
('Nacionalna', 'Instruktor');

INSERT INTO polaznik (ime, prezime, brojTelefona, mesto) VALUES
('Petar', 'Petrović', '0641112233', 1),
('Ana', 'Anić', '0652223344', 2);

INSERT INTO instruktorlicenca (instruktor, licenca, datumIzdavanja, datumIsteka) VALUES
(1, 1, '2024-01-15', '2027-01-15'),
(2, 2, '2024-03-01', '2026-03-01');
