-- Finn hvor mange (antallet) stolmodeller som finnes av hver stoltype.
SELECT COUNT(modell_id) FROM stoltype 
	NATURAL JOIN modell 
	GROUP BY stoltype_id;

-- Ut fra alle registerte ordre (bestillinger): Finn gjennomsnittlig antall bestilte stoler av hver stoltype.
SELECT AVG(stoltype_id) FROM stoltype 
	NATURAL JOIN stol 
	NATURAL JOIN ordre; --??

-- Finn det totale antallet stoler som finnes i bestilling, og som enda ikke er levert kunder. Tips: Sjekk på reell leveringsdato (dvs. om ordren er effektuert).
SELECT COUNT(stol_id) FROM stol 
	NATURAL JOIN ordre 
	WHERE reell_leveringsdato NOT null; --??

-- Finn ut hvor mange (antallet) av stolene i spørring 3 over som er standardstoler.
SELECT COUNT(standardstol.stol_id) FROM standardstol 
	NATURAL JOIN stol 
	NATURAL JOIN ordre 
	WHERE leell_leveringsdato NOT null;