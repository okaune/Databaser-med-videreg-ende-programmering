/**
* Main.java
*
*/

import static javax.swing.JOptionPane.*;

class Main {
	
	public static void main(String[] args) {
		
		Konferansesenter senter = new Konferansesenter();
		
		int antRom = 0;
		try {
			antRom = Integer.parseInt(showInputDialog("Hvor mange rom vil du registrere?"));
		} catch (Exception e) {
			System.out.println("Line 18: " + e);
			showMessageDialog(null, "Kan ikke skrive inn annet enn tall.");
		}
	
		for (int i = 0; i < antRom; i++) {
			try {
				int romNr = Integer.parseInt(showInputDialog("Hva er romnummeret for dette rommet?"));
				int storrelse = Integer.parseInt(showInputDialog("Hvor mange er det plass til i dette rommet?"));
				if (senter.registrerRom(romNr, storrelse)) {
					showMessageDialog(null, "Rommet ble registrert med romnummer " + romNr + " og har plass til " + storrelse);
				} else {
					showMessageDialog(null, "Rommet ble ikke registrert");
					i--; //FIXME Hacky løsning for å loope gjennom en ekstra gang. Bør endres til while loop, men fingerer greit for denne oppgaven.
				}
			} catch (Exception e) {
				System.out.println("Line 33: " + e);
				showMessageDialog(null, "Kan ikke skrive inn annet enn tall.");
				i--; //FIXME Hacky løsning for å loope gjennom en ekstra gang. Bør endres til while loop, men fingerer greit for denne oppgaven.
			}
		}
		
		int antReservasjoner = 0;
		if (senter.finnAntRom() > 0) {
			try {
				antReservasjoner = Integer.parseInt(showInputDialog("Hvor mange reservasjoner vil du gjøre?"));
			} catch (Exception e) {
				System.out.println("Line 44: " + e);
				showMessageDialog(null, "Kan ikke skrive inn annet enn tall.");
			}
		}
	
		for (int i = 0; i < antReservasjoner; i++) {
			try {
				Tidspunkt fraTid = new Tidspunkt(Long.parseLong(showInputDialog("Når vil du reservere fra?\n Format: YYYYMMDDHHMM")));
				Tidspunkt tilTid = new Tidspunkt(Long.parseLong(showInputDialog("Når vil du reservere til?\n Format: YYYYMMDDHHMM")));
				int antPers = Integer.parseInt(showInputDialog("Hvor mange personer skal det reserveres til?"));
				String navn = showInputDialog("Hvilket navn skal det reserveres på?");
				String tlf = showInputDialog("Telefonnummer du kan kontaktes på?");
		
				if (senter.reserverRom(fraTid, tilTid, antPers, new Kunde(navn, tlf))) {
					showMessageDialog(null, "Rommet ble reservert.");
				} else {
					showMessageDialog(null, "Rommet ble ikke reservert.");
					i--; //FIXME Hacky løsning for å loope gjennom en ekstra gang. Bør endres til while loop, men fingerer greit for denne oppgaven.
				}
			} catch (Exception e) { //FIXME Bør egentlig sjekke spesifikke exceptions her, da mer enn å skrive inn integers kan gå galt.
				System.out.println("Line 64: " + e);
				showMessageDialog(null, "Kan ikke skrive inn annet enn tall.");
				i--; //FIXME Hacky løsning for å loope gjennom en ekstra gang. Bør endres til while loop, men fingerer greit for denne oppgaven.
			}
		}
		
		if (senter.finnAntRom() > 0) {
			showMessageDialog(null, senter);
			boolean gyldigRomNr;
			do {
				gyldigRomNr = false;
				try {
					int romNr = Integer.parseInt(showInputDialog("Hvilket romnummer ønsker du info om?"));
					if (senter.finnRomNr(romNr) != null) {
						showMessageDialog(null, senter.finnRomNr(romNr));
						gyldigRomNr = true;
					} else {
						showMessageDialog(null, "Det finnes ikke no rom med romnummer " + romNr);
					}
				} catch (Exception e) {
					System.out.println("Line 84: " + e);
					showMessageDialog(null, "Kan ikke skrive inn annet enn tall.");
				}
			} while (!gyldigRomNr);
		}
		
	}
	
}