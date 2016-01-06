class Table {
	private String[] tables;
	
	public Table(int numOfTables) {
		tables = new String[numOfTables];
	}
	
	public String[] getTables() {
		return tables;
	}
	
	public int findAvailableTables() {
		int available = 0;
		for (String table : tables) {
			if (table == null) {
				available++;
			}
		}
		return available;
	}
	
	public int findOccupiedTables() {
		int occupied = 0;
		for (String table : tables) {
			if (table != null) {
				occupied++;
			}
		}
		return occupied;
	}
	
	public void clearTables(int[] tables) {
		for (int table : tables) {
			if (table < this.tables.length) {
				this.tables[table] = null;
			}	
		}
	}
	
	public boolean reserveTable(String name) {
		for (int i = 0; i < tables.length; i++) {
			if (tables[i] == null) {
				tables[i] = name;
				return true;
			}
		}
		return false;
	}
	
	public int[] findReservedTables(String reservedName) { // FIXME
		System.out.println(reservedName);
		int tablesReserved = 0;
		for (int i = 0; i < tables.length; i++) {
			System.out.println(tables[i]);
			if (reservedName.equals(tables[i])) {
				tablesReserved++;
			}
		}
		
		int[] tableNumbers = new int[tablesReserved];
		for (int i = 0; i < tables.length; i++) {
			if (reservedName.equals(tables[i])) {
				tableNumbers[--tablesReserved] = i;
			}
		}
		return tableNumbers;
	}
}