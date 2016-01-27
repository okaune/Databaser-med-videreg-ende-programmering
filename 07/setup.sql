DROP TABLE IF EXISTS medlem;
DROP TABLE IF EXISTS leilighet;
DROP TABLE IF EXISTS bygning;
DROP TABLE IF EXISTS borettslag;
 
-- opprette tabeller, inkl. entitetsintegritet
-- dvs. primarnokler
 
CREATE TABLE borettslag(
 borettslag_navn VARCHAR(30) NOT NULL,
 adresse VARCHAR(30),
 Âr INTEGER,
CONSTRAINT borettslag_pk PRIMARY KEY(borettslag_navn)
);
 
CREATE TABLE bygning(
 bygning_id INTEGER AUTO_INCREMENT,
 borettslag_navn VARCHAR(30) NOT NULL,
 adresse VARCHAR(30),
 etasjer INTEGER,
 CONSTRAINT bygning_pk PRIMARY KEY(bygning_id)
);
 
CREATE TABLE leilighet(
 leilighet_id INTEGER AUTO_INCREMENT,
 bygning_id INTEGER NOT NULL,
 leilighet_nr INTEGER,
 etasje INTEGER,
 areal INTEGER,
 rom INTEGER,
 CONSTRAINT leilighet_pk PRIMARY KEY(leilighet_id)
);
-- bytt ut PK til bygning_id og leilighet_nr ?
 
CREATE TABLE medlem(
 medlemsnummer INTEGER NOT NULL,
 leilighet_id INTEGER,
 -- id kan vÊre null, da ikke alle medlemmer har en leilighet
CONSTRAINT medlem_pk PRIMARY KEY(medlemsnummer)
);
 
-- legger inn referanseintegritet
-- dvs. fremmednokler
 
ALTER TABLE bygning
 ADD CONSTRAINT bygning_fk FOREIGN KEY(borettslag_navn)REFERENCES borettslag(borettslag_navn);
-- DELETE CASCADE: bygninger vil fjernes nÂr borettslag slettes, ikke reelt
-- UPDATE CASCADE: fint å ha dersom borettslaget endrer navn og det er mange bygg
 
ALTER TABLE leilighet
 ADD CONSTRAINT leilighet_fk FOREIGN KEY(bygning_id)REFERENCES bygning(bygning_id);
-- DELETE CASCADE: dersom bygningen rives vil ogsÂ leiligheter forsvinne, så her er det nok greit
-- UPDATE CASCADE: trengs ikke, har en inkrementell id
 
ALTER TABLE medlem
 ADD CONSTRAINT medlem_fk FOREIGN KEY(leilighet_id)REFERENCES leilighet(leilighet_id);
-- DELETE CASCADE: hvis leiligheten forsvinner er man fortsatt medlem, så det er ikke lurt Â bare slette
-- UPDATE CASCADE: trengs ikke, har en inkrementell id

INSERT INTO borettslag VALUES('Det lille borettslaget', 'Trondheim', 1989);
-- INSERT INTO borettslag VALUES('Det lille borettslaget', 'Trondheim', 1989); feiler

INSERT INTO bygning VALUES(DEFAULT, 'Det lille borettslaget', 'Adressegata 1a', 1);
INSERT INTO bygning VALUES(DEFAULT, 'Det lille borettslaget', 'Adressefata 1b', 1);
INSERT INTO bygning VALUES(DEFAULT, 'Det lille borettslaget', 'Adressegata 1c', 1);
-- INSERT INTO bygning VALUES(DEFAULT, 'Det lille borettslaget', 'Adressegata 1a', 1); feiler
-- INSERT INTO bygning VALUES(DEFAULT, 'Det store borettslaget', 'Adressegata 1a', 1); feiler

INSERT INTO leilighet VALUES(DEFAULT, 1, 1, 1, 12, 1);
INSERT INTO leilighet VALUES(DEFAULT, 2, 1, 1, 12, 1);
INSERT INTO leilighet VALUES(DEFAULT, 3, 1, 1, 12, 1);
-- INSERT INTO leilighet VALUES(DEFAULT, 3, 1, 1, 12, 1);
-- kan legge inn flere leiligheter med samme nummer, kan vÊre bra eller dÂrlig etter situasjonen..

INSERT INTO medlem VALUES(1,2);
-- INSERT INTO medlem VALUES(2,15); feiler