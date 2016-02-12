import java.time.*;

class Main {
	public static void main(String[] args) throws Exception {
		Medlemsarkiv arkiv = new Medlemsarkiv();
		
		// Testdata
	    Personalia ole = new Personalia("Olsen", "Ole", "ole.olsen@dot.com", "ole");
	    Personalia tove = new Personalia("Hansen", "Tove", "tove.hansen@dot.com", "tove");
	    LocalDate testdato = LocalDate.of(2008, 2, 10);
		
		int medl1 = arkiv.nyMedlem(ole, LocalDate.now().minusDays(5)); // Kan oppgradere
		int medl2 = arkiv.nyMedlem(tove, LocalDate.now().minusYears(2)); // Kan ikke oppgradere
		
		// Begge får nok poeng til å oppgradere
		arkiv.registrerPoeng(medl1, 75000);
		arkiv.registrerPoeng(medl2, 26000);
		
		arkiv.sjekkMedlemmer();
		
		// Ole skal nå være GullMedlem, mens tove er fortsatt BasicMedlem
		if (arkiv.getBonusMedlem(medl1) instanceof BasicMedlem) {
			System.out.println("Ole ber BasicMedlem");
		} else if (arkiv.getBonusMedlem(medl1) instanceof SoelvMedlem) {
			System.out.println("Ole er SølvMedlem");
		} else if (arkiv.getBonusMedlem(medl1) instanceof GullMedlem) {
			System.out.println("Ole er GullMedlem");
		}
		
		
		if (arkiv.getBonusMedlem(medl2) instanceof BasicMedlem) {
			System.out.println("Tove ber BasicMedlem");
		} else if (arkiv.getBonusMedlem(medl2) instanceof SoelvMedlem) {
			System.out.println("Tove er SølvMedlem");
		} else if (arkiv.getBonusMedlem(medl2) instanceof GullMedlem) {
			System.out.println("Tove er GullMedlem");
		}
		
	}
}