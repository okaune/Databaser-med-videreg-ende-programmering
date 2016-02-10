import java.util.*;
import java.time.*;

class Medlemsarkiv {
	private ArrayList<BonusMedlem> medlemmer = new ArrayList<BonusMedlem>();
	private static Random random = new Random();
	
	private int finnLedigNr() {
		// TODO: Denne metoden skal hente ut et tilfeldig heltall 
		//		 (bruk klassen Random) som ikke allerede er i bruk som medlemsnr.
		int nr = 0;
		boolean gyldigNr = false;
		while (!gyldigNr) {
			nr = random.nextInt(Integer.MAX_VALUE);
			gyldigNr = true; // Antar at nummeret er gyldig før vi sjekker det.
			for (BonusMedlem medlem : medlemmer) {
				if (medlem.getMedlnr() == nr) {
					gyldigNr = false;
				}
			}
		}
		return nr;
	}
	
	public int nyMedlem(Personalia pers, LocalDate innmeldt) {
		int medlNr = finnLedigNr();
		BasicMedlem medlem = new BasicMedlem(medlNr, pers, innmeldt);
		medlemmer.add(medlem);
		return medlNr;
	}
	
	public int finnPoeng(int medlNr, String passord) {
		for (BonusMedlem medlem : medlemmer) {
			if (medlem.getMedlnr() == medlNr && medlem.okPassord(passord)) {
				return medlem.getPoeng();
			}
		}
		return -1;
		
	}
	
	public void registrerPoeng(int medlNr, int poeng) {
		for (BonusMedlem medlem : medlemmer) {
			if (medlem.getMedlnr() == medlNr) {
				medlem.registrerPoeng(poeng);
			}
		}
	}
	
	
	public void sjekkMedlemmer() {
		for (BonusMedlem medlem : medlemmer) {
			if (medlem instanceof BasicMedlem) {
				if (medlem.finnKvalPoeng(LocalDate.now()) >= 25000) {
					if (medlem.finnKvalPoeng(LocalDate.now()) >= 75000) {
						// Kvalifisert for GullMedlem
						GullMedlem gm = new GullMedlem(medlem.getMedlnr(), medlem.getPers(), medlem.getInnmeldtDato(), medlem.getPoeng());
						medlemmer.set(medlemmer.indexOf(medlem), gm);
					} else {
						// Kvalifisert for SoelvMedlem
						SoelvMedlem sm = new SoelvMedlem(medlem.getMedlnr(), medlem.getPers(), medlem.getInnmeldtDato(), medlem.getPoeng());
						medlemmer.set(medlemmer.indexOf(medlem), sm);
					}
				}
			} else if (medlem instanceof SoelvMedlem) {
				if (medlem.finnKvalPoeng(LocalDate.now()) >= 75000) {
					// Kvalifisert for GullMedlem
					GullMedlem gm = new GullMedlem(medlem.getMedlnr(), medlem.getPers(), medlem.getInnmeldtDato(), medlem.getPoeng());
					medlemmer.set(medlemmer.indexOf(medlem), gm);
				}
			}
		}
	}
	
	public BonusMedlem getBonusMedlem(int medlNr) {
		for (BonusMedlem medlem : medlemmer) {
			if (medlem.getMedlnr() == medlNr) {
				return medlem;
			}
		}
		return null;
	}
}