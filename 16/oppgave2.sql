-- Sett opp en SELECT-setning som er UNION mellom alle forlag med Oslo-nummer (telefonnummer begynner med 2) og alle som ikke er Oslo-nummer. Får du med forlaget med NULL-verdi på telefonnummer? Hvis ikke, utvid unionen med en mengde til.
SELECT * FROM forlag WHERE telefon = 2*
UNION
SELECT * FROM forlag WHERE telefon != 2*;
-- Sett opp SQL-setninger som finner gjennomsnittlig alder på forfattere der fødselsåret er oppgitt. For forfattere der dødsåret ikke er oppgitt, skal du kun ta med de som er født etter 1900.  Tips for å få ut året i år:
--    MySQL: SELECT YEAR(CURRENT_DATE) FROM ... hvilken tabell som helst ...

-- Sett opp SQL-setninger som finner hvor stor andel av forfatterne som ble med i beregningene under b).