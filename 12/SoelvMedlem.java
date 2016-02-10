import java.time.*;

// SoelvMedlem
class SoelvMedlem extends BonusMedlem {
	private final double FAKTOR = 1.5;
	public SoelvMedlem(int medlNr, Personalia pers, LocalDate innmeldtDato, int poeng) {
		super(medlNr, pers, innmeldtDato, poeng, 1.2);
	}
}