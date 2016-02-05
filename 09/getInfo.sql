-- Oppgave 1 --
SELECT bedrift_navn, telefon, epost FROM bedrift;

-- Oppgave 2 --
SELECT o.oppdrag_id, b.bedrift_navn, b.telefon FROM oppdrag o, bedrift b WHERE o.bedrift_id = b.org_nr;
-- eller --
SELECT o.oppdrag_id, b.bedrift_navn, b.telefon FROM oppdrag o JOIN bedrift b ON (o.bedrift_id = b.org_nr);

-- Oppgave 3 --
SELECT k.kandidat_id, k.fornavn, k.etternavn, kv.kval_id, kv.beskrivelse
FROM kandidat k, kval_kandidat, kvalifikasjon kv
WHERE k.kandidat_id = kval_kandidat.kandidat_id AND kv.kval_id = kval_kandidat.kval_id;
-- eller --
SELECT k.kandidat_id, k.fornavn, k.etternavn, kv.kval_id, kv.beskrivelse
FROM kandidat k JOIN kval_kandidat ON (k.kandidat_id = kval_kandidat.kandidat_id)
JOIN kvalifikasjon kv ON(kv.kval_id = kval_kandidat.kval_id);

-- Oppgave 4 --
SELECT k.kandidat_id, k.fornavn, k.etternavn, kv.kval_id, kv.beskrivelse
FROM kandidat k LEFT JOIN kval_kandidat kk ON (k.kandidat_id = kk.kandidat_id)
LEFT JOIN kvalifikasjon kv ON(kv.kval_id = kk.kval_id);

-- Oppgave 5 --
SELECT k.fornavn, k.etternavn, o.virkelig_sluttdato, o.oppdrag_id, b.bedrift_navn
FROM kandidat k, oppdrag o, bedrift b
WHERE k.kandidat_id = 2 AND k.kandidat_id = o.kandidat_id AND o.bedrift_id = b.org_nr;