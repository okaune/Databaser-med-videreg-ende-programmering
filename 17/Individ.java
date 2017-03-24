class Individ extends Dyr {
	private final String navn;
	private final int fDato;
	private final boolean hanndyr;
	private final boolean farlig;
	
	public Individ(String norskNavn, String latNavn, String latFamilie, int ankommetDato, String adresse, String navn, int fDato, boolean hanndyr, boolean farlig) {
		super(norskNavn, latNavn, latFamilie, ankommetDato, adresse);
		this.navn = navn;
		this.fDato = fDato;
		this.hanndyr = hanndyr;
		this.farlig = farlig;
	}
	
	public String getNavn() {
		return navn;
	}
	
	public int getFDato() {
		return fDato;
	}
	
	public boolean getHanndyr() {
		return hanndyr;
	}
	
	public boolean getFarlig() {
		return farlig;
	}
	
	public String toString() {
		return super.toString() + "\nNavn: " + navn + "\nFødselsår: " + fDato + "\nHanndyr: " + hanndyr + "\nFarlig: " + farlig;
	}
}