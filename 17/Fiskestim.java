class Fiskestim extends Dyregruppe {
	private final int gjennomsnittligLengde;
	private final boolean kanDeleAkvarium;
	
	public Fiskestim(String norskNavn, String latNavn, String latFamilie, int ankommetDato, String adresse, String gruppenavn, int antIndivider, int gjennomsnittligLengde, boolean kanDeleAkvarium) {
		super(norskNavn, latNavn, latFamilie, ankommetDato, adresse, gruppenavn, antIndivider);
		this.gjennomsnittligLengde = gjennomsnittligLengde;
		this.kanDeleAkvarium = kanDeleAkvarium;
	}
	
	public int getGjennomsnittligLengde() {
		return gjennomsnittligLengde;
	}
	
	public boolean getKanDeleAkvarium() {
		return kanDeleAkvarium;
	}
	
	public String toString() {
		return super.toString() + "\nGjennomsnittlig lengde: " + gjennomsnittligLengde + "\nKan dele akvarium: " + kanDeleAkvarium;
	}
}