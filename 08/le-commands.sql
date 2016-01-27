-- Oppgave 1 --
-- Oppgave a --

-- Selecsjon --
SELECT * FROM bok WHERE forlag_id = 3;

-- Projeksjon --
SELECT DISTINCT bok_id, tittel FROM bok;


-- Oppgave b --
SELECT * FROM forlag, bok;
-- Dette gir ikke mening å bruke fordi tabellene ikke vet hvordan de skal kobles sammen, og derfor lager alle mulige koblinger --

-- Oppgave c --

SELECT * FROM forlag, bok WHERE forlag.forlag_id = bok.forlag_id;
SELECT forlag.*, tittel, bok_id, utgitt_aar FROM forlag JOIN bok ON (forlag.forlag_id = bok.forlag_id);

--Natural join --
SELECT * FROM bok NATURAL JOIN forlag;
-- Her sier vi hvordan tabellene skal kobles sammen, så her kobles riktig forlag til riktig bok --


-- Oppgave d --
-- Har det samme antallet attributter, og at attributtene er definert på samme domene (har samme datatype) --
-- forfatter.fornavn og konsulent.fornavn --
SELECT fornavn FROM forfatter
UNION
SELECT fornavn FROM konsulent;
-- Er dette riktig?? --


-- Oppgave 2 --
-- Oppgave a --
SELECT DISTINCT forlag_navn FROM forlag;
-- DISTINCT er unødvendig her, da alle forlag har forskjellige navn --

-- Oppagve b --
SELECT forlag_id FROM forlag WHERE forlag_id NOT IN (
	SELECT forlag_id FROM bok
);
-- Mengdedifferanse (minus) --

-- Oppgave c --
SELECT * FROM forfatter WHERE fode_aar = 1948;

-- Oppgave d --
SELECT forlag_navn, adresse FROM bok JOIN forlag ON (bok.forlag_id = forlag.forlag_id) WHERE tittel = 'Generation X'; -- Her hadde det også gått ann å bruke natural join --

-- Oppgave e --
SELECT bok.tittel FROM bok 
	NATURAL JOIN bok_forfatter
	NATURAL JOIN forfatter
	WHERE forfatter.etternavn = 'Hamsun';
	
-- Oppgave f --
SELECT bok.tittel, bok.utgitt_aar, forlag.forlag_navn, forlag.adresse, forlag.telefon FROM forlag
LEFT JOIN bok ON (forlag.forlag_id = bok.forlag_id);

