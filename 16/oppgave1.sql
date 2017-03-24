-- List ut all informasjon (ordrehode og ordredetalj) om ordrer for leverandør nr 44.
SELECT * FROM ordredetalj 
	NATURAL JOIN ordrehode 
	WHERE levnr = 44;
	
-- Finn navn og by ("LevBy") for leverandører som kan levere del nummer 1.
SELECT navn, levby FROM levinfo 
	NATURAL JOIN prisinfo 
	WHERE delnr = 1;
	
-- Finn nummer, navn og pris for den leverandør som kan levere del nummer 201 til billigst pris.
SELECT levnr, navn, pris FROM levinfo 
	NATURAL JOIN prisinfo 
	WHERE pris IN (SELECT MIN(pris) FROM prisinfo WHERE delnr = 201);
	
-- Lag fullstendig oversikt over ordre nr 16, med ordrenr, dato, delnr, beskrivelse, kvantum, (enhets-)pris og beregnet beløp (=pris*kvantum).
SELECT ordrenr, dato, delnr, beskrivelse, kvantum, pris, pris*kvantum 'beregnet beløp' FROM ordrehode
	NATURAL JOIN ordredetalj
	NATURAL JOIN delinfo
	NATURAL JOIN prisinfo
	WHERE ordrenr = 16;
	
-- Finn delnummer og leverandørnummer for deler som har en pris som er høyere enn prisen for del med katalognr X7770.
SELECT delnr, levnr FROM delinfo
	NATURAL JOIN prisinfo
	NATURAL JOIN levinfo
	WHERE pris > (SELECT pris FROM prisinfo WHERE katalognr = 'X7770');
	
-- i) Tenk deg at tabellen levinfo skal deles i to. Sammenhengen mellom by og fylke skal tas ut av tabellen. Det er unødvendig å lagre fylketilhørigheten for hver forekomst av by. Lag én ny tabell som inneholder byer og fylker. Fyll denne med data fra levinfo. Lag også en tabell som er lik levinfo unntatt kolonnen Fylke. (Denne splittingen av tabellen levinfo gjelder bare i denne oppgaven. I resten av oppgavesettet antar du at du har den opprinnelige levinfo-tabellen.)
CREATE TABLE by_fylke(  
levby   VARCHAR(20) NOT NULL, 
fylke   VARCHAR(20) NOT NULL,
CONSTRAINT by_fylke_pk PRIMARY KEY(levby));
-- Kopierer over info fra levinfo
INSERT INTO by_fylke(levby, fylke) SELECT levby, fylke FROM levinfo GROUP BY levby;

CREATE TABLE levinfo2( 
levnr   INTEGER, 
navn    VARCHAR(20) NOT NULL, 
adresse VARCHAR(20) NOT NULL, 
levby   VARCHAR(20) NOT NULL,
postnr  INTEGER NOT NULL,
CONSTRAINT levinfo_pk PRIMARY KEY(levnr));
-- Kopierer over info fra levinfo
INSERT INTO levinfo2(levnr, navn, adresse, levby, postnr) SELECT levnr, navn, adresse, levby, postnr FROM levinfo GROUP BY levnr;

-- ii) Lag en virtuell tabell (view) slik at brukerne i størst mulig grad kan jobbe på samme måte mot de to nye tabellene som den gamle. Prøv ulike kommandoer mot tabellen (select, update, delete, insert). Hvilke begrensninger, hvis noen, har brukerne i forhold til tidligere?
CREATE VIEW levinfo3 AS
SELECT *
FROM levinfo2 l NATURAL JOIN by_fylke b; -- Sjekk begrensninger

-- Anta at en vurderer å slette opplysningene om de leverandørene som ikke er representert i Prisinfo-tabellen. Finn ut hvilke byer en i tilfelle ikke får leverandør i. (Du skal ikke utføre slettingen.) (Tips: Svaret skal bli kun én by, "Ål".)
SELECT levby FROM levinfo WHERE levnr NOT IN (SELECT DISTINCT levnr FROM prisinfo); -- Finn ut hvordan man får hvilke byer som mistes hvis man fjerner noe. Ås er fortsatt representert, så metoden over vil ikke fungere.

-- Finn leverandørnummer for den leverandør som kan levere ordre nr 18 til lavest totale beløp (vanskelig).
-- Hint: Løs oppgaven i tre steg:
-- Lag en virtuell tabell (view) som viser hvem som kan levere hele eller deler av ordren.
-- Fra denne velger du så ut de leverandørene som kan levere like mange deler til ordren som ordren krever. Det vil si, kan levere hele ordren.
-- Til slutt finner du ut hvem som leverer billigst.
-- Svaret skal bli at leverandør 6 kan levere ordren for 6798 kroner.

-- view over de som kan lever deler eller hele orderen
CREATE VIEW kanlevere AS
	SELECT DISTINCT * FROM prisinfo 
		NATURAL JOIN delinfo
		NATURAL JOIN ordredetalj
		WHERE ordrenr = 18;
		
-- velgerr den leverandøren som kan levere hele orderen billigst
SELECT levnr, SUM(pris*kvantum) 'total' FROM kanlevere 
WHERE levnr IN (
	-- velger de som kan levere hele orderen
	SELECT DISTINCT levnr FROM kanlevere
		WHERE levnr IN (SELECT levnr FROM kanlevere WHERE delnr = 3 AND kvantum >= 2)
		AND levnr IN (SELECT levnr FROM kanlevere WHERE delnr = 4 AND kvantum >= 8)
) GROUP BY levnr ORDER BY total LIMIT 1;

	