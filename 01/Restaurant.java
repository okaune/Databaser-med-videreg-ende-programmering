import java.util.*;

class Restaurant {
	private String name;
	private int estYear;
	private Table tables;
	
	public Restaurant(String name, int estYear, int numOfTables) {
		this.name = name;
		this.estYear = estYear;
		tables = new Table(numOfTables);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getEstYear() {
		return estYear;
	}
	
	public int findAge() {
		int currYear = Calendar.getInstance().get(Calendar.YEAR);
		return currYear - estYear;
	}
	
	public int findAvailableTables() {
		return tables.findAvailableTables();
	}
	
	public int findOccupiedTables() {
		return tables.findOccupiedTables();
	}
	
	public void reserveTables(String name, int numOfTables) {
		if (numOfTables > findAvailableTables()) {
			System.out.println("reserveTables -- numOfTables grater than findAvailableTables");
			return;
		}
		
		for (int i = 0; i < numOfTables; i++) {
			tables.reserveTable(name);
		}
		
	}
	
	public int[] findReservedTables(String reservedName) {
		return tables.findReservedTables(reservedName);
	}
	
	public void clearTables(int[] tables) {
		this.tables.clearTables(tables);
	}
}