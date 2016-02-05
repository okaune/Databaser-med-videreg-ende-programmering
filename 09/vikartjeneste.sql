-- DROP TABLES --
DROP TABLE IF EXISTS vikartjeneste;
DROP TABLE IF EXISTS oppdrag;
DROP TABLE IF EXISTS kval_kandidat;
DROP TABLE IF EXISTS kvalifikasjon;
DROP TABLE IF EXISTS kandidat;
DROP TABLE IF EXISTS bedrift; 

-- CREATE TABLES --

-- vikartjeneste-table only contains one row --
CREATE TABLE vikartjeneste(
 org_nr VARCHAR(15) PRIMARY KEY,
 navn VARCHAR(20),
 adresse VARCHAR(30),
 telefon CHAR(12),
 epost VARCHAR(30));


CREATE TABLE bedrift(
 org_nr CHAR(15) PRIMARY KEY,
 bedrift_navn VARCHAR(20),
 telefon CHAR(12),
 epost VARCHAR(30));


CREATE TABLE oppdrag(
 oppdrag_id INTEGER PRIMARY KEY AUTO_INCREMENT,
 planlagt_startdato DATE,
 planlagt_sluttdato DATE,
 planlagt_ant_timer INTEGER,
 virkelig_startdato DATE,
 virkelig_sluttdato DATE,
 virkelig_ant_timer INTEGER,
 sluttattest VARCHAR(200),
 bedrift_id CHAR(15) NOT NULL,
 kval_id INTEGER NOT NULL,
 kandidat_id INTEGER);


CREATE TABLE kandidat(
 kandidat_id INTEGER PRIMARY KEY AUTO_INCREMENT,
 fornavn VARCHAR(20),
 etternavn VARCHAR(20),
 telefon CHAR(12),
 epost VARCHAR(30));


CREATE TABLE kvalifikasjon(
 kval_id INTEGER PRIMARY KEY AUTO_INCREMENT,
 beskrivelse VARCHAR(30));


CREATE TABLE kval_kandidat(
 kval_id INTEGER,
 kandidat_id INTEGER,
 CONSTRAINT kval_kandidat_pk PRIMARY KEY(kval_id, kandidat_id));
 
 
 
-- FOREIGN KEYS --
ALTER TABLE oppdrag ADD CONSTRAINT oppdrag_fk1 
	FOREIGN KEY(bedrift_id) 
	REFERENCES bedrift (org_nr);
ALTER TABLE oppdrag ADD CONSTRAINT oppdrag_fk2 
	FOREIGN KEY(kval_id) 
	REFERENCES kvalifikasjon (kval_id);
ALTER TABLE oppdrag ADD CONSTRAINT oppdrag_fk3 
	FOREIGN KEY(kval_id, kandidat_id) 
	REFERENCES kval_kandidat (kval_id, kandidat_id);
ALTER TABLE kval_kandidat ADD CONSTRAINT kval_kandidat_fk1 
	FOREIGN KEY(kval_id) 
	REFERENCES kvalifikasjon (kval_id);
ALTER TABLE kval_kandidat ADD CONSTRAINT kval_kandidat_fk2 
	FOREIGN KEY(kandidat_id) 
	REFERENCES kandidat (kandidat_id);

-- Add data to tables  --
INSERT INTO vikartjeneste(org_nr, navn, adresse, telefon, epost)
	VALUES('1122334455', 'Vikarer AS', 'Adressegata 20, 0000 By', '12345678', 'hjelp_AT_vikarer.no');
 
INSERT INTO bedrift(org_nr, bedrift_navn, telefon, epost)
	VALUES('1029384756', 'Bollebutikken', '12345678', 'kontrakt_AT_boller.no');
INSERT INTO bedrift(org_nr, bedrift_navn, telefon, epost)
	VALUES('1234567890', 'Ny hage', '22345678', 'kontakt_AT_nyhage.no'); 
 
INSERT INTO kandidat(kandidat_id, fornavn, etternavn, telefon, epost)
 	VALUES(DEFAULT, 'Bob', 'Karlsen', '11111111', 'bob_AT_mail.com');
INSERT INTO kandidat(kandidat_id, fornavn, etternavn, telefon, epost)
 	VALUES(DEFAULT, 'Noah', 'Ali', '22222222', 'noah_AT_mail.com');
INSERT INTO kandidat(kandidat_id, fornavn, etternavn, telefon, epost)
	VALUES(DEFAULT, 'Kathrine', 'Jensen', '33333333', 'kathrine_AT_mail.com');

INSERT INTO kvalifikasjon(kval_id, beskrivelse) VALUES(DEFAULT, 'html');
INSERT INTO kvalifikasjon(kval_id, beskrivelse) VALUES(DEFAULT, 'css');
INSERT INTO kvalifikasjon(kval_id, beskrivelse) VALUES(DEFAULT, 'js');

INSERT INTO kval_kandidat(kval_id, kandidat_id) VALUES(1, 2);
INSERT INTO kval_kandidat(kval_id, kandidat_id) VALUES(1, 1);
INSERT INTO kval_kandidat(kval_id, kandidat_id) VALUES(2, 2);
INSERT INTO kval_kandidat(kval_id, kandidat_id) VALUES(3, 2);

INSERT INTO oppdrag(oppdrag_id, planlagt_startdato, planlagt_sluttdato, planlagt_ant_timer, virkelig_startdato, virkelig_sluttdato, virkelig_ant_timer, sluttattest, bedrift_id, kval_id, kandidat_id)
	VALUES(DEFAULT, DATE('2016-05-02'), DATE('2016-06-30'), 400, 
					DATE('2016-05-02'), DATE('2016-06-20'), 410, 
					'sluttattest en tekst', '1029384756', 1, 2);
INSERT INTO oppdrag(oppdrag_id, planlagt_startdato, planlagt_sluttdato, planlagt_ant_timer, virkelig_startdato, virkelig_sluttdato, virkelig_ant_timer, sluttattest, bedrift_id, kval_id, kandidat_id)
	VALUES(DEFAULT, DATE('2008-04-02'), DATE('2008-04-30'), 500,
					DATE('2008-04-02'), DATE('2008-04-30'), 470,
					'sluttattest enda en tekst', '1029384756', 1, 2);
INSERT INTO oppdrag(oppdrag_id, planlagt_startdato, planlagt_sluttdato, planlagt_ant_timer, bedrift_id, kval_id)
	VALUES(DEFAULT, DATE('2008-05-02'), DATE('2008-06-30'), 400, '1029384756', 3);