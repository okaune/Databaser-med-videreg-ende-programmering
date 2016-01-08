/**
* Konferansesenter.java
*
*/

import java.util.*;

class Konferansesenter {
	
	ArrayList<Rom> rom = new ArrayList<Rom>();
	
	public boolean reserverRom(Tidspunkt fraTid, Tidspunkt tilTid, int antPers, Kunde kunde) {
		for (Rom r : rom) {
			if (r.getAntPers() >= antPers) {
				if(r.reserverRom(fraTid, tilTid, kunde)) {
					return true;
				}
			}
		}
		return false;
		 
	}
	
	public boolean registrerRom(int romNr, int storrelse) {
		// Sjekk om romNr eksisterer fra før, ellers registrer rom
		for (Rom r : rom) {
			if (r.equals(romNr)) {
				return false;
			}
		}
		rom.add(new Rom(romNr, storrelse));
		return true;
	}
	
	public int finnAntRom() {
		// Returner antall rom
		return rom.size();
	}
	
	public Rom finnRomIndex(int index) {
		// Returner rom med riktig index
		if (rom.get(index) != null) {
			return rom.get(index);
		}
		return null;
	}
	
	public Rom finnRomNr(int romNr) {
		// Returner rom med riktig romNr
		for (Rom r : rom) {
			if (r.getRomNr() == romNr) {
				return r;
			}
		}
		return null;
	}
	
	public String toString() {
		String output = "Konferansesenteret har følgende rom:\n";
		for (Rom r : rom) {
			output += r;
		}
		return output;
	}
}