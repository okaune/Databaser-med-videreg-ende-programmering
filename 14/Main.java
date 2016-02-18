import java.util.*;
import java.io.*;

class Main{
	private static Tribune[] tribuner = {
		new Staa("Staa1", 300, 50),
		new Staa("Staa2", 100, 80),
		new Sitte("Sitte", 100, 120, 5),
		new VIP("VIP", 50, 350, 5)
	};
	
	private static ArrayList<Billett> billetter = new ArrayList<Billett>();
	
	public static void main(String[] args) {
		
		final String filnavn = "tribuner.ser";
		
		String[] familienTilStine = {"Arne", "Stine", "Ingunn", "Markus"};
		
		// Kjøper billetter
		for (Billett b : tribuner[0].kjøpBilletter(3)) {
			billetter.add(b);
		}
		
		for (Billett b : tribuner[1].kjøpBilletter(3)) {
			billetter.add(b);
		}
		
		for (Billett b : tribuner[2].kjøpBilletter(3)) {
			billetter.add(b);
		}
		
		for (Billett b : tribuner[3].kjøpBilletter(familienTilStine)) {
			billetter.add(b);
		}

		
		for (Billett billett : billetter) {
			System.out.println(billett);
		}
		
		for (Tribune tribune : tribuner) {
			System.out.println(tribune);
		}
		
		sortTribuner(tribuner);
		
		for (Tribune tribune : tribuner) {
			System.out.println(tribune);
		}
		
		if(skrivTilFil(tribuner, filnavn)){
			System.out.println("Data er lagret på fil!");
		} else {
			System.out.println("Ingen data lagret!");
		}
		
		Tribune[] tribuner = lesFraFil(filnavn);
		if (tribuner == null) {
			System.out.println("Fil er null?");
		} else {
			for(Tribune tribune : tribuner) {
				System.out.println(tribune);
			}
		}
		
		
	}
	
	private static void sortTribuner(Tribune[] array) {
		Arrays.sort(array, new Comparator<Tribune>() { // Fortelle hva arrayen skal sorteres etter
			
			public int compare(Tribune o1, Tribune o2) {
				int inntekt1 = o1.finnInntekt();
				int inntekt2 = o2.finnInntekt();
			
				return inntekt2 - inntekt1; // Fra høyeste sum til laveste (for omvendt ta inntekt1 - inntekt2)
			}
		});
	}
	
    static boolean skrivTilFil(Tribune[] tribuner, String filnavn) {
        boolean ok = false;
        try {
            FileOutputStream utstrom = new FileOutputStream(filnavn, false);
            ObjectOutputStream ut = new ObjectOutputStream(utstrom);
			ut.writeObject(tribuner);
            ut.close();
            ok = true;
        } catch (FileNotFoundException e) {
            System.out.println("Fil ikkje funnet!");
        } catch (EOFException e) {
            System.out.println("Fil funnet, men tom!");
        } catch (IOException ioe) {
            System.out.println("Feil med IO: " + ioe.toString());
        } catch (Exception e) {
            System.out.println("Noe gikk galt");
        }
        return ok;
    }
	
    static Tribune[] lesFraFil(String filnavn) { //FIXME -- denne metoden returnerer ikke det jeg ønsker
		Tribune[] tribuner = null;
        try {
            FileInputStream innstrom = new FileInputStream(filnavn);
            ObjectInputStream inn = new ObjectInputStream(innstrom);
			
            tribuner = (Tribune[]) inn.readObject();
            inn.close();
        } catch (Exception e) {
            System.out.println("Noe gikk galt");
        }
        return tribuner;
    }
	
}