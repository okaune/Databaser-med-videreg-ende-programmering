/**
* Main.java
*
*/

import static javax.swing.JOptionPane.*;

class Main {
	
	public static void main(String[] args) {
		
		Konferansesenter senter = new Konferansesenter();
	
		int antRom = Integer.parseInt(showInputDialog("Hvor mange rom vil du registrere?"));
	
		for (int i = 0; i < antRom; i++) {
			int romNr = Integer.parseInt(showInputDialog("Hva er romnummeret for dette rummet?"));
			int storrelse = Integer.parseInt(showInputDialog("Hvor mange er det plass til i dette rummet?"));
			if (senter.registrerRom(romNr, storrelse)) {
				showMessageDialog(null, "Rommet ble registrert med romnr. " + romNr + " og har plass til " + storrelse);
			} else {
				showMessageDialog(null, "Rommet ble ikke registrert");
				i--; // Hacky løsning for å loope gjennom en ekstra gang. Bør endres til while loop, men fingerer greit for denne oppgaven.
			}
		}
	
		int antReservasjoner = Integer.parseInt(showInputDialog("Hvor mange reservasjoner vil du gjøre?"));
	
		for (int i = 0; i < antReservasjoner; i++) {
		
			Tidspunkt fraTid = new Tidspunkt(Long.parseLong(showInputDialog("Når vil du reservere fra?\n Format: YYYYMMDDHHMM")));
			Tidspunkt tilTid = new Tidspunkt(Long.parseLong(showInputDialog("Når vil du reservere til?\n Format: YYYYMMDDHHMM")));
			int antPers = Integer.parseInt(showInputDialog("Hvor mange personer skal det reserveres til?"));
			String navn = showInputDialog("Hvilket navn skal det reserveres på?");
			String tlf = showInputDialog("Telefonnummer du kan kontaktes på?");
		
			if (senter.reserverRom(fraTid, tilTid, antPers, navn, tlf)) {
				showMessageDialog(null, "Rommet ble reservert.");
			} else {
				showMessageDialog(null, "Rommet ble ikke reservert.");
				i--; // Hacky løsning for å loope gjennom en ekstra gang. Bør endres til while loop, men fingerer greit for denne oppgaven.
			}
		}
	
		showMessageDialog(null, senter);
		
		do {
			boolean gyldigRomNr = false;
			int romNr = Integer.parseInt(showInputDialog("Hvilket romnummer ønsker du info om?"));
			if (senter.finnRomNr(romNr)) {
				showMessageDialog(null, senter.finnRomNr(romNr));
				gyldigRomNr = true;
			}
		} while (!gyldigRomNr);
		
	}
	
}