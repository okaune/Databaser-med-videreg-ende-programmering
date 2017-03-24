class Fugleflokk extends Dyregruppe {
	private final int gjennomsnittligVekt;
	private final boolean svømmer;
	
	public Fugleflokk(String norskNavn, String latNavn, String latFamilie, int ankommetDato, String adresse, String gruppenavn, int antIndivider, int gjennomsnittligVekt, boolean svømmer) {
		super(norskNavn, latNavn, latFamilie, ankommetDato, adresse, gruppenavn, antIndivider);
		this.gjennomsnittligVekt = gjennomsnittligVekt;
		this.svømmer = svømmer;
	}
	
	public int getGjennomsnittligVekt() {
		return gjennomsnittligVekt;
	}
	
	public boolean getSvømmer() {
		return svømmer;
	}
	
	public String toString() {
		return super.toString() + "\nGjennomsnittlig vekt: " + gjennomsnittligVekt + "\nSvømmer: " + svømmer;
	}
}