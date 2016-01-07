/**
* Rom.java
*
*/

import java.util.*;

class Rom {
	int romNr;
	int antPers;
	ArrayList<Reservasjon> reservasjoner = new ArrayList<Reservasjon>();
	
	public Rom(int romNr, int antPers) {
		this.romNr = romNr;
		this.antPers = antPers;
	}
	
	public int getRomNr() {
		return romNr;
	}
	
	public int getAntPers() {
		return antPers;
	}
	
	public boolean reserverRom(Tidspunkt fraTid, Tidspunkt tilTid, Kunde kunde) {
		for (Reservasjon reservasjon : reservasjoner) {
			if (reservasjon.overlapp(fraTid, tilTid)) {
				return false;
			}
		}
		reservasjoner.add(new Reservasjon(fraTid, tilTid, kunde));
		return true;
	}
	
	public boolean equals(int romNr) { 	// FIXME - burde bruke standard Object obj,
										// men dette fungerer for nå
		if (romNr == this.romNr) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		String output = "Romnummer: " + romNr + "\n" +
						"Antall personer: " + antPers + "\n" +
						"Reservasjoner:\n";
		for (Reservasjon reservasjon : reservasjoner) {
			output += reservasjon + "\n";
		}
		return output;
	}
	
	
	// For testing
	public static void main(String[] args) {
		System.out.println("Totalt antall tester: 3");
		Rom r = new Rom(1, 5);
		if (r.getRomNr() == 1 && r.getAntPers() == 5) {
			System.out.println("Rom: Test 1 vellykket.");
		}
		
		Kunde k = new Kunde("Anne Hansen", "12345678");
		if (r.reserverRom(new Tidspunkt(200302011000L), new Tidspunkt(200302011100L), k) && 
			!r.reserverRom(new Tidspunkt(200302011000L), new Tidspunkt(200302011100L), k) && 
			r.reserverRom(new Tidspunkt(201602011000L), new Tidspunkt(201602011100L), k)) {
			System.out.println("Rom: Test 2 vellykket.");
		}
		
		if (r.equals(1)) {
			System.out.println("Rom: Test 3 vellykket.");
		}
		
	}
}