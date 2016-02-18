import java.io.Serializable;

abstract class Tribune implements Serializable{
	private final String tribunenavn;
	private final int kapasitet;
	private final int pris;
	
	public Tribune(String tribunenavn, int kapasitet, int pris) {
		this.tribunenavn = tribunenavn;
		this.kapasitet = kapasitet;
		this.pris = pris;
	}
	
	public String getTribunenavn() {
		return tribunenavn;
	}
	
	public int getKapasitet() {
		return kapasitet;
	}
	
	public int getPris() {
		return pris;
	}
	
	public abstract int finnAntallSolgteBilletter();
	
	public int finnInntekt() {
		return pris * finnAntallSolgteBilletter();
	}
	
	public abstract Billett[] kjøpBilletter(int antBilletter);
	
	public Billett[] kjøpBilletter(String[] navn) {
		return kjøpBilletter(navn.length);
	}
	
	public String toString() {
		return "Tribunenavn " + tribunenavn + ", Kapasitet: " + kapasitet;
	}
}

class Staa extends Tribune {
	private int antSolgteBilletter; // Antar at vi starter med å sette denne til 0 i konstuktøren??
	
	public Staa(String tribunenavn, int kapasitet, int pris) {
		super(tribunenavn, kapasitet, pris);
		antSolgteBilletter = 0;
	}
	
	public int finnAntallSolgteBilletter() {
		return antSolgteBilletter;
	}
	
	@Override
	public Billett[] kjøpBilletter(int antBilletter) {
		if (getKapasitet() - antSolgteBilletter < antBilletter) {
			return null;
		}
		Billett[] billetter = new Billett[antBilletter];
		for (int i = 0; i < antBilletter; i++) {
			billetter[i] = new StaaplassBillett(getTribunenavn(), getPris());
		}
		antSolgteBilletter += antBilletter;
		return billetter;
	}
	
	@Override
	public String toString() {
		return super.toString() + ", Antall solgte billetter: " + antSolgteBilletter + ", Inntekt: " + getPris()*antSolgteBilletter;
	}
}

class Sitte extends Tribune {
	private int[] antOpptatt;  // tabellstørrelse: antall rader
	
	public Sitte(String tribunenavn, int kapasitet, int pris, int rader) {
		super(tribunenavn, kapasitet, pris);
		antOpptatt = new int[rader];
	}
	
	public int finnAntallSolgteBilletter() {
		int antSolgteBilletter = 0;
		for (int solgt : antOpptatt) {
			antSolgteBilletter += solgt;
		}
		return antSolgteBilletter;
	}
	
	@Override
	public Billett[] kjøpBilletter(int antBilletter) {
		Billett[] billetter = new Billett[antBilletter];
		for (int i = 0; i < antOpptatt.length; i++) {
			if (getKapasitet() / antOpptatt.length - antOpptatt[i] >= antBilletter) {
				for (int j = 0; j < antBilletter; j++) {
					billetter[j] = new SitteplassBillett(getTribunenavn(), getPris(), i, antOpptatt[i] + j);
				}
				antOpptatt[i] += antBilletter;
				return billetter;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		int solgt = 0;
		for (int opptatt : antOpptatt) {
			solgt += opptatt;
		}
		return super.toString() + ", Antall solgte billetter: " + solgt + ", Inntekt: " + getPris()*solgt;
	}
}

class VIP extends Sitte {
	private String[][] tilskuer; // tabellstørrelse: antall rader * antall plasser pr rad
	
	public VIP(String tribunenavn, int kapasitet, int pris, int rader) {
		super(tribunenavn, kapasitet, pris, rader);
		tilskuer = new String[rader][kapasitet/rader];
	}
	
	@Override
	public int finnAntallSolgteBilletter() {
		int ant = 0;
		for (String[] t : tilskuer) {
			for (String s : t) {
				if (s != null) {
					ant++;
				}
			}
		}
		return ant;
	}
	
	@Override
	public Billett[] kjøpBilletter(int antBilletter) {
		return null;
	}
	
	@Override
	public Billett[] kjøpBilletter(String[] navn) {
		Billett[] billetter = new Billett[navn.length];
		
		for (int i = 0; i < tilskuer.length; i++) { // Loop through rows
			// Register available seats in the row
			int ledig = 0;
			for (String t : tilskuer[i]) {
				if (t == null) {
					ledig++;
				}
			}
			
			if (ledig >= navn.length ) {
				
				for (int j = 0; j < navn.length; j++) {
					
					tilskuer[i][ getKapasitet() / tilskuer.length - ledig + j ] = navn[j];
					billetter[j] = new SitteplassBillett(getTribunenavn(), getPris(), i, getKapasitet() / tilskuer.length - ledig + j);
					
				}
				return billetter;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		int solgt = 0;
		for (String[] t : tilskuer) {
			for (String navn : t) {
				if (navn != null) {
					solgt++;
				}
			}
		}
		return "Tribunenavn " + getTribunenavn() + ", Kapasitet: " + getKapasitet() + ", Antall solgte billetter: " + solgt + ", Inntekt: " + getPris()*solgt;
	}
}