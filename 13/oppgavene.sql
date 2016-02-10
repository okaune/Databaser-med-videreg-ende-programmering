-- Finn alle borettslag etablert i årene 1975-1985.
SELECT * FROM borettslag WHERE etabl_aar BETWEEN 1975 AND 1985;
-- Skriv ut en liste over andelseiere. Listen skal ha linjer som ser slik ut (tekster i kursiv er data fra databasen): 
-- "fornavn etternavn, ansiennitet: ansiennitet år".
-- Listen skal være sortert på ansiennitet, de med lengst ansiennitet øverst.
SELECT fornavn, etternavn, ansiennitet FROM andelseier ORDER BY ansiennitet DESC;
-- I hvilket år ble det eldste borettslaget etablert?
SELECT MIN(etabl_aar) AS "Første etablerte borettslag" FROM borettslag;
-- Finn adressene til alle bygninger som inneholder leiligheter med minst tre rom.
SELECT DISTINCT bygning.bygn_adr FROM bygning JOIN leilighet ON (bygning.bygn_id = leilighet.bygn_id) WHERE ant_rom >= 3;
-- Finn antall bygninger i borettslaget "Tertitten".
SELECT COUNT(*) AS "Antall bygninger i Tertitten" FROM bygning WHERE bolag_navn = "Tertitten";
-- Lag en liste som viser antall bygninger i hvert enkelt borettslag. Listen skal være sortert på borettslagsnavn. Husk at det kan finnes borettslag uten bygninger - de skal også med.
SELECT bo.bolag_navn, COUNT(bygn_id) 'Antall bygninger' FROM bygning b RIGHT JOIN borettslag bo ON (b.bolag_navn = bo.bolag_navn) GROUP BY bolag_navn ORDER BY bolag_navn;
-- Finn antall leiligheter i borettslaget "Tertitten".
SELECT COUNT(*) AS "Antall leiligheter i Tertitten" FROM leilighet JOIN bygning ON (bygning.bygn_id = leilighet.bygn_id) WHERE bolag_navn = "Tertitten";
-- Hvor høyt kan du bo i borettslaget "Tertitten"?
SELECT MAX(etasje) FROM leilighet JOIN bygning ON (bygning.bygn_id = leilighet.bygn_id) WHERE bolag_navn = "Tertitten";
-- Finn navn og nummer til andelseiere som ikke har leilighet.
SELECT fornavn, etternavn, telefon FROM andelseier WHERE and_eier_nr NOT IN (
	SELECT and_eier_nr FROM leilighet
);
-- Finn antall andelseiere pr borettslag, sortert etter antallet. Husk at det kan finnes borettslag uten andelseiere - de skal også med.
SELECT COUNT(and_eier_nr) 'antall', b.bolag_navn FROM borettslag b LEFT JOIN andelseier a ON (b.bolag_navn = a.bolag_navn) GROUP BY b.bolag_navn ORDER BY antall;
-- Skriv ut en liste over alle andelseiere. For de som har leilighet, skal leilighetsnummeret skrives ut.
SELECT a.*, l.leil_nr FROM andelseier a LEFT JOIN leilighet l ON (a.and_eier_nr = l.and_eier_nr);
-- Hvilke borettslag har leiligheter med eksakt 4 rom?
SELECT bo.* FROM borettslag bo NATURAL JOIN bygning b NATURAL JOIN leilighet l WHERE l.ant_rom = 4;
-- Skriv ut en liste over antall andelseiere pr postnr og poststed, begrenset til de som bor i leiligheter tilknyttet et borettslag. Husk at postnummeret til disse er postnummeret til bygningen de bor i, og ikke postnummeret til borettslaget. Du trenger ikke ta med poststeder med 0 andelseiere. (Ekstraoppgave: Hva hvis vi vil ha med poststeder med 0 andelseiere?)
SELECT COUNT(and_eier_nr), postnr FROM andelseier NATURAL JOIN leilighet NATURAL JOIN bygning;