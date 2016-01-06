import static javax.swing.JOptionPane.*;

class Main {
	
	public static void main(String[] args) {
		
		Restaurant restaurant = new Restaurant("El Pato", 1989, 20);
		int choice;
		String[] arg = {"Reserver bord","Finn reserverte bord", "Frigi bord"};
		
		do{
			choice = showOptionDialog(null, //Component parentComponent
	                               "Hva vil du gjøre?", //Object message,
	                               "Bordreservasjon", //String title
	                               YES_NO_OPTION, //int optionType
	                               INFORMATION_MESSAGE, //int messageType
	                               null, //Icon icon,
	                               arg, //Object[] options,
	                               arg[0]);//Object initialValue
			
			switch (choice) {
				case 0:
					String name = showInputDialog("Navn:");
					int numOfTables = Integer.parseInt(showInputDialog("Antall bord:"));
					restaurant.reserveTables(name, numOfTables);
					break;
				case 1:
					String reserveName = showInputDialog("Navn:");
					int[] reservedTables = restaurant.findReservedTables(reserveName);
					String tablesReserved = "Bord reservert: ";
					for (int table : reservedTables) {
						tablesReserved += "\n" + table;
					}
					showMessageDialog(null, tablesReserved);
					break;
				case 2:
					int numOfClearTables = Integer.parseInt(showInputDialog("Hvor mange bord vil du frigi?"));
					int[] tables = new int[numOfClearTables];
					for (int i = 0; i < tables.length; i++) {
						tables[i] = Integer.parseInt(showInputDialog("Bordnr:"));
					}
					restaurant.clearTables(tables);
					break;
				default:
					break;
			}
			
		} while (choice != -1);
		
	}
}